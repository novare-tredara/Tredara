package com.novare.tredara.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novare.tredara.dtos.LoginRequest;
import com.novare.tredara.dtos.UserDTO;
import com.novare.tredara.exceptions.TredaraException;
import com.novare.tredara.models.ERole;
import com.novare.tredara.models.Role;
import com.novare.tredara.models.User;
import com.novare.tredara.repositories.RoleRepository;
import com.novare.tredara.repositories.UserRepository;
import com.novare.tredara.security.jwt.JwtUtils;
import com.novare.tredara.security.services.UserDetailsImpl;
import com.novare.tredara.services.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Validated
public class UserController {
	@Autowired
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<UserDTO> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		LOGGER.info("Start: Authenticating the User, [api/login] User:{}", loginRequest.getEmail());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		String roleName = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList()).get(0);

		Role role = roleRepository.findByName(ERole.valueOf(roleName))
				.orElseThrow(() -> new TredaraException(HttpStatus.BAD_REQUEST, "Role Not Found " + roleName));

		User user = new User(userDetails.getId(), userDetails.getFullName(), userDetails.getUsername(), role);
		LOGGER.info("End: Found the User, [api/login] User:{}", user.getFullName());
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(UserDTO.build(user));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		LOGGER.info("End: Successfully logged out User, [api/logout]");
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("You've been logged out!");
	}

	@PostMapping("/signup")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO signUpRequest) throws TredaraException {
		LOGGER.info("Start: Create the User, [api/signup] User:{}", signUpRequest.getEmail());
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new TredaraException(HttpStatus.BAD_REQUEST, "User Already Exist " + signUpRequest.getEmail());
		}
		User toUser = UserDTO.buildModel(signUpRequest);
		final User createUser = userService.createUser(toUser);
		LOGGER.info("End: Successfully Created the User, [api/signup] User:{}", createUser.getFullName());
		return ResponseEntity.ok(UserDTO.build(createUser));
	}

	@GetMapping("/user")
	public List<User> findAll() throws TredaraException {
		LOGGER.info("End: Get all the users, [api/user] ");
		return userRepository.findAll();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId) throws TredaraException {
		LOGGER.info("Start: Get the User by ID, [api/user/{}]", userId);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND, "User not found on :: " + userId));
		LOGGER.info("End: Found the User by ID, [api/user/{}] User:{}", userId, user.getFullName());
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User updateRequest)
			throws TredaraException {
		LOGGER.info("Start: Updating the User by ID, [api/user/{}]", userId);
		updateRequest.setId(userId);
		final User updatedUser = userService.updateUser(updateRequest);
		LOGGER.info("End: Successfully Updated the User by ID, [api/user/{}] User:{}", userId,
				updatedUser.getFullName());
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws TredaraException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND, "User not found on :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		LOGGER.info("End: Successfully deleted the User by ID, [api/user/{}] Deleted:{}", userId, response.toString());
		return response;
	}
}
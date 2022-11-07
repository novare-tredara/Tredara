
package com.novare.natflix.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.novare.natflix.exceptions.NatflixException;
import com.novare.natflix.models.ERole;
import com.novare.natflix.models.Role;
import com.novare.natflix.models.User;
import com.novare.natflix.repositories.RoleRepository;
import com.novare.natflix.repositories.UserRepository;
import com.novare.natflix.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User createUser(User user) throws NatflixException {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setStatus(1);
		Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
				.orElseThrow(() -> new NatflixException(HttpStatus.NOT_FOUND, "Error: Role is not found."));
		user.setRole(userRole);
		return userRepository.save(user);
	}

	public User updateUser(User updateUser) throws NatflixException {
		User user = userRepository.findById(updateUser.getId()).orElseThrow(
				() -> new NatflixException(HttpStatus.NOT_FOUND, "User not found on :: " + updateUser.getId()));
		user.setEmail(updateUser.getEmail());
		user.setFullName(updateUser.getFullName());
		user.setPassword(bCryptPasswordEncoder.encode(updateUser.getPassword()));
		user.setStatus(updateUser.getStatus());
		return userRepository.save(user);
	}
}
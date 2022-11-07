
package com.novare.tredara.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.novare.tredara.exceptions.TredaraException;
import com.novare.tredara.models.ERole;
import com.novare.tredara.models.Role;
import com.novare.tredara.models.User;
import com.novare.tredara.repositories.RoleRepository;
import com.novare.tredara.repositories.UserRepository;
import com.novare.tredara.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User createUser(User user) throws TredaraException {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setStatus(1);
		Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
				.orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND, "Error: Role is not found."));
		user.setRole(userRole);
		return userRepository.save(user);
	}

	public User updateUser(User updateUser) throws TredaraException {
		User user = userRepository.findById(updateUser.getId()).orElseThrow(
				() -> new TredaraException(HttpStatus.NOT_FOUND, "User not found on :: " + updateUser.getId()));
		user.setEmail(updateUser.getEmail());
		user.setFullName(updateUser.getFullName());
		user.setPassword(bCryptPasswordEncoder.encode(updateUser.getPassword()));
		user.setStatus(updateUser.getStatus());
		return userRepository.save(user);
	}
}
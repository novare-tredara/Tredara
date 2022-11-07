package com.novare.tredara.services;

import com.novare.tredara.exceptions.TredaraException;
import com.novare.tredara.models.User;

public interface IUserService {
	User createUser(User user) throws TredaraException;

	User updateUser(User user) throws TredaraException;

}
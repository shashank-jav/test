package com.dgh.service;

import javax.servlet.http.HttpSession;

import com.dgh.model.SingleSignonUserModel;

public interface MainService {

	Integer registerUser(SingleSignonUserModel registrationForm);

	SingleSignonUserModel getLoggedInUserDetails(String username);

	boolean checkIfEmailUnique(String username);

	Integer updateUser(SingleSignonUserModel ssouserModel);

	SingleSignonUserModel ifemailalreadyregistered(String email);

	boolean sendPasswordResetLink(String email);

	boolean checkIfUserRequestedPasswordRecovery(String username);

	boolean checkIfRecoveryTokenValid(String username, String token);

	boolean changePassword(SingleSignonUserModel changePassword, HttpSession session);

	




	
	
}
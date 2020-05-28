package com.dgh.dao;

import java.util.List;

import com.dgh.model.entity.SingleSignonUser;

public interface MainDao {

	Integer registerUser(SingleSignonUser user);

	List<?> executeNamedQuery(String string, String[] strings, String[] strings2);

	boolean checkIfEmailExists(String username);

	List<SingleSignonUser> getUsersWithRole();

	boolean savePasswordResetToken(SingleSignonUser userAuthInfo);

	boolean checkIfUserRequestedPasswordRecovery(SingleSignonUser userByEmail);

	SingleSignonUser checkIfRecoveryTokenValid(String username, String token);

	boolean changePassword(SingleSignonUser sso, String password);

	SingleSignonUser getUserByEmail(String email);

	void updateUserById(SingleSignonUser user);

	

	
	
	
}
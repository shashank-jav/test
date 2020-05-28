package com.dgh.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dgh.dao.MainDao;
import com.dgh.model.SingleSignonUserModel;
import com.dgh.model.SingleSignonUserRoleModel;
import com.dgh.model.entity.SingleSignonUser;
import com.dgh.model.entity.SingleSignonUserRole;
import com.dgh.utility.CommonUtil;
import com.dgh.utility.EmailUtil;
import com.dgh.utility.SSOConstants;


@Service
public class MainServiceImpl implements MainService {

	@Autowired
	MainDao mainDao;

	@SuppressWarnings("unchecked")
	public SingleSignonUserModel getLoggedInUserDetails(String username) {
		
		List<SingleSignonUser> userList = (List<SingleSignonUser>)mainDao.executeNamedQuery("findUser", new String[] {"username"}, new String[] {username});
		SingleSignonUser user = userList.get(0);
		SingleSignonUserModel userModel = new SingleSignonUserModel();
		
		BeanUtils.copyProperties(user, userModel);
		
		
		
		List<SingleSignonUserRoleModel> userRoles = new ArrayList<>();
		for(SingleSignonUserRole userRole : user.getUserRoles()) {
			SingleSignonUserRoleModel userRoleModel = new SingleSignonUserRoleModel();
			BeanUtils.copyProperties(userRole, userRoleModel);
			userRoles.add(userRoleModel);
		}
		userModel.setUserRoles(userRoles);
				
		return userModel;
	}
	
	
	
	@Override
	public Integer registerUser(SingleSignonUserModel userModel) {
		Integer userid=0;
		
		SingleSignonUser user=new SingleSignonUser();
		BeanUtils.copyProperties(userModel, user);
		user.setEnabled(1);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String securepassword=CommonUtil.getRandomStringOfSize(8);
		//System.out.println(securepassword);
		String hashedPassword = passwordEncoder.encode(securepassword);
		//String hashedPassword = passwordEncoder.encode("123");
		user.setTempColumn(securepassword);
		user.setPassword(hashedPassword);
		
		{
		
		SingleSignonUserRole userRole=new SingleSignonUserRole();
		userRole.setRole("ROLE_USER");
		userRole.setSinglesignonuser(user);
		user.getUserRoles().add(userRole);
		
		userid=mainDao.registerUser(user);
		
		}
		return userid;
	}


	@Override
	public boolean checkIfEmailUnique(String username) {
		boolean ifEmailExists=mainDao.checkIfEmailExists(username);
		return ifEmailExists;
	}


	@Override
	public Integer updateUser(SingleSignonUserModel ssouserModel) {
		Integer flag=0;
		SingleSignonUser user=new SingleSignonUser();
		
		BeanUtils.copyProperties(ssouserModel, user);
		
		
		List<SingleSignonUserRole> userRoles = new ArrayList<>();
		for(SingleSignonUserRoleModel userRole : ssouserModel.getUserRoles()) {
			SingleSignonUserRole userRoleentity = new SingleSignonUserRole();
			BeanUtils.copyProperties(userRole, userRoleentity);
			userRoleentity.setSinglesignonuser(user);
			userRoles.add(userRoleentity);
		}
		user.setUserRoles(userRoles);
		
		
		return flag;
	}



	@Override
	public SingleSignonUserModel ifemailalreadyregistered(String email) {
		SingleSignonUserModel user=new SingleSignonUserModel();
		SingleSignonUser ssouser=mainDao.getUserByEmail(email);
		
		return user;
	
	}



	@Override
	public boolean sendPasswordResetLink(String email) {
		boolean flagFromService=false;
		SingleSignonUser ssouser = mainDao.getUserByEmail(email);
		String recoveryToken;
		try {
			recoveryToken = CommonUtil.getSecureRandomToken();
			if (ssouser != null) {
				ssouser = new SingleSignonUser();
				ssouser.setUsername(mainDao.getUserByEmail(email).getUsername());
				ssouser.setToken(recoveryToken);
				//ssouser.setTokenCreatedOn(new Date());
				
				Date today=new Date(); 
				Calendar c = Calendar.getInstance();
			    c.setTime(today);
			    c.add(Calendar.DAY_OF_YEAR, SSOConstants.PASSWORD_EXPIRY_IN_DAYS);
			    Date expiryDate =c.getTime();
			    
				ssouser.setTokenExpiresOn(expiryDate);
			}
	
			boolean ifRecoveryTokenSaved = mainDao.savePasswordResetToken(ssouser);
			mainDao.updateUserById(ssouser);
	
			if (ifRecoveryTokenSaved) {
				try {
	
					EmailUtil.sendMail("Password Reset Link",
							SSOConstants.APPLICATION_CONTEXT_PATH+"changePassword/"+ssouser.getUsername()+"/"+ recoveryToken, null, ssouser.getUsername(), null);
					flagFromService = true;
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return flagFromService;
	}
	
	@Override
	public boolean checkIfUserRequestedPasswordRecovery(String username) {
		boolean ifRegistered = mainDao.checkIfUserRequestedPasswordRecovery(mainDao.getUserByEmail(username));
		return ifRegistered;

	}

	@Override
	public boolean checkIfRecoveryTokenValid(String username,String token)
	{
		SingleSignonUser ssouser=mainDao.checkIfRecoveryTokenValid(mainDao.getUserByEmail(username).getUsername(),token);
		Date today=new Date();
		if((ssouser!=null) && (today.compareTo(ssouser.getTokenExpiresOn()) < 0)) //empty check may be needed
			return true;
		else
			return false;
		
	}
	
	@Override
	public boolean changePassword(SingleSignonUserModel changePassword, HttpSession session) {

		boolean flagService = mainDao.changePassword(mainDao.getUserByEmail(changePassword.getUsername()), changePassword.getPassword());

		return flagService;

	}


}
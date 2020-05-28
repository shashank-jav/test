package com.dgh.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtil {
	
	public static String getEncryptedPassword(String plainpwd)
	{
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String encyptedpwd=passwordEncoder.encode(plainpwd);
	System.out.println(plainpwd+":"+encyptedpwd);
	return encyptedpwd;
	}
	public static void main(String[] args)
	{
		
		//getEncryptedPassword("4N5E3SIJ");
		System.out.println(org.hibernate.Version.getVersionString());
	}
}
package com.dgh.utility;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public class CommonUtil {
	
	public static String getRandomStringOfSize(int size) {
			//return RandomStringUtils.randomAlphanumeric(size);
		//	return new BigInteger( (size*5), new Random()).toString(32);
			return new RandomStringGenerator.Builder().withinRange('0', 'Z').filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build().generate(size);
	}
	
	
	public static String getSecureRandomToken() throws UnsupportedEncodingException{
	  SecureRandom rnd=new SecureRandom();
	  byte[] tmp=new byte[16];
	  rnd.nextBytes(tmp);
	  return Base64.getEncoder().encodeToString(tmp);
	}
	
	public static UUID getUUIDAsToken(){
	
	UUID uuid = UUID.randomUUID();
	return uuid;
	
	}
	
	
	
public static void main(String[] args) throws UnsupportedEncodingException {
		//System.out.println("Secure Random Token::"+getSecureRandomToken());
		//System.out.println(" Random String::"+getRandomStringOfSize(5));
		//System.out.println("UUID:"+getUUIDAsToken());
	
	Date now=new Date();
	Date expiryTime = DateUtils.addMinutes(now, 30);
	
	System.out.println("Now:"+now);
	System.out.println("new datetime:"+expiryTime);
		
		
	}
}
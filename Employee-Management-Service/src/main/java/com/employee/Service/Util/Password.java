package com.employee.service.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Password {

	public String getEncryptPassword(String password)
	{	
		//exponential cost (log2 factor) between MIN_COST and MAX_COST e.g. 12 --> 2^12 = 4,096 iterations
		//password to hash, will be internally converted to a utf-8 byte array representation
		String encryptPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
		
		//bcrypt as utf-8 encoded String, which includes version, cost-factor.
		return encryptPassword;
	}
}

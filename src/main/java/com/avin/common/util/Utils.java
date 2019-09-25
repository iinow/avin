package com.avin.common.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author iinow
 * @since 2019.09.25
 *
 */
public final class Utils {
	private static Utils instance = null;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private Utils() {}
	
	public static synchronized Utils getInstance() {
		if(instance == null) {
			instance = new Utils();
		}
		return instance;
	}
	
	public String passwordEncoded(String password) {
		return encoder.encode(password);
	}
	
	public boolean passwordMatch(String password, String encodedPassword) {
		return BCrypt.checkpw(password, encodedPassword);
	}
}

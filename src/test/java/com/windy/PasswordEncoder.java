package com.windy;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		//	www.fileformat.info/tool/hash.htm
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String encodedPassword = encoder.encode("spring");
		System.out.println(encodedPassword);
	}
}

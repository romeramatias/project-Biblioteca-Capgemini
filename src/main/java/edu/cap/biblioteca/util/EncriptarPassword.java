/**
 * @author romeramatias
 */

package edu.cap.biblioteca.util;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {

	public static void main(String[] args) {
		
		var password = "123";
		System.out.println(password);
		System.out.println(encriptarPassword(password));
		System.out.println(LocalDate.now());
	}

	public static String encriptarPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

}

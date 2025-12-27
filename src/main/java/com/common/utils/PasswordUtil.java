package com.common.utils;

import com.common.exception.BusinessException;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.http.HttpStatus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.common.utils.Constants.PASSWORD_ALGO;

public class PasswordUtil {

	public static String encryptPass(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(PASSWORD_ALGO);
		} catch (NoSuchAlgorithmException e) {
			throw new BusinessException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		md.update(password.getBytes());
		byte[] digest = md.digest();
		return DatatypeConverter.printHexBinary(digest).toUpperCase();
	}

	public static boolean checkPassword(String password, String encryptedPassword) {
        String myHash = encryptPass(password);
        return myHash.equals(encryptedPassword);
    }
}

package com.MovieBackIn.registeration;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class Encryptpass {
	
	private static final String SECRET_KEY = "1234567890abcdef";
    private static final String INIT_VECTOR = "abcdef1234567890";
    public static String encrypt(String input) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(input.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }
}

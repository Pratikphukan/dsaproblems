package com.dsaproblems.DSAProblems.learning;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

public class Client {

	private static final byte[] rawSecretKey_IV = { 1, 2, 3, 4, 5, 6, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final String secretKey = "FC7AB7666419FE5414ADDA1F0B468B55CF25D022EEF2CCBEF875994FB7584D57";

	public static void main(String[] args) {
		String originalMessage = "{\"acsTransID\":\"e6894fe1-20bd-42c2-aec5-91e5ee035044\",\"challengeWindowSize\":\"02\",\"messageType\":\"CReq\",\"messageVersion\":\"2.1.0\",\"threeDSServerTransID\":\"0d093032-6c65-4f7a-9245-7860a72a9bc2\"}";

		// Encode the string into Base64 URL encoding
		String encodedMessage = Base64.getUrlEncoder().encodeToString(originalMessage.getBytes());

		System.out.println(encodedMessage);

		// Decode the Base64 URL encoded string into a byte array
		byte[] decodedByteMessage = Base64.getUrlDecoder().decode(encodedMessage);

		// Convert the byte array back to a string
		String decodedMessage = new String(decodedByteMessage);

		System.out.println(decodedMessage);

		System.out.println(mfaaesdecrypt("tzNAp5NZQaY+7Wv9C768Ig=="));
	}

	public static String mfaaesencrypt(String strToEncrypt) {
		try {
			// Constants
			// byte[] rawSecretKey_IV = { 1, 4, 5, 5, 7, 8, 99, 1, 2, 11, 4, 2, 1, 3, 5, 2
			// };
			String secretKeyHash = DigestUtils.sha256Hex(secretKey);
			final int AES_KEY_LENGTH = 16;

			// Derive the AES key
			byte[] passwordKey = MessageDigest.getInstance("SHA-256").digest(secretKeyHash.getBytes());
			byte[] passwordKey128 = Arrays.copyOf(passwordKey, AES_KEY_LENGTH);
			SecretKey secretKey = new SecretKeySpec(passwordKey128, "AES");

			// Initialization Vector (IV)
			IvParameterSpec ivParameterSpec = new IvParameterSpec(rawSecretKey_IV);

			// Encrypt the PAN
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			byte[] encryptedBytes = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));

			return org.apache.commons.codec.binary.Base64.encodeBase64String(encryptedBytes);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("An unexpected error occurred: " + ex.getMessage());
		}
	}

	public static String mfaaesdecrypt(String strToDecrypt) {
		try {
			// Constants
			// byte[] rawSecretKey_IV = { 1, 4, 5, 5, 7, 8, 99, 1, 2, 11, 4, 2, 1, 3, 5, 2
			// };

			String secretKeyHash = DigestUtils.sha256Hex(secretKey);
			final int AES_KEY_LENGTH = 16;

			// Derive the AES key
			byte[] passwordKey = MessageDigest.getInstance("SHA-256").digest(secretKeyHash.getBytes());
			byte[] passwordKey128 = Arrays.copyOf(passwordKey, AES_KEY_LENGTH);
			SecretKey secretKey = new SecretKeySpec(passwordKey128, "AES");

			// Initialization Vector (IV)
			IvParameterSpec ivParameterSpec = new IvParameterSpec(rawSecretKey_IV);

			// Decrypt the PAN
			byte[] encryptedBytes = org.apache.commons.codec.binary.Base64.decodeBase64(strToDecrypt);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

			return new String(decryptedBytes, StandardCharsets.UTF_8);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("An unexpected error occurred: " + ex.getMessage());
		}
	}

}

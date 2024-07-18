package br.com.allrisk.weather.core;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static String encrypt(String input, String key) throws Exception {
        return Base64.getEncoder().encodeToString(doCrypto(Cipher.ENCRYPT_MODE, input.getBytes(), key));
    }

    public static String decrypt(String input, String key) throws Exception {
        return new String(doCrypto(Cipher.DECRYPT_MODE, Base64.getDecoder().decode(input), key));
    }

    private static byte[] doCrypto(int cipherMode, byte[] inputBytes, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(cipherMode, secretKey);
        return cipher.doFinal(inputBytes);
    }

    public static String generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

}
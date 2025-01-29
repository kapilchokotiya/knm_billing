package com.knm.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class AESEncryptionProvider {
    private String secretKey = "sbibanksecretkey";
    private String strAlgorithm = "AES/CBC/PKCS5PADDING";

    public String encrypt(String plainText, String data) {
        String encryptedinput = null;
        try {
            byte[] secret = secretKey.getBytes(StandardCharsets.UTF_8);

            byte[] plain = Arrays.copyOfRange(secret, 0, 16);
            if (secretKey.isEmpty()) {

            }
            IvParameterSpec ivParameterSpec = new IvParameterSpec(plain);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret, "AES");
            Cipher encryptionCipher = Cipher.getInstance(strAlgorithm);
            encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedBytes = encryptionCipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during encryption", e);
        }
    }


    public String decrypt(String encryptedText) {
        String decryptedInput = null;
        try {
            // Get secret key bytes
            final byte[] secret = secretKey.getBytes(StandardCharsets.UTF_8);
            final byte[] key = Arrays.copyOfRange(secret, 0, 16); // Ensure 16 bytes for AES-128

            // Initialization vector (IV)
            IvParameterSpec ivParameterSpec = new IvParameterSpec(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

            // Cipher instance for decryption
            Cipher decryptionCipher = Cipher.getInstance(strAlgorithm);
            decryptionCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            // Decode the Base64 encoded encrypted text to bytes
            byte[] inputBytes = Base64.getDecoder().decode(encryptedText);

            // Decrypt the data
            byte[] decryptedBytes = decryptionCipher.doFinal(inputBytes);

            // Convert decrypted bytes to string
            decryptedInput = new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during decryption", e);
        }

        return decryptedInput;
    }

    public static void main(String[] args) {
        AESEncryptionProvider aes = new AESEncryptionProvider();
       // System.out.println(aes.encrypt("sbibank"));
       System.out.println(aes.decrypt("4UAM2Vty1EaVXkUBAguS/Q=="));

    }
}
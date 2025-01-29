package com.knm.security;
/*
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {
    private SecretKey key;
    private int KEY_SIZE = 128;
    private int TLEN = 128;
    private Cipher encryptionCipher;

    public void init() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();
    }

    public String encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] dataBytes = data.getBytes();
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);

        // Get the IV and encrypted data
        byte[] iv = encryptionCipher.getIV();
        byte[] encryptedBytes = encryptionCipher.doFinal(dataBytes);

        // Combine IV and encrypted data for transmission
        byte[] ivAndEncryptedData = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, ivAndEncryptedData, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, ivAndEncryptedData, iv.length, encryptedBytes.length);

        return encode(ivAndEncryptedData);  // Encode the combined IV and encrypted data
    }

    public String decrypt(String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] ivAndEncryptedDataBytes = decode(encryptedData);

        // Extract the IV and encrypted data
        byte[] iv = new byte[12]; // IV is always 12 bytes in AES/GCM
        byte[] encryptedBytes = new byte[ivAndEncryptedDataBytes.length - 12];
        System.arraycopy(ivAndEncryptedDataBytes, 0, iv, 0, 12);
        System.arraycopy(ivAndEncryptedDataBytes, 12, encryptedBytes, 0, encryptedBytes.length);

        // Initialize the cipher for decryption
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(TLEN, iv);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);

        byte[] decryptedBytes = decryptionCipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static void main(String[] args) throws Exception {
        AES aes = new AES();
        aes.init();

        System.out.println(aes.encrypt("Hello"));




    }
}
*/
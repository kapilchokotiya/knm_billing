package com.knm.security;



import java.nio.charset.StandardCharsets;
import java.security.*;

import java.util.Base64;

public class DigitalSignatureUtil {
//            KeyPair keyPair = generateKeyPair();
//            PrivateKey privateKey = keyPair.getPrivate();
            //PublicKey publicKey = keyPair.getPublic();

    public DigitalSignatureUtil() throws NoSuchAlgorithmException {
    }

    /**
     * Generates an RSA KeyPair with 2048-bit strength
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * Generates a digital signature using SHA256WithRSA
     */
    public static String generateSignature(String data) throws Exception {
        KeyPair keyPair = generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] signedBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signedBytes); // Convert to Base64 for readability
    }

    /**
     * Verifies the digital signature
     */
    public static boolean verifySignature(String data, String signatureBase64, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64); // Convert Base64 back to bytes
        return signature.verify(signatureBytes);
    }

    /**
     * Example usage (Can be removed)
     */
//    public static void main(String[] args) {
//        try {
//            // Generate Key Pair
//            KeyPair keyPair = generateKeyPair();
//            PrivateKey privateKey = keyPair.getPrivate();
//            PublicKey publicKey = keyPair.getPublic();
//
//            // Original Data
//            String message = "Hello, this is a secure message!";
//
//            // Generate Signature
//            String signature = generateSignature(message);
//            System.out.println("Digital Signature: " + signature);
//
//            // Verify Signature
//            boolean isValid = verifySignature(message, signature, publicKey);
//            System.out.println("Is signature valid? " + isValid);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}









































//package com.knm.security;
//
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.util.Base64Utils;
//
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.NoSuchAlgorithmException;
//import java.security.Signature;
//
//
//public class Digisign {
//    public static void main(String[] args) throws Exception {
//
//        KeyPair keyPair = getKeyPair();
//        KeyPair keypair1 = getKeyPair();
//
//        byte[] data = "test".getBytes("UTF8");
//
//        Signature sig = Signature.getInstance("SHA1WithRSA");
//        sig.initSign(keyPair.getPrivate());
//        sig.update(data);
//        byte[] signatureBytes = sig.sign();
//     //   System.out.println("Signature:" + new BASE64Encoder().encode(signatureBytes));
//        System.out.println("Signature:" + new Base64().encodeToString(signatureBytes));
//
//        sig.initVerify(keypair1.getPublic());
//        sig.update(data);
//
//        System.out.println(sig.verify(signatureBytes));
//    }
//
//    private static KeyPair getKeyPair() throws NoSuchAlgorithmException {
//        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//        kpg.initialize(1024);
//        return kpg.genKeyPair();
//    }
//}
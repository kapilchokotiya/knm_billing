package com.knm.service;

import com.knm.entity.TestingAPI;
import com.knm.security.AESEncryptionProvider;

import com.knm.security.DigitalSignatureUtil;
import org.springframework.stereotype.Service;

@Service
public class TestApi {
    String secretkey="c2VjcmV0S2V5VG9wU2VjcmV0S2V5c3RyaW5n";
    byte[] scrt=secretkey.getBytes();
    public TestingAPI EncryDecydata(String data) throws Exception {

        AESEncryptionProvider aesEncryptionProvider = new AESEncryptionProvider();
        DigitalSignatureUtil digitisign = new DigitalSignatureUtil();
        TestingAPI objTestingAPI =new TestingAPI();
        objTestingAPI.setDigisign(digitisign.generateSignature(data));
        objTestingAPI.setEncryptedbody(aesEncryptionProvider.encrypt(secretkey,data));

        return objTestingAPI;
    }
    public TestingAPI DecryptedData(String data){
        AESEncryptionProvider aesEncryptionProvider = new AESEncryptionProvider();
        TestingAPI objTestingAPI =new TestingAPI();
        objTestingAPI.setEncryptedbody(aesEncryptionProvider.decrypt(data));
        return objTestingAPI;
    }
    public TestingAPI EncryptedData(String data){
        AESEncryptionProvider aesEncryptionProvider = new AESEncryptionProvider();
        TestingAPI objTestingAPI =new TestingAPI();
        objTestingAPI.setEncryptedbody(aesEncryptionProvider.encrypt(secretkey,data));
        return objTestingAPI;
    }
}

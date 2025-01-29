package com.knm.controller;

import com.knm.service.TestApi;
import com.knm.entity.TestingAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {
//http://localhost:8088/api/test/decencryptData
    @Autowired
    private TestApi testApiService;

    @PostMapping("/Digisign")
    public TestingAPI encryptData(@RequestBody String data) throws Exception {
        return testApiService.EncryDecydata(data);
    }
    @PostMapping("/DecryptedData")
    public TestingAPI DecryptedData(@RequestBody String data){
        return testApiService.DecryptedData(data);
    }
    @PostMapping("/EncryptedData")
    public TestingAPI EncryptedData(@RequestBody String data){
        return testApiService.EncryptedData(data);
    }

}

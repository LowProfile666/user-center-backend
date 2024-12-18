package com.zsm.usercenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
        StringBuilder hexPassword = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest("userPassword".getBytes());
            System.out.println(new String(bytes));
            for (byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexPassword.append('0');
                }
                hexPassword.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        System.out.println(hexPassword.toString());

        String s = DigestUtils.md5DigestAsHex("userPassword".getBytes());
        System.out.println(s);
    }

}

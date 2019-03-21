package com.db.sys.service.realm;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.UUID;
import java.util.Vector;

public class TestShiro {
    public static void main(String[] args) {
        String str = "123456";
        String salt = "5e3e1475-1ea9-4a6a-976e-b07545827139";
        String simpleHash = new SimpleHash("MD5", str, salt).toString();
        System.out.println("simpleHash=" + simpleHash);
    }

}


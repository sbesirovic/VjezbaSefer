package com.example.FirstApp;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;


@Component("userSecurity")
public class UserSecurity{
    public  boolean hasUserName (org.springframework.security.authentication.UsernamePasswordAuthenticationToken authentication, String userName)
    {
        return  authentication.getName().equals(userName);
    }
}
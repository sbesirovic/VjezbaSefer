package com.example.FirstApp;



import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Properties {

    @Value("${spring.data.mongodb.database}")
    private String  databaseName;

    @Value("${spring.data.mongodb.port}")
    private String  portNumber;

 @Autowired
 private Environment environment;

 public String getActiveProfiles()
 {
     String profiles = "";
     for (String profileName : environment.getActiveProfiles()) {
        profiles += profileName+" ";
            }
     return profiles;
 }


}

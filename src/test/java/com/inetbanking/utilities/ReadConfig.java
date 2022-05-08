package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ReadConfig {
    public Properties prop;

    public ReadConfig(){
        File src = new File("./Configuration/config.properties");

        try{
            FileInputStream fis = new FileInputStream(src);
            prop = new Properties();
            prop.load(fis);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    public String getURL(){
        return prop.getProperty("baseURL");
    }

    public String getUsername(){
        return prop.getProperty("username");
    }

    public String getPassword(){
        return prop.getProperty("password");
    }

    public String getChromepath(){
        return prop.getProperty("chromepath");
    }

    public String getFirefoxpath(){
        return prop.getProperty("firefoxpath");
    }
}

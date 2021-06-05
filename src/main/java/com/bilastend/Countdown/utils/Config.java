package com.bilastend.Countdown.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {
   private final File config;
   Properties properties;

   public Config(){
       config = new File("countdown.properties");

       properties = new Properties();
       try {
           if(!config.exists()) config.createNewFile();
           properties.load(new FileInputStream(config));
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public Properties getProperties() {
        return properties;
    }

    public void save(){
        try {
            properties.store(new FileWriter(config),"Countdown properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

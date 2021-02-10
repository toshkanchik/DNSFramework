package ru.appline.dnsFramework.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropMngr {
    private final Properties props =new Properties();
    private static PropMngr instance = null;

    private PropMngr(){
        loadProps();
    }

    public static PropMngr GetPropMngr(){
        if(instance == null){
            instance = new PropMngr();
        }
        return instance;
    }

    private void loadProps(){
        try {
            props.load(new FileInputStream("src/main/resources/sets.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getProperty(String key) {
        return props.getProperty(key);
    }
}

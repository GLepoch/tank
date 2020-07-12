package com.glepoch.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertiesMgr {
    static Properties prop = new Properties();

    static {
        try {
            prop.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config/config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if (prop == null || prop.size() == 0) return null;
        return prop.get(key);
    }
    public static int getInt(String key) {
        if (prop == null || prop.size() == 0) return 0;
        return Integer.valueOf((String)prop.get(key));
    }
    public static String getString(String key) {
        if (prop == null || prop.size() == 0) return null;
        return (String) prop.get(key);
    }

    public static void main(String[] args) {
        Object badTankCount = get("badTankCount");
        System.out.println(badTankCount);
    }
}

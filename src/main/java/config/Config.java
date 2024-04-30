package config;

import utils.Utils;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

public class Config {

    public static final String PATH = "src/main/java/config/config.properties";

    public static String getPathUsers(){
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_users", "src/main/java/data/users");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathDrivers(){
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_drivers", "src/main/java/data/drivers");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathAdmins(){
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_admins", "src/main/java/data/admins");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathRegisterLogin(){
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_registerLogin", "src/main/java/data/registerLogin/historicLogin.data");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathData() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_data", "src/main/java/data");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getPathPackage() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_packageUnassigned", "src/main/java/data/packageUnassigned");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLastLogin(String id, LocalDateTime date){
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            p.setProperty(id, Utils.fechaAString(date));
            p.store(new FileWriter(PATH), "Update Login Access");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLastLogin(String id) {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package config;

import utils.Utils;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class Config {

    public static final String PATH = "src/main/java/config/config.properties";

    public static String getPathUsers() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_users", "src/main/java/data/users");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathDrivers() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_drivers", "src/main/java/data/drivers");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathAdmins() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_admins", "src/main/java/data/admins");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathRegisterLogin() {
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

    public static String getPathPackageUnassigned() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_packageUnassigned", "src/main/java/data/packageUnassigned");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathPackageNoRegisterUser() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_packageNoRegisterUser", "src/main/java/data/packageNoRegisterUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLastLogin(String id, LocalDateTime date) {
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

    public static String getPathFile() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            return p.getProperty("route_files", "src/main/java/files");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean getUserInvited() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            if (p.getProperty("user_invited").equals("s")) return true;
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public static ArrayList<String> getInfo() {
        ArrayList<String> info = new ArrayList<>();
        FileReader fr = null;
        try {
            fr = new FileReader(PATH);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (line != null) {
                line = br.readLine();
                if (line != null && !line.contains("#")) info.add(line);
            }
        } catch (IOException e) {
            return null;
        }
        return info;
    }

    public static boolean changeProperties(String respuesta) {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PATH));
            p.setProperty("user_invited", respuesta);
            p.store(new FileWriter(PATH), "Modo invitado actualizado");
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}

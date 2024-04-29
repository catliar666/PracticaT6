package persistence;

import appcontroller.AppController;
import models.Admin;
import models.Driver;
import models.User;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PersistenceDisk {

    public static final String ROUTE_DATA = "src/main/java/data";
    private static final String ROUTE_DATA_USERS = "src/main/java/data/users";
    private static final String ROUTE_DATA_DRIVERS = "src/main/java/data/drivers";
    private static final String ROUTE_DATA_ADMINS = "src/main/java/data/admins";


    public static void recordLogin(Object user, LocalDateTime date) {
        String tipo = "", nombre = "";
        int id = -1;
        if (user instanceof User) {
            tipo = "usuario";
            id = ((User) user).getId();
            nombre = ((User) user).getName();
        }
        if (user instanceof Driver) {
            tipo = "conductor";
            id = ((Driver) user).getId();
            nombre = ((Driver) user).getName();
        }
        if (user instanceof Admin) {
            tipo = "administrador";
            id = ((Admin) user).getId();
            nombre = ((Admin) user).getName();
        }
        try {
            FileWriter fw = new FileWriter(ROUTE_DATA + "/registerLogin/historicLogin.data", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Inicio de sesión: " + id + " \nNombre del usuario: " + nombre
                     + " \nTipo de usuario:" + tipo + " \nFecha del inicio de sesión: " + date + "\n" +
                     "==============================================================\n");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void closeRegister(int id, String nombre, String tipo, LocalDateTime date) {
        try {
            FileWriter fw = new FileWriter(ROUTE_DATA + "/registerLogin/historicLogin.data", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Cierre de sesión: " + id + " \nNombre del usuario: " + nombre +
                     " \nTipo de usuario:" + tipo + " \nFecha del inicio de sesión: " + date + "\n" +
                     "==============================================================\n");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void recordShipment(int idRecieved, int idSender, LocalDateTime date) {
        try {
            FileWriter fw = new FileWriter(ROUTE_DATA + "/registerLogin/historicLogin.data", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Nuevo envío\n" + "Usuario destinatario: " + ((idRecieved == -1) ? "No registrado" : idRecieved) + " \nUsuario remitente: " + idSender +
                     " \nFecha de la creación: " + date + "\n" +
                     "==============================================================\n");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveUser(User user) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(ROUTE_DATA + "/users/" + user.getId() + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveDriver(Driver driver) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(ROUTE_DATA + "/drivers/" + driver.getId() + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(driver);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveAdmin(Admin admin) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(ROUTE_DATA + "/admins/" + admin.getId() + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(admin);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean backup(AppController appController, String route) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(route);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(appController);
            oos.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    public static AppController restore(String route) {
        FileInputStream fis = null;
        AppController appController = null;
        try {
            fis = new FileInputStream(route);
            ObjectInputStream ois = new ObjectInputStream(fis);
            appController = (AppController) ois.readObject();
            ois.close();
            return appController;
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    public static boolean existsData() {
        File f = new File(ROUTE_DATA);
        return f.list().length > 0;
    }

    public static ArrayList<User> readUsersDisk() {
        File f = new File(ROUTE_DATA_USERS);
        if (f.list().length == 0) return new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        String[] ficheros = f.list();
        for (int i = 0; i < ficheros.length; i++) {
            FileInputStream fis;
            try {
                fis = new FileInputStream(ROUTE_DATA_USERS + "/" + ficheros[i]);
                ObjectInputStream ois = new ObjectInputStream(fis);
                User temp = (User) ois.readObject();
                users.add(temp);
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                return null;
            }

        }
        return users;
    }

    public static ArrayList<Driver> readDriversDisk() {
        File f = new File(ROUTE_DATA_DRIVERS);
        if (f.list().length == 0) return new ArrayList<>();
        ArrayList<Driver> drivers = new ArrayList<>();
        String[] ficheros = f.list();
        for (int i = 0; i < ficheros.length; i++) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(ROUTE_DATA_DRIVERS + "/" + ficheros[i]);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Driver temp = (Driver) ois.readObject();
                drivers.add(temp);
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                return null;
            }

        }
        return drivers;
    }

    public static ArrayList<Admin> readAdminsDisk() {
        File f = new File(ROUTE_DATA_DRIVERS);
        if (f.list().length == 0) return new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        String[] ficheros = f.list();
        for (int i = 0; i < ficheros.length; i++) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(ROUTE_DATA_ADMINS + "/" + ficheros[i]);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Admin temp = (Admin) ois.readObject();
                admins.add(temp);
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                return null;
            }

        }
        return admins;
    }

//    public static boolean excelDocument(AppController controller) {
//    }
}

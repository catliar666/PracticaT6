package persistence;

import models.Admin;
import models.Driver;
import models.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersistenceData {

    private static final String ROUTE_DATA_USERS = "src/main/java/data/users";
    private static final String ROUTE_DATA_DRIVERS = "src/main/java/data/drivers";
    private static final String ROUTE_DATA_ADMINS = "src/main/java/data/admins";
    public static void recordUsers(ArrayList<User> users) {
        for (User u:
             users) {
            if (u != null) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(ROUTE_DATA_USERS + "/" + u.getId() + ".dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(u);
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void recordDrivers(ArrayList<Driver> drivers) {
        for (Driver d:
                drivers) {
            if (d != null) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(ROUTE_DATA_DRIVERS + "/" + d.getId() + ".dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(d);
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void recordAdmins(ArrayList<Admin> admins) {
        for (Admin a:
                admins) {
            if (a != null) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(ROUTE_DATA_ADMINS + "/" + a.getId() + ".dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(a);
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

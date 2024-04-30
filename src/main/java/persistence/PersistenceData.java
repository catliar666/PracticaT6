package persistence;

import comunication.ValidarCorreo;
import config.Config;
import models.Admin;
import models.Driver;
import models.Shipment;
import models.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersistenceData {

    public static void recordUsers(ArrayList<User> users) {
        for (User u:
             users) {
            if (u != null) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(Config.getPathUsers() + "/" + u.getId() + ".dat");
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
                    fos = new FileOutputStream(Config.getPathDrivers() + "/" + d.getId() + ".dat");
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
                    fos = new FileOutputStream(Config.getPathAdmins() + "/" + a.getId() + ".dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(a);
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void recordShipment(ArrayList<Shipment> shipments) {
        for (Shipment s:
                shipments) {
            if (s != null) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(Config.getPathPackage() + "/" + s.getId() + ".dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(s);
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

package persistence;

import models.Admin;
import models.Driver;
import models.User;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersistenceDisk {

        public static final String ROUTE_DATA = "src/main/java/data";

        public static void recordLogin(Object user, LocalDateTime date){
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
        public static void closeRegister(int id, String nombre, String tipo, LocalDateTime date){
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

        public static void recordShipment(int idRecieved, int idSender, LocalDateTime date){
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

        public static void saveUser(User user){
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

        public static void saveDriver(Driver driver){
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
        public static void saveAdmin(Admin admin){
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
}

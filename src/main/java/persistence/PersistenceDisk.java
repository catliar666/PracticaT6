package persistence;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersistenceDisk {

        public static final String ROUTE_DATA = "src/main/java/data";

        public static void recordLogin(int id, String nombre, String tipo, LocalDateTime date){
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
}

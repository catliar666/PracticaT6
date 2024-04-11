package utils;

import java.util.Scanner;

public class Utils {
    public static void clickToContinue(){
        var s = new Scanner(System.in);
        System.out.print("Pulsa cualquier tecla para continuar: ");
        s.nextLine();
    }

    public static void checkEmail(){
        System.out.print("Comprobando correo electrónico");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(". ");
        }
    }
    public static void login(){
        System.out.print("Iniciando sesión");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(". ");
        }
        System.out.println();
    }

    public static void closeSesion(){
        System.out.print("Cerrando sesión");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(". ");
        }
        System.out.println();
    }
    public static void exitOption(){
        System.out.print("Saliendo");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(". ");
        }
        System.out.println();
    }
}

package dataclass;

import utils.Utils;

import java.util.Scanner;

public class Menus {


    public static String startMenuNotInvited() {
        return """
                     _____                     _____            \s
                    |   __|___ ___ ___ ___ ___|  _  |___ ___ ___\s
                    |   __| -_|  _|   | .'|   |   __| .'| .'| . |
                    |__|  |___|_| |_|_|__,|_|_|__|  |__,|__,|_  |
                                                              |_|
                                                           
        
                    ┌──. ■ .────────────────────────────────────────────────┐
                    █   Menú de inicio                                      █
                    █       1. Login                                        █
                    █       2. Registro                                     █
                    █       3. Sigue un envío con el número de seguimiento  █
                    └────────────────────────────────────────────────. ■ .──┘
                    Elija una opción:""";
    }

    public static String startMenuInvited() {
        return """
                     _____                     _____            \s
                    |   __|___ ___ ___ ___ ___|  _  |___ ___ ___\s
                    |   __| -_|  _|   | .'|   |   __| .'| .'| . |
                    |__|  |___|_| |_|_|__,|_|_|__|  |__,|__,|_  |
                                                              |_|
                                                           
                              ESTAS EN MODO INVITADO
            ┌──. ■ .──────────────────────────────────────────────────────────────────┐
             Para cambiar de modo dirigase a \"src/main/java/config/config.properties\"
            └──────────────────────────────────────────────────────────────────. ■ .──┘
                    ┌──. ■ .────────────────────────────────────────────────┐
                    █   Menú de inicio                                      █
                    █       1. Sigue un envío con el número de seguimiento  █
                    └────────────────────────────────────────────────. ■ .──┘
                    Elija una opción:""";
    }
}

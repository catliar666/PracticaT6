package models;

import persistence.PersistenceDisk;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    //ATRIBUTOS
    public boolean first_login;
    private int id; //codigo que generaremos
    private String name; //nombre
    private String surname; //Apellidos
    private String email; //Correo electronico
    private String pass; //contraseña
    private int phone; //telefono
    private String street; //calle
    private int num; //numero de la casa
    private String city; //ciudad o pueblo
    private String state; //provincia
    private int postalCode; //codigo postal
    private int token;
    private boolean notification; //Atributo que sirve para saber si el usuario quiere o no notificaciones en el email
    private boolean validate; //indica si la cuenta está validada o no mediante el codigo que se envia por correo
    private ArrayList<Shipment> shipments; //envios que tiene este usuario

    //CONSTRUCTOR

    public User(int id, String name, String surname, String email, String pass,
                int phone, String street, int num, String city, String state,
                int postalCode, int token) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.street = street;
        this.num = num;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.token = token;
        notification = true;
        validate = false;
        shipments = new ArrayList<>();
        first_login = false;


    }



    //GETTERS AND SETTERS


    public boolean isFirst_login() {
        return first_login;
    }

    public void setFirst_login(boolean first_login) {
        this.first_login = first_login;
        PersistenceDisk.saveUser(this);
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
        PersistenceDisk.saveUser(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public ArrayList<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(ArrayList<Shipment> shipments) {
        this.shipments = shipments;
    }


    //MÉTODOS

    /*Método que comprueba si el email y la contraseña introducido por el usuario coincide con uno ya existente o no
      devuelve true si lo encuentra y false si no coincide con ninguno*/
    /*Method that checks if the email and password entered by the user matches an existing
      one or does not return true if it finds it and false if it does not match any*/
    public boolean login(String email, String pass){
        return this.email.equals(email) && this.pass.equals(pass);
    }


    /*Este método cuenta los pedidos que quedan pendientes por ser entregados, devuelve un contador con la cantidad de los envios*/
    /*This method counts the orders that are pending to be delivered, returns a counter with the quantity of shipments*/
    public int numDeliveriesPendingToDeliver() {
        int shipmentDeliverPending = 0;
        for (Shipment s:
             shipments) {

            if (s != null && s.getIdSender() != this.id && !s.getStatus().equals("Entregado")) shipmentDeliverPending++;
        }
        return shipmentDeliverPending;
    }

    /*Este método busca los envios según el id que nos pasen por teclado, al final, si lo encuentra, devuelve el objeto Shipment*/
    /*This method searches for the shipments according to the id that is passed to us on the keyboard,
    at the end, if it finds it, it returns the Shipment object*/
    public Shipment searchDeliveryById(int id){
        if (shipments.isEmpty()) return null;
        else {
            for (Shipment s:
                 shipments) {
                if (s != null && s.getId() == id) return s;
            }
        }
        return null;
    }

    /*Este método comprueba si el usuario tiene dentro de su información el paquete con la id que pasa por teclado, si lo encuentra devuelve true para indicarlo*/
    /*This method checks if the user has in his information the package with the id that he passes by keyboard, if it is found it returns true to indicate it*/
    public boolean containsShipment(int idShipment){
        if (!shipments.isEmpty()) {
            for (Shipment s :
                    shipments) {
                if (s != null && s.getIdSender() != this.id && s.getId() == idShipment) return true;
            }
        }
        return false;
    }

    /*Este método busca los envios que todavia no han sido entregados, los mete en un ArrayList y devuelve esa lista*/
    /*This method looks for shipments that have not yet been delivered, puts them in an ArrayList and returns that list*/
    public ArrayList<Shipment> shipmentsPendingToDelivery() {
        ArrayList<Shipment> shipmentsPendings = new ArrayList<>();
        if (shipments.isEmpty()) return null;
        else {
            for (Shipment s:
                 shipments) {
                if (s != null && (s.getIdSender() != this.id) && (s.getStatus().equals("En almacen") || s.getStatus().equals("En oficina de origen"))) shipmentsPendings.add(s);
            }
        }
        return shipmentsPendings;
    }

    /*Este método añade un paquete al usuario, no devuelve nada*/
    /*This method adds a package to the user, it does not return anything*/
    public void addShipment(Shipment s){
        shipments.add(s);
        PersistenceDisk.saveUser(this);
    }

    /*Este método junta los atributos, street, num, postalcode, city y state para crear un solo string con toda esa informacion*/
    /*This method brings together the attributes, street, num, postalcode, city and state to create a single string with all that information*/
    public String getAddress(){
        String results = "";
        results += street + ", " + num + ", " + postalCode + " " + city + "(" + state + ")";
        return results;
    }
    public boolean validarToken(int tokenIngresado) {
        // Verificar si el token ingresado coincide
        if (tokenIngresado == this.token) {
            PersistenceDisk.saveUser(this);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "┌──. ■ .─────────────────────────────────────────────────────────┐\n" +
               "                    Informacion del usuario\n" +
               "└─────────────────────────────────────────────────────────. ■ .──┘\n" +
               "█  Número de referencia del usuario: " + id + "\n" +
               "█  Nombre: " + name + "\n" +
               "█  Apellidos: " + surname + "\n" +
               "█  Email: " + email + "\n" +
               "█  Número de teléfono: " + phone + "\n" +
               "█  Dirección: " + street + " " + num + ", " + city + ", " + state + "(" + postalCode + ")" + "\n" +
               "──────────────────────────────────────────────────────────. ■ .──";
    }

    public boolean checkPass(String pass) {
        return this.pass.equals(pass);
    }

    public String resumeForAdmin() {
        return "──────. ■ .──────────────────────────────────────────────────────\n" +
               "█  Número de referencia del usuario: " + id + "\n" +
               "█  Nombre y apellidos: " + name + " " + surname + "\n" +
               "█  Email: " + email + "\n" +
               "█  Validación de cuenta: " + ((validate) ? "Realizada" : "No realizada") + "\n" +
               "█  Recibe notificaciones: " + ((notification) ? "Sí" : "No") + "\n" +
               "█  Número de teléfono: " + phone + "\n" +
               "█  Dirección: " + street + " " + num + ", " + city + ", " + state + "(" + postalCode + ")" + "\n" +
               "█  Envíos pendientes de entrega: " + numDeliveriesPendingToDeliver() + "\n" +
               "──────────────────────────────────────────────────────────. ■ .──";
    }

    public String resumeMock() {
        return "──────. ■ .──────────────────────────────────────────────────────\n" +
               "                        USUARIO DE PRUEBA\n" +
               "█  Email: " + email + "\n" +
               "█  Contraseña: " + pass + "\n" +
               "█  Nombre y apellidos: " + name + " " + surname + "\n" +
               "█  Dirección: " + street + " " + num + ", " + city + ", " + state + "(" + postalCode + ")" + "\n" +
               "──────────────────────────────────────────────────────────. ■ .──";
    }


    public void changeDeliveryStatus(String newStatus, int id) {
        for (Shipment s:
             shipments) {
            if (s.getId() == id) s.setStatus(newStatus);
        }
        PersistenceDisk.saveUser(this);
    }

    public void changeDeliveryData(String address, int postalCode, String city, int idShipment) {
        for (Shipment s:
                shipments) {
            if (s.getId() == idShipment) {
                s.setAlternativeAddress(address);
                s.setAlternativePostalCode(postalCode);
                s.setAlternativeCity(city);
            }
        }
        PersistenceDisk.saveUser(this);
    }
}

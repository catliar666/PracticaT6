package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Driver implements Serializable {
    //ATRIBUTOS
    private int id; //Codigo que crearemos nosotros
    private String name; //nombre del conductor
    private String pass; //Contraseña del conductor
    private String email; //Correo electronico
    private boolean validate; //indica si la cuenta está validada o no mediante el codigo que se envia por correo
    private ArrayList<Integer> deliveryZones; //Ciudades en las que puede repartir el conductor TODO:Mirar a ver si esta bien
    private ArrayList<Shipment> shipments; //Envios que tiene por repartir o que ya están entregados

    //CONSTRUCTOR

    public Driver(int id, String name, String pass, String email) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        validate = false;
        deliveryZones = new ArrayList<>();
        shipments = new ArrayList<>();
    }


    //GETTERS AND SETTERS

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Integer> getDeliveryZones() {
        return deliveryZones;
    }

    public void setDeliveryZones(ArrayList<Integer> deliveryZones) {
        this.deliveryZones = deliveryZones;
    }

    public ArrayList<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(ArrayList<Shipment> shipments) {
        this.shipments = shipments;
    }

    //MÉTODOS

    //Metodo que comprueba si el email y la contraseña introducido por el usuario coincide con uno ya existente o no
    //Devuelve true si lo encuentra y false si no coincide con ninguno
    public boolean login(String email, String pass){
        return this.email.equals(email) && this.pass.equals(pass);
    }

    public Shipment searchShipmentById(int idShipment){
        if (shipments == null) return null;
        else {
            for (Shipment s:
                 shipments) {
                if (s != null && s.getId() == idShipment) return s;
            }
        }
        return null;
    }

    /*Añade un envío comprobando antes si el codigo postal introducido
    por teclado coincide con algún codigo postal de la lista del conductor*/
    /*Add a shipment, first checking if the postal code entered
     by keyboard matches any zip code on the driver's list*/
    public void addShipment(Shipment s, int postalCode){
        if (deliveryZones != null){
            for (Integer d:
                 deliveryZones) {
                if (d != null && d == postalCode) shipments.add(s);
            }
        }
    }

    /*Añade un envío a la lista de envios del conductor, no comprueba nada, no devuelve nada*/
    /*Adds a shipment to the driver's shipment list, checks nothing, returns nothing*/
    public void addShipment(Shipment s){
        shipments.add(s);
    }

    /*Comprueba si el codigo postal introducido por teclado ya existe en la
    lista de las zonas de entrega del conductor, si lo encuentra devuelve true*/
    /*Checks if the zip code entered by keyboard already exists in the
     list of the driver's delivery zones, if found it returns true*/
    public boolean hasPostalCodeZone(int postalCode){
        if (!deliveryZones.isEmpty()) {
            for (Integer c :
                    deliveryZones) {
                if (c != null && c == postalCode) return true;
            }
        }
        return false;
    }

    /*Cuenta los envíos que le queda al conductor por entregar*/
    /*Counts the shipments that the driver has left to deliver*/
    public int numShipmentsPendings() {
        int cont = 0;
        if (shipments == null) return cont;
        else {
            for (Shipment s:
                 shipments) {
                if (s != null && (s.getStatus().equals("En almacen") ||
                                  s.getStatus().equals("En oficina de origen") || s.getStatus().equals("En reparto"))) cont++;
            }
        }
        return cont;
    }

    /*Añade una zona de entrega según el codigo postal introducido por teclado*/
    /*Adds a delivery area according to the postal code entered by keyboard*/
    public void addPostalCodeZone(int newPostalCode){
        deliveryZones.add(newPostalCode);
    }

    /*Actualiza el estado del envio seleccionado, el estado se pasa por teclado, se busca el envío, si lo encuentra cambia el estado
    * y si el estado es "Entregado" le indica la fecha de entrega*/
    /*Updates the status of the selected shipment, the status is passed by keyboard, the shipment is searched, if it is found, the status changes
     * and if the status is "Delivered" it indicates the delivery date*/
    public boolean updateShipmentStatus(String newStatus, int idShipment){
        Shipment shipmentStatus;
        shipmentStatus = searchShipmentById(idShipment);
        if (shipmentStatus == null) return false;
        else {
            shipmentStatus.setStatus(newStatus);
            if (newStatus.equals("Entregado")) shipmentStatus.setDeliveryDate(LocalDate.now());
            return true;
        }
    }

    /*Recorre toda la lista de zonas de entregas y la guarda en una variable String para luego mostrarla por pantalla*/
    /*Go through the entire list of delivery areas and save it in a String variable and then display it on the screen*/
    public String getDeliveryZoneToString(){
        String results = "";
        if (deliveryZones == null) results = "No hay zonas añadidas";
        else {
            for (Integer p:
                 deliveryZones) {
                if (p != null) results += p + ", ";
            }
        }
        return results;
    }

    /*Comprueba si la contraseña introducida es igual a la contraseña que contiene el conductor, de ser asi, retorna true(verdadero)
    Esto lo utilizo yo para que se pueda modificar la contraseña y el email, modo más seguridad, por si se deja la cuenta iniciada sesión y la coge otra persona
    que no le puedan cambiar las creedenciales
     */
    /*Checks if the password entered is equal to the password contained in the driver, if so, returns true
     I use this so that you can modify the password and email, for more security, in case you leave the account logged in and someone else takes it
     that your credentials cannot be changed
      */
    public boolean checkPass(String pass) {
        return this.pass.equals(pass);
    }

    public String resumeForAdmin(){
        return "┌──. ■ .─────────────────────────────────────────────────────────┐\n" +
               "                    Informacion del conductor\n" +
               "└─────────────────────────────────────────────────────────. ■ .──┘\n" +
               "█  Número de referencia del conductor: " + id + "\n" +
               "█  Nombre: " + name + "\n" +
               "█  Email: " + email + "\n" +
               "█  Zonas de entrega asignadas: " + (getDeliveryZoneToString().isEmpty() ? "No hay zonas añadidas" : getDeliveryZoneToString()) + "\n" +
               "█  Envíos pendientes de entrega: " + numShipmentsPendings() + "\n" +
               "──────────────────────────────────────────────────────────. ■ .──";
    }

    public String showProfile(){
        return "┌──. ■ .─────────────────────────────────────────────────────────┐\n" +
               "                    Informacion del perfil\n" +
               "└─────────────────────────────────────────────────────────. ■ .──┘\n" +
               "█  Número de referencia del conductor: " + id + "\n" +
               "█  Nombre: " + name + "\n" +
               "█  Email: " + email + "\n" +
               "█  Zonas de entrega asignadas: " + (getDeliveryZoneToString().isEmpty() ? "No hay zonas añadidas" : getDeliveryZoneToString()) + "\n" +
               "█  Paquetes asignados: " + getShipments().size() + "\n" +
               "──────────────────────────────────────────────────────────. ■ .──";
    }


    public String resumeMock() {
        return "┌──. ■ .─────────────────────────────────────────────────────────┐\n" +
               "                    CONDUCTOR DE PRUEBA\n" +
               "█  Nombre: " + name + "\n" +
               "█  Email: " + email + "\n" +
               "█  Contraseña: " + pass + "\n" +
               "█  Zonas de entrega asignadas: " + (getDeliveryZoneToString().isEmpty() ? "No hay zonas añadidas" : getDeliveryZoneToString()) + "\n" +
               "█  Paquetes asignados: " + getShipments().size() + "\n" +
               "──────────────────────────────────────────────────────────. ■ .──";
    }
}

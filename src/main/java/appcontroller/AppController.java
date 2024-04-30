package appcontroller;

import comunication.MensajeTelegram;
import config.Config;
import dataclass.InfoShipmentDataClass;
import models.Admin;
import models.Driver;
import models.Shipment;
import models.User;
import persistence.PersistenceDisk;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class AppController implements Serializable {

    private ArrayList<User> users; //Guarda los usuarios clientes
    private ArrayList<Driver> drivers; //Guarda los usuarios conductores
    private ArrayList<Admin> admins; //Guarda los usuarios admins
    private ArrayList<Shipment> shipmentsToAssign; //Guarda los paquetes que falta por asignar a un conductor
    private ArrayList<Shipment> shipmentsToNoRegisterUsers; //Guarda los paquetes que no tienen usuario



    //CONSTRUCTOR

    public AppController() {
        if (PersistenceDisk.existsData()) {
            users = PersistenceDisk.readUsersDisk();
            drivers = PersistenceDisk.readDriversDisk();
            admins = PersistenceDisk.readAdminsDisk();
            shipmentsToAssign = PersistenceDisk.readPackageDisk();
            shipmentsToNoRegisterUsers = new ArrayList<>();
        } else {
            users = new ArrayList<>();
            drivers = new ArrayList<>();
            admins = new ArrayList<>();
            shipmentsToAssign = new ArrayList<>();
            shipmentsToNoRegisterUsers = new ArrayList<>();
        }
    }


    //GETTERS AND SETTERS


    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public ArrayList<Shipment> getShipmentsToAssign() {
        return shipmentsToAssign;
    }

    public void setShipmentsToAssign(ArrayList<Shipment> shipmentsToAssign) {
        this.shipmentsToAssign = shipmentsToAssign;
    }

    public ArrayList<Shipment> getShipmentsToNoRegisterUsers() {
        return shipmentsToNoRegisterUsers;
    }

    public void setShipmentsToNoRegisterUsers(ArrayList<Shipment> shipmentsToNoRegisterUsers) {
        this.shipmentsToNoRegisterUsers = shipmentsToNoRegisterUsers;
    }

    //METODOS


    /* Método que busca al usuario según el id que le pasen por teclado, si lo encuentra, devuelve al usuario
       Method that searches for the user according to the id that is passed to them on the keyboard, if it is found, it returns the user
     */
    public User searchUserById(int id) {
        for (User u :
                users) {
            if (u != null && u.getId() == id) return u;
        }
        return null;
    }


    /* Método que busca al usuario según el id que le pasen por teclado, si lo encuentra, devuelve al usuario
       Method that searches for the user according to the email that is passed to them on the keyboard, if it is found, it returns the user
     */
    public User searchUserByEmail(String email) {
        if (!users.isEmpty()) {
            for (User u :
                    users) {
                if (u != null && u.getEmail().equals(email)) return u;
            }
        }
        return null;
    }

    /*Método que busca un usuario y lo retorna si contiene el envio que le pasan por teclado*/
    /*Method that searches for a user and returns it if it contains the message that is passed through the keyboard*/
    public User searchUserByIdShipment(int idShipment){
        if (users.isEmpty()) return null;
        else {
            for (User u:
                 users) {
                if (u != null && u.containsShipment(idShipment)) return u;
                }
            }
        return null;
    }

    /*Añade un nuevo usuario a la lista del controlador, si la accion se ha realizado correctamente retorna true*/
    /*Adds a new user to the controller list, if the action has been performed correctly it returns true*/
    public boolean addUser(String name, String surname, String email, int phone, String pass, String street, int num, String city,
                           String state, int postalCode, int token) {
        User userAdd = new User(uniqueUserId(), name, surname, email, pass, phone, street, num, city, state, postalCode, token);
        PersistenceDisk.saveUser(userAdd);
        return users.add(userAdd);
    }


    /*Método que sirve para asignar a un nuevo usuario un ID UNICO, si el número random
    asignado es igual a uno ya existente vuelve a crearlo hasta que este sea distinto de los que hay creados*/

    /*Method that is used to assign a UNIQUE ID to a new user, if the random number assigned
    is equal to an existing one, create it again until it is different from the ones that have been created*/

    private int uniqueUserId() {
        int idUnique;
        do {
            idUnique = (int) (Math.random() * 10000);
        } while (searchUserById(idUnique) != null);
        return idUnique;
    }

    /*Método que sirve para buscar en una lista de conductores un conductor usando su ID, si el ID es encontrado el
    método devuelve el conductor, en cambio, si no se encuentra el método devuelve null*/

    /*Method used to search a list of drivers for a driver using its ID, if the ID is found
    the method returns the driver, if not found the method returns null*/

    public Driver searchDriverById(int id) {
        for (Driver d :
                drivers) {
            if (d != null && d.getId() == id) return d;
        }
        return null;
    }


    /*Método que sirve para buscar en una lista de conductores un conductor usando su EMAIL, si el EMAIL es encontrado el
    método devuelve el conductor, en cambio, si no se encuentra el método devuelve null*/

    /*Method used to search a list of drivers for a driver using its EMAIL, if the EMAIL is found
    the method returns the driver, if not found the method returns null*/
    public Driver searchDriverByEmail(String email) {
        for (Driver d :
                drivers) {
            if (d != null && d.getEmail().equals(email)) return d;
        }
        return null;
    }


    /*Método que genera un ID único para cada conductor usando los números aleatorios, si el ID generado ya está siendo utilizado
    se vuelve a generar otro hasta que no coincida con ninguno anterior*/

    /*Method that generates a unique ID for each driver using the random numbers, if the generated ID is already in use,
    a new one is generated until it does not match any previous one*/
    private int uniqueDriverId() {
        int idUnique;
        do {
            idUnique = (int) (Math.random() * 10000);
        } while (searchDriverById(idUnique) != null);
        return idUnique;
    }

    /*Método que crea un nuevo objeto DRIVER con un ID único, nombre, email y contraseña, además lo agrega a
    la coleccion que administra los conductores del sistema*/

    /*Method that creates a new DRIVER object with a unique ID, name, email and password,
    and adds it to the collection that manages the system drivers*/
    public boolean addDriver(String name, String email, String pass) {
        Driver driverAdd = new Driver(uniqueDriverId(), name, pass, email);
        PersistenceDisk.saveDriver(driverAdd);
        return drivers.add(driverAdd);
    }

    public boolean addAdmin(String name, String email, String pass) {
        Admin adminAdd = new Admin(name, pass, email);
        PersistenceDisk.saveAdmin(adminAdd);
        return admins.add(adminAdd);
    }

    /*Método que busca un envío en la colección por su ID, si lo encuentra lo retorna*/
    /*Method that searches for a consignment in the collection by its ID, if found it returns it*/

    public Shipment searchShipmentById(int id) {
        if (shipmentsToAssign != null) {
            for (Shipment s :
                    shipmentsToAssign) {
                if (s != null && s.getId() == id) return s;
            }
        }
        if (!drivers.isEmpty()) {
            for (Driver d :
                    drivers) {
                if (d != null) {
                    for (Shipment s :
                            d.getShipments()) {
                        if (s != null && s.getId() == id) return s;
                    }
                }
            }
        }
        return null;
    }


    /*Método que tiene igual funcionamiento a searchShipmentByID pero con la condición que busca los envíos para usuarios
    que no han iniciado sesión*/

    /*Method that searches for a consignment in the collection by its ID, if found it returns it*/
    public InfoShipmentDataClass searchShipmentByIdNoLogin(int id) {
        InfoShipmentDataClass shipmentNoLogin = null;
        for (Shipment s :
                shipmentsToNoRegisterUsers) {
            if (s != null && s.getId() == id)
                shipmentNoLogin = new InfoShipmentDataClass(s.getId(), s.getCreateDate(), s.getExpectDate(), s.getDeliveryDate(), s.getAlternativePostalCode(), s.getStatus(),
                        s.getIdSender(), s.getNameUserNoRegister(), s.getAlternativeAddress(), s.getAlternativeCity());
        }
        for (User u :
                users) {
            Shipment envio = u.searchDeliveryById(id);
            if (u != null && envio != null)
                shipmentNoLogin = new InfoShipmentDataClass(envio.getId(), envio.getCreateDate(), envio.getExpectDate(), envio.getDeliveryDate(), u.getPostalCode(), envio.getStatus(), envio.getIdSender(), u.getName(), u.getStreet(), u.getCity());
        }
        return shipmentNoLogin;
    }

    /*Método que genera un número aleatorio para el identificar un envío, lo crea utilizando los números aleatorios
    cada número generado pasa por una comprobación para ver que no está siendo utilizado ni por los métodos de
    searchShipmentById ni por el searchShipmentByIdNoLogin*/

    /*Method that generates a random number to identify a shipment, creates it using the random numbers
    Each number generated goes through a check to see that it is not being used by either the
    searchShipmentById or searchShipmentByIdNoLogin*/

    public int uniqueShipmentId() {
        int idUnique;
        do {
            idUnique = (int) (Math.random() * 10000);
        } while (searchShipmentById(idUnique) != null && searchShipmentByIdNoLogin(idUnique) != null);
        return idUnique;
    }


    /*Método para iniciar sesión, si el email y la contraseña introducidos encuentra una cuenta con esas credenciales devuelve el objeto
     * correspondiente, si no lo encuentra en ninguno de los arrays alojados en el controller devuelve null*/

    /* Method to log in, if the email and password entered finds an account with those credentials, it returns the object
  corresponding, if it is not found in any of the arrays hosted in the controller it returns null*/

    public Object login(String email, String pass) {
        for (User u : users) {
            if (u != null && u.login(email, pass)) {
                PersistenceDisk.recordLogin(u, LocalDateTime.now());
                return u;
            }

        }
        for (Driver d : drivers) {
            if (d != null && d.login(email, pass)) {
                PersistenceDisk.recordLogin(d, LocalDateTime.now());
                return d;
            }
        }
        for (Admin a : admins) {
            if (a != null && a.login(email, pass)) {
                PersistenceDisk.recordLogin(a, LocalDateTime.now());
                return a;
            }
        }
        return null;
    }

    /*Método que buca un usuario mediante su teléfono movil, si lo encuentra devuelve el usuario que lo contiene, sino devuelve null*/

    /*Method that searches for a user using their mobile phone, if it finds it, it returns the user that contains it, otherwise it returns null*/

    public User searchUserByPhone(int phone) {
        for (User u :
                users) {
            if (u != null && u.getPhone() == phone) return u;
        }
        return null;
    }

    /*Método que busca el mejor conductor de la zona especificada mediante el código postal*/
    /*Method that searches for the best driver in the area specified by postal code*/

    public Driver searchBestDriverByPostalCode(int postalCode) {
        for (Driver d :
                drivers) {
            if (d != null && d.hasPostalCodeZone(postalCode)) return d;
        }
        return null;
    }

    /*Metemos información tanto de usuarios, conductores como de envíos*/
    /*We enter information about users, drivers and shipments*/
    public void mock() {
        /*Admin admin = new Admin("Maria", "123pipo", "admin@gmail.com");
        admins.add(admin);
        PersistenceDisk.saveAdmin(admin);
        User user = new User(uniqueUserId(), "Maria", "Ordóñez", "maria@gmail.com", "123pipo", 656666005, "Málaga", 11,
                "Torredonjimeno", "Jaén", 23650, 0);
        users.add(user);
        PersistenceDisk.saveUser(user);
        Driver driver = new Driver(uniqueDriverId(), "Miguel", "123pipo", "conductor@gmail.com");
        drivers.add(driver);
        PersistenceDisk.saveDriver(driver);*/
        Shipment shipment = new Shipment(uniqueShipmentId(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
                true, "Malaga", 23650, "asdf", "Entregado", 3, "123@gmail.com", 6528, "Pepito");
        shipmentsToAssign.add(shipment);
        PersistenceDisk.savePackageUnassigned(shipment);
    }

    /*Método que devuelve un ArrayList del dataclass con información de los paquetes que faltan por asignar
     * este método lo utiliza solo el admin del programa para poder asignar paquetes a conductores*/
    /*Method that returns an ArrayList of the dataclass with information about the packages that remain to be assigned
     * this method is used only by the program admin to be able to assign packages to drivers*/

    public ArrayList<InfoShipmentDataClass> getShipmentsUnassigned() {
        ArrayList<InfoShipmentDataClass> results = new ArrayList<>();
        if (!shipmentsToAssign.isEmpty()) {
            for (Shipment s :
                    shipmentsToAssign) {
                if (s != null)
                    results.add(new InfoShipmentDataClass(s.getId(), s.getCreateDate(), s.getExpectDate(), s.getDeliveryDate(), s.getAlternativePostalCode(), s.getStatus(),
                            s.getIdSender(), s.getNameUserNoRegister(), s.getAlternativeAddress(), s.getAlternativeCity()));
            }

        }
        Collections.sort(results);
        Collections.reverse(results);
        return results;
    }

    /*Devuelve un array con toda la informacion recogida de los envios del usuario
    Si no tiene nada, devuelve null
    Hace una comprobación en el main, si es null y si no lo es*/
    public ArrayList<InfoShipmentDataClass> getShipmentFromUser(int idUser) {
        ArrayList<InfoShipmentDataClass> shipmentUsers = new ArrayList<>();
        User userFind = searchUserById(idUser);
        for (Shipment s :
                userFind.getShipments()) {
            if (s != null)
                shipmentUsers.add(new InfoShipmentDataClass(s.getId(), s.getCreateDate(), s.getExpectDate(), s.getDeliveryDate(), s.getAlternativePostalCode(), s.getStatus(),
                        s.getIdSender(), s.getNameUserNoRegister(), s.getAlternativeAddress(), s.getAlternativeCity()));
        }
        Collections.sort(shipmentUsers);
        return shipmentUsers;
    }


    /*Método que busca los envíos pendientes de un usuario específico, obtiene la información de cada envío pendiente, crea
    objetos InfoShipmentDataClass para representar los envíos con su información y devuelve una lista de estos objetos*/

    /*Method that searches for pending shipments for a specific user, obtains the information of each pending shipment,
    creates InfoShipmentDataClass objects to represent the shipments with their information and returns a list of these objects*/
    public ArrayList<InfoShipmentDataClass> getShipmentPendingsToUser(int idUser) {
        ArrayList<InfoShipmentDataClass> shipmentsPendings = new ArrayList<>();
        User userFind = searchUserById(idUser);
        if (userFind != null) {
            ArrayList<Shipment> results = userFind.shipmentsPendingToDelivery();
            if (results.isEmpty()) return shipmentsPendings;
            else {
                for (Shipment s :
                        results) {
                    if (s != null)
                        shipmentsPendings.add(new InfoShipmentDataClass(s.getId(), s.getCreateDate(), s.getExpectDate(), s.getDeliveryDate(), s.getAlternativePostalCode(),
                                s.getStatus(), s.getIdSender(), s.getNameUserNoRegister(), s.getAlternativeAddress(), s.getAlternativeCity()));
                }
            }
        }
        Collections.sort(shipmentsPendings);
        return shipmentsPendings;
    }

    /*Método que busca los envíos pendientes de un conductor específico, obtiene la información de cada envío pendiente, crea
    objetos InfoShipmentDataClass para representar los envíos con su información y devuelve una lista de estos objetos*/

    /*Method that searches for pending shipments for a specific driver, obtains the information of each pending shipment,
    creates InfoShipmentDataClass objects to represent the shipments with their information and returns a list of these objects*/
    public ArrayList<InfoShipmentDataClass> getShipmentsPendingsDriver(int idDriver) {
        ArrayList<InfoShipmentDataClass> results = new ArrayList<>();
        Driver driver = searchDriverById(idDriver);
        for (Shipment s:
             driver.getShipments()) {
            if (s != null && (s.getStatus().equals("En oficina de origen") ||
                                 s.getStatus().equals("En almacen") || s.getStatus().equals("En reparto")))
                results.add(new InfoShipmentDataClass(s.getId(), s.getCreateDate(), s.getExpectDate(), s.getDeliveryDate(), s.getAlternativePostalCode(), s.getStatus(),
                        s.getIdSender(), s.getNameUserNoRegister(), s.getAlternativeAddress(), s.getAlternativeCity()));
        }

        return results;
    }

    /*Método que indica la media de días que tardan los conductores en entregar los paquetes*/
    /*Method that indicates the average number of days it takes drivers to deliver packages*/

    public double numDaysToDeliver() {
        int contDelivery = 0;
        double results = 0, contDays = 0;
        if (drivers.isEmpty()) return 2;
        else {
            for (Driver d :
                    drivers) {
                if (d != null) {
                    for (Shipment s :
                            d.getShipments()) {
                        if (s != null && s.getStatus().equals("Entregado")) {
                            contDelivery++;
                            contDays += s.getCreateDate().until(s.getDeliveryDate(), ChronoUnit.DAYS);
                        }
                    }
                }
                if (contDelivery > 0) results = contDays / contDelivery;
            }
        }
        return results;
    }

    /*Añade un envío si este no está asociado a ningun usuario, se añade a la lista de envios sin usuario del controlador, retorna el envio*/
    /*Adds a shipment if it is not associated with any user, it is added to the list of shipments without a controller user, returns the shipment*/
    public Shipment addShipmentToNoRegisterUser(String status, int idUser, String email, int postalCode, String name, boolean notifications, String address, String city) throws IOException {
        Shipment shipmentCreate = new Shipment(uniqueShipmentId(), LocalDate.now(), LocalDate.now().plusDays(2), null, notifications, address, postalCode,
                city, status, calculatedCost(postalCode), email, idUser, name);
        shipmentsToNoRegisterUsers.add(shipmentCreate);
        User sender = searchUserById(idUser);
        sender.addShipment(shipmentCreate);
        addShipmentBestDriver(shipmentCreate);
        PersistenceDisk.recordShipment(-1, idUser, LocalDateTime.now());
        return shipmentCreate;
    }

    /*Calcula el coste a pagar por el remitente sobre el paquete que quiere enviar, si el codigo postal está registrado en algun conductor
    el precio es más barato que si no hay ningun conductor que pueda enviarlo
     */
    /*Calculates the cost to be paid by the sender for the package he wants to send, if the postal code is registered in a driver
     the price is cheaper than if there is no driver who can send it
      */
    private double calculatedCost(int postalCode) {
        double costTotal = 0;
        if (drivers.isEmpty()) return 0;
        else {
            for (Driver d:
                 drivers) {
                if (d != null && d.hasPostalCodeZone(postalCode)) costTotal += 3.53;
                else costTotal += 10.20;
            }
        }
        return costTotal;
    }

    /*Cambia la informacion de un envio seleccionado, cambia toda la direccion*/
    /*Change the information of a selected shipment, change the entire address*/
    public boolean changeDeliveryData(int idShipment, String address, int postalCode, String city) {
        Shipment shipmentChange;
        shipmentChange = searchShipmentById(idShipment);
        if (shipmentChange == null) return false;
        else {
            shipmentChange.setAlternativeAddress(address);
            shipmentChange.setAlternativeCity(city);
            shipmentChange.setAlternativePostalCode(postalCode);
            return true;
        }
    }

    /*Obtiene todos los envios que ha entregado el conductor que se indica por teclado*/
    /*Obtains all the shipments that the driver indicated on the keyboard has delivered*/
    public ArrayList<InfoShipmentDataClass> getShipmentsFinishedDriver(int id) {
        ArrayList<InfoShipmentDataClass> results = new ArrayList<>();
        Driver driverUse = searchDriverById(id);
        if (driverUse != null) {
            for (Shipment s :
                    driverUse.getShipments()) {
                if (s != null && s.getStatus().equals("Entregado"))
                    results.add(new InfoShipmentDataClass(s.getId(), s.getCreateDate(), s.getExpectDate(), s.getDeliveryDate(),
                            s.getAlternativePostalCode(), s.getStatus(), s.getIdSender(), s.getNameUserNoRegister(), s.getAlternativeAddress(), s.getAlternativeCity()));
            }
        }
        Collections.sort(results);
        return results;
    }

    /*Cambia el estado del envio al indicado por teclado y al envio seleccionado*/
    /*Change the status of the shipment to the one indicated by keyboard and to the selected shipment*/
    public void changeDeliveryStatus(String newStatus, int id) {
        Shipment shipmentSelect = searchShipmentById(id);
        if (shipmentSelect != null) {
            shipmentSelect.setStatus(newStatus);
            shipmentSelect.setDeliveryDate(LocalDate.now());
        }
    }

    /*Añade un envio y comprueba si el usuario remitente y el
    destinatario existen en la plataforma, para guardarlo dentro de la informacion de cada uno*/
    /*Add a shipment and check if the sender and recipient users exist on the platform, to save it within each one's information*/

    public Shipment addShipment(int idSender, int idReciever, boolean notifications) throws IOException {
        User reciever = searchUserById(idReciever);
        Shipment shipmentCreate = new Shipment(uniqueShipmentId(), LocalDate.now(), LocalDate.now().plusDays(2), null, notifications,
                reciever.getStreet() + " " + reciever.getNum(), reciever.getPostalCode(), reciever.getCity(),
                "En oficina de origen", calculatedCost(reciever.getPostalCode()), reciever.getEmail(), idSender, reciever.getName());
        reciever.addShipment(shipmentCreate);
        addShipmentBestDriver(shipmentCreate);
        PersistenceDisk.saveUser(reciever);
        //Guardo la informacion del nuevo envio en el archivo log
        PersistenceDisk.recordShipment(idReciever, idSender, LocalDateTime.now());
        return shipmentCreate;
    }

    /*Añade el paquete al mejor conductor encontrado según el
    codigo postal que contienen, si no encuentra ninguno lo manda a la lista de envios sin asignar*/
    /*Adds the package to the best driver found according to the postal code they contain,
    if none is found, it is sent to the list of unassigned shipments*/

    public void addShipmentBestDriver(Shipment s) throws IOException {
        Driver driverBest = searchBestDriverByPostalCode(s.getAlternativePostalCode());
        if (driverBest == null) {
            shipmentsToAssign.add(s);
        } else {
            driverBest.addShipment(s);
            PersistenceDisk.saveDriver(driverBest);
            MensajeTelegram.enviaMensajeTelegram(MensajeTelegram.mensajePredeterminado(s.getId(), s.getStatus(), driverBest.getName(), s.getExpectDate()));
        }
    }

    /*Añade una zona de entrega al conductor que se elija por teclado*/
    /*Adds a delivery area to the driver chosen by keyboard*/
    public boolean addZoneToDriver(int idDriver, int newPostalCode){
        Driver driverFind = searchDriverById(idDriver);
        if (driverFind != null) {
            driverFind.addPostalCodeZone(newPostalCode);
            return true;
        }
        return false;
    }

    public boolean setShipmentToDriver(int idShipment, int idDriver){
        Driver driverFind = searchDriverById(idDriver);
        if (driverFind != null) {
            if (!shipmentsToAssign.isEmpty()) {
                for (Shipment s :
                        shipmentsToAssign) {
                    if (s != null && s.getId() == idShipment) driverFind.addShipment(s, s.getAlternativePostalCode());
                }
            }
        }
        return false;
    }

    /*Elimina un envio de la lista de envios sin asignar, esto indica que el envio ya ha sido asignado a un conductor*/
    /*Remove a shipment from the list of unassigned shipments, this indicates that the shipment has already been assigned to a driver*/
    public void deleteShipmentUnassigned(int idShipment){
        int pos = 0;
        if (!shipmentsToAssign.isEmpty()){
            for (int i = 0; i < shipmentsToAssign.size(); i++) {
                if (shipmentsToAssign.get(i).getId() == idShipment) pos = i;
            }
            shipmentsToAssign.remove(pos);
            PersistenceDisk.deletePackage(idShipment);
        }
    }
    public int getNumShipmentsMadeByUser(int idUser){
        int cont = 0;
        User userFind = searchUserById(idUser);
        if (userFind != null){
            for (Shipment s:
                 userFind.getShipments()) {
                if (s != null && s.getIdSender() == userFind.getId()) cont++;
            }
        }
        return cont;
    }

    //METODOS AÑADIDOS POR MI

    public void findShipmentCreateUser(String email){
        User userFind = searchUserByEmail(email);
        if (userFind != null){
            for (Shipment s:
                 shipmentsToNoRegisterUsers) {
                if (s != null && s.getEmailUserNoRegister().equals(email)) userFind.addShipment(s);
            }
        }
    }


    /*Método que nos muestra cuantos usuarios están registrados o almacenados en el sistema*/

    /*Method that shows how many users are registered or stored in the system.*/
    public int numUsers() {
        if (users == null) return 0;
        return users.size();
    }

    /*Método que nos muestra cuantos conductores están registrados o almacenados en el sistema*/

    /*Method that shows how many drivers are registered or stored in the system.*/
    public int numDrivers() {
        if (drivers == null) return 0;
        return drivers.size();
    }

    /*Método que nos muestra el numero de envios pendientes
    Method that shows us the number of pending shipments*/
    public int numShipmentsPendings() {
        int cont = 0;
        for (Shipment s :
                shipmentsToNoRegisterUsers) {
            if (s != null && (s.getStatus().equals("En oficina de origen") ||
                              s.getStatus().equals("En almacen") || s.getStatus().equals("En reparto"))) cont++;
        }
        for (User u :
                users) {
            if (u != null) {
                for (Shipment r :
                        u.getShipments()) {
                    if (r != null && (r.getStatus().equals("En oficina de origen") ||
                                      r.getStatus().equals("En almacen") || r.getStatus().equals("En reparto"))) cont++;
                }
            }
        }
        return cont;
    }


    /*Método que nos muestra el numero de envios por asignar
    Method showing the number of consignments to be assigned*/
    public int numShipmentsToAssing() {
        if (shipmentsToAssign.isEmpty()) return 0;
        return shipmentsToAssign.size();
    }


    /*Método que nos muestra cuantos envios están actualmente asociados a usuarios no registrados
    Method that shows us how many shipments are currently associated with unregistered users*/
    public int numShipmentsToNoUserRegister() {
        if (shipmentsToNoRegisterUsers.isEmpty()) return 0;
        return shipmentsToNoRegisterUsers.size();
    }

    public boolean addShipmentDriver(int idShipment, int idDriver) throws IOException {
        Driver d = searchDriverById(idDriver);
        Shipment s = searchShipmentById(idShipment);
        if (d != null && s != null) {
            d.addShipment(s);
            deleteShipmentUnassigned(idShipment);
            MensajeTelegram.enviaMensajeTelegram(MensajeTelegram.mensajePredeterminado(s.getId(), s.getStatus(), d.getName(), s.getExpectDate()));
            return true;
        }
        return false;
    }

    public void closeLogin(Object user) {
        if (user instanceof User) {
            PersistenceDisk.closeRegister(((User) user).getId(), ((User) user).getName(), "usuario", LocalDateTime.now());
            Config.updateLastLogin(String.valueOf(((User) user).getId()), LocalDateTime.now());
        }
        if (user instanceof Driver) {
            PersistenceDisk.closeRegister(((Driver) user).getId(), ((Driver) user).getName(), "conductor", LocalDateTime.now());
            Config.updateLastLogin(String.valueOf(((Driver) user).getId()), LocalDateTime.now());
        }
        if (user instanceof Admin) {
            PersistenceDisk.closeRegister(((Admin) user).getId(), ((Admin) user).getName(), "admin", LocalDateTime.now());
            Config.updateLastLogin(String.valueOf(((Admin) user).getId()), LocalDateTime.now());
        }
    }

    public Admin searchAdminByEmail(String emailAdmin) {
        for (Admin a :
                admins) {
            if (a != null && a.getEmail().equals(emailAdmin)) return a;
        }
        return null;
    }

    public void recordLogin(User userUse, LocalDateTime fecha) {
        PersistenceDisk.recordLogin(userUse, fecha);
    }

    public String getLastLogin(Object user) {
        String id = "";
        if (user instanceof User) id = String.valueOf(((User) user).getId());
        if (user instanceof Driver) id = String.valueOf(((Driver) user).getId());
        if (user instanceof Admin) id = String.valueOf(((Admin) user).getId());
        String date = Config.getLastLogin(id);
        return date;
    }
}

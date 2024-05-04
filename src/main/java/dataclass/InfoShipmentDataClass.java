package dataclass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InfoShipmentDataClass implements Comparable<InfoShipmentDataClass> {
    private int id; //Codigo que crearemos nosotros
    private LocalDate createDate; //Fecha de creacion
    private LocalDate expectDate; //Fecha estimada de entrega
    private LocalDate deliveryDate; //Fecha de envio
    private int postalCode; //Codigo postal de la ciudad a la que va a ser enviado
    private String status; //Estado del paquete, si esta en reparto, entregado, en almacen, en oficina
    private String sender; //Remitente
    private String receiver; //Persona que recibe el paquete
    private String address; //Direccion de envio
    private String city; //Ciudad a la que va dirigido el paquete
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //CONSTRUCTOR

    public InfoShipmentDataClass(int id, LocalDate createDate, LocalDate expectDate, LocalDate deliveryDate,
                                 int postalCode, String status, String sender, String receiver, String address, String city) {
        this.id = id;
        this.createDate = createDate;
        this.expectDate = expectDate;
        this.deliveryDate = deliveryDate;
        this.postalCode = postalCode;
        this.status = status;
        this.sender = sender;
        this.receiver = receiver;
        this.address = address;
        this.city = city;
    }


    //GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(LocalDate expectDate) {
        this.expectDate = expectDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    //MÉTODOS


    @Override
    public String toString() {
        return "InfoShipmentDataClass{" +
               "id=" + id +
               ", createDate=" + createDate +
               ", expectDate=" + expectDate +
               ", deliveryDate=" + deliveryDate +
               ", postalCode=" + postalCode +
               ", status='" + status + '\'' +
               ", sender='" + sender + '\'' +
               ", receiver='" + receiver + '\'' +
               ", address='" + address + '\'' +
               ", city='" + city + '\'' +
               '}';
    }


    public String forTracking() {
        String dateExpect = expectDate.format(formatter);
        return String.format(
                "┌──. ■ .──────────────────────────┐\n" +
                "          Envío nº %d\n" +
                "└──────────────────────────. ■ .──┘\n" +
                "█  Fecha aproximada de entrega: %s\n" +
                "█  Dirección de envío: %s, %s, %d\n" +
                "█  Estado actual del envío: %s\n" +
                "█  Destinatario: %s\n" +
                "──────────────────────────────────────────. ■ .──", id, dateExpect,
                address, city, postalCode, status, receiver);
    }
    public String forAdminUnassigned(){
        String dateCreate = createDate.format(formatter);
        String dateExpect = expectDate.format(formatter);
        String dateDelivery = (deliveryDate != null) ? deliveryDate.format(formatter) : "";
        return String.format(
                            "┌──. ■ .──────────────────────────┐\n" +
                            "    Envío nº referencia %d\n" +
                            "└──────────────────────────. ■ .──┘\n" +
                            "█    Fecha de expedición: %s\n" +
                            "█    Fecha aproximada de entrega: %s\n" +
                            "█    Fecha real de entrega: %s\n" +
                            "█    Dirección de envío: %s, %s, %d\n" +
                            "█    Estado actual del envío: %s\n" +
                            "█    Remitente: %s\n" +
                            "█    Destinatario: %s\n" +
                            "─────────────────────────────────────────────────. ■ .──", id, dateCreate,
                dateExpect, (deliveryDate != null) ? dateDelivery : "Todavia no se ha entregado",
                address, city, postalCode, status, sender, receiver);
    }

    public String forReciever(){
        String dateCreate = createDate.format(formatter);
        String dateExpect = expectDate.format(formatter);
        return String.format(
                "┌──. ■ .──────────────────────────┐\n" +
                "          Envío nº %d\n" +
                "└──────────────────────────. ■ .──┘\n" +
                "█    Fecha de expedición: %s\n" +
                "█    Fecha aproximada de entrega: %s\n" +
                "█    Estado actual del envío: %s\n" +
                "█    Dirección de envío: %s, %s, %d\n" +
                "█    Remitente: %s\n" +
                "█    Destinatario: %s\n" +
                "─────────────────────────────────────────────. ■ .──", id, dateCreate,
                dateExpect, status, address, city, postalCode, sender, receiver);

    }

    public String forSender() {
        String dateCreate = createDate.format(formatter);
        String dateExpect = expectDate.format(formatter);
        return String.format(
                "┌──. ■ .──────────────────────────┐\n" +
                "          Envío nº %d\n" +
                "└──────────────────────────. ■ .──┘\n" +
                "█    Fecha de expedición: %s\n" +
                "█    Fecha aproximada de entrega: %s\n" +
                "█    Estado actual del envío: %s\n" +
                "█    Dirección de envío: %s, %s, %d\n" +
                "█    Remitente: %s\n" +
                "█    Destinatario: %s\n" +
                "─────────────────────────────────────────────. ■ .──", id, dateCreate, dateExpect, status, address, city, postalCode, sender, receiver);
    }

    public String forDriverFinished() {
        String dateDelivery = deliveryDate.format(formatter);
        String dateCreate = createDate.format(formatter);
        return String.format(
                "┌──. ■ .──────────────────────────┐\n" +
                "          Envío nº %d \n" +
                "└──────────────────────────. ■ .──┘\n" +
                "█    Remitente: %s\n" +
                "█    Destinatario: %s\n" +
                "█    Fecha de expedición: %s\n" +
                "█    Fecha de entrega: %s\n" +
                "█    Dirección de envío: %s, %s, %d\n" +
                "█    Estado actual del envío: %s\n" +
                "─────────────────────────────────────────────. ■ .──", id, sender, receiver, dateCreate, dateDelivery, address, city, postalCode, status);
    }

    public String forDriverPending() {
        String dateCreate = createDate.format(formatter);
        String dateExpect = expectDate.format(formatter);
        return String.format(
                "┌──. ■ .──────────────────────────┐\n" +
                "          Envío nº %d\n" +
                "└──────────────────────────. ■ .──┘\n" +
                "█    Fecha de expedición: %s\n" +
                "█    Fecha de entrega límite: %s\n" +
                "█    Dirección de envío: %s, %s, %d\n" +
                "█    Destinatario: %s\n" +
                "─────────────────────────────────────────────. ■ .──", id, dateCreate,
                dateExpect, address, city, postalCode, receiver);
    }


    @Override
    public int compareTo(InfoShipmentDataClass o) {
        if (this.createDate.isBefore(o.getCreateDate())) {
            return 1;
        }
        if (this.createDate.isAfter(o.getCreateDate())) {
            return -1;
        }
        return 0;
    }
}

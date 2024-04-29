package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Shipment implements Serializable {

    //ATRIBUTOS
    private int id; //Código que identifica al paquete
    private LocalDate createDate; //Fecha en la que se ha creado el paquete
    private LocalDate expectDate; //Fecha estimada de entrega
    private LocalDate deliveryDate; //Fecha en la que se envió el paquete
    private boolean notifications;
    private String alternativeAddress; //Direccion alternativa por si el usuario no está registrado
    private int alternativePostalCode; //Codigo postal para los usuarios no registrados
    private String alternativeCity; //Ciudad para los usuarios no registrados
    private String status; //Estado del paquete, si está entregado o no
    private double cost; //Coste del envio
    private String emailUserNoRegister; //Email del usuario no registrado
    private int idSender; //Código de la persona que envia el paquete
    private String nameUserNoRegister; //Nombre del usuario no registrado

    //CONSTRUCTOR

    public Shipment(int id, LocalDate createDate, LocalDate expectDate, LocalDate deliveryDate, boolean notifications, String alternativeAddress,
                    int alternativePostalCode, String alternativeCity, String status, double cost, String emailUserNoRegister, int idSender, String nameUserNoRegister) {
        this.id = id;
        this.createDate = createDate;
        this.expectDate = expectDate;
        this.deliveryDate = deliveryDate;
        this.notifications = notifications;
        this.alternativeAddress = alternativeAddress;
        this.alternativePostalCode = alternativePostalCode;
        this.alternativeCity = alternativeCity;
        this.status = status;
        this.cost = cost;
        this.emailUserNoRegister = emailUserNoRegister;
        this.idSender = idSender;
        this.nameUserNoRegister = nameUserNoRegister;
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

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public String getAlternativeAddress() {
        return alternativeAddress;
    }

    public void setAlternativeAddress(String alternativeAddress) {
        this.alternativeAddress = alternativeAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getEmailUserNoRegister() {
        return emailUserNoRegister;
    }

    public void setEmailUserNoRegister(String emailUserNoRegister) {
        this.emailUserNoRegister = emailUserNoRegister;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public String getNameUserNoRegister() {
        return nameUserNoRegister;
    }

    public void setNameUserNoRegister(String nameUserNoRegister) {
        this.nameUserNoRegister = nameUserNoRegister;
    }

    public int getAlternativePostalCode() {
        return alternativePostalCode;
    }

    public void setAlternativePostalCode(int alternativePostalCode) {
        this.alternativePostalCode = alternativePostalCode;
    }

    public String getAlternativeCity() {
        return alternativeCity;
    }

    public void setAlternativeCity(String alternativeCity) {
        this.alternativeCity = alternativeCity;
    }

    //MÉTODOS


    @Override
    public String toString() {
        return "Shipment{" +
               "id=" + id +
               ", createDate=" + createDate +
               ", expectDate=" + expectDate +
               ", deliveryDate=" + deliveryDate +
               ", notifications=" + notifications +
               ", alternativeAddress='" + alternativeAddress + '\'' +
               ", status='" + status + '\'' +
               ", cost=" + cost +
               ", emailUserNoRegister='" + emailUserNoRegister + '\'' +
               ", idSender='" + idSender + '\'' +
               ", nameUserNoRegister='" + nameUserNoRegister + '\'' +
               '}';
    }

    public String resume(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateExpect = expectDate.format(formatter);
        String resultado = "";
        resultado += "──. ■ .───────────────────────────────────────────\n" +
        "█ Número de id del paquete: " + id + "\n"
        + "█ Estado: " + status + "\n" +
        "█ Fecha estimada de entrega: " + dateExpect + "\n" +
        "█ Será entregado en: " + alternativeAddress + " " + alternativeCity + "(" + alternativePostalCode + ")" + "\n" +
        "█ Destinatario: " + nameUserNoRegister + "\n" +
        "█ Cantidad a pagar: " + cost + "\n" +
        "───────────────────────────────────────────. ■ .──";
        return resultado;
    }
    public String resumeToNoLogin(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateExpect = expectDate.format(formatter);
        String resultado = "";
        resultado += "──. ■ .───────────────────────────────────────────\n" +
                     "█ Estado: " + status + "\n" +
                     "█ Fecha estimada de entrega: " + dateExpect + "\n" +
                     "█ Será entregado en: " + alternativeAddress + " " + alternativeCity + "(" + alternativePostalCode + ")" + "\n" +
                     "───────────────────────────────────────────. ■ .──";
        return resultado;
    }
    public String resumeForDriver(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateExpect = expectDate.format(formatter);
        String resultado = "";
        resultado += "──. ■ .───────────────────────────────────────────\n" + "█ Número de id del paquete: " + id + "\n"
                     + "█ Estado: " + status + "\n" +
                     "█ Fecha de creación: " + createDate.format(formatter) + "\n" + "█ Fecha estimada de entrega: " + dateExpect + "\n" +
                     "█ Será entregado en: " + alternativeAddress + " " + alternativeCity + "(" + alternativePostalCode + ")" + "\n" +
                     "█ Destinatario: " + nameUserNoRegister + "\n" +
                     "───────────────────────────────────────────. ■ .──";
        return resultado;
    }


    public String resumeMock() {
        return "──. ■ .───────────────────────────────────────────\n" +
               "          PAQUETE DE PRUEBA\n" +
               "█ Estado: " + status + "\n" +
               "█ ID del paquete: " + id + "\n" +
               "─────────────────────────────────────────. ■ .────\n";
    }
}

package persistence;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import config.Config;
import models.Admin;
import models.Driver;
import models.Shipment;
import models.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import utils.Utils;

import java.io.*;
import java.util.ArrayList;

public class PersistenceData {

    public static void recordUsers(ArrayList<User> users) {
        for (User u:
             users) {
            if (u != null) {
                FileOutputStream fos;
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

    public static String recordPdf(Shipment shipment, User user) {
        String nameFile = shipment.getId() + "" + user.getId() + ".pdf";
        try {
            File file = new File(Config.getPathFile() + "/" + nameFile);
            PdfWriter pdfWriter = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            Paragraph paragraph = new Paragraph(shipment.resume());
            document.add(paragraph);
            document.close();
            pdfWriter.close();
            return nameFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean recordExcel(ArrayList<Shipment> shipments) {
        String filePath = Config.getPathFile() + "/listadoEnvios.xls";
        Workbook workbook = new HSSFWorkbook();

        // Crear una hoja en el libro de trabajo
        Sheet sheet = workbook.createSheet("Envíos");

        // Crear algunas filas y celdas
        int rowNum = 0;
        for (Shipment shipment : shipments) {
            if (shipment != null) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(shipment.getId());
                row.createCell(1).setCellValue(Utils.fechaAString(shipment.getCreateDate()));
                row.createCell(2).setCellValue(Utils.fechaAString(shipment.getExpectDate()));
                row.createCell(3).setCellValue(Utils.fechaAString(shipment.getDeliveryDate()));
                row.createCell(4).setCellValue(shipment.getAlternativeAddress());
                row.createCell(5).setCellValue(shipment.getAlternativeCity());
                row.createCell(6).setCellValue(shipment.getAlternativePostalCode());
                row.createCell(7).setCellValue(shipment.getStatus());
                row.createCell(8).setCellValue(shipment.getCost());
                row.createCell(9).setCellValue(shipment.getEmailUserNoRegister());
                row.createCell(10).setCellValue(shipment.getIdSender());
                row.createCell(11).setCellValue(shipment.getNameUserNoRegister());
            }
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(filePath);
            // Escribir el libro de trabajo en un archivo
            workbook.write(fos);
            workbook.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public static void recordDrivers(ArrayList<Driver> drivers) {
        for (Driver d:
                drivers) {
            if (d != null) {
                FileOutputStream fos;
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
                FileOutputStream fos;
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


    public static void recordShipmentToAssing(ArrayList<Shipment> shipmentsToAssign) {
        for (Shipment s:
                shipmentsToAssign) {
            if (s != null) {
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(Config.getPathPackageUnassigned() + "/" + s.getId() + ".dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(s);
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void recordShipmentToNoRegisterUser(ArrayList<Shipment> shipmentsToNoRegisterUsers) {
            for (Shipment s:
                    shipmentsToNoRegisterUsers) {
                if (s != null) {
                    FileOutputStream fos;
                    try {
                        fos = new FileOutputStream(Config.getPathPackageNoRegisterUser() + "/" + s.getId() + ".dat");
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

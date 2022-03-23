package me.parzibyte.escribirexcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author parzibyte
 */
public class EscribirExcel {

    public static void main(String[] args) {
        Workbook libro = new XSSFWorkbook();
        final String nombreArchivo = "Mi archivo creado con Java.xlsx";
        Sheet hoja = libro.createSheet("Hoja 1");
        Row primeraFila = hoja.createRow(0);
        Cell primeraCelda = primeraFila.createCell(0);
        primeraCelda.setCellValue("Yo voy en la primera celda y primera fila");
        File directorioActual = new File(".");
        String ubicacion = directorioActual.getAbsolutePath();
        String ubicacionArchivoSalida = ubicacion.substring(0, ubicacion.length() - 1) + nombreArchivo;
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(ubicacionArchivoSalida);
            libro.write(outputStream);
            libro.close();
            System.out.println("Libro guardado correctamente");
        } catch (FileNotFoundException ex) {
            System.out.println("Error de filenotfound");
        } catch (IOException ex) {
            System.out.println("Error de IOException");
        }
    }
}

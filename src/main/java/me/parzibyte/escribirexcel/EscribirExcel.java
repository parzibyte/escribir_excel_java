package me.parzibyte.escribirexcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Persona {

    private String nombre, web;
    private int edad;

    public Persona(String nombre, String web, int edad) {
        this.nombre = nombre;
        this.web = web;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}

/**
 *
 * @author parzibyte
 */
public class EscribirExcel {

    public static void crearAPartirDeArrayList() {

        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Luis", "https://parzibyte.me", 50));
        personas.add(new Persona("Rasmus Lerdorf", "https://toys.lerdorf.com/", 53));
        personas.add(new Persona("Brian Kernighan", "https://www.cs.princeton.edu/~bwk/", 80));
        Workbook libro = new XSSFWorkbook();
        final String nombreArchivo = "Personas.xlsx";
        Sheet hoja = libro.createSheet("Hoja 1");

        String[] encabezados = {"Nombre", "Web", "Edad"};
        int indiceFila = 0;

        Row fila = hoja.createRow(indiceFila);
        for (int i = 0; i < encabezados.length; i++) {
            String encabezado = encabezados[i];
            Cell celda = fila.createCell(i);
            celda.setCellValue(encabezado);
        }

        indiceFila++;
        for (int i = 0; i < personas.size(); i++) {
            fila = hoja.createRow(indiceFila);
            Persona persona = personas.get(i);
            fila.createCell(0).setCellValue(persona.getNombre());
            fila.createCell(1).setCellValue(persona.getWeb());
            fila.createCell(2).setCellValue(persona.getEdad());
            indiceFila++;
        }

        // Guardamos
        File directorioActual = new File(".");
        String ubicacion = directorioActual.getAbsolutePath();
        String ubicacionArchivoSalida = ubicacion.substring(0, ubicacion.length() - 1) + nombreArchivo;
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(ubicacionArchivoSalida);
            libro.write(outputStream);
            libro.close();
            System.out.println("Libro de personas guardado correctamente");
        } catch (FileNotFoundException ex) {
            System.out.println("Error de filenotfound");
        } catch (IOException ex) {
            System.out.println("Error de IOException");
        }

    }

    public static void main(String[] args) {
        crearAPartirDeArrayList();
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

package ar.edu.unju.fi.poo.archivo;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import ar.edu.unju.fi.poo.model.Categoria;
import ar.edu.unju.fi.poo.model.Titulo;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Utilidades {
    
    // Método para leer el archivo de títulos profesionales
    public static Titulo leerTituloProfesional(String ruta) {
    	   String rutaAbsoluta=System.getProperty("user.home") + "\\Desktop\\" + ruta;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaAbsoluta))) {
            double adicional =(double) Double.parseDouble(br.readLine());
            return new Titulo("Ingeniero", adicional);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de títulos profesionales: " + e.getMessage());
        }
        return null;
    }

    // Método para leer categorías de administrativos
    public static Map<Integer, Categoria> leerCategoriasAdministrativo(String ruta) {
        Map<Integer, Categoria> categorias = new HashMap<>();
        String rutaAbsoluta=System.getProperty("user.home") + "\\Desktop\\" + ruta;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaAbsoluta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(": ");
                int numero = Integer.parseInt(partes[0].trim());
                double valor = Double.parseDouble(partes[1].trim());
                categorias.put(numero, new Categoria(numero, valor));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de categorías de administrativos: " + e.getMessage());
        }
        return categorias;
    }

    // Método para leer valores familiares
    public static Map<String, Double> leerValoresFamiliares(String ruta) {
    	String rutaAbsoluta=System.getProperty("user.home") + "\\Desktop\\" + ruta;
        Map<String, Double> valoresFamiliares = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaAbsoluta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(": ");
                String clave = partes[0].trim();
                double valor = Double.parseDouble(partes[1].trim());
                valoresFamiliares.put(clave, valor);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de valores familiares: " + e.getMessage());
        }
        return valoresFamiliares;
    }

    // Método para guardar archivos en el escritorio
    public static void guardarEnArchivo(String nombreArchivo, String contenido) {
        try {
            String rutaEscritorio = System.getProperty("user.home") + "/Desktop/" + nombreArchivo;
            Files.write(Paths.get(rutaEscritorio), contenido.getBytes());
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
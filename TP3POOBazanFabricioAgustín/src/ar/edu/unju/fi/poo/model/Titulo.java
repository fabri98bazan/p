package ar.edu.unju.fi.poo.model;

public class Titulo {
    private String nombre;
    private double adicional;

    public Titulo(String nombre, double adicional) {
        this.nombre = nombre;
        this.adicional = adicional;
    }

    public String getNombre() {
        return nombre;
    }

    public double getAdicional() {
        return adicional;
    }
}

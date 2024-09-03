package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;


public class Profesional extends Empleado {
    private Titulo titulo;

    public Profesional(int id, String legajo, String nombre, int cantidadHijos, LocalDate fechaNacimiento, int antiguedad, Titulo titulo) {
        super(id, legajo, nombre, cantidadHijos, fechaNacimiento, antiguedad);
        this.titulo = titulo;
    }

    @Override
    public double calcularSueldoNeto() {
        double remunerativos = getRemunerativosBonificables();
        return remunerativos + getSalarioFamiliar() - getDescuentos();
    }

    @Override
    protected double getAdicional() {
        return titulo.getAdicional();
    }
}
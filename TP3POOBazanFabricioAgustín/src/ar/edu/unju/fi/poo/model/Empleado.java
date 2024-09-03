package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Empleado {
    private int id;
    private String legajo;
    private String nombre;
    private int cantidadHijos;
    private LocalDate fechaNacimiento;
    private int antiguedad;
    private double sueldoBasico = 150000;

    public Empleado(int id, String legajo, String nombre, int cantidadHijos, LocalDate fechaNacimiento, int antiguedad) {
        this.id = id;
        this.legajo = legajo;
        this.setNombre(nombre);
        this.cantidadHijos = cantidadHijos;
        this.fechaNacimiento = fechaNacimiento;
        this.setAntiguedad(antiguedad);
    }

    public abstract double calcularSueldoNeto();

    public double getRemunerativosBonificables() {
        return getSueldoBasico() + getAdicional() + (getAntiguedad() * 7000);
    }

    public double getSalarioFamiliar() {
        return cantidadHijos * 8000;
    }

    public double getDescuentos() {
        return getRemunerativosBonificables() * 0.15;
    }

    protected abstract double getAdicional();

    public int calcularEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Legajo: " + legajo + ", Nombre: " + getNombre() + ", Antigüedad: " + getAntiguedad() + " años";
    }

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSueldoBasico() {
		return sueldoBasico;
	}

	public void setSueldoBasico(double sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}
}
package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;

public class Administrativo extends Empleado {
	private Categoria categoria;
	public Administrativo(int id, String legajo, String nombre, int cantidadHijos, LocalDate fechaNacimiento,
			int antiguedad, Categoria categoria) {
		super(id, legajo, nombre, cantidadHijos, fechaNacimiento, antiguedad);
		this.categoria=categoria;
	}
	@Override
	public double calcularSueldoNeto() {
		double remunerativos = getRemunerativosBonificables();
        return remunerativos + getSalarioFamiliar() - getDescuentos();
	}
	@Override
	protected double getAdicional() {
		return categoria.getValor();
	}

	
}

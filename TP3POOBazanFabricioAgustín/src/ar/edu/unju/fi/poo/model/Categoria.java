package ar.edu.unju.fi.poo.model;

public class Categoria {
    private int numero;
    private double valor;

    public Categoria(int numero, double valor) {
        this.numero = numero;
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public double getValor() {
        return valor;
    }
}

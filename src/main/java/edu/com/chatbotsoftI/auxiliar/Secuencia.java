package edu.com.chatbotsoftI.auxiliar;

public class Secuencia {
    private int numero;
    private String fase;

    public Secuencia() {
        
    }

    public Secuencia(String fase) {
        this.fase = fase;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }
}

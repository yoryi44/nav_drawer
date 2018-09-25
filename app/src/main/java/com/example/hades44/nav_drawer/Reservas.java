package com.example.hades44.nav_drawer;

public class Reservas {

     private String destino;
     private String inicio;
     private String fin;
    private String id;

    public Reservas()
    {

    }

    public Reservas(String destino, String inicio, String fin) {
        this.destino = destino;
        this.inicio = inicio;
        this.fin = fin;
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFin() {
        return fin;
    }

    public String getId() { return id; }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public void setId(String id) { this.id = id; }
}

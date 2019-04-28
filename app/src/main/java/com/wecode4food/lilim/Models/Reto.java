package com.wecode4food.lilim.Models;

public class Reto {
    private String titulo;
    private String owner;
    private String descripcion;
    private String start;
    private String end;
    private String srchelp;

    public Reto(String titulo, String owner, String descripcion, String start, String end, String srchelp) {
        this.titulo = titulo;
        this.owner = owner;
        this.descripcion = descripcion;
        this.start = start;
        this.end = end;
        this.srchelp = srchelp;
    }

    public Reto(){}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSrchelp() {
        return srchelp;
    }

    public void setSrchelp(String srchelp) {
        this.srchelp = srchelp;
    }
}

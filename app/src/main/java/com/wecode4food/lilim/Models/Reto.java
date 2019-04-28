package com.wecode4food.lilim.Models;

public class Reto {
    private String titulo;
    private String descripcion;
    private String srchelp;
    private String key;
    private String start;
    private String end;

    public Reto(String titulo, String descripcion, String srchelp, String key, String start, String end) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.srchelp = srchelp;
        this.key = key;
        this.start = "28/04/19";
        this.end = "28/04/19";
    }

    public Reto() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSrchelp() {
        return srchelp;
    }

    public void setSrchelp(String srchelp) {
        this.srchelp = srchelp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}

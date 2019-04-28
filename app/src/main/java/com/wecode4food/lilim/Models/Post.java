package com.wecode4food.lilim.Models;

public class Post {
    private String descripcion;
    private String reto;
    private String titulo;
    private String owner;
    private String key;
    private String src;

    public Post(String descripcion, String reto, String titulo, String owner, String key, String src) {
        this.descripcion = descripcion;
        this.reto = reto;
        this.titulo = titulo;
        this.owner = owner;
        this.key = key;
        this.src = src;
    }

    public Post() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReto() {
        return reto;
    }

    public void setReto(String reto) {
        this.reto = reto;
    }

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

package com.wecode4food.lilim.Models;

public class Post {
    private String descripcion;
    private String reto;
    private String srchelp;
    private String titulo;

    public Post(String descripcion, String reto, String srchelp, String titulo)
    {
        this.descripcion = descripcion;
        this.reto = reto;
        this.srchelp = srchelp;
        this.titulo = titulo;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getReto()
    {
        return reto;
    }

    public void setReto(String reto)
    {
        this.reto = reto;
    }

    public String getSrchelp()
    {
        return srchelp;
    }

    public void setSrchelp(String srchelp)
    {
        this.srchelp = srchelp;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }
}

package com.wecode4food.lilim.Models;

public class User
{
    private String nombre;
    private String mail;
    private String cc;
    private boolean admin;
    private String barrio;

    public User(String name, String mail, String cc)
    {
        this.nombre = name;
        this.mail = mail;
        this.cc = cc;
        this.admin = false;
        this.barrio = "Dummy";
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getCc()
    {
        return cc;
    }

    public void setCc(String cc)
    {
        this.cc = cc;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }

    public String getBarrio()
    {
        return barrio;
    }

    public void setBarrio(String barrio)
    {
        this.barrio = barrio;
    }
}

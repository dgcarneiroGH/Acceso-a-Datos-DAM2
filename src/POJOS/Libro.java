/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJOS;

import java.util.Date;

/**
 *
 * @author Dg_Ca
 */
public class Libro {
    
    private int cod;
    private String titulo;
    private float precio;
    private Date fechaPublicacion;

    public Libro() {
    }

    public Libro(int cod, String titulo, float precio, Date fechaPublicacion) {
        this.cod = cod;
        this.titulo = titulo;
        this.precio = precio;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "\nCódigo: " + cod + 
                "\nTitulo: " + titulo + 
                "\nPrecio: " + precio + "€"
                + "\nFecha de publicación: " + fechaPublicacion;
    }
 
    
}

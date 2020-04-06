/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJOS;

import java.util.ArrayList;

/**
 *
 * @author Dg_Ca
 */
public class Autor {
    
    private String dni;
    private String nombre;
    private String nacionalidad;
    private int edad;
    private ArrayList<Libro> libros;

    public Autor() {
    }

    public Autor(String dni, String nombre, String nacionalidad, int edad, ArrayList<Libro> libros) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.libros = libros;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "\nDNI: " + dni
                + "\nNombre: " + nombre
                + "\nNacionalidad: " + nacionalidad + 
                "\nEdad: " + edad;
    }
    
        
}

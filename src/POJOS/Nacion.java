/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJOS;

/**
 *
 * @author Dg_Ca
 */
public class Nacion {
    
    String nacionalidad;
    int numero;

    public Nacion(String nacionalidad, int numero) {
        this.nacionalidad = nacionalidad;
        this.numero = numero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "\nNacionalidad: " + nacionalidad +
                "\nNº de autores:" + numero;
    }
    
        
}

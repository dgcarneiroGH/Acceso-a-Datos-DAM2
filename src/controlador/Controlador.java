/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import POJOS.Autor;
import POJOS.Libro;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;

/**
 *
 * @author Dg_Ca
 */
public class Controlador {
    
    public static Date validaFecha(String fechaS) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaD = null;

        try {
            fechaD = sdf.parse(fechaS);

        } catch (Exception e) {
            System.out.println("Formato no válido");
        }

        if (!sdf.format(fechaD).equals(fechaS)) {
            System.out.println("Fecha inválida!!");
        }

        return fechaD;

    }
    
    public static boolean dniExiste(ODB odb, String dni){
        boolean existe = false;
        
        Objects<Autor> autores = odb.getObjects(Autor.class);
        
        while(autores.hasNext()){
            
            Autor autor = autores.next();            
            if(autor.getDni().equals(dni))
                existe = true;
                        
        }
        
        return existe;
    }
    
    public static boolean libroExiste(ODB odb, int cod){
        boolean existe = false;
        
        Objects<Libro> libros = odb.getObjects(Libro.class);
        
        while(libros.hasNext()){
            
            Libro libro = libros.next();            
            if(libro.getCod() == cod)
                existe = true;
                        
        }
        
        return existe;
    }
    
}

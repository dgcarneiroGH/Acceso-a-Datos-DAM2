/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import POJOS.Autor;
import POJOS.Libro;
import java.util.Scanner;
import org.neodatis.odb.ODB;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author Dg_Ca
 */
class Eliminar {

    static Scanner teclado = new Scanner(System.in);
    static final String NOEXISTE = "ERROR. El objeto NO existe en la Base de Datos";
    static final String OPCIONINCORRECTA = "ERROR. Opción incorrecta";
    
    static void libro(ODB odb) {
                
        System.out.println("-- BORRAR LIBRO --");
        
        System.out.println("Nombre del autor:");
        String nombre = teclado.next();
        
        try{
            IQuery query = new CriteriaQuery(Autor.class, Where.equal("nombre", nombre));
            Autor autor = (Autor) odb.getObjects(query).getFirst();
            
            System.out.println("Código del libro:");
            int cod = teclado.nextInt();
            
            if(Controlador.libroExiste(odb, cod)){
                
                for(Libro libro : autor.getLibros()){
                    
                    if(libro.getCod() == cod){
                        
                        eliminaLibro(odb, libro);
;                    }
                }
            }else{
                System.out.println(NOEXISTE);
            }            
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println(NOEXISTE);
        }        
    }
    
    static void eliminaLibro(ODB odb, Libro libro){
        
        System.out.printf("Eliminar %s, estás seguro?(S/N):",libro.getTitulo());
        char opcion = teclado.next()
                .toUpperCase().charAt(0);

        switch (opcion) {
            case 'S':
                odb.delete(libro);
                System.out.println("Borrado completado con éxito");
                break;
                
            case 'N':
                break;

            default:
                System.out.println(OPCIONINCORRECTA);
                break;
        }
    }    
}

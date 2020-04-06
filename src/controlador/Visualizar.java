/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import POJOS.Autor;
import POJOS.Libro;
import POJOS.Nacion;
import java.util.ArrayList;
import java.util.Scanner;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author Dg_Ca
 */
class Visualizar {

    static Scanner teclado = new Scanner(System.in);
    static final String NOEXISTE = "ERROR. El objeto NO existe en la Base de Datos";

    static void autor(ODB odb) {

        System.out.println("-- AUTORES ESPAÑOLES --");

        IQuery query = new CriteriaQuery(Autor.class, Where.equal("nacionalidad", "ESPA?OLA"));
        Objects autores = odb.getObjects(query);

        System.out.println("Nombre\tEdad\tLibros");
        System.out.println("------\t----\t------");

        boolean existe = false;
        while (autores.hasNext()) {

            existe = true;
            Autor autor = (Autor) autores.next();

            if (autor.getEdad() < 60) {
                System.out.println("\n" + autor.getNombre() + "\t" + autor.getEdad());

                for (Libro libro : autor.getLibros()) {

                    System.out.println("\t\t" + libro.getTitulo());
                }
            }
        }

        if (!existe) {
            System.out.println(NOEXISTE);
        }
    }

    static void nAutores(ODB odb) {
        
        System.out.println("-- NÚMERO DE AUTORES POR NACIÓN --");        
        
        Objects<Autor> autores = odb.getObjects(Autor.class);
        ArrayList<Nacion> naciones = new ArrayList();
        
        while(autores.hasNext()){
            
            Autor autor = autores.next();
            
            boolean existe = false;
            for(Nacion nacionalidad : naciones){
                
                if(autor.getNacionalidad().equals(nacionalidad.getNacionalidad())){
                    existe = true;
                    nacionalidad.setNumero(nacionalidad.getNumero()+1);
                }                
            }
            
            if(!existe){
                
                Nacion na = new Nacion(autor.getNacionalidad(),1);
                naciones.add(na);
            }
        }
        
        for(Nacion nacionalidad : naciones){
            System.out.println(nacionalidad);
        }
    }

    static void libro(ODB odb) {
        
        System.out.println("-- LIBROS --");        
        
        System.out.println("Nombre del autor:");
        String nombre = teclado.next();
        
        try{
            
            IQuery query = new CriteriaQuery(Autor.class,Where.equal("nombre", nombre));
            Autor autor = (Autor) odb.getObjects(query).getFirst();
            
            System.out.println("Título\t\tPrecio");
            System.out.println("-------\t\t------");
            
            for(Libro libro : autor.getLibros()){
                
                System.out.println(libro.getTitulo()+"\t\t"+libro.getPrecio()+"€");
            }
            
        }catch(java.lang.IndexOutOfBoundsException e){
            System.out.println(NOEXISTE);
        }
    }

}

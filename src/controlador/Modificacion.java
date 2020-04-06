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
class Modificacion {

    static Scanner teclado = new Scanner(System.in);
    static final String NOEXISTE = "ERROR. El objeto NO existe en la Base de Datos";
    static final String OPCIONINCORRECTA = "ERROR. Opción incorrecta";

    static void nacionalidad(ODB odb) {
        System.out.println("-- MODIFICACIÓN NACIONALIDAD --");

        System.out.println("DNI:");
        String dni = teclado.next();

        if (Controlador.dniExiste(odb, dni)) {

            IQuery query = new CriteriaQuery(Autor.class, Where.equal("dni", dni));
            Autor autor = (Autor) odb.getObjects(query).getFirst();

            System.out.printf("La nacionalidad actual de %s es %s, quieres cambiarla?(S/N):",
                    autor.getNombre(), autor.getNacionalidad());
            char opcion = teclado.next()
                    .toUpperCase().charAt(0);

            switch (opcion) {
                case 'S':
                    System.out.println("Nueva nacionalidad:");
                    autor.setNacionalidad(teclado.next());
                    odb.store(autor);
                    System.out.printf("Has cambiado la nacionalidad de %s a %s",
                            autor.getNombre(), autor.getNacionalidad());
                    break;
                    
                case 'N':
                    break;

                default:
                    System.out.println(OPCIONINCORRECTA);
                    break;
            }

        } else {
            System.out.println(NOEXISTE);
        }
    }

    static void precio(ODB odb) {

        System.out.println("-- MODIFICACION PRECIO --");

        System.out.println("Nombre del autor:");
        String nombre = teclado.next();

        try {
            IQuery query = new CriteriaQuery(Autor.class, Where.equal("nombre", nombre));
            Autor autor = (Autor) odb.getObjects(query).getFirst();                                 

            System.out.println("Título del libro:");
            String titulo = teclado.next();

            boolean existe = false;
            for (Libro libro : autor.getLibros()) {

                if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                    existe = true;
                    modificaPrecio(odb, libro);
                }
            }

            if (!existe) {
                System.out.println(NOEXISTE);
            }
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println(NOEXISTE);
        }
    }

    static void modificaPrecio(ODB odb, Libro libro) {

        System.out.printf("El precio actual de %s es %s, quieres cambiarlo?(S/N):",
                libro.getTitulo(), libro.getPrecio());
        char opcion = teclado.next()
                .toUpperCase().charAt(0);

        switch (opcion) {
            case 'S':
                System.out.println("Nuevo precio:");
                libro.setPrecio(teclado.nextFloat());
                odb.store(libro);
                System.out.printf("Has cambiado el precio de %s a %s",
                        libro.getTitulo(), libro.getPrecio());
                break;

            default:
                System.out.println(OPCIONINCORRECTA);
                break;
        }
    }

}

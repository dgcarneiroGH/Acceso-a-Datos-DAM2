/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import POJOS.Autor;
import POJOS.Libro;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.neodatis.odb.ODB;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author Dg_Ca
 */
class Alta {

    static Scanner teclado = new Scanner(System.in);
    static final String EXISTE = "ERROR. El objeto ya existe en la Base de Datos";
    static final String NOEXISTE = "ERROR. El objeto NO existe en la Base de Datos";
    static final String OPCIONINCORRECTA = "ERROR. Opción incorrecta";

    static void autor(ODB odb) {

        System.out.println("-- ALTA AUTOR --");

        System.out.println("DNI:");
        String dni = teclado.next();

        if (!Controlador.dniExiste(odb, dni)) {
            System.out.println("Nombre:");
            String nombre = teclado.next();

            System.out.println("Nacionalidad:");
            String nacionalidad = teclado.next();

            System.out.println("Edad:");
            int edad = teclado.nextInt();

            ArrayList<Libro> libros = new ArrayList();
            char opcion;
            do {
                System.out.println("Desea añadir libros?(S/N):");
                opcion = teclado.next()
                        .toUpperCase().charAt(0);

                switch (opcion) {
                    case 'S':
                        libros.add(anadirLibro(odb));
                        break;
                        
                    case 'N':
                        break;

                    default:
                        System.out.println(OPCIONINCORRECTA);
                        break;
                }

            } while (opcion != 'N');

            odb.store(new Autor(dni, nombre, nacionalidad, edad, libros));
        } else {
            System.out.println(EXISTE);
        }

    }

    static Libro anadirLibro(ODB odb) {

        int cod;
        String titulo = "";
        float precio = 0;
        Date fechaPublicacion = null;

        do {
            System.out.println("Código:");
            cod = teclado.nextInt();

            if (!Controlador.libroExiste(odb, cod)) {

                System.out.println("Título:");
                titulo = teclado.next();

                System.out.println("Precio:");
                precio = teclado.nextFloat();

                System.out.println("Fecha de publicación (dd/MM/yyyy):");
                String sFechaPublicacion = teclado.next();
                fechaPublicacion = Controlador.validaFecha(sFechaPublicacion);

            } else {
                System.out.println(EXISTE);
            }
        } while (Controlador.libroExiste(odb, cod));

        return new Libro(cod, titulo, precio, fechaPublicacion);
    }

    static void libro(ODB odb) {

        System.out.println("-- ALTA LIBRO --");

        System.out.println("DNI:");
        String dni = teclado.next();
        if (Controlador.dniExiste(odb, dni)) {

            IQuery query = new CriteriaQuery(Autor.class,Where.equal("dni", dni));

            Autor autor = (Autor) odb.getObjects(query).getFirst();
            
            ArrayList<Libro> libros = autor.getLibros();
            libros.add(anadirLibro(odb));
            
            autor.setLibros(libros);
            odb.store(autor);
            
        } else {
            System.out.println(NOEXISTE);
        }

    }

}

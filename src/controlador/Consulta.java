/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import POJOS.Autor;
import POJOS.Libro;
import static controlador.Modificacion.NOEXISTE;
import java.util.Date;
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
class Consulta {

    static Scanner teclado = new Scanner(System.in);
    static final String NOEXISTE = "ERROR. El objeto NO existe en la Base de Datos";

    static void autor(ODB odb) {

        System.out.println("-- AUTORES ITALIANOS --");

        IQuery query = new CriteriaQuery(Autor.class, Where.equal("nacionalidad", "ITALIANA"));
        Objects<Autor> autores = odb.getObjects(query);

        boolean existe = false;
        while (autores.hasNext()) {

            existe = true;
            Autor autor = (Autor) autores.next();
            System.out.println(autor);
        }

        if (!existe) {
            System.out.println(NOEXISTE);
        }
    }

    static void libro(ODB odb) {

        System.out.println("-- LIBROS PUBLICADOS --");

        System.out.println("Nombre del autor:");
        String nombre = teclado.next();

        try {

            IQuery query = new CriteriaQuery(Autor.class, Where.equal("nombre", nombre));
            Autor autor = (Autor) odb.getObjects(query).getFirst();

            System.out.println("Selecciona entre que fechas quieres hacer la consulta:");
            System.out.println("Fecha 1 (dd/MM/yyyy):");
            String sFecha1 = teclado.next();
            long fecha1 = Controlador.validaFecha(sFecha1).getTime();

            System.out.println("Fecha 2 (dd/MM/yyyy):");
            String sFecha2 = teclado.next();
            long fecha2 = Controlador.validaFecha(sFecha2).getTime();

            boolean existe = false;
            for (Libro libro : autor.getLibros()) {

                if (libro.getFechaPublicacion().getTime() >= fecha1
                        && libro.getFechaPublicacion().getTime() <= fecha2) {

                    existe = true;
                    System.out.println(libro);
                }
            }

            if (!existe) {
                System.out.println(NOEXISTE);
            }

        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println(NOEXISTE);
        }
    }

    static void libroAutor(ODB odb) {

        System.out.println("-- LIBRO/AUTOR --");

        System.out.println("Título del libro:");
        String titulo = teclado.next();

        try {

            IQuery queryLibro = new CriteriaQuery(Libro.class, Where.equal("titulo", titulo));
            Libro libro = (Libro) odb.getObjects(queryLibro).getFirst();

            Objects autores = odb.getObjects(Autor.class);

            while (autores.hasNext()) {

                Autor autor = (Autor) autores.next();

                for (Libro li : autor.getLibros()) {

                    if (li == libro) {

                        System.out.println("-- Autor --" + autor
                                + "\n-------------------\n"
                                + "-- Libro --"
                                + li);
                    }
                }
            }

        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println(NOEXISTE);
        }
    }

}

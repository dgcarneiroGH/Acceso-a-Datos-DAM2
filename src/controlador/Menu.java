/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Scanner;
import org.neodatis.odb.ODB;

/**
 *
 * @author Dg_Ca
 */
class Menu {

    static Scanner teclado = new Scanner(System.in);
    
    static void principal(ODB odb) {
       
        int opcion;
        do{
        System.out.println("\n-- MENÚ PRINCIPAL --"
                + "\n1.- Nuevo autor"
                + "\n2.- Nuevo libro"
                + "\n3.- Modificar nacionalidad"
                + "\n4.- Modificar precio"
                + "\n5.- Eliminar libro"
                + "\n6.- Consulta: Autores italianos"
                + "\n7.- Consulta: Libros escritos por un autor entre 2 fechas"
                + "\n8.- Visualización_ Autores Españoles menores de 60"
                + "\n9.- Visualización: Número de autores por nación"
                + "\n10.- Visualización: Libros de un autor"
                + "\n11.- Consultas: Autor de un libro"
                + "\n0.- Salir");
        opcion = teclado.nextInt();
        
        switch(opcion){
            case 1:
                Alta.autor(odb);
                break;
                                
            case 2:
                Alta.libro(odb);
                break;
                
            case 3:
                Modificacion.nacionalidad(odb);
                break;
                
            case 4:
                Modificacion.precio(odb);
                break;
                
            case 5:
                Eliminar.libro(odb);
                break;
                
            case 6:
                Consulta.autor(odb);
                break;
                
            case 7:
                Consulta.libro(odb);
                break;
                
            case 8:
                Visualizar.autor(odb);
                break;
                
            case 9:
                Visualizar.nAutores(odb);
                break;
                
            case 10:
                Visualizar.libro(odb);
                break;
                
            case 11:
                Consulta.libroAutor(odb);
                break;
                
            case 0:
                System.out.println("Saliendo...");
                break;
                
            default:
                System.out.println("ERROR. Selección incorrecta, selecciona una opción del menú");
                break;                
        }
                
        }while(opcion!=0);
    }
    
}

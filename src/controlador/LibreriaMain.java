/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;



/**
 *
 * @author Dg_Ca
 */
public class LibreriaMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ODB odb = ODBFactory.open("Libreria.test");
        
        Menu.principal(odb);
        
        odb.close();
    }
    
}

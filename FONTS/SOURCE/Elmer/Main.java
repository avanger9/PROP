/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

//import Controladors.CtVista;

import Persistencia.FileAlreadyExistsException;
import Persistencia.FileNotExistsException;
import Vistas.CtVista;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author Administrador
 */
public class Main {
    
    public static void main(String[] args) throws FileNotExistsException, IOException, FileAlreadyExistsException  {            
                CtVista CVista = new CtVista();
                CVista.ActivarVVistaInici();            
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Partida;

import Vistas.CtVista;
import Vistas.ElementsInteractius.Botons;
import Vistas.ElementsInteractius.Titol;
import Vistas.VistaGenerica;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Administrador
 */
public class VistaTipusPartida extends VistaGenerica{
        
    public void MostrarTipusPartida(final CtVista CVista, String s) { 
        // Botó "Carregar Partida" per carregar el joc
        Botons b2 = new Botons("Hidato Random",240,165,310,70);
        b2.setBackground(new Color(187,222,48));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 CVista.ActivarVTipusPartidaHidato(s);
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b2);
        
        // Botó "Afegir Hidato" pe afegir hidato al joc
        Botons b3 = new Botons("Hidatos Creats",240,260,310,70);
        b3.setBackground(new Color(187,222,48));
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              //   CVista.ActivarVJocPartida();
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b3);
        
        String[] hidatosCreats = { "Hidatocreat_1", "Hidatocreat_2", "Hidatocreat_3", "Hidatocreat_4" };
        JComboBox jcb = new JComboBox(hidatosCreats);
        jcb.setSelectedIndex(0);
        jcb.setBounds(300, 350, 200, 35);
        jcb.setForeground(new Color(95,160,0));        
        jcb.setBackground(Color.WHITE);
        jcb.setFont(new Font("Arial", Font.BOLD, 14));        
        getContentPane().add(jcb);          
        
        // Botó "Enrere" 
        Botons b1 = new Botons("Enrere",80,420,220,70);
        b1.setBackground(new Color(0,0,0));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CVista.ActivarVMenuPrincipal(s);
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b1);                          
        
    }
    
    public VistaTipusPartida (final CtVista CVista) {
        setTitol("Tipus Partida");
       
        Titol t = new Titol("Tipus Partida", 50,30,400,60);       
        getContentPane().add(t);                           
        
        
    }        
}
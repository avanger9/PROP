/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.ElementsInteractius;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
/**
 *
 * @author Administrador
 */
public class Titol extends JTextPane {
    
    // Característiques semblants dels panells de text a la nostra aplicació
    public Titol(String titol, int x, int y, int w, int h) {
        this.setFont(new Font("Lucida Sans", Font.BOLD, 50));
        this.setText(titol);
        this.setForeground(new Color(255,0,0));
        this.setBackground(new Color(0,0,0,0));        
        this.setEditable(false);
        this.setOpaque(false);
        this.setLocation(x, y);  
        this.setSize(w, h);   
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);        
    }
}

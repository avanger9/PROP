/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Usuari;

import Vistas.CtVista;
import Vistas.ElementsInteractius.Botons;
import Vistas.ElementsInteractius.Titol;
import Vistas.VistaGenerica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class VistaEscollirUsuari extends VistaGenerica {
    
    String usuariSeleccionat;
    private ArrayList<String> r;
  
    
    public void CarregarUsuaris(ArrayList<String> aux) {
		r = aux;
	}
    
    public void MostrarUsuaris(final CtVista CVista) {       
        // Sub-contenidor         
        
        JPanel contenidor = new JPanel();
        contenidor.setBounds(50, 125, 700, 350);
        contenidor.setLayout(null);
        contenidor.setBackground(new Color(219,239,165));
        contenidor.setBorder(new EmptyBorder(5, 5, 5, 5)); 

        JComboBox jcb = new JComboBox();      
        jcb.setEditable(false);
        jcb.setBounds(300, 200, 230, 45);
        jcb.setForeground(new Color(95,160,0));        
        jcb.setBackground(Color.WHITE);
        jcb.setFont(new Font("Arial", Font.BOLD, 14));
        
        Iterator<String> nombreIterator = r.iterator();
        while(nombreIterator.hasNext()){
             String elemento = nombreIterator.next();
             jcb.addItem(elemento);
        }                                                 
        getContentPane().add(jcb);      
        
        
        // Botó d'accés a la sessió     
        Botons b2 = new Botons("Entrar",500,380,220,70);
        b2.setBackground(new Color(0,0,0));
       
        b2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {                              
                usuariSeleccionat = (String) jcb.getSelectedItem();
                CVista.ActivarVMenuPrincipal(usuariSeleccionat);
                Sortir();
            }
	});     

        getContentPane().add(b2);                
        
        getContentPane().add(contenidor);

        
    }   
    
    public VistaEscollirUsuari (final CtVista CVista) {
       
        
        setTitol("Escollir Usuari");
       
        Titol t = new Titol("Escollir Usuari", 50,30,400,60);       
        getContentPane().add(t);    
              
        // Botó "Enrere" 
        Botons b1 = new Botons("Enrere",80,380,220,70);
        b1.setBackground(new Color(187,222,48));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CVista.ActivarVVistaInici();
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b1);
        
        
        
            /*
        
                JTextPane txtError = new JTextPane();
                txtError.setForeground(Color.WHITE);
                txtError.setBounds(385, 88, 240, 61);
                txtError.setFont(new Font("Nirmala UI", Font.BOLD, 12));
                txtError.setText("");
                txtError.setEditable(false);
                txtError.setBackground(null);
                txtError.setAlignmentX(CENTER_ALIGNMENT);
                txtError.setAlignmentY(CENTER_ALIGNMENT);
			
                
		JTextPane n = new JTextPane();
                n.setBounds(36, 46, 240, 61);
                n.setText("\"Por favor, introduce el nombre del tablero.");
                n.setFont(new Font("Nirmala UI", Font.BOLD, 15));
		n.setSize(313, 30);
		getContentPane().add(n);
		
		JTextField textField = new JTextField();
		textField.setBounds(36, 81, 207, 34);
		getContentPane().add(textField);
		textField.setColumns(10);

		
		

		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		spinner.setBounds(36, 179, 35, 30);
		getContentPane().add(spinner);
		
		JButton B = new JButton("Consultar Ranking");                
                B.setBackground(new Color(102, 153, 204));
                B.setForeground(SystemColor.window);
                B.setFont(new Font("Candara", Font.BOLD, 38));
                B.setBounds(129, 269, 245, 70);
                B.setBorderPainted(false);
                B.setFocusPainted(false);
		
		B.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				nTab = textField.getText();
				nPos = (Integer) spinner.getValue();
				if (!nTab.equals("")&& nTab.equals("20")) {
					textField.setText("");
					spinner.setValue(0);
					JTextPane txtError = new JTextPane();
                                        txtError.setBounds(185, 88, 240, 61);                
                                        txtError.setText("");
                                        txtError.setFont(new Font("Nirmala UI", Font.BOLD, 12));		
					getContentPane().add(txtError);
					CVista.ActivarVMostrarRanking();
					Sortir();
				}
				else if (!nTab.equals("")&& !nTab.equals("20")) {
					textField.setText("");
					spinner.setValue(0);
                                        txtError.setBounds(385, 88, 240, 61);                
                                        txtError.setText("El tablero introducido no existe.");
                                        txtError.setFont(new Font("Nirmala UI", Font.BOLD, 12));		
                                        
					txtError.setForeground(Color.RED);
					getContentPane().add(txtError);
				}
			}
		});
		B.setSize(368, 46);
		getContentPane().add(B);

        
        */
        

        
    }

    public String get_usuari_escollit() {
        return usuariSeleccionat;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Ranking;

import Vistas.CtVista;
import Vistas.ElementsInteractius.Botons;
import Vistas.ElementsInteractius.Titol;
import Vistas.VistaGenerica;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class VistaMostrarRanking extends VistaGenerica {
    private JTable table;
    /*
    private static final long serialVersionUID = 1L;
    private JTable table;
    private ArrayList<String> r;
    String nTab;
    int nPos;
    
    public void setR(ArrayList<String> aux, String nTab,int nPos) {
            r = aux;
            this.nTab = nTab;
            this.nPos = nPos;
    }
    */
    public void MostrarTotRanking(final CtVista CVista, String s, ArrayList<String> aux) {                 
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        table.setBorder(new LineBorder(new Color(255, 255, 255), 2));
        table.setBounds(150, 300, 495, 30);
        table.setModel(new DefaultTableModel(new Object[][] {
                {"Nombre", "Tiempos (s)"},
                },new String[] {"a", "b"}));
        

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int y = 32;
        for (int i = 0; i < aux.size(); ++i) {

            String[] datos = aux.get(i).split(" ");                
                model.addRow(datos);                        
                table.setBounds(108, 120, 495, y);
                y += 16;

        }
        getContentPane().add(table);

        // Botó "Enrere" 
        Botons b1 = new Botons("Enrere",40,420,220,70);
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

    
    private String[] concatenar(int i, String s) {
           
            String[] s1 =  s.split(",");
            
            String y = ""+i;
            String[] concat = new String[3];
            concat[0] = y;
            concat[1] = s1[0];
            concat[2] = s1[1];
            return concat;

    }        
        
    public VistaMostrarRanking (final CtVista CVista) {
       
        setTitol("Mostra de Ranking");
        
        Titol t = new Titol("Mostra Ranking", 50,30,400,60);       
        getContentPane().add(t);                            
    }    
}

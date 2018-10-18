/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Domini.CtDomini;
import Domini.GetNumException;
import Domini.HidatoNoSolucionableException;
import Persistencia.AdjacenciaNoExisteixException;
import Persistencia.CelaNoExisteixException;
import Persistencia.FileAlreadyExistsException;
import Persistencia.FileNotExistsException;
import Persistencia.UsuariJaExisteixException;
import Persistencia.UsuariNoExisteixException;
import Vistas.Hidato.VistaAfegirHidato;
import Vistas.Hidato.VistaAfegirTipusHidato;
import Vistas.Menu.VistaMenuPrincipal;
import Vistas.Partida.VistaCarregarPartida;
import Vistas.Partida.VistaJocPartida;
import Vistas.Partida.VistaJocPartidaSolucionada;
import Vistas.Partida.VistaTipusPartida;
import Vistas.Partida.VistaTipusPartidaHidato;
import Vistas.Ranking.VistaMostrarRanking;
import Vistas.Usuari.VistaCrearUsuari;
import Vistas.Usuari.VistaEscollirUsuari;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Administrador
 */

public class CtVista {
    private VistaInici VInici; 
    private VistaCrearUsuari VCrearUsuari; 
    private VistaEscollirUsuari VEscollirUsuari;    
    private VistaMenuPrincipal VMenuPrincipal;   
    private VistaMostrarRanking VMostrarRanking;    
    private VistaCarregarPartida VCarregarPartida;
    private VistaTipusPartida VTipusPartida;
    private VistaTipusPartidaHidato VTipusPartidaHidato;
    private VistaJocPartida VJocPartida;
    private VistaJocPartidaSolucionada VJocPartidaSolucionada;    
    private VistaAfegirTipusHidato VAfegirTipusHidato;
    private VistaAfegirHidato VAfegirHidato;
    private CtDomini CDominio;
    
        public CtVista() throws FileNotExistsException, IOException, FileAlreadyExistsException {
            //CDominio = new CtrlDominio();
            VInici = new VistaInici(this);
            VCrearUsuari = new VistaCrearUsuari(this);
            VEscollirUsuari = new VistaEscollirUsuari(this);            
            VMenuPrincipal = new VistaMenuPrincipal(this);
            VMostrarRanking = new VistaMostrarRanking(this);
            VCarregarPartida = new VistaCarregarPartida(this);
            VTipusPartida = new VistaTipusPartida(this);
            VTipusPartidaHidato = new VistaTipusPartidaHidato(this);
            VJocPartida = new VistaJocPartida(this);
            VJocPartidaSolucionada = new VistaJocPartidaSolucionada(this);
            VAfegirTipusHidato = new VistaAfegirTipusHidato(this);
            VAfegirHidato = new VistaAfegirHidato(this);
            CDominio = new CtDomini();
        }
        
        public void desar_ranking(String user, int temps) throws IOException, FileAlreadyExistsException {

            CDominio.afegir_al_ranking(user,temps);            
        }
        
        public void crear_usuari(String aux) throws IOException, UsuariJaExisteixException, FileAlreadyExistsException {
            CDominio.crea_usuari(aux);
        }
        
    /**
     *
     * @param nom nom de l'usuari
     * @param hidato hidato creat
     * @param tC tipus de cella
     * @param tA tipus d'adj
     */
    public void desar_hidato(String nom, String[][] hidato, String tC, String tA)  throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
           
            if (tC.equals("H")) CDominio.desar_hidato(nom, hidato, tC);
            else CDominio.desar_hidato(nom, hidato, tC, tA);
    }
    
    public void desar_partida_hidato(String nom, int time, String hidato[][], String tipus_cel, String adj, Integer n) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
            if (tipus_cel.equals("H")) CDominio.desar_partida(nom, time, hidato, tipus_cel, n);
            else CDominio.desar_partida(nom, time, hidato, tipus_cel, adj,n);                    
    }
    
    public String get_usuari() {
        return VEscollirUsuari.get_usuari_escollit();
    }
        
    public String[][] get_solucio_hidato(String hidato[][], String f1[]) throws HidatoNoSolucionableException, GetNumException {
        return CDominio.solve(hidato,f1);
    }  
    
    public boolean es_solucio_hidato(String[][] sol, String[] aux) {
        return CDominio.es_solucionable(sol,aux);
    }
    
        public String [] FormatejarLinea(int cel, int adj, String fil, String col) {            
            String aux[] = new String[4];
            if (cel == 0) aux[0] = "Q";
            else if (cel == 1) aux[0] = "H";
            else if (cel == 2) aux[0] = "T";

            if (adj == 0) aux[1] ="C";
            else if (adj == 1) aux[1] ="CA"; 

            aux[2] = fil;            
            aux[3] = col;
            return aux;            
            
        }
        // Vista inici visible
        public void ActivarVVistaInici() {
             VInici.setVisible(true);
        }  
        
        // Vista crear usuari visible
        public void ActivarVCrearUsuari(){
            VCrearUsuari.setVisible(true);
        }
        
        public void ActivarVEscollirUsuari() throws IOException, FileNotFoundException, FileNotExistsException {                                      
            
            
            
            ArrayList aux;
            aux = CDominio.get_usuaris();
            VEscollirUsuari.CarregarUsuaris(aux);
            VEscollirUsuari.MostrarUsuaris(this);                   
            VEscollirUsuari.setVisible(true);               
        }
        
        public boolean usuariExistent(String aux) throws IOException, FileNotFoundException, FileNotExistsException {
            ArrayList s;
            s = CDominio.get_usuaris();
            for(int i = 0 ; i <s.size();i++) {
                if (s.get(i).equals(aux))
                {
                    return true;
                }
            }
            return false;
        }
        
        public void ActivarVMenuPrincipal(String aux){
            VMenuPrincipal.MostrarNomUsuari(this,aux);
            VMenuPrincipal.setVisible(true);
        }
                
        public void ActivarVMostrarRanking(String aux) throws IOException, FileNotFoundException, FileNotExistsException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
            
            ArrayList rank;
            rank = CDominio.get_ranking();
            
            /*
            Iterator<String> nombreIterator = aux.iterator();
            while(nombreIterator.hasNext()){
                String elemento = nombreIterator.next();
                System.out.print(elemento+" / ");
            }                         
            */      
            //VMostrarRanking.Mostrar(this, aux, rank);
            VMostrarRanking.MostrarTotRanking(this,aux, rank);
            VMostrarRanking.setVisible(true);
        }
        
        public void ActivarVCarregarPartida(){
            VCarregarPartida.setVisible(true);
        }     
        public void ActivarVTipusPartida(String aux){
            VTipusPartida.MostrarTipusPartida(this,aux);
            VTipusPartida.setVisible(true);
        }             
        
        public void ActivarVTipusPartidaHidato(String aux){
            VTipusPartidaHidato.MostrarTipusPartidaHidato(this,aux);
            VTipusPartidaHidato.setVisible(true);
        }        
        
        public void ActivarVJocPartida(String s, String [] aux, int dif, int time_start) throws GetNumException{
            //VJocPartida.MostrarJocPartida(aux, dif);
            String[][] h_random =  CDominio.hidato_random(aux,dif);
            VJocPartida.MostrarJocPartida(this, aux, h_random,time_start,s);
            VJocPartida.setVisible(true);
        }
        
        public void ActivarVJocPartidaSolucionada(String[][] sol, String[][] sol_correcta, String s) {
            VJocPartidaSolucionada.MostrarJocPartidaSolucionadaERROR(this, sol, sol_correcta, s);
            VJocPartidaSolucionada.setVisible(true);
        }
        
        public void PartidaCorrecta(String[][] sol, int ts, int te, String s) {
            VJocPartidaSolucionada.MostrarJocPartidaSolucionadaOK(this, sol, ts, te, s);
            VJocPartidaSolucionada.setVisible(true);
        }        

    /**
     *
     * @param nom nom de l'usuari
     */
    public void ActivarVAfegirTipusHidato(String aux){
            VAfegirTipusHidato.MostrarAfegirTipusHidato(this,aux);            
            VAfegirTipusHidato.setVisible(true);
        }        
        
        public void ActivarVAfegirHidato(String s, String [] aux, int dif){
            VAfegirHidato.MostrarAfegirHidato(this, s, aux,dif);
            /*
            String nom = get_usuari();
            String tC = Vget_tC();
            String tA = Vget_tA();
            String tD = Vget_tD();
            
            VAfegirHidato = new VistaAfegirHidato(this, nom, tC, tA, tD);
            */
            VAfegirHidato.setVisible(true);
        }
       
    public String Vget_tD() {
        return VAfegirTipusHidato.get_tD();
    }
    public String Vget_tA() {
        return VAfegirTipusHidato.get_tA();
    }
    public String Vget_tC() {
        return VAfegirTipusHidato.get_tC();
    }
}
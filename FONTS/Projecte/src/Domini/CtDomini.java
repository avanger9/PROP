package Domini;
import Persistencia.AdjacenciaNoExisteixException;
import Persistencia.CelaNoExisteixException;
import java.util.Scanner;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import Persistencia.CtPersistencia;
import Persistencia.FileAlreadyExistsException;
import Persistencia.FileNotExistsException;
import Persistencia.UsuariJaExisteixException;
import Persistencia.UsuariNoExisteixException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CtDomini {
	private CtPersistencia ctP;
        private Ranking r;
	
	public CtDomini() throws FileNotExistsException, IOException, FileAlreadyExistsException{
		ctP=new CtPersistencia();
                r=new Ranking(ctP.get_ranking());
	}
	
	public ArrayList<String> get_usuaris() throws IOException, FileNotFoundException, FileNotExistsException {           
            return ctP.get_usuaris();
	}
        
	public void crea_usuari(String nom) throws IOException, UsuariJaExisteixException, FileAlreadyExistsException  {
		ctP.crea_usuari(nom);
	}
        
        public void desar_hidato(String nom, String hidato[][], String tipus_cel, String adj) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		ctP.desar_hidato(nom,hidato,tipus_cel,adj);
	}
        
	public void desar_hidato(String nom, String hidato[][], String tipus_cel) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		ctP.desar_hidato(nom,hidato,tipus_cel);
	}
        
        
	public void elimina_usuari(String nom) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		ctP.elimina_usuari(nom);
	}
	public String[][] carregar_hidato(String nom, String tipus_cel, String adj, int num) throws FileNotExistsException, UsuariNoExisteixException, IOException, AdjacenciaNoExisteixException, CelaNoExisteixException {
		return ctP.carregar_hidato(nom,tipus_cel,adj,num);
	}
	public String[][] carregar_hidato(String nom, String tipus_cel, int num) throws FileNotExistsException, UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException {
		return ctP.carregar_hidato(nom,tipus_cel,num);
	}
	
	
	public String[][] carregar_partida(String nom, String tipus_cel, String adj, Integer num) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		return ctP.carregar_partida(nom,tipus_cel,adj,num);
	}
	public String[][] carregar_partida(String nom, String tipus_cel, Integer num) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		return ctP.carregar_partida(nom,tipus_cel,num);
	}
	
	public void desar_partida(String nom, int time, String hidato[][], String tipus_cel, String adj, Integer n) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		ctP.desar_partida(nom,time,hidato,tipus_cel,adj,n);
	}
	
	public void desar_partida(String nom, int time, String hidato[][], String tipus_cel, Integer n) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		ctP.desar_partida(nom,time,hidato,tipus_cel,n);
	}
	
	
	public ArrayList<String> get_ranking() throws FileNotFoundException, IOException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException  {
		return r.getRanking();
	}
	public void set_ranking() throws IOException, FileAlreadyExistsException{
		ctP.set_ranking(r.getRanking());
	}
	
	//Generar Hidato
	public String[][] hidato_random(String f1[], int dif) throws GetNumException {                
            
		Hidato h = new Hidato();

		h.hidato_random(f1,dif);
                
                return h.get_hidato();
                
                /*
               String[][] tab = new String[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if((j % 2) == 0) tab[i][j] = "?";
                        else tab[i][j] = Integer.toString(i) ;
                    }
                }
		return tab;*/
	}
	
	//Validar Hidato
        
	public boolean es_solucionable(String hidato[][], String f1[]) {
		Hidato h=new Hidato();
		h.llegir(hidato, f1);
		return h.te_solucio();
	}
	
	//Resoldre Hidato
	public String[][] solve(String hidato[][], String f1[]) throws HidatoNoSolucionableException, GetNumException{
		Hidato h=new Hidato();
		h.llegir(hidato,f1);
		h.solve();
		return h.get_hidato();
	}
        
        public void afegir_al_ranking(String user, int temps) throws IOException, FileAlreadyExistsException{
            System.out.println(user);
            System.out.println(Integer.toString(temps));
            r.afegir(Ranking.converteix_a_fila_ranking(user,temps));
            set_ranking();
        }
        
        public int get_num_hidatos(String user, String cela, String adj) throws UsuariNoExisteixException, FileNotExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException {
            return ctP.get_num_hidatos(user, cela, adj);
        }
}

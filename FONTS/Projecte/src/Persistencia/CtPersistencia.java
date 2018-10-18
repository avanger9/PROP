package Persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CtPersistencia {
	
	public CtPersistencia() throws IOException, FileAlreadyExistsException{
                if (!Utils_pers.existeix_directori(Utils_pers.get_directori())) {
                    Utils_pers.crea_directori((Utils_pers.get_directori()));
                    Utils_pers.crea_directori(Utils_pers.get_nom_carpeta_hidatos());
                    Utils_pers.construeix_carpetes(Utils_pers.get_nom_carpeta_hidatos());
                }
		if (!Ranking_pers.existeix_ranking()) {
			try {
				Ranking_pers.crear_txt();
			}catch(FileAlreadyExistsException e) {
				//No sé què fer aquí, ja que no hauria de llençar-se mai
			}
		}
		if (!Cjt_usuaris_persistencia.existeix_arxiu()) Cjt_usuaris_persistencia.crear_text();
            
	}
	
	
	public void crea_usuari(String nom) throws IOException, UsuariJaExisteixException, FileAlreadyExistsException {
		Usuari_pers user=new Usuari_pers(nom);
		user.crea_usuari(); //Propaguem l'excepció
		Cjt_usuaris_persistencia.afegir_usuari(nom);
	}
	
        
	public void elimina_usuari(String nom) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException {
		Usuari_pers user=new Usuari_pers(nom);
		user.elimina_usuari(); //Propaguem excepció
		Cjt_usuaris_persistencia.eliminar_usuari(nom);
	}
	
	/*
	 * Malgrat que un hidato no pertany a un usuari, exigim ser un usuari ja creat per poder-ne carregar un
	 */
        
	public String[][] carregar_hidato(String nom, String tipus_cel, String adj, int num) throws FileNotExistsException, UsuariNoExisteixException, IOException, AdjacenciaNoExisteixException, CelaNoExisteixException {
		Usuari_pers user=new Usuari_pers(nom);
		return user.carregar_hidato(tipus_cel,adj,num); //Propaguem l'excepció
	}
	/*
	 * Igual que abans
	 */
        
	public String[][] carregar_hidato(String nom, String tipus_cel, int num) throws FileNotExistsException, UsuariNoExisteixException, IOException, AdjacenciaNoExisteixException, CelaNoExisteixException {
		Usuari_pers user=new Usuari_pers(nom);
		return user.carregar_hidato(tipus_cel,num);
	}
	
	public void desar_hidato(String nom, String hidato[][], String tipus_cel, String adj)  throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		Usuari_pers user=new Usuari_pers(nom);
		user.desar_hidato(hidato, tipus_cel, adj);
	}
	
        
	public void desar_hidato(String nom, String hidato[][], String tipus_cel) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		Usuari_pers user=new Usuari_pers(nom);
		user.desar_hidato(hidato, tipus_cel);
	}
        
        
	/*
	 * Retorna una matriu de String
	 * La primera fila té longitud 1, i conté un String que representa l'enter retornat per la funció de Usuari_pers
	 * La resta de files tenen la mateixa longitud que les retornades per la funció de Usuari_pers
	 */
        
	public String[][] carregar_partida(String nom, String tipus_cel, String adj, Integer num) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		Usuari_pers user=new Usuari_pers(nom);
		Pair<Integer,String[][]> p=user.carregar_partida(tipus_cel, adj, num);
		String res[][]=new String[p.second.length+1][];
		res[0]=new String[1];
		res[0][0]=p.first.toString();
		for (int i=0;i<p.second.length;++i) res[i+1]=p.second[i];
		return res;
	}
	
	public String[][] carregar_partida(String nom, String tipus_cel, Integer num) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		Usuari_pers user=new Usuari_pers(nom);
		Pair<Integer,String[][]> p=user.carregar_partida(tipus_cel, num);
		String res[][]=new String[p.second.length+1][];
		res[0]=new String[1];
		res[0][0]=p.first.toString();
		for (int i=0;i<p.second.length;++i) res[i+1]=p.second[i];
		return res;
	}
	
	public void desar_partida(String nom, int time, String hidato[][], String tipus_cel, String adj, Integer n) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		Usuari_pers user=new Usuari_pers(nom);
		user.desar_partida(time, hidato, tipus_cel, adj, n);
	}
	
	public void desar_partida(String nom, int time, String hidato[][], String tipus_cel, Integer n) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		Usuari_pers user=new Usuari_pers(nom);
		user.desar_partida(time, hidato, tipus_cel, n);
	}
	
	public ArrayList<String> get_ranking() throws FileNotFoundException, IOException, FileNotExistsException, IOException, FileAlreadyExistsException {
		return Ranking_pers.carregar_ranking();
	}
	
	public void set_ranking(ArrayList<String> ranking) throws IOException, FileAlreadyExistsException{
		Ranking_pers.escriure_ranking(ranking);
	}
	
	
        public ArrayList<String> get_usuaris() throws IOException, FileNotFoundException, FileNotExistsException{        
		return Cjt_usuaris_persistencia.carregar_usuaris();
	}
        
        public int get_num_hidatos(String user, String cela, String adj) throws UsuariNoExisteixException, FileNotExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException {
            Usuari_pers usr=new Usuari_pers(user);
            return usr.consultar_num_hidatos(cela,adj);
        }
}

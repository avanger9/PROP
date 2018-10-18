package Persistencia;
import java.util.*;
import java.io.IOException;
public class Usuari_pers {
	
	private final String nom;
	private static final String MISSATGE_USUARI_NO_EXISTEIX="L'usuari no existeix";
	private static final String MISSATGE_USUARI_JA_EXISTEIX="L'usuari ja existeix";
	public Usuari_pers(String nom) { //FER QUE FACI LES CARPETES QUE TOQUEN
		this.nom=nom;
		
	}
	
	public String get_nom() {
		return nom;
	}
	
	public boolean existeix_usuari() {
		return Utils_pers.existeix_directori(Utils_pers.get_usuari_nom(nom));
	}
	
	public void crea_usuari() throws UsuariJaExisteixException, FileAlreadyExistsException {
		String dir_usuari=Utils_pers.get_usuari_nom(nom);
		if (!Utils_pers.existeix_directori(dir_usuari)) {
			Utils_pers.crea_directori(dir_usuari);
			
//			construeix_carpetes(dir_usuari+Utils_pers.get_nom_carpeta_hidatos());
			Utils_pers.construeix_carpetes(dir_usuari+Utils_pers.get_nom_carpeta_partides());
		}
		else throw new UsuariJaExisteixException(MISSATGE_USUARI_JA_EXISTEIX);
	}
	
	
	
	public void elimina_usuari() throws UsuariNoExisteixException, FileNotExistsException{
		if (!existeix_usuari()) throw new UsuariNoExisteixException(MISSATGE_USUARI_NO_EXISTEIX);
		Utils_pers.elimina_directori(Utils_pers.get_usuari_nom(nom));
	}
	
	public String[][] carregar_hidato(String tipus_cel, String adj, int num) throws FileNotExistsException, UsuariNoExisteixException, IOException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		if (!existeix_usuari()) throw new UsuariNoExisteixException(MISSATGE_USUARI_NO_EXISTEIX);
		String dir=Utils_pers.get_nom_carpeta_hidatos()+Utils_pers.get_nom_tipus(tipus_cel);
		if (!Utils_pers.es_hexagon(tipus_cel)) {
			dir+=Utils_pers.get_nom_adjacencia(adj);
		}
		dir+=Utils_pers.get_nom_hidato(num);
		if (!Utils_pers.existeix_arxiu(dir)) {
			throw new FileNotExistsException("No tenim l'hidato amb tipus de cel·la "+tipus_cel+(Utils_pers.es_hexagon(tipus_cel)?"":(" amb adjacència "+adj))+" i amb número "+num);
		}
		ArrayList<String> aux=Utils_pers.llegir(dir);
		String mat[][]=new String[aux.size()][];
		for (int i=0;i<aux.size();++i) {
			mat[i]=aux.get(i).split(",");
		}
		return mat;
	}
	
	public String[][] carregar_hidato(String tipus_cel, int num) throws FileNotExistsException, UsuariNoExisteixException, IOException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		return carregar_hidato(tipus_cel,"",num);
	}
	public void desar_hidato(String hidato[][], String tipus_cel, String adj)  throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		if (!existeix_usuari()) throw new UsuariNoExisteixException(MISSATGE_USUARI_NO_EXISTEIX);
		String dir=Utils_pers.get_nom_carpeta_hidatos()+Utils_pers.get_nom_tipus(tipus_cel);
		if (!tipus_cel.equals(Utils_pers.get_nom_hexagons())) {
			dir+=Utils_pers.get_nom_adjacencia(adj);
		}
		ArrayList<String> aux=new ArrayList<>();
		for (int i=0;i<hidato.length;++i) {
			String s="";
			for (int j=0;j<hidato[i].length;++j) {
				if (j!=0) s+=",";
				s+=hidato[i][j];
			}
			aux.add(s);
		}
		int n=Utils_pers.compta_arxius_directori(dir);
		Utils_pers.escriure(dir+Utils_pers.get_nom_hidato(n),aux);
	}
	public void desar_hidato(String hidato[][],String tipus_cel) throws UsuariNoExisteixException, FileNotExistsException, IOException, FileAlreadyExistsException, AdjacenciaNoExisteixException, CelaNoExisteixException{
		desar_hidato(hidato,tipus_cel,"");
	}
        //Comentem la funció perquè ara està malament
        
	public int consultar_num_hidatos(String tipus_cel,String adj) throws UsuariNoExisteixException, FileNotExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		if (!existeix_usuari()) throw new UsuariNoExisteixException(MISSATGE_USUARI_NO_EXISTEIX);
		if (tipus_cel==null) return Utils_pers.compta_arxius_directori(Utils_pers.get_nom_carpeta_hidatos());
		if (adj==null) return Utils_pers.compta_arxius_directori(Utils_pers.get_nom_carpeta_hidatos()+Utils_pers.get_nom_tipus(tipus_cel));
		return Utils_pers.compta_arxius_directori(Utils_pers.get_nom_carpeta_hidatos()+Utils_pers.get_nom_tipus(tipus_cel)+Utils_pers.get_nom_adjacencia(adj));
	}
	/*
	 * Rep el temps, l'hidato (modificat o no), el tipus de la cel·la, el tipus d'adjacència i el número de l'hidato amb el que està jugant (si n'hi ha)
	 */
	public void desar_partida(int time, String hidato[][], String tipus_cel, String adj, Integer n) throws UsuariNoExisteixException, IOException, FileAlreadyExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		if (!existeix_usuari()) throw new UsuariNoExisteixException(MISSATGE_USUARI_NO_EXISTEIX);
		String dir=Utils_pers.get_usuari_nom(nom)+Utils_pers.get_nom_carpeta_partides()+Utils_pers.get_nom_tipus(tipus_cel);
		if (!tipus_cel.equals(Utils_pers.get_nom_hexagons())) {
			dir+=Utils_pers.get_nom_adjacencia(adj);
		}
		ArrayList<String> aux=new ArrayList<String>();
		aux.add(""+time);
		for (int i=0;i<hidato.length;++i) {
			String s="";
			for (int j=0;j<hidato[i].length;++j) {
				if (j!=0) s+=",";
				s+=hidato[i][j];
			}
			aux.add(s);
		}
		//A més dels hidatos desats, podem jugar a un hidato generat al moment. Aquest tindrà el número -1
		if (n==null) n=-1;
		Utils_pers.escriure(dir+Utils_pers.get_nom_hidato(n),aux);
	}
	public void desar_partida(int time, String hidato[][], String tipus_cel, Integer n) throws UsuariNoExisteixException, IOException, FileAlreadyExistsException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		desar_partida(time,hidato, tipus_cel, "", n);
	}
	
	public Pair<Integer,String[][]> carregar_partida(String tipus_cel, String adj, Integer num) throws UsuariNoExisteixException, FileNotExistsException, IOException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		if (!existeix_usuari()) throw new UsuariNoExisteixException(MISSATGE_USUARI_NO_EXISTEIX);
		String dir=Utils_pers.get_usuari_nom(nom)+Utils_pers.get_nom_carpeta_partides()+Utils_pers.get_nom_tipus(tipus_cel);
		if (!Utils_pers.es_hexagon(tipus_cel)) {
			dir+=Utils_pers.get_nom_adjacencia(adj);
		}
		dir+=Utils_pers.get_nom_hidato(num);
		if (!Utils_pers.existeix_arxiu(dir)) {
			//EXCEPCIÓ (?)
		}
		ArrayList<String> aux=Utils_pers.llegir(dir);
		int t=Integer.parseInt(aux.get(0));
		String mat[][]=new String[aux.size()][];
		for (int i=1;i<aux.size();++i) {
			mat[i]=aux.get(i).split(",");
		}
		return new Pair<Integer,String[][]>(t,mat);
	}
	public Pair<Integer,String[][]> carregar_partida(String tipus_cel, Integer num) throws UsuariNoExisteixException, FileNotExistsException, IOException, CelaNoExisteixException, AdjacenciaNoExisteixException{
		return carregar_partida(tipus_cel,"",num);
	}
}

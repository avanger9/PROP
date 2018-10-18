package Persistencia;
import java.util.*;
import java.io.*;
//import java.nio.file.FileAlreadyExistsException;


public class Cjt_usuaris_persistencia {

	/** 
	* @pre: cert
	* @post: crear un fitxer .txt si no existeix
	*/
    
	public static void crear_text() throws FileAlreadyExistsException, IOException {
		if (!Utils_pers.existeix_directori(Utils_pers.get_directori()))
			Utils_pers.crea_directori(Utils_pers.get_directori());
		Utils_pers.crear_txt(Utils_pers.get_arxiu_usuaris());
	}



	public static ArrayList<String> carregar_usuaris() throws IOException, FileNotFoundException, FileNotExistsException {
            
		return Utils_pers.llegir(Utils_pers.get_arxiu_usuaris());
	}
        
  	public static void afegir_usuari(String usuari) throws IOException, FileNotFoundException {
		Utils_pers.escriure_al_final(Utils_pers.get_arxiu_usuaris(),usuari);
	}
        

	public static void llegir_llista(ArrayList<String> usr) {
		for (String s : usr) {
			System.out.println(s);
		}
	}

	public static void eliminar_usuari(String usuari) throws IOException, FileAlreadyExistsException, FileNotExistsException {
		ArrayList<String> usuaris=Utils_pers.llegir(Utils_pers.get_arxiu_usuaris());
		for (int i=0;i<usuaris.size();++i) {
			if (usuaris.get(i).equals(usuari)) {
				usuaris.remove(i);
				break;
			}
		}
		
		Utils_pers.escriure(Utils_pers.get_arxiu_usuaris(),usuaris);
	}

	public static void escriure_usuaris(ArrayList<String> usuaris) throws IOException, FileAlreadyExistsException {
		Utils_pers.escriure(Utils_pers.get_arxiu_usuaris(),usuaris);
	}
	
	public static void resetejar_fitxer() throws IOException, FileAlreadyExistsException{
		Utils_pers.escriure(Utils_pers.get_arxiu_usuaris(),new ArrayList<String>());
	}
	
	public static boolean existeix_arxiu() {
		return Utils_pers.existeix_arxiu(Utils_pers.get_arxiu_usuaris());
	}
}

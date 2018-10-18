package Persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
public class Ranking_pers {
	
	
	public static void crear_txt() throws FileAlreadyExistsException, IOException {
		Utils_pers.crear_txt(Utils_pers.get_arxiu_ranking());
	}
	/*
	 * Retorna una ArrayList<String> amb el contingut del ranking
	 * El contingut de cada String és un username i un número, separats per un espai
	 */
	public static ArrayList<String> carregar_ranking() throws FileNotExistsException, IOException{
		return Utils_pers.llegir(Utils_pers.get_arxiu_ranking());
	}
	/*
	 * Rep una ArrayList<String> amb el contingut a escriure al Ranking
	 * Cada String és un username i un número, separats per un espai
	 * Si no hi ha prou usuaris al ranking, omplim amb "### ###"
	 */
	public static void escriure_ranking(ArrayList<String> ranking) throws IOException, FileAlreadyExistsException {
		Utils_pers.escriure(Utils_pers.get_arxiu_ranking(),ranking);
	}
	
	public static boolean existeix_ranking() {
		return Utils_pers.existeix_arxiu(Utils_pers.get_arxiu_ranking());
	}
}

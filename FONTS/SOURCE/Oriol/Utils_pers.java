package Persistencia;
import java.util.*;
import java.io.*;

public final class Utils_pers {
	private static final String DIRECTORI="./DadesPersistencia/";
	private static final String USUARIS=DIRECTORI+"usuaris.txt";
	private static final String RANKING=DIRECTORI+"ranking.txt";
	private static final String NOM_COSTATS="C/";
	private static final String NOM_COSTATS_ARESTES="CA/";
	private static final String NOM_HEXAGONS="H/";
	private static final String NOM_QUADRATS="Q/";
	private static final String NOM_TRIANGLES="T/";
	private static final String NOM_HIDATO="Hidato";
	private static final String NOM_CARPETA_HIDATOS=DIRECTORI+"Hidatos/";
	private static final String NOM_CARPETA_PARTIDES="Partides/";
	
	private Utils_pers(){} //Així la classe no es pot instanciar :D
	
	public static String get_directori() {
		return DIRECTORI;
	}
	public static String get_arxiu_usuaris() {
		return USUARIS;
	}
        
	public static String get_arxiu_ranking() {
		return RANKING;
	}
	public static String get_usuari_nom(String s) {
		return DIRECTORI+s+"/";
	}
	public static String get_nom_costats() {
		return NOM_COSTATS;
	}
	public static String get_nom_costats_arestes() {
		return NOM_COSTATS_ARESTES;
	}
	
	public static String get_nom_hexagons() {
		return NOM_HEXAGONS;
	}
	public static String get_nom_quadrats() {
		return NOM_QUADRATS;
	}
	public static String get_nom_triangles() {
		return NOM_TRIANGLES;
	}
	public static String get_nom_hidato(int i) {
		return NOM_HIDATO+ "_" + i;
	}
	
	public static String get_nom_tipus(String s) throws CelaNoExisteixException{
		if (s.equals("H")) return get_nom_hexagons();
		if (s.equals("Q")) return get_nom_quadrats();
		if (s.equals("T")) return get_nom_triangles();
		throw new CelaNoExisteixException("No existeix el tipus de cel·la"+s);
	}
	public static String get_nom_adjacencia(String s) throws AdjacenciaNoExisteixException{
		if (s.equals("C")) return get_nom_costats();
		if (s.equals("CA")) return get_nom_costats_arestes();
		throw new AdjacenciaNoExisteixException("No existeix el tipus d'adjacència"+s);
	}
	public static String get_nom_carpeta_hidatos() {
		return NOM_CARPETA_HIDATOS;
	}
	public static String get_nom_carpeta_partides() {
		return NOM_CARPETA_PARTIDES;
	}
	
	public static boolean es_hexagon(String s) {
		return s.equals("H");
	}
	
        
	public static void escriure(String arxiu, ArrayList<String> elements) throws IOException, FileAlreadyExistsException {
		if (!existeix_arxiu(arxiu)) crear_txt(arxiu);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(arxiu))) {
                for (int i=0;i<elements.size();++i) {
                    System.out.println("DEBUG: "+elements.get(i));
                    bw.write(elements.get(i));
                    bw.write("\n");
                }
            }
	}
	
	public static void escriure_al_final(String arxiu, String element) throws IOException{
		
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(arxiu,true))) {
                bw.write(element);
                bw.write('\n');
            }
	}
	
	public static ArrayList<String> llegir(String arxiu) throws FileNotExistsException, IOException{
		ArrayList<String> res=new ArrayList<>();
		
                //String ruta = System.getProperties().getProperty("user.dir");
                //File arxiu1 = new File("usuaris.txt");
		BufferedReader br=new BufferedReader(new FileReader(arxiu));
		String linia;
		while ((linia=br.readLine())!=null) res.add(linia);
                
		return res;
	}
        
	public static void crear_txt (String arxiu) throws FileAlreadyExistsException, IOException {
		if (existeix_arxiu(arxiu)) throw new FileAlreadyExistsException("L'arxiu ja existeix");
		File f=new File(arxiu);
		f.createNewFile();
	}
	
	public static boolean existeix_directori(String s) {
		File f=new File(s);
		return (f.exists() && f.isDirectory());
	}
	
	public static boolean existeix_arxiu(String s) {
		File f=new File(s);
		return f.exists() && f.isFile();
	}
	
	public static void crea_directori(String s) throws FileAlreadyExistsException{
		if (existeix_directori(s) || existeix_arxiu(s)) {
			throw new FileAlreadyExistsException("Ja existeix el directori "+s);
		}
		File f=new File(s);
		f.mkdirs();
	}
	
	private static void elimina_directori(File f){
		File arxius[]=f.listFiles();
		for (int i=0;i<arxius.length;++i) {
			if (arxius[i].isDirectory() && !arxius[i].getName().equals(".") && !arxius[i].getName().equals("..")) elimina_directori(arxius[i]);
			else arxius[i].delete();
		}
		f.delete();
	}
	
	public static void elimina_directori(String s) throws FileNotExistsException{
		if (!existeix_directori(s)) {
			throw new FileNotExistsException("No existeix el directori "+s);
		}
		File f=new File(s);
		elimina_directori(f);
	}
	
	public static int compta_arxius_directori(String s) throws FileNotExistsException{
		if (!existeix_directori(s)) {
			throw new FileNotExistsException("No existeix el directori "+s);
		}
		File arxius[]=new File(s).listFiles();
		int num=0;
		for (int i=0;i<arxius.length;++i) {
			String nom=arxius[i].getName();
			if (!nom.equals(".") && !nom.equals("..")) {
				if (existeix_directori(s+nom)) num+=compta_arxius_directori(s+nom);
				else ++num;
			}
		}
		return num;
	}
        public static void construeix_carpetes(String dir) throws FileAlreadyExistsException{
		Utils_pers.crea_directori(dir+Utils_pers.get_nom_hexagons());
			
		String dir_quadrats=dir+Utils_pers.get_nom_quadrats();
		Utils_pers.crea_directori(dir_quadrats+Utils_pers.get_nom_costats());
		Utils_pers.crea_directori(dir_quadrats+Utils_pers.get_nom_costats_arestes());
		
		String dir_triangles=dir+Utils_pers.get_nom_triangles();
		Utils_pers.crea_directori(dir_triangles+Utils_pers.get_nom_costats());
		Utils_pers.crea_directori(dir_triangles+Utils_pers.get_nom_costats_arestes());
	}
}

package Domini;
import java.util.*;
public class Hidato {
	private Tauler t;
//	private Tauler solucio;
	boolean es_solucionable;
	boolean solucio_comprovada=false;
	/*
	 * Rep una matriu de strings, amb l'hidato seguint el format de l'enunciat
	 * També rep un array de strings, amb la informació de l'hidato 
	 *   (tipus de cel·la, d'adjacència, nombre de files i nombre de columnes)
	 * Amb això, crea un tauler que conté aquest hidato
	 */
	public void llegir(String aux[][], String fl[]) {
		if (fl[0].equals("H")) 
			t=new Tauler_hexagon();
		else if (fl[0].equals("Q")) {
			if (fl[1].equals("C")) 	
				t=new Tauler_quadrat();
			else 
				t=new Tauler_quadrat_vertex();
		}
		else {
			if (fl[1].equals("C")) {
				t=new Tauler_triangle();
			}
			else {
				t=new Tauler_triangle_vertex();
			}
		}
		t.llegir(aux);
	}
	
	/*
	 * Escriu l'hidato
	 */
	public void escriure() {
		t.escriure();
	}
	
	/*
	 * Funció recursiva, que resol un hidato
	 * Per fer-ho, fa un DFS per recórrer els nodes adjacents a n posant n.get_value()+1
	 */
	private static boolean solve(Tauler t, Node n) {
		int val;
		try {
			val=n.get_value();
		}catch(GetNumException e) {
			e.printStackTrace();
			return false;
		}
		if (val==t.get_max()) {
			return true;
		}
		ArrayList<Node> nodes=t.get_adjacents(n);
		for (int i=0;i<nodes.size();++i) {
			Node n2=nodes.get(i);
			int v=val+1;
			if (t.es_impepinable(v)) {
				if (n2.es_nombre()){
					int val2;
					try {
						val2=n2.get_value();
					}catch(GetNumException e) {
						e.printStackTrace();
						continue;
					}
					if (val2==v) return solve(t,n2);
				}
			}
			else if (n2.es_buida()) {
				try {
					t.afegir_nombre(n2,v);
				}catch(ItemExistanceException e) {
					e.printStackTrace();
				}					
				if (solve(t,n2)) return true;
				t.eliminar_nombre(n2);
			}
		}
		return false;
	}
	
	/*
	 * Resol un hidato. Per fer-ho busca el node que conté un 1, i crida a la funció solve de dalt a partir d'aquí
	 */
	public static boolean solve(Tauler t) {
		Node n;
		try {
			n=t.get_by_value(1);
		}
		catch(ElementNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return solve(t,n);
	}
	
	/*
	 * Si l'hidato ha sigut resolt, escriu la solució
	 * Si no, informa de que no sap
	 */
	public void solve() throws HidatoNoSolucionableException{
		if (!te_solucio())
			throw new HidatoNoSolucionableException("L'hidato no té solució");
	}
	
	/*
	 * Comprova si l'hidato té solució
	 * El primer cop que ho soluciona, es guarda el resultat
	 * Així els següents cops no cal que ho faci
	 */
	public boolean te_solucio() {
		if (!solucio_comprovada) {
			es_solucionable=solve(t);
			solucio_comprovada=true;
		}
		return es_solucionable;
	}
	
	/*
	 * Rep un array de strings amb el format de l'hidato, i un enter amb la dificultat
	 * Com més nivell de dificultat, més cel·les amb ?
	 */
	public void hidato_random(String f1[], int dif) throws GetNumException {
                
		if (f1[0].equals("H")) {
			t=new Tauler_hexagon();
		}
		else if (f1[0].equals("Q")) {
			if (f1[1].equals("C"))
				t=new Tauler_quadrat();
			else
				t=new Tauler_quadrat_vertex();
		}
		else {
			if (f1[1].equals("C"))
				t=new Tauler_triangle();
			else
				t=new Tauler_triangle_vertex();
		}
		t.random_hidato(f1,dif);                  
	}
	
	public String[][] get_hidato() throws GetNumException {
		return t.get_hidato();
	}
}

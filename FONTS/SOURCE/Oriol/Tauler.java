package Domini;
import java.util.*;
import java.lang.Math.*;
import java.lang.NoSuchMethodException;
abstract public class Tauler {
	protected int intents=0;//Els intents que ha fet per fer un camí
	protected Graph g;
	protected Node mat[][];
	protected Node vec[];
	protected Set<Integer> impepinables;//Els nodes que no poden ser modificats
	protected Set<Integer> nums_existents;
	protected int max;
	protected int dif;
	
	/*
	 * Crea un graf amb les adjacències en funció del tipus de tauler que sigui
	 * Per això és abstracta, perquè a cada tauler serà diferent
	 */
	abstract public void crea_graf();
	
	/*
	 * Fa una còpia del que conté 
	 */
	abstract protected Tauler makeCopy();
	
	/*
	 * Constructora. Reinicia el comptador dels nodes
	 */
	public Tauler(){
		Node.reset_counter();
	}
	
	/*
	 * Rep una matriu de String amb el format de l'enunciat (però sense les ,)
	 * En funció d'això, crea una matriu de nodes amb els nodes tal qual estan a la matriu entrant, 
	 *   un vector de nodes amb els nodes segons les seves id, i un graf amb les adjacències
	 */
	public void llegir(String aux[][]) {
		max=0;
		impepinables=new HashSet<Integer>();
		int f=aux.length;
		int c=aux[0].length;
		vec=new Node[f*c];
		mat=new Node[f][c];
		for (int i=0;i<f;++i) {
			for (int j=0;j<c;++j) {
				if (aux[i][j].equals("#")) {
					mat[i][j]=new Node();
					mat[i][j].set_fora();
				}
				else if (aux[i][j].equals("*")) {
					mat[i][j]=new Node();
					mat[i][j].set_no_accessible();
				}
				else if (aux[i][j].equals("?")) {
					mat[i][j]=new Node();
					mat[i][j].set_buida();
					++max;
				}
				else {
					mat[i][j]=new Node();
					mat[i][j].set_nombre(Integer.parseInt(aux[i][j]));
					int val;
					try {
						val=mat[i][j].get_value();
					}catch(GetNumException e) {
						e.printStackTrace();
						continue;
					}
					impepinables.add(val);
					++max;
				}
				
				vec[mat[i][j].get_id()]=mat[i][j];
			}
		}
		crea_graf();
		nums_existents=new HashSet<Integer>(impepinables);
	}
	
	/*
	 * Escriu el contingut del tauler, seguint el format de l'enunciat
	 */
	public void escriure() {
		int f=mat.length;
		int c=mat[0].length;
		for (int i=0;i<f;++i) {
			for (int j=0;j<c;++j) {
				System.out.print(j==0?"":",");
				if(mat[i][j].es_nombre()) {
					int val;
					try {
						val=mat[i][j].get_value();
					}catch(GetNumException e) {
						e.printStackTrace();
						continue;
					}
					if (es_impepinable(val)) System.out.print(val);
					else System.out.print("?");
				}
				else if (mat[i][j].es_fora()) System.out.print("#");
				else if (mat[i][j].es_no_accessible()) System.out.print("*");
				else System.out.print("?");
			}
			System.out.println();
		}
	}
	
	public void escriure_solucio() {
		int f=mat.length;
		int c=mat[0].length;
		for (int i=0;i<f;++i) {
			for (int j=0;j<c;++j) {
				System.out.print(j==0?"":",");
				if(mat[i][j].es_nombre()) {
					int val;
					try {
						val=mat[i][j].get_value();
					}catch(GetNumException e) {
						e.printStackTrace();
						continue;
					}
					System.out.print(val);
				}
				else if (mat[i][j].es_fora()) System.out.print("#");
				else if (mat[i][j].es_no_accessible()) System.out.print("*");
				else System.out.print("?");
			}
			System.out.println();
		}
	}
	
	/*
	 * Obté el node amb id=id
	 */
	public Node get_by_id(int id) {
		return vec[id];
	}
	
	/*
	 * Obté el node que conté val
	 */
	public Node get_by_value(int val) throws ElementNotFoundException {
		for (int i=0;i<vec.length;++i) 
			if (vec[i].es_nombre()) {
				int val2;
				try {
					val2=vec[i].get_value();
				}catch(GetNumException e) {
					e.printStackTrace();
					continue;
				}
				if (val==val2) return vec[i];
			}
		
		throw new ElementNotFoundException("No he trobat cap element amb valor "+val);
//		return new Node();//Per posar algo
	}
	
	/*
	 * Obté el nombre màxim que hi ha al tauler
	 * Aquest coincideix amb la quantitat de nombres que hi haurà quan estigui resolt
	 * Que coincideix amb la suma de la quantitat de nombres i la quantitat de buits
	 */
	public int get_max() {
		return max;
	}
	
	/*
	 * Retorna els adjacents al node n
	 */
	public ArrayList<Node> get_adjacents(Node n) {
		return g.get_adjacents(n);
	}
	
	/*
	 * Comprova si el nombre és impepinable
	 *   AKA: nombre que segur que va a la posició on està
	 */
	public boolean es_impepinable(int n) {
		return impepinables.contains(n);
	}
	
	/*
	 * Afegeix el nombre al node indicat
	 * Si ja existeix el nombre a algun lloc, llença excepció
	 */
	public boolean afegir_nombre(Node n, int num)  throws ItemExistanceException {
		if (nums_existents.contains(num)) throw new ItemExistanceException("El nombre ya existeix");
		if (n.es_nombre()) {
			int val=0;
			try {
				val=n.get_value();
			}catch (GetNumException e) {
				e.printStackTrace();
			}
			if (es_impepinable(val)) return false;
			nums_existents.remove(val);
		}
		nums_existents.add(num);
		n.set_nombre(num);
		return true;
	}
	
	/*
	 * Elimina el nombre que conté el node
	 */
	public boolean eliminar_nombre(Node n) {
		if (n.es_nombre()) {
			int val=0;
			try {
				val=n.get_value();
			}catch(GetNumException e) {
				e.printStackTrace();
				return false;
			}
			if (impepinables.contains(val)) return false; 
			nums_existents.remove(val);
			n.set_buida();
		}
		return true;
	}
	
	/*
	 * retorna un nombre aleatori entre 0 i max (no inclòs
	 */
	protected static int random_number(int max) {
		Random rand=new Random();
		return rand.nextInt(max);
	}
	
	/*
	 * Rep un tauler amb un hidato ja resolt, i converteix alguns nombres en buits
	 *   Com més gran sigui dif, més nombres seran buits
	 */
	protected void crea_buits() {
		int f=mat.length;
		int c=mat[0].length;
		int glob_max=max;
		for (int i=0;i<f;++i) {
			for (int j=0;j<c;++j) {
				Node act=mat[i][j];
				if (act.es_nombre()) {
					int val=0;
					try {
						val=act.get_value();
					}catch(GetNumException e) {
						e.printStackTrace();
						continue;
					}
					if (val==1 || val==glob_max) {
						impepinables.add(val);
					}
					else {
						int r=random_number(dif+1);
						if (r!=0) {
//							--max;
							act.set_buida();
						}
						else {
							impepinables.add(val);
						}
					}
				}
			}
		}
	}
	
	/*
	 * Posa el node com a cel·la fora
	 * Recursivament aplica el mateix als adjacents que estan buits
	 */
	protected void celes_fora(Node n) {
		n.set_fora();
		ArrayList<Node> adj=g.get_adjacents(n);
		for (int i=0;i<adj.size();++i) {
			Node act=adj.get(i);
			if (act.es_buida()) {
				celes_fora(act);
			}
		}
	}
	
	/*
	 * Primer posa a fora totes les caselles que estiguin a un dels costats i estiguin buides
	 * Per cada una d'elles, crida a la funció celes_fora, per fer que totes les que hi toquin estiguin fora
	 * Finalment, posa com a no accessibles les buides que quedin
	 * Acabat això, tenim un hidato resolt. Crida la funció crea_buits, perquè passi a ser un hidato per resoldre
	 */
	protected void arregla() {
		int f=mat.length;
		int c=mat[0].length;
		for (int i=0;i<f;++i) {
			if (mat[i][0].es_buida()) celes_fora(mat[i][0]);
			if (mat[i][f-1].es_buida()) celes_fora(mat[i][f-1]);
		}
		for (int j=0;j<c;++j) {
			if (mat[0][j].es_buida()) celes_fora(mat[0][j]);
			if (mat[f-1][j].es_buida()) celes_fora(mat[c-1][j]);
		}
		for (int i=0;i<f;++i) {
			for (int j=0;j<c;++j) {
				if (mat[i][j].es_buida()) {
					mat[i][j].set_no_accessible();
				}
			}
		}
		crea_buits();
	}
	
	/*
	 * Funció recursiva que crea un hidato a partir d'un node
	 * El que fa és ordenar aleatòriament els nodes adjacents, i fa un "backtracking"
	 * El "backtracking" va provant de posar el nombre següent als adjacents que no en tenen cap, i es crida recursivament
	 * No obstant, si ha fet més de 200 backtracks, assumeix que està a una mala posició i que el cost de generar-lo serà massa alt, i retorna false
	 */
	protected boolean random_hidato(Node n) {
		ArrayList<Node> adj=new ArrayList<Node>(g.get_adjacents(n));
		Collections.shuffle(adj);
		int val;
		try {
			val=n.get_value();
		}catch(GetNumException e) {
			e.printStackTrace();
			return false;
		}
		for(int i=0;i<adj.size();++i) {
			Node act=adj.get(i);
			if (act.es_buida()) {
				act.set_nombre(val+1);
				++max;
				if (random_hidato(act)) return true;
				act.set_buida();
				--max;
				++intents;
				if (intents>200) return false;//Si ha necessitat 200 intents, abortem i tornem a provar
			}
		}
		return max>=(mat.length*mat[0].length)/1.5;
	}
	/*
	 * Crea un hidato aleatori amb el format i la dificultat indicats
	 * El que fa és anar agafant nodes aleatoris, i provant si surt un hidato prou bo a partir d'allà
	 * Si surt, l'arregla i acaba. Mentre no surt, segueix provant
	 */
	public void random_hidato(String f1[], int dif) {
		this.dif=dif;
		impepinables=new HashSet<Integer>();
		int f=Integer.parseInt(f1[2]);
		int c=Integer.parseInt(f1[3]);
		mat=new Node[f][c];
		vec=new Node[f*c];
		for (int i=0;i<f;++i) for(int j=0;j<c;++j) {
			mat[i][j]=new Node();
			mat[i][j].set_buida();
			vec[mat[i][j].get_id()]=mat[i][j];
		}
		crea_graf();
		Node n=null;//Només perquè si no ho poso, el compilador és tan inútil que no sap fer res
		do {
			if (n!=null) n.set_buida();
			n=mat[random_number(f)][random_number(c)];
			n.set_nombre(1);
			max=1;
			intents=0;
		}
		while (!random_hidato(n));
		arregla();		
		nums_existents=new HashSet<Integer>(impepinables);
	}
	
	public String[][] get_hidato() throws GetNumException {
		String res[][]=new String[mat.length][mat[0].length];
		for (int i=0;i<mat.length;++i) {
			for (int j=0;j<mat[i].length;++j) {

					if (mat[i][j].es_nombre()) res[i][j]=Integer.toString(mat[i][j].get_value());
					else if (mat[i][j].es_fora()) res[i][j]="*";
					else if (mat[i][j].es_buida()) res[i][j]="?";
					else res[i][j]="#";

			}
		}
		return res;
	}
}

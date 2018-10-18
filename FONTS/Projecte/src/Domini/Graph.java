package Domini;
import java.util.*;
import java.security.InvalidParameterException;
/*
 * Conté les adjacències entre n nodes, amb les id entre 0 i n-1
 * Els nodes han d'estar instanciats en alguna altra banda prèviament
 */
public class Graph {
	private ArrayList<Node> nodes[];
	int n;
	
	public Graph(int n) {
		this.n=n;
		nodes=new ArrayList [n];
		for(int i=0;i<n;++i) {
			nodes[i]=new ArrayList<Node>();
		}
	}
	
	/*
	 * Creadora per còpia
	 */
	public Graph(Graph g) {
		n=g.n;
		nodes=new ArrayList[n];
		System.arraycopy(g.nodes,0,nodes,0,n);
	}
	
	/*
	 * Afegeix una adjacència entre els dos nodes
	 */
	public void afegir_adjacencia (Node n1, Node n2)  throws InvalidParameterException{
		if (n1==null) System.out.println("n1 és null");
		if (n2==null) System.out.println("n2 és null");
		if (n1.get_id()>=n || n2.get_id()>=n) throw new InvalidParameterException("Els nombres 'n1' i 'n2' han de ser dins el rang");
		nodes[n1.get_id()].add(n2);
	}
	
	/*
	 * Obtenim les adjacències del node
	 */
	public ArrayList<Node> get_adjacents(Node n1)  throws InvalidParameterException{
		if (n1.get_id()>=n) throw new InvalidParameterException("El nombre 'n1' ha de ser dins el rang");
		return nodes[n1.get_id()];
	}
}

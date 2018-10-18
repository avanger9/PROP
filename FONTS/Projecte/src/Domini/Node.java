package Domini;
import java.io.IOException;
import java.security.InvalidParameterException;

public class Node {
	private enum Tipus {
		nombre, fora, no_accessible, buida
	}
	private int n;
	private int id;
	private static int id_counter=0;
	private Tipus t;
	
	public Node() {
		id=Node.id_counter++;
	}
	
	public Node(Node n) {
		this.n=n.n;
		id=n.id;
		id=n.id;
		t=n.t;
	}
	
	/*
	 * Mira si el valor contingut és un nombre
	 */
	public boolean es_nombre() {
		return t==Tipus.nombre;
	}
	
	/*
	 * Mira si el valor contingut és fora
	 */
	public boolean es_fora() {
		return t==Tipus.fora;
	}
	
	/*
	 * Mira si el valor contingut és no accessible
	 */
	public boolean es_no_accessible() {
		return t==Tipus.no_accessible;
	}
	
	/*
	 * Mira si el valor contingut és buit
	 */
	public boolean es_buida() {
		return t==Tipus.buida;
	}
	
	/*
	 * Posa un nombre a la cel·la
	 * Llença una excepció si el nombre és <=0
	 */
	public void set_nombre(int n)  throws InvalidParameterException{
		if (n<=0) throw new InvalidParameterException("El nombre ha de ser major que 0");
		this.n=n;
		t=Tipus.nombre;
	}
	
	/*
	 * Fa que la cel·la passi a estar fora
	 */
	public void set_fora() {
		t=Tipus.fora;
	}
	
	/*
	 * Fa que la cel·la passi a ser no accessible
	 */
	public void set_no_accessible() {
		t=Tipus.no_accessible;
	}
	
	/*
	 * Fa que la cel·la passi a ser buida
	 */
	public void set_buida(){
		t=Tipus.buida;
	}
	
	/*
	 * Obté el valor que conté aquella cel·la
	 * Si no és un número, llença una excepció
	 */
	public int get_value() throws GetNumException{
		if (t!=Tipus.nombre) throw new GetNumException("No existeix el nombre demanat");		
		return n;
	}
	
	/*
	 * Obté la id de la cel·la
	 */
	public int get_id() {
		return id;
	}
	
	/*
	 * Posa a 0 el comptador de les id
	 */
	public static void reset_counter() {
		id_counter=0;
	}
}

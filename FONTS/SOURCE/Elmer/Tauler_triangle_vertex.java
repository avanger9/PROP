package Domini;
import java.util.*;

public class Tauler_triangle_vertex extends Tauler_triangle{
	public Tauler_triangle makeCopy() {
		return  super.makeCopy();
	}
	public void crea_graf() {
		super.crea_graf();
		for (int i=0;i<mat.length;++i) {
			for (int j=0;j<mat[i].length;++j) {
				if (i-1>=0 && j-1>=0) g.afegir_adjacencia(mat[i][j],mat[i-1][j-1]);
				if (i-1>=0 && j+1<mat[i].length) g.afegir_adjacencia(mat[i][j],mat[i-1][j+1]);
				if (j-2>=0) g.afegir_adjacencia(mat[i][j],mat[i][j-2]);
				if (j+2<mat[i].length) g.afegir_adjacencia(mat[i][j],mat[i][j+2]);
				if (i+1<mat.length && j-1>=0) g.afegir_adjacencia(mat[i][j],mat[i+1][j-1]);						
				if (i+1<mat.length && j+1<mat[i].length) g.afegir_adjacencia(mat[i][j],mat[i+1][j+1]);

				if ((i+j)%2==0) {						
					if (i-1>=0) g.afegir_adjacencia(mat[i][j],mat[i-1][j]);															
					if (i+1<mat.length && j-2>=0) g.afegir_adjacencia(mat[i][j],mat[i+1][j-2]);
					if (i+1<mat.length && j+2<mat[i].length) g.afegir_adjacencia(mat[i][j],mat[i+1][j+2]);
				} 
				else {
					if (i-1>=0 && j-2>=0) g.afegir_adjacencia(mat[i][j],mat[i-1][j-2]);								
					if (i-1>=0 && j+2<mat[i].length) g.afegir_adjacencia(mat[i][j],mat[i-1][j+2]);										
					if (i+1<mat.length) g.afegir_adjacencia(mat[i][j],mat[i+1][j]);
				
				}
			}
		}
	}
}

package Domini;
import java.util.*;

public class Tauler_quadrat_vertex extends Tauler_quadrat {
	public Tauler_quadrat makeCopy() {
		return (Tauler_quadrat) super.makeCopy();
	}
	public void crea_graf() {
		super.crea_graf();
		for (int i=0;i<mat.length;++i) {
			for (int j=0;j<mat[i].length;++j) {
				if (j-1>=0 && i-1>=0) g.afegir_adjacencia(mat[i][j],mat[i-1][j-1]);
				if (j+1<mat[i].length && i-1>=0) g.afegir_adjacencia(mat[i][j],mat[i-1][j+1]);
				if (j+1<mat[i].length && i+1<mat.length) g.afegir_adjacencia(mat[i][j],mat[i+1][j+1]);
				if (j-1>=0 && i+1<mat.length) g.afegir_adjacencia(mat[i][j],mat[i+1][j-1]);
			}
		}
	}
}

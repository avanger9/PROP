/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domini;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class Ranking{

    private ArrayList<String> ranking;
    private int s;
    private static final int SMAX=10;
    

    public Ranking(ArrayList<String> ranking) {
        this.ranking=new ArrayList<String>(ranking);
        s=calc_size();
    }

    
    private int calc_size() {
            for (int i=0;i<ranking.size();++i) if (ranking.get(i).equals("### ###")) return i;
            return ranking.size();
    }
    /**
     * Consultora del tamano del ranking.
     * @return Numero de posiciones de dicho ranking.
     */
    public int size() {
        return s;
    }
    
    public ArrayList getRanking() {
            return ranking;
    }
    
    public void afegir(String fila) {
        String aux[]=fila.split(" ");
        int t=Integer.parseInt(aux[1]);
        boolean insertat=false;
        for (int i=0;i<s && !insertat;++i){
            String aux2[]=ranking.get(i).split(" ");
            if (Integer.parseInt(aux2[1])>t) {
                ranking.add(i,fila);
                insertat=true;
            }
        }
        if (insertat) {
            ++s;
            if (s>SMAX) ranking.remove(ranking.size()-1);
        }
        else if (s<SMAX) {
            ranking.add(fila);
            ++s;
        }
        
        for (String s:ranking) System.out.println(":D"+s);
    }
    
    public static String converteix_a_fila_ranking(String user, int temps) {
        return user+" "+(temps);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CM;

import GrafoMolde.Grafo;

/**
 *
 * @author LENOVO
 */
public class Floyd {
    double floyd[][];
    int ruta[][];
    Grafo<String, Double> grafo;
    
    public String mostrar(){
        String list = " ";
        for (int i = 0; i < floyd.length; i++) {
            for (int j = 0; j < floyd.length; j++) {
                list+=" "+floyd[i][j];
            }
            list+="\n";
        }
        return list;
    }
    
    public Floyd(Grafo<String, Double> grafo){
        this.grafo = grafo;
        int n = grafo.orden();
        floyd = new double[n][n];
        ruta = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                floyd[i][j] = grafo.obtCostoArista(i, j);
                ruta[i][j] = -1;
            }
        }
        costoMinimo();
    }

    private void costoMinimo() {
        for (int k = 0; k < floyd.length; k++) {
            for (int i = 0; i < floyd.length; i++) {
                for (int j = 0; j < floyd.length; j++) {
                    if(floyd[i][k]+floyd[k][j] < floyd[i][j]){
                        floyd[i][j] = floyd[i][k]+floyd[k][j];
                        ruta[i][j] = k;
                    }
                }
            }
        }
    }
    //A - B - C
            
    public void ruta(int i, int j){//A-C            A - B - C
        int k=ruta[i][j];
        if(k != -1){
            ruta(i, k);
            System.out.print(""+k); //1 -B
            ruta(k, j);
        }
    }
    
    public double getCosto(int vi, int vf){
        return floyd[vi][vf];
    }
    
    public int[][] getR(){
        return ruta;
    }
}

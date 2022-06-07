/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoMolde;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class GrafoMat<V, A> implements Grafo<V, A> {
    private ArrayList<V> vertices;
    private Object aristas[][];
    public static Double inf = 9999.0;
    
    //Dado un vertice y utilizando el recorrido por profundidad, 
    //indique el costo de cada una de las posibles rutas del vertice dato a los que demás vertices
    
    public void copiar(ArrayList<V> vertices, Object aristas[][]){
        this.vertices = new ArrayList<>();
        this.aristas = new Object[aristas.length][aristas.length];
        this.vertices = vertices;
        this.aristas = aristas;
    }
    
    public GrafoMat() {
        vertices = new ArrayList<>();
        aristas = new Object[100][100];
        for (int i = 0; i < aristas.length; i++) {
            for (int j = 0; j < aristas.length; j++) {
                if(i == j){
                    aristas[i][j] = 0.0;
                }else{
                    aristas[i][j] = inf;
                }
            }
        }
    }
    
    @Override
    public void insVertice(V x) {
        vertices.add(x);
    }

    @Override
    public V obtVertice(int pos) {
        if(pos >= 0 && pos < orden())
            return vertices.get(pos);
        return null;
    }

    @Override
    public void insArista(int vi, int vf, A costo) {
        aristas[vi][vf] = costo;
    }

    @Override
    public A obtCostoArista(int vi, int vf) {
        return (A) aristas[vi][vf];
    }

    @Override
    public int orden() {
        return vertices.size();
    }

    @Override
    public ArrayList<V> sucesores(int pos) {
        ArrayList<V> aux = new ArrayList<>();
        for (int i = 0; i < orden(); i++) {
            if(pos != i &&  !aristas[pos][i].equals(inf)){
                aux.add(vertices.get(i));
            }
        }
        return aux;
    }

    @Override
    public String mostrar() {
        String informacion = vertices.toString() +"\n";
        for (int i = 0; i < orden(); i++) {
            for (int j = 0; j < orden(); j++) {
                informacion += aristas[i][j] + " ";
            }
            informacion += "\n";
        }
        return informacion;
    }
}

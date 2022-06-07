/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recorridos;

import GrafoMolde.Grafo;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class DFS<V, A> {
    HashSet<V> visitados = new HashSet<>();
    LinkedList<V> pila = new LinkedList<>();
    LinkedList<A> costos = new LinkedList<>();
    
    public void profundidad(Grafo<V, A> g, V vertice){
        System.out.println(vertice.toString());
        visitados.add(vertice);
        int pos = posicion(g, vertice);
        ArrayList<V> sucesores = g.sucesores(pos);
        for (V sucesor: sucesores) {
            if(!visitados.contains(sucesor)){
                profundidad(g, sucesor);
            }
        }
    }
    
    public void profundidadPila(Grafo<V, A> g, V vertice){
        visitados.clear();
        pila.addLast(vertice);
        while(!pila.isEmpty()){
            V verticeP = pila.removeLast();
            System.out.println(verticeP);
            visitados.add(verticeP);
            ArrayList<V> sucesores = g.sucesores(posicion(g, verticeP));
            sucesores = invertir(sucesores);
            for(V suc: sucesores){
                if(!visitados.contains(suc) && !pila.contains(suc)){
                    pila.addLast(suc);
                    costos.addLast(g.obtCostoArista(posicion(g, verticeP), posicion(g, suc)));
                }
            }
            if(!costos.isEmpty()){
                System.out.println(costos.removeLast());
            } 
        }
    }
    
    public int posicion(Grafo<V,A> g, V vertice){
        for(int i=0;i<g.orden();i++){
            if(g.obtVertice(i).equals(vertice)){
                return i;
            }
        }
        return -1;
    }

    public void limpiar() {
        pila.clear();
    }

    private ArrayList<V> invertir(ArrayList<V> sucesores) {
        ArrayList<V> aux = new ArrayList<>();
        for (int i = sucesores.size()-1; i >= 0; i--) {
            aux.add(sucesores.get(i));
        }
        return aux;
    }
    
}

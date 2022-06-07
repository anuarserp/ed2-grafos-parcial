/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recorridos;

import GrafoMolde.Grafo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author LENOVO
 */
public class BFS<V, A> {
    public HashSet<V> visitados = new HashSet<>();
    public LinkedList<V> cola = new LinkedList<>();
    
    public void limpiar(){
        visitados.clear();
    }
    
    public void anchura(Grafo<V, A> g, V vertice){
        DFS profundidad = new DFS();
        cola.addLast(vertice);
        while(!cola.isEmpty()){
            V verticeP = cola.removeFirst();
            System.out.println(verticeP.toString());
            visitados.add(verticeP);
            ArrayList<V> sucesores = g.sucesores(profundidad.posicion(g, verticeP));    
            for(V suc: sucesores){
                if(!visitados.contains(suc)){
                    cola.addLast(suc);
                }
            }
        }
    }    
}

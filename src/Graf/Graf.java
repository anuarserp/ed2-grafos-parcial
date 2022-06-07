/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graf;

import CM.Floyd;
import GrafoMolde.GrafoMat;
import java.util.ArrayList;
import recorridos.BFS;
import recorridos.DFS;

/**
 *
 * @author LENOVO
 */
public class Graf {
    GrafoMat<String, Double> grafo = new GrafoMat<String, Double>();
    GrafoMat<String, Double> aux = new GrafoMat<String, Double>();
    public Graf() {
        grafo.insVertice("A");
        grafo.insVertice("B");
        grafo.insVertice("C");
        grafo.insVertice("D");
        grafo.insArista(0, 1, 4.0); //A - B - C - D 6
        grafo.insArista(1, 0, 5.0);
        grafo.insArista(1, 3, 2.0); //0-1-3
        grafo.insArista(0, 3, 3.0); 
        grafo.insArista(2, 3, 5.0);
        grafo.insArista(2, 1, 2.0);
        grafo.insArista(3, 2, 3.0);

    }
    //Guardar el String y hacer uso de funciones String para leer las casillas
    public ArrayList<String> obtVertices(GrafoMat<String, Double> g){
        ArrayList<String> vertices = new ArrayList<>();
        for (int i = 0; i < g.orden(); i++) {
            vertices.add(g.obtVertice(i));
        }
        return vertices;
    }
    public Double[][] obtAristas(GrafoMat<String, Double> g){
        Double aristas[][] = new Double[g.orden()][g.orden()];
        for (int i = 0; i < g.orden(); i++) {
            for (int j = 0; j < g.orden(); j++) {
                aristas[i][j] = g.obtCostoArista(i, j);
            }
        }
        return aristas;
    }
    
    public void restaurar(){
        grafo.copiar(obtVertices(aux), obtAristas(aux));
    }
    public void mostrar(){
        System.out.println(grafo.mostrar());
    }
    
    public void mostrarMatrizAristas(){
        for (int i = 0; i < grafo.orden(); i++) {
            for (int j = 0; j < grafo.orden(); j++) {
                System.out.print(" "+grafo.obtCostoArista(i, j));
            }
            System.out.println();
        }
    }
    
    public ArrayList<String> antecesores(int pos){
        ArrayList<String> ant = new ArrayList<>();
        for (int i = 0; i < grafo.orden(); i++) {
            if(pos != i && !(grafo.obtCostoArista(i, pos).equals(grafo.inf))){
                ant.add(grafo.obtVertice(i));   
            }
        }
        return ant;
    }
    
    //Dado un grafo invertirlo 
    public void invertirGrafo(){
        Double aux;
        for (int i = 0; i < grafo.orden(); i++) {
            for (int j = 0; i > j; j++) {
                aux = grafo.obtCostoArista(j, i);
                grafo.insArista(j, i, grafo.obtCostoArista(i, j));
                grafo.insArista(i, j, aux);
            }
        }
    }
    
    public void anchura(){
        BFS recorridoAnchura =new BFS();
        recorridoAnchura.limpiar();
        recorridoAnchura.anchura(grafo, grafo.obtVertice(2));
    }
    
    public void profundidad(){
        DFS recorridoAnchura =new DFS();
        System.out.println("\n\nProfundidad");
        recorridoAnchura.profundidad(grafo, grafo.obtVertice(2));
        System.out.println("\n\nProfundidad con pila");
        recorridoAnchura.limpiar();
        recorridoAnchura.profundidadPila(grafo, grafo.obtVertice(2));
    }
    
    //Dado un grafo de enteros hallar la suma de todos vertices
    public float suma(){
        float suma = 0;
        for (int i = 0; i < grafo.orden(); i++) {
            System.out.println((int) grafo.obtVertice(i).charAt(0));
            suma += (int) grafo.obtVertice(i).charAt(0);
        }
        return suma;
    }
    
    /*Dado un grafo decir si está completo: Un grafo completo, es donde todos sus 
    vertices están conectados entre si y son bidireccionales*/
    private boolean estaCompleto(){
        for (int i = 0; i < grafo.orden(); i++) {
            for (int j = 0; j < grafo.orden(); j++) {
                if(grafo.obtCostoArista(i, j).equals(grafo.inf)){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void siEstaCompleto(){
        if(estaCompleto())
            System.out.println("Si está completo");
        else
            System.out.println("No está completo");
    }
    
    //Suma de costos de aristas
    public Double sumaArista(){
        Double suma = 0.0;
        for(int i=0; i < grafo.orden(); i++){
            for (int j = 0; j < grafo.orden(); j++) {
                if(i != j && !grafo.obtCostoArista(i, j).equals(grafo.inf)){
                    suma += grafo.obtCostoArista(i, j);
                }
            }
        }
        return suma;
    }
    
    //A - 5.0 -> B
    public String aristaMasLarga(){
        Double masLarga = 0.0;
        String vi = "";
        String vf = "";
        for(int i=0; i < grafo.orden(); i++){
            for (int j = 0; j < grafo.orden(); j++) {
                Double aux = grafo.obtCostoArista(i, j);
                if(i != j && !aux.equals(grafo.inf) && masLarga < aux){
                    masLarga = aux;
                    vi = grafo.obtVertice(i);
                    vf = grafo.obtVertice(j);
                }
            }
        }
        return vi+"-"+masLarga+"->"+vf;
    }
    
    //Vértice con más aristas
    public String verticeMasAristas(){
        String vertice = "";
        int masAristas = 0;
        for (int i = 0; i < grafo.orden(); i++) {
            if(grafo.sucesores(i).size() > masAristas){
                masAristas = grafo.sucesores(i).size();
                vertice = grafo.obtVertice(i);
            }
        }
        return vertice;
    }
    
    //Vértice con menos aristas
    public String verticeMenosAristas(){
        String vertice = "";
        int menosAristas = 101;
        for (int i = 0; i < grafo.orden(); i++) {
            if(grafo.sucesores(i).size() < menosAristas){
                menosAristas = grafo.sucesores(i).size();
                vertice = grafo.obtVertice(i);
            }
        }
        return vertice;
    }
    
    //Vértice con mayor cantidad de antecesores
    public String verticeMasAnt(){
        String vertice = "";
        int masAnt = 0;
        for (int i = 0; i < grafo.orden(); i++) {
            if(antecesores(i).size() > masAnt){
                masAnt = grafo.sucesores(i).size();
                vertice = grafo.obtVertice(i);
            }
        }
        return vertice;
    } 
    
    public void ruta(){
        Floyd ruta = new Floyd(grafo);
        System.out.println(ruta.mostrar());
        System.out.print("0-");
        ruta.ruta(0, 3);
        System.out.print("-3\n");
        System.out.println("Costo de la carrera de 0 a 3: "+ruta.getCosto(0, 3)*1000);
        if(grafo.obtVertice(4) == null){
            System.out.println("El dato es nulo");
        }else{
            System.out.println("El dato no es nulo");
        }
    }
    
    //Eliminar o aislar un vertice
    void aislarVertice(int v){
        aux.copiar(obtVertices(grafo), obtAristas(grafo));
	for(int i=0; i < grafo.orden(); i++){
            grafo.insArista(v, i, GrafoMat.inf);
            grafo.insArista(i,v, GrafoMat.inf);
	}
    }
    
    //Hallar el mayor costo de ir de un nodo a otro en todo el grafo - hallar cada vertice y hallar el costo
    String aristaMasCostosa(){
	String lis="";
	double mayor=-1.0;
	for(int i=0; i<grafo.orden(); i++){
		for(int j=0; j<grafo.orden(); j++){
			if(grafo.obtCostoArista(i, j)>mayor && !grafo.obtCostoArista(i, j).equals(GrafoMat.inf)){
                            mayor = grafo.obtCostoArista(i,j);
                       	    lis="la arista mas costosa va de "+grafo.obtVertice(i)+" a "+grafo.obtVertice(j)+", el costo es: "+grafo.obtCostoArista(i, j);
			}
		}
	}
	return lis;
    }
    
    //Hallar el promedio de las aristas
    double promedioArista(){
        double costo=0, iterador=0;
        for(int i=0; i < grafo.orden(); i++){
            for (int j = 0; j < grafo.orden(); j++) {
                if(!grafo.obtCostoArista(i, j).equals(GrafoMat.inf) && !grafo.obtCostoArista(i, j).equals(0.0)){
                    costo = costo +grafo.obtCostoArista(i, j);
                    iterador = iterador + 1;
                }
            }
        }
        return costo/iterador;
    }
    
    //Dado un grafo decir si es conexo. -> un grafo conexo es aquel que todos sus nodos están conectados
    // por un camino simple
    boolean grafoConex() {
        for(int i=0;i<grafo.orden();i++){
            if(grafo.sucesores(i).size()<1) {
                return false;
            } 
        }
        return true;
    }
    
    int numAristas(int v){
        return grafo.sucesores(v).size();
    }
    //Dado un vertice decir si su grado es par o impar = numero de aristas ->
    void gradoPar(int v) {
        if(numAristas(v)%2!=0){
            System.out.println("El vertice es de grado impar");
        } else{
            System.out.println("El vertice es de grado par");
        } 
    }
    
    //Dado un grafo decir si su grado es par o impar = numero de aristas de todos los nodos ->
    void grafoPar(){
        int sum=0;
        for (int i = 0; i < grafo.orden(); i++) {
            sum+=numAristas(i);
        }
        if(sum%2==0){
            System.out.println("grafo de grado par");
        }else{
            System.out.println("grafo de grado impar"); 
        }
    }               
    
    //Dado un grafo decir si es un multigrafo: un multigrafo es un grafo que acepta más de una 
    //arista entre dos vertices
    boolean multigrafo(){
        if(!grafoNulo()){
            for (int i = 0; i < grafo.orden(); i++) {
                for (int j = 0; j < i; j++) {
                    if(!grafo.obtCostoArista(i, j).equals(GrafoMat.inf) && !grafo.obtCostoArista(j, i).equals(GrafoMat.inf)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    //Decir si un grafo es nulo, un grafo nulo es un grafo que no tiene ningún vertice
    boolean grafoNulo(){
        return grafo.orden() == 0;
    }
    
    //Hallar el promedio de costos de ir de un vertice (A) a cualquier vertice sucesor (pos)
    double promedioCostos(int v) {
        ArrayList<String> verticesSuc = grafo.sucesores(v) ;
        DFS deep = new DFS();
        double sum=0;
        for(int i=0;i<verticesSuc.size();i++){
            sum += grafo.obtCostoArista(v, deep.posicion(grafo, verticesSuc.get(i)));
        }
        return sum/verticesSuc.size();
    }
    
    //Dado dos vertices (vi, vf) hallar el vertice que los conecta -> retornar el vertice
    String verticeQueConectaAnt(int vi, int vf) {
        ArrayList<String> ant = antecesores(vf);
        DFS deep = new DFS();
        for(int i=0;i<ant.size();i++){
            String vert = ant.get(i);
            int pos = deep.posicion(grafo, vert);
            ArrayList<String> anteriorAnt = antecesores(pos);
            for(int j=0;j<anteriorAnt.size();j++){
                if(anteriorAnt.get(j).equals(grafo.obtVertice(vi))){
                    return vert;
                }
            }
        }
        return " ";
    }
    
    String verticeQueConectaSuc(int vi, int vf) {
        ArrayList<String> suc = grafo.sucesores(vi);
        DFS deep = new DFS();
        for(int i=0;i<suc.size();i++){
            String vert = suc.get(i);
            int pos = deep.posicion(grafo, vert);
            ArrayList<String> siguienteSuc = grafo.sucesores(pos);
            for(int j=0;j<siguienteSuc.size();j++){
                if(siguienteSuc.get(j).equals(grafo.obtVertice(vf))){
                    return vert;
                }
            }
        }
        return " ";
    }
    
    //Dado un grafo decir si paso estructura de datos II
    
    public static void main(String[] args) {
        Graf g = new Graf();
        g.mostrar();
        g.aislarVertice(2);
        g.mostrar();
        g.restaurar();
        g.mostrar();
        g.profundidad();
        if(g.grafoConex()){
            System.out.println("El grafo es conexo");
        }else{
            System.out.println("El grafo no es conexo");
        }
        //g.restaurar();
        //g.mostrar();
        System.out.println(g.aristaMasCostosa());
        System.out.println(g.promedioArista());
        g.gradoPar(0);
        g.grafoPar();
        if(g.multigrafo()){
            System.out.println("Es multigrafo");
        }else{
            System.out.println("No es un multigrafo");
        }
        if(g.grafoNulo()){
            System.out.println("El grafo es nulo");
        }else{
            System.out.println("El grafo no es nulo");
        }
        System.out.println("El promedio de ir de el nodo A a cualquier nodo sucesor es: " + g.promedioCostos(0));
        System.out.println("El vertice entre A y C es: " + g.verticeQueConectaSuc(0, 2));
    }
}

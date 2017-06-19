import java.io.*;
import java.util.*;

public class TestClass {
	
    static class Arco {
        int nodo_adyacente;
        int peso;
        
        public Arco(int nodo_adyacente, int peso) {
            this.nodo_adyacente = nodo_adyacente;
            this.peso = peso;
        }
    }
    static class Grafo {
        int numero_nodos;
        List<Arco>[] arcos;
        
        public Grafo(int numero_nodos) {
            
            this.numero_nodos = numero_nodos;
            arcos = new ArrayList[numero_nodos];
            
            for (int i = 0; i < numero_nodos; i++) {
                arcos[i] = new ArrayList();
            }
        }
        
        public void agregarArco(int nodo_inicio, int nodo_fin, int peso) {
            arcos[nodo_inicio].add(new Arco(nodo_fin, peso));
        }
        
        public List<Arco> adyacentes(int nodo) {
            return arcos[nodo];
        }
    }
    
    public static String tiempoMinimo(Grafo g, int fuente, int destino) {
        List<Arco>[] arcos=g.arcos;
        int[] visitados=new int[arcos.length];
        for(int i=0;i<visitados.length;i++){
            visitados[i]=-1;
        }
        ArrayList<Integer> espera=new ArrayList<>();
        espera.add(fuente);
        visitados[fuente]=0;
        while(!espera.isEmpty()){
            int actual_aux = espera.remove(0);
            //System.out.println(actual_aux);
            List<Arco> actual=arcos[actual_aux];
            for(Arco it : actual){
                //System.out.println("adyacente: "+it.nodo_adyacente);
                if(visitados[it.nodo_adyacente]==-1){
                    //System.out.println("primera vez");
                    visitados[it.nodo_adyacente]=visitados[actual_aux]+it.peso;
                    espera.add(it.nodo_adyacente);
                }
                else{
                    //System.out.println("otra vez");
                    visitados[it.nodo_adyacente]=Math.min(visitados[it.nodo_adyacente], visitados[actual_aux]+it.peso); 
                }
            }
        }
        if(visitados[destino]==-1){
            return "Inalcanzable";
        }
        return Integer.toString(visitados[destino]);
    }
    
    public static void main(String[] args) throws Exception {
        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nCasos = Integer.parseInt(br.readLine());
		
		for (int caso = 1; caso <= nCasos; caso++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int numero_nodos = Integer.parseInt(st.nextToken());
			int numero_arcos = Integer.parseInt(st.nextToken());
			int fuente = Integer.parseInt(st.nextToken());
			int destino = Integer.parseInt(st.nextToken());
		    int tiempo = Integer.parseInt(st.nextToken());
            // Inicialice el grafo...
			Grafo g = new Grafo(numero_nodos);
			
			for (int i = 0; i < numero_arcos; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				g.agregarArco(u, v, tiempo);
				g.agregarArco(v, u, tiempo);
			}
			System.out.printf("Caso #%d:\n", caso);
			System.out.println(tiempoMinimo(g, fuente, destino));
		}
	}
}

package A11_DijkstraDGShortestPath;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ArrayGraph implements Graph {

	private int[][] graph;
	private int numVertices;
	private boolean directed;
	
	public ArrayGraph(int numVertices, boolean directed) {
		graph = new int[numVertices][numVertices];
		this.numVertices = numVertices;
		this.directed = directed;
	}
	
	public int numVertices() {
		return numVertices;
	}

	public boolean isDirected() {
		return directed;
	}

	public boolean hasEdge(int u, int v) {
		return (graph[u][v] > 0);
	}

	public int getEdgeWeight(int u, int v) {
		return graph[u][v];
	}

	public void addEdge(int u, int v) {
		addEdge(u, v, 1);
	}

	public void addEdge(int u, int v, int weight) {
		graph[u][v] = weight;
		if(!directed){
			graph[v][u] = weight;
		}
	}

	@Override
	public void addEdge(int u, int v, int weight, boolean charge) {
		graph[u][v] = weight;
		if(!directed){
			graph[v][u] = weight;
		}
	}

	public void removeEdge(int u, int v) {
		graph[u][v] = 0;
		if(!directed){
			graph[v][u] = 0;
		}
		// TODO
	}

	public List<WeightedEdge> getEdges(int v) {
		List<WeightedEdge> result = new LinkedList<>();
		for (int i = 0; i < numVertices; i++) {
			if(graph[v][i] != 0){
				result.add(new WeightedEdge(v,i,getEdgeWeight(v,i)));
			}
		}
		return result;
	}
}

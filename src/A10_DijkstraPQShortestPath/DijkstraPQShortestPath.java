package A10_DijkstraPQShortestPath;

import java.util.HashSet;
import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernung initialisieren
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			dist[i] = Integer.MAX_VALUE; // Summen im Graph dürfen nie mehr ergeben
		}
	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {
		VertexHeap heapy = new VertexHeap(graph.numVertices());
		HashSet<WeightedEdge> set = new HashSet<>();

		for (int i = 0; i < graph.numVertices(); i++) {
			pred[i]=-1;
			dist[i]=Integer.MAX_VALUE;
			heapy.insert(new Vertex(i,dist[i]));
		}

		dist[from]=0;
		heapy.setCost(from,0);

		while (!heapy.isEmpty()){
			Vertex current = heapy.remove();

			if(current.vertex==to){
				break;
			}
			List<WeightedEdge> edges = graph.getEdges(current.vertex);

			for (WeightedEdge we:edges) {
				if(dist[we.to_vertex]>dist[current.vertex]+we.weight){
					dist[we.to_vertex]=dist[current.vertex]+we.weight;
					pred[we.to_vertex]=current.vertex;
					heapy.setCost(we.to_vertex,dist[we.to_vertex]);
				}
			}

		}

		return true;
	}
}

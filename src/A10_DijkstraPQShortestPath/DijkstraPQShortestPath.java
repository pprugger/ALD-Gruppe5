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
		VertexHeap heapy = new VertexHeap(graph.numVertices());            //initialize heap

		for (int i = 0; i < graph.numVertices(); i++) {
			pred[i]= -1;
			//dont have to initialize dist here because that's done in initPathSearch()
			heapy.insert(new Vertex(i,dist[i]));
		}

		dist[from] = 0;                    //startvertex has distance 0 and cost 0
		heapy.setCost(from,0);

		while (!heapy.isEmpty()){
			Vertex current = heapy.remove();    //get vertex out of heap

			if(current.vertex == to) { break; } //break if we reached the destination

			List<WeightedEdge> edges = graph.getEdges(current.vertex);    //create a list of all the edges of the current vertex

			//iterate through the list and set the distances
			for (WeightedEdge we : edges) {
				if( dist[we.to_vertex] > dist[current.vertex] + we.weight)
				{
					dist[we.to_vertex] = dist[current.vertex] + we.weight;   //update distance of vertex
					pred[we.to_vertex] = current.vertex;                     //set predecessor of vertex
					heapy.setCost(we.to_vertex, dist[we.to_vertex]);          //set cost of vertex
				}
			}
		}

		return true;
	}
}

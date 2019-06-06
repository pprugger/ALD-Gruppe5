package A12_DijkstraLand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

	public static List<Integer> dijkstra(Graph g, int von, int nach) {

		int[] pred = new int[g.numVertices()];
		int[] dist = new int[g.numVertices()];
		int crossedLand;

		ArrayList<Integer> results = new ArrayList<>();
		VertexHeap heapy = new VertexHeap(g.numVertices());

		for (int i = 0; i < g.numVertices(); i++) {
			dist[i]=Integer.MAX_VALUE;
			pred[i]=-1;
			heapy.insert(new WeightedEdge(i,dist[i]));
		}

		dist[von] = 0;
		heapy.setPriority(von,0);

		while (!heapy.isEmpty()){
			WeightedEdge current = heapy.remove();
			crossedLand = 0;

			if(current.vertex == nach){
				break;
			}

			List<WeightedEdge> edgeList = g.getEdges(current.vertex);

			for (WeightedEdge we:edgeList) {

				if(!g.getLand(we.vertex).equals(g.getLand(current.vertex))){
					crossedLand = 1;
				}
				if(dist[we.vertex] > (dist[current.vertex] + we.weight + crossedLand)){

					dist[we.vertex] = dist[current.vertex] + we.weight + crossedLand;
					heapy.setPriority(we.vertex, dist[we.vertex]);
					pred[we.vertex] = current.vertex;
				}
			}
		}


		// pred ausgeben

		for(int i=1; i<pred.length; i++) {
			System.out.println(i + " über " + pred[i]);
		}


		// Way ausgeben
		System.out.println();
		ArrayList<Integer> way = predToWay(pred, von, nach);
		return way;
	}

	private static ArrayList<Integer> predToWay(int[] pred, int from, int to) {
		int current = to;
		ArrayList<Integer> way = new ArrayList<Integer>();

		while (current!=-1){
			way.add(current);
			current=pred[current];
		}

		Collections.reverse(way);

		return way;
	}
}

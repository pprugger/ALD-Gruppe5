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

		for (int i = 0; i < g.numVertices(); i++) {       //Initialize our heap, Distance at start is infinity
			dist[i] = Integer.MAX_VALUE;                  //predecessor is -1
			pred[i] = -1;
			heapy.insert(new WeightedEdge(i,dist[i]));    //add the new Weighted edge to the heap
		}

		dist[von] = 0;                                    //the start node has a distance of 0 and a priority of 0
		heapy.setPriority(von,0);

		while (!heapy.isEmpty()){
			WeightedEdge current = heapy.remove();        //get the node out of the head
			crossedLand = 0;                              //reset crossedLand to 0

			if(current.vertex == nach){                   //break of we reached the destination
				break;
			}

			List<WeightedEdge> edgeList = g.getEdges(current.vertex);     //Create a list of all the edges of current

			for (WeightedEdge we : edgeList)                               //iterate through the list
			{
				if(!g.getLand(we.vertex).equals(g.getLand(current.vertex)))   //determine if we crossed a border
				{
					crossedLand = 1;
				}

				//check if the distance from we is bigger than dist of current vertex + we.weight + crossed land
				if(dist[we.vertex] > ( dist[current.vertex] + we.weight + crossedLand ))
				{
					dist[we.vertex] = dist[current.vertex] + we.weight + crossedLand;    //set the new distance
					heapy.setPriority(we.vertex, dist[we.vertex]);                       //change the priority of we
					pred[we.vertex] = current.vertex;                                    //set the predecessor of we
				}
			}
		}

		// pred ausgeben

		for(int i = 1; i < pred.length; i++) {
			System.out.println(i + " over " + pred[i]);
		}

		// Way ausgeben
		System.out.println();
		ArrayList<Integer> way = predToWay(pred, von, nach);
		return way;
	}

	private static ArrayList<Integer> predToWay(int[] pred, int from, int to) {
		int current = to;
		ArrayList<Integer> way = new ArrayList();

		while (current != -1)            // -1 is pred from the start node
		{
			way.add(current);            //add current to the way
			current = pred[current];     //get the predecessor of the current node
		}

		//Now we have a list from the end node to the start node, so we have to reverse it
		Collections.reverse(way);

		return way;
	}
}

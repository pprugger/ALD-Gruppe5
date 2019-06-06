package A10_DijkstraPQShortestPath;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FindWay {
	protected Graph graph;
	protected int[] pred;
	
	public FindWay(Graph graph) {
		this.graph = graph;
		this.pred = new int[graph.numVertices()];
	}

	/**
	 * Liefert den Weg von (from) nach (to) als Liste zurück
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg von Start nach Ziel oder null
	 */
	public List<Integer> findWay(int from, int to) {
		initPathSearch();
		if (!calculatePath(from, to)) {
			return null;
		}
		return createWay(from, to);
	}

	/**
	 * Initialisierungsfunktion
	 */
	abstract protected void initPathSearch();

	/**
	 * Berechnungsfunktion für Weg von (from) nach (to)
	 */
	abstract protected boolean calculatePath(int from, int to);
	
	/**
	 * Weg von (to) nach (from) aus Vorgängerknoten rekonstruieren
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg als Liste
	 */
	protected ArrayList<Integer> createWay(int from, int to) {

		if(pred[to] == -1) { return null; }   //if to has no predecessor than we didn't find a way to to

		ArrayList<Integer> results = new ArrayList<>();
		int current = to;

		while (current != -1)                     //the predecessor from the first vertex is -1
		{
			results.add(current);
			current = pred[current];
		}

		Collections.reverse(results);             //now we have from the end to the start, so reverse it
		return results;
	}
}
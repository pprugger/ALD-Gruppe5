package A05_Breitensuche;

import java.sql.SQLOutput;
import java.util.*;

public class Breitensuche extends BaseTree<Integer> {

	@Override
	protected int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
	 * @param start Startknoten für Teilbaum
	 * @return Liste der Knoten in Breitenfolge
	 */
	public List<Integer> getBreadthFirstOrder(Node<Integer> start) {

		List<Integer> list = new ArrayList<>();
		list.add(start.getValue());

		Queue<Node<Integer>> queue = new LinkedList<>();

		queue.add(start);
		while (!queue.isEmpty()) {
			Node<Integer> s = queue.poll();

			if(s.getLeft() != null)
			{
				queue.add(s.getLeft());
				list.add(s.getLeft().getValue());
			}

			if(s.getRight() != null)
			{
				queue.add(s.getRight());
				list.add(s.getRight().getValue());
			}
		}

		return list;
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
	 * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
	 * @param start Startknoten für Teilbaum
	 * @param level Nur Knoten dieser Ebene ausgeben
	 * @return Liste aller Knoten
	 */
	public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {

		List<Integer> list = new ArrayList<>();
		Queue<Node<Integer>> queue = new LinkedList<Node<Integer>>();

		//Special case if we have only one level
		if(level < 2)
		{
			list.add(start.getValue());
			return list;
		}

		//Set the level of the start node to one and add to queue
		start.setLevel(1);
		queue.add(start);

		while (!queue.isEmpty()) {
			Node<Integer> parentNode = queue.poll();    //Get node from queue
			int currentLevel = parentNode.getLevel()+1; 	 //Increment the level

			if(currentLevel <= level)
			{
				if(parentNode.getLeft() != null)
				{
					parentNode.getLeft().setLevel(currentLevel);      //Set the level of the left node
					queue.add(parentNode.getLeft());               //Add the node to the queue
					//If we are on the searched level add the node value to the list
					if(currentLevel == level) {list.add(parentNode.getLeft().getValue()); }
				}

				if(parentNode.getRight() != null)
				{
					parentNode.getRight().setLevel(currentLevel);
					queue.add(parentNode.getRight());
					if(currentLevel == level ) {list.add(parentNode.getRight().getValue());}
				}
			}
		}

		return list;
	}

}

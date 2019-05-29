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

		Map<Node<Integer>, Integer> abstand = new HashMap<Node<Integer>, Integer>();
		Queue<Node<Integer>> queue = new LinkedList<Node<Integer>>();

		if(level < 2)
		{
			list.add(start.getValue());
			return list;
		}

		abstand.put(start, 0);
		queue.add(start);
		while (!queue.isEmpty()) {
			Node<Integer> s = queue.poll();
			int d = abstand.get(s)+1; // muss enthalten sein

			if(d < level)
			{
				if(s.getLeft() != null)
				{
					abstand.put(s.getLeft(), d);
					queue.add(s.getLeft());
					if(d == (level -1)) {list.add(s.getLeft().getValue()); }

					System.out.println("Wert: " + s.getLeft().getValue() );
					System.out.println("Level: " + level);
					System.out.println("d: " + d);
					System.out.println("");
				}

				if(s.getRight() != null)
				{
					abstand.put(s.getRight(), d);
					queue.add(s.getRight());
					if(d == (level -1)) {list.add(s.getRight().getValue());}
					System.out.println("Wert: " + s.getRight().getValue() );
					System.out.println("Level: " + level);
					System.out.println("d: " + d);
					System.out.println("");
				}
			}
		}

		return list;
	}

}

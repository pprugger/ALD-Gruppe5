package A06_Tiefensuche;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: Länge des Films
	 */
	protected int compare(Film a, Film b) {

		if(a.getLänge() < b.getLänge()) {return -1; }
		if(a.getLänge() > b.getLänge()) {return 1; }
		if(a.getLänge() == b.getLänge()) {return 0; }

		return 0;
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node) {

		List<String> list = new ArrayList<>();
		Stack<Node<Film>> stack = new Stack<>();
		List<String> temp;

		if(node == null) { return null; }
		//list.add(node.getValue().getTitel());

			if (node.getLeft() != null) {

				temp = new ArrayList<>();
				temp = getNodesInOrder(node.getLeft());

				for (String a: temp) {
					list.add(a);
				}
			}		//if not null, push left element to stack

			list.add(node.getValue().getTitel());
			System.out.println(node.getValue().getTitel());
			if(node.getRight() != null)
			{
				temp = new ArrayList<>();
				temp = getNodesInOrder(node.getRight());

				for (String a: temp) {
					list.add(a);
				}
			}		//if not null, push right element to stack

		//System.out.println("List End");

		return list;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {


		Node<Film> node = root;
		List<String> list = new ArrayList<>();
		List<String> temp;

		Stack<Node<Film>> stack = new Stack<>();

		if(node == null) { return null; }										//check if the root of the tree is null
		stack.push(node);														//add the root of the tree to the stack

		while(true)
		{
			if(!stack.empty()) { node = stack.pop(); }					//get the next element from stack if the stack
			else { break;}												//if it isn't empty, else break loop

			if(node.getValue().getLänge() > min && node.getValue().getLänge() < max )
			{
				list.add(node.getValue().getTitel());
				System.out.println(node.getValue().getTitel());
			}

			if(node.getRight() != null) { stack.push(node.getRight()); }	//if not null, push right element to stack
			if(node.getLeft() != null) { stack.push(node.getLeft()); }		//if not null, push left element to stack
			/* check if element contains prefix, if so add the String to the HashSet */
		}
		return list;
	}

}

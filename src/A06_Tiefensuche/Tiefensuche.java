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
		List<String> temp;

		if(node == null) { return null; }   //return null if no element in tree

			if (node.getLeft() != null)
			{
				temp = new ArrayList<>();
				temp = getNodesInOrder(node.getLeft());     //call recursively

				for (String a: temp)     //copy new elements into list
				{
					list.add(a);
				}
			}

			list.add(node.getValue().getTitel());   //Add current element to list
			//System.out.println(node.getValue().getTitel());   //DEBUG print Titel

			if(node.getRight() != null)
			{
				temp = new ArrayList<>();
				temp = getNodesInOrder(node.getRight());     //call recursively

				for (String a: temp) 	//copy new elements into list
				{
					list.add(a);
				}
			}

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
		Stack<Node<Film>> stack = new Stack<>();

		if(node == null) { return null; }                          //check if the root of the tree is null
		stack.push(node);                                          //add the root of the tree to the stack

		while(true)
		{
			if(!stack.empty()) { node = stack.pop(); }             //get the next element from stack if the stack
			else { break;}                                         //isn't empty, else break loop

			if(node.getValue().getLänge() > min && node.getValue().getLänge() < max )  //check if we are in the interval
			{
				list.add(node.getValue().getTitel());
				System.out.println(node.getValue().getTitel());
			}

			if(node.getRight() != null) { stack.push(node.getRight()); }    //if not null, push right element to stack
			if(node.getLeft() != null) { stack.push(node.getLeft()); }      //if not null, push left element to stack
		}
		return list;
	}
}

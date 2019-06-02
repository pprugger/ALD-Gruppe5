package A04_TraverseTree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Woerterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;

	public Wort getRoot() {
		return root;
	}     //return the root of the tree

	/**
	 * Zaehlt alle Woerter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der Woerter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {

		int wordcount = 0;
		if (w == null) { return 0; }		//if word is empty return 0
		else { wordcount++; }				//else add 1 to the counter

		if (w.getLeft() != null)
		{
			wordcount += countWordsInSubTree(w.getLeft());		//if the left word isn't null, call the count function
		}														//recursively with the left word

		if (w.getRight() != null)
		{
			wordcount += countWordsInSubTree(w.getRight());		//if the right word isn't null, call the count function
		}														//recursively with the right word
		return wordcount;

		/* Wordcount is saved on the stack for each function called and returned to the calling function
		   This way the first instance of the function get's all the wordcounts from the recursively called functions.
		 */
	}

	/**
	 * Liefert die Menge aller Woerter retour, die ein spezifisches Praefix haben.
	 * @param prefix Woerter muessen diesen Praefix haben
	 * @return Menge aller zutreffenden Woerter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {

		Wort word = root;
		int count = 0;
		Set<String> Strings =  new HashSet<>();
		Stack<Wort> stack = new Stack<>();


		if(word == null) { return null; }										//check if the root of the tree is null

		stack.push(word);														//add the root of the tree to the stack

		while(true)
		{
			if(!stack.empty()) { word = stack.pop(); }					//get the next element from stack if the stack
			else { break;}												//if it isn't empty, else break loop

			if (word.getLeft() != null) { stack.push(word.getLeft()); }		//if not null, push left element to stack
			if(word.getRight() != null) { stack.push(word.getRight()); }	//if not null, push right element to stack
			if(word.getWort().contains(prefix)) { Strings.add(word.getWort()); }
			/* check if element contains prefix, if so add the String to the HashSet */
		}

		return Strings;		//return the HashSet
	}

	/**
	 * Neues Wort hinzufuegen
	 * @param wort Hinzuzufuegendes Wort
	 */
	public void add(String wort) {
		Wort neu = new Wort(wort);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Wort w = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = wort.compareTo(w.getWort());
			if (vgl < 0) {			// Neues Wort ist lexikographisch kleiner
				if (w.getLeft() == null) {
					w.setLeft(neu);
					neu.setParent(w);
					return;
				}
				w = w.getLeft();
			}
			else if (vgl > 0) {		// Neues Wort ist lexikographisch groesser
				if (w.getRight() == null) {
					w.setRight(neu);
					neu.setParent(w);
					return;
				}
				w = w.getRight();
			}
			else {					// Neues Wort ist lexikographisch gleich
				return;
			}
		}
	}

	public Wort find(String s) {
		return find(root, s);
	}

	private Wort find(Wort current, String s) {
		if (current == null) {
			return null;
		}
		int vgl = s.compareTo(current.getWort());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), s);
		}
		else {				// Rechts
			return find(current.getRight(), s);
		}
	}
}

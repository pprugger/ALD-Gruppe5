package A04_TraverseTree;

import java.util.HashSet;
import java.util.Set;


public class Woerterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;

	public Wort getRoot() {
		return root;
	}

	/**
	 * Zaehlt alle Woerter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der Woerter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {

		int wordcount = 0;
		if (w == null) { return 0; }
		else { wordcount++; }

		if (w.getLeft() != null)
		{
			wordcount += countWordsInSubTree(w.getLeft());
		}

		if (w.getRight() != null)
		{
			wordcount += countWordsInSubTree(w.getRight());
		}
		return wordcount;
	}

	/**
	 * Liefert die Menge aller Woerter retour, die ein spezifisches Praefix haben.
	 * @param prefix Woerter muessen diesen Praefix haben
	 * @return Menge aller zutreffenden Woerter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {

		Wort word = null;
		int count = 0;
		Set<String> Strings =  new HashSet<String>();

		if(root == null) { return null; }
		if(root.getWort().contains(prefix)) { Strings.add(root.getWort()); }

		word = find("Hom");
		System.out.println(countWordsInSubTree(word));


		return Strings;
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

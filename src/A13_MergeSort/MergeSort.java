package A13_MergeSort;

import java.util.Arrays;


public class MergeSort implements PersonenSort {
	
	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		// Start des rekursiven Aufrufs
		sort(personen, 0, personen.length-1);
	}

	/**
	 * Rekursive Funktion zum Sortieren eines Teils des Arrays
	 * @param personen Zu sortierendes Array
	 * @param start    Startpunkt im Array
	 * @param end      Endpunkt im Array
	 */
	public void sort(Person[] personen, int start, int end)
	{
		int mitte;

		if(start < end) { mitte = start + (end - start) / 2; }
		else { return;}


		// F�r Merge: H�lften in eigene Arrays kopieren
		// Hinweis: bei copyOfRange ist Obergrenze exklusiv, deshalb "+ 1"
		Person[] teil1 = Arrays.copyOfRange(personen, start, mitte+1);
		Person[] teil2 = Arrays.copyOfRange(personen, mitte+1, end+1);
		// Beide H�lften zusammenf�gen und in data-Array schreiben

		sort(teil1, start, mitte);
		sort(teil2, mitte+1,end);

		merge(teil1, teil2, personen, start);
	}

	/**
	 * Merge zweier Arrays in ein Ergebnis-Array
	 * @param pers1 Erstes Array
	 * @param pers2 Zweites Array
	 * @param result Ergebnisarray
	 * @param start  Position f�r Speicherung in Ergebnisarray
	 */
	public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {

		int end = pers1.length -1 + pers2.length -1;
		int middle = end /2;

		int i = start;
		int j = start;
		int k = start;

		while(i<= middle && j <= end)
		{
			if(pers1[i].compareTo(pers2[j]) > 0)
			{
				result[k] = pers1[i];
				i++;
			}
			else
			{
				result[k] = pers2[j];
				j++;
			}
			k++;
		}

		while( i <= middle)
		{
			result[k] = pers1[i];
			k++;
			i++;
		}
	}

}

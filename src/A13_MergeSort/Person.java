package A13_MergeSort;

public class Person {
	
	private final String nachname;
	
	private final String vorname;

	public Person(String vorname, String nachname) {
		this.nachname = nachname;
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	/**
	 * Vergleicht zwei Personen miteinander
	 * @return <0, wenn a<b || =0, wenn a=b || >0, wenn a>b
	 */
	public int compareTo(Person p)
	{
		if( p.getNachname().equals(this.getNachname()))
		{
			return p.getVorname().compareTo(this.getVorname());
		}
		else
		{
			return p.getNachname().compareTo(this.getNachname());
		}
	}
}

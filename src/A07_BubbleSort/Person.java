package A07_BubbleSort;

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
		if(this.getNachname().equals(p.getNachname()))
		{
			if(this.getVorname().equals(p.getVorname())) { return 0; }
			else
			{
				return this.getVorname().compareTo(p.getVorname());
			}
		}

		return this.getNachname().compareTo(p.getNachname());
	}






}

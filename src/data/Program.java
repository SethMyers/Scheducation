package data;

/* A year of study in pursuit of a particular degree (e.g. Bachelor of Computer Science Year 2). */
public class Program {

	public final int ID;
	public final String degree;
	public final int year;

	public Program(int ID, String degree, int year) {
		this.ID = ID;
		this.degree = degree;
		this.year = year;
	}

}

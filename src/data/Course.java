package data;

/* A course (e.g. Computer Science III). */
public class Course {

	public final String ID;
	public final String name;
	public final int rating;
	
	//made a different constructor for use in the getClass() method
	public Course(String ID){
		this.ID=ID;
		name=null;
		rating=0;
	}
	public Course(String ID, String name, int rating) {
		this.ID = ID;
		this.name = name;
		this.rating = rating;
	}
	public String getID(){return ID;}
}

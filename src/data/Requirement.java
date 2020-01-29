package data;

/* Information about a course that is required for a particular degree in a particular year of study (program). */
public class Requirement {

	public final int programID;
	public final int courseID;

	public Requirement(int programID, int courseID) {
		this.programID = programID;
		this.courseID = courseID;
	}

}

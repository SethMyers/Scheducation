package data;

/* A tentative, perhaps partially filled scheduleID for a particular year of study in a particular degree program. */
public class Schedule {

	public final int ID;
	public final int programID;

	public Schedule(int ID, int programID) {
		this.ID = ID;
		this.programID = programID;
	}
}

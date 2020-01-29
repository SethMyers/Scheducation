package data;

/* An association between a class and a scheduleID. */
public class ScheduledClass {

	public final int scheduleID;
	public final int classCRN;

	public ScheduledClass(int scheduleID, int classCRN) {
		this.scheduleID = scheduleID;
		this.classCRN = classCRN;
	}

}

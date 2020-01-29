package data;

/* A lecture, lab or tutorial that occurs on specific days at a specific time (e.g. CSCI2110 Lecture Section 01). */
public class Class{

	public final int CRN;
	public final Course course;
	public int section;
	public final Period period;
	public final int creditHrs;
	public final int type;
	public final String building;
	public final int room;
	public final String lecturer;

	public Class(int CRN, Course course, Period period, int creditHrs,
	             int type, String building, int room, String lecturer) {
		this.CRN = CRN;
		this.course=course;
		this.period=period;
		this.creditHrs = creditHrs;
		this.type = type;
		this.building = building;
		this.room = room;
		this.lecturer = lecturer;
	}
}

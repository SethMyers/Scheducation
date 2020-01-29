package data;

import java.sql.Time;

/* Information about the term, days and times that a class (lecture, lab or tutorial) occurs. */
public class Period {

	public final int ID;
	public final boolean[] terms;
	public final Time startTime;
	public final Time endTime;
	public final boolean[] days;

	//new constructor for getClass()
	public Period(int ID){
		this.ID=ID;
		terms=null;
		startTime=null;
		endTime=null;
		days=null;
	}
	public Period(int ID, boolean[] terms, Time startTime, Time endTime, boolean[] days) {
		this.ID = ID;
		this.terms = terms;
		this.startTime = startTime;
		this.endTime = endTime;
		this.days = days;
	}
	public void setTerms(boolean fall, boolean winter, boolean summer){
		terms[0]=fall;
		terms[1]=winter;
		terms[2]=summer;
	}
	public void setDays(boolean mon, boolean tues, boolean wed, boolean thurs, boolean fri){
		terms[0]=mon;
		terms[1]=tues;
		terms[2]=wed;
		terms[3]=thurs;
		terms[4]=fri;
	}
}

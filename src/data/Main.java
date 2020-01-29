package data;
import java.sql.*;
import java.util.ArrayList;

public class Main {


	// JDBC driver name and database URL
	protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "Scheducation";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "seth926";

	// Current connection to the database
	public static Connection connection;

	public static void main(String[] args) {
		try {
			java.lang.Class.forName(JDBC_DRIVER);
			connectToDatabase();
			
			System.out.println("DONE");
		} catch (SQLException | ClassNotFoundException x) {
			x.printStackTrace();
		}
	}

	// Connect to the database, creating it if it doesn't exist
	public static void connectToDatabase() throws SQLException {

		System.out.printf("Connecting to %s with username %s...\n", DB_URL, USER);
		connection = DriverManager.getConnection(DB_URL, USER, PASS);

		System.out.printf("Creating database %s...\n", DB_NAME);
		connection.createStatement().executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);

		System.out.println("Connecting to database...");
		connection = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);

	}

	// Create tables if they don't exist
	public static void createTables() throws SQLException {

		System.out.println("Creating tables...");
		
		connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PROGRAM (ID int AUTO_INCREMENT NOT NULL, DEGREE varchar(64), YEAR int, PRIMARY KEY (ID));\n");
		connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS COURSE(ID varchar(64) NOT NULL, NAME varchar(64), RATING int, PRIMARY KEY (ID));\n");
		connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PERIOD(ID int AUTO_INCREMENT NOT NULL, FALL boolean, WINTER boolean, SUMMER boolean, TIME_START time, TIME_END time, MONDAY boolean, TUESDAY boolean, WEDNESDAY boolean, THURSDAY boolean, FRIDAY boolean, PRIMARY KEY (ID));\n");
		connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS SCHEDULE(ID int AUTO_INCREMENT NOT NULL, PROGRAM int, FALL_HRS int, WINTER_HRS int, SUMMER_HRS int, PRIMARY KEY (ID), FOREIGN KEY (PROGRAM) REFERENCES PROGRAM(ID));\n");
		connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS REQUIREMENT(PROGRAM int NOT NULL, COURSE varchar(64) NOT NULL, PRIMARY KEY (PROGRAM, COURSE), FOREIGN KEY (PROGRAM) REFERENCES PROGRAM(ID), FOREIGN KEY (COURSE) REFERENCES COURSE(ID));\n");
		connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS CLASS(CRN numeric(5, 0) NOT NULL, COURSE varchar(64), PERIOD int, CREDIT_HRS int, TYPE int, BUILDING varchar(64), ROOM int, LECTURER varchar(64), PRIMARY KEY (CRN), FOREIGN KEY (COURSE) REFERENCES COURSE(ID), FOREIGN KEY (PERIOD) REFERENCES PERIOD(ID));\n");
		connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS SCHEDULEDCLASS(SCHEDULE int NOT NULL, CLASS numeric(5, 0) NOT NULL, PRIMARY KEY (SCHEDULE, CLASS), FOREIGN KEY (SCHEDULE) REFERENCES SCHEDULE(ID), FOREIGN KEY (CLASS) REFERENCES CLASS(CRN));");
	}


	public static void insertInfo() throws SQLException{
		connection.createStatement().executeUpdate("INSERT INTO PROGRAM(DEGREE, YEAR) VALUES('COMPUTER SCIENCE', 2), ('HISTORY', 1), ('MANAGEMENT', 2);");
		connection.createStatement().executeUpdate("INSERT INTO COURSE VALUES('CSCI2110', 'COMPUTER SCIENCE III', 1), ('CSCI2121', 'DISCRETE STRUCTURES', -1), ('CSCI2132', 'SOFTWARE DEVELOPMENT', 1), ('HIST2002', 'LATER MEDIEVAL EUROPE', -1), ('HIST2007', 'ATLANTIC WORLD 1650-1800', 1),('HIST2015', 'WAR & SOCIETY', -1),('MGMT1000', 'INTRO TO MANAGEMENT ISSUES I', 1),('MGMT1001', 'INTRO TO MANAGEMENT ISSUES II', -1),('ECON1101', 'MICROECONOMICS', 1);");
		connection.createStatement().executeUpdate("INSERT INTO PERIOD(FALL, WINTER, SUMMER, TIME_START, TIME_END, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY) VALUES(TRUE, FALSE, FALSE, '13:35:00', '14:25:00', TRUE, FALSE, TRUE, FALSE, TRUE),(TRUE, FALSE, FALSE, '11:35:00', '12:25:00', TRUE, FALSE, TRUE, FALSE, TRUE),(FALSE, TRUE, FALSE, '10:35:00', '11:25:00', TRUE, FALSE, TRUE, FALSE, TRUE),(TRUE, FALSE, FALSE, '14:35:00', '16:25:00', FALSE, FALSE, TRUE, FALSE, FALSE),(TRUE, FALSE, FALSE, '14:35:00', '15:25:00', TRUE, FALSE, FALSE, FALSE, FALSE),(FALSE, TRUE, FALSE, '13:35:00', '14:55:00', TRUE, FALSE, TRUE, FALSE, FALSE),(FALSE, TRUE, FALSE, '12:35:00', '13:25:00', TRUE, FALSE, TRUE, FALSE, FALSE),(TRUE, FALSE, TRUE, '16:05:00', '17:25:00', FALSE, TRUE, FALSE, FALSE, FALSE),(FALSE, TRUE, FALSE, '10:05:00', '11:25:00', FALSE, TRUE, FALSE, FALSE, FALSE),(FALSE, TRUE, FALSE, '10:35:00', '11:25:00', TRUE, FALSE, TRUE, FALSE, TRUE);");
		connection.createStatement().executeUpdate("INSERT INTO SCHEDULE(PROGRAM, FALL_HRS, WINTER_HRS, SUMMER_HRS) VALUES(1, 30, 15, 0),(2, 15, 30, 0),(3, 15, 30, 0);");
		connection.createStatement().executeUpdate("INSERT INTO REQUIREMENT VALUES(2, 'CSCI2110'),(1, 'CSCI2121'),(1, 'CSCI2132'),(3, 'HIST2002'),(2, 'MGMT1000'),(2, 'MGMT1001'),(2, 'ECON1101');");
		connection.createStatement().executeUpdate("INSERT INTO CLASS VALUES(10702, 'CSCI2110', 1, 3, 0, 'JAMES DUNN', 117, 'S. SAMPALLI'),(10709, 'CSCI2121', 2, 3, 0, 'LSC', 223, 'N. KALYANIWALLA'),(10714, 'CSCI2132', 3, 3, 0, 'COMPUTER SCIENCE', 127, 'V. KESELJ'),(21168, 'HIST2002', 4, 3, 0, 'JAMES DUNN', 135, 'C. NEVILLE'),(21169, 'HIST2002', 5, 0, 2,  'LSC', 234, 'C. NEVILLE'),(21171, 'HIST2007', 6, 3, 0, 'LSC', 338, 'J. ROBERTS'),(21173, 'HIST2015', 7, 3, 0, 'CHEMISTRY', 226, 'G. HANLON'),(11757, 'MGMT1000', 8, 3, 0, 'MANAGEMENT', 1028, 'C. LEACH'),(21619, 'MGMT1001', 9, 3, 0, 'MANAGEMENT', 1028, 'C. LEACH'),(10835, 'ECON1101', 10, 3, 0, 'MANAGEMENT', 1028, 'P. KOTO');");
		connection.createStatement().executeUpdate("INSERT INTO SCHEDULEDCLASS VALUES(1, 10702),(1, 10709),(1, 10714),(2, 21168),(2, 21169),(2, 21171),(2, 21173),(3, 11757),(3, 21619),(3, 10835);");
	}
	// Return a Class object with all info about a class in the CLASS table with specific CRN (incl. info about period)
	public static Class getClass(int CRN) throws SQLException {

		PreparedStatement query = connection.prepareStatement("SELECT * FROM (CLASS JOIN PERIOD ON CLASS.PERIOD = PERIOD.ID) WHERE CRN = ?;");
		query.setInt(1, CRN);
		query.executeQuery();

		ResultSet classInfo = query.getResultSet();
		classInfo.next();
		// ... create Class object, then return it
		Class c=new Class(classInfo.getInt("CRN"), new Course(classInfo.getString("COURSE")), new Period(classInfo.getInt("PERIOD")), classInfo.getInt("CREDIT_HRS"), classInfo.getInt("TYPE"), classInfo.getString("BUILDING"), classInfo.getInt("ROOM"), classInfo.getString("LECTURER"));
		return c;
	}

	public static void addBlankClass(Course course) throws SQLException {
		String c=course.getID();
		PreparedStatement query = connection.prepareStatement("INSERT INTO CLASS(COURSE) VALUES (?);");
		query.setString(1,  c);
		query.executeUpdate();
		ResultSet classInfo = query.getResultSet();
		classInfo.next();
	}

	public static void updateClass(int CRN, Class info) throws SQLException {
        //Set up SQL statement for updating table
        PreparedStatement update = connection.prepareStatement("UPDATE CLASS SET COURSE=?, PERIOD=?, CREDIT_HRS=?, TYPE=?, BUILDING=?, ROOM=?, LECTURER=? WHERE CRN=?;");
        update.setString(1, info.course.ID);
        update.setInt(2, info.period.ID);
        update.setInt(3, info.creditHrs);
        update.setInt(4, info.type);
        update.setString(5, info.building);
        update.setInt(6, info.room);
        update.setString(7, info.lecturer);
        update.setInt(8, CRN);
        update.executeUpdate();
       
        //SQL Statement to update row in period table with corresponding period id
        PreparedStatement update2 = connection.prepareStatement("UPDATE PERIOD SET FALL=?, WINTER=?, SUMMER=?, TIME_START=?, TIME_END=?, MONDAY=?, TUESDAY=?, WEDNESDAY=?,"
                + " THURSDAY=?, FRIDAY=? WHERE ID=?;");
        for (int i=0; i<3; i++)
            update2.setBoolean(i+1, info.period.terms[i]);
        update2.setTime(4, info.period.startTime);
        update2.setTime(5, info.period.endTime);
        for (int i=0; i<5; i++)
            update2.setBoolean(i+6, info.period.days[i]);
        update2.executeUpdate();
    }

	public static void removeClass(int CRN) throws SQLException {
		//disables foreign key check so that class can be deleted
		PreparedStatement disable=connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
		PreparedStatement enable=connection.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
		PreparedStatement query = connection.prepareStatement("DELETE FROM CLASS WHERE CRN=?;");
		query.setInt(1, CRN);
		disable.executeQuery();
		query.executeUpdate();
		enable.executeQuery();
	}

	// Analogous to getClass(), return a Course object constructed from a row in the COURSE table
	public static Course getCourse(String id) throws SQLException {
		 //Pull course record with matching id as resultset
		 PreparedStatement query = connection.prepareStatement("SELECT * FROM COURSE WHERE ID = ?;");
		 query.setString(1, id);
		 ResultSet courseInfo = query.executeQuery();
		 courseInfo.next();
		 //Create new course object using values in the result set, then return it
		 Course courseTemp = new Course(courseInfo.getString(1), courseInfo.getString(2), courseInfo.getInt(3));
		 return courseTemp;
	 }
	// Analogous to addBlankClass() except no parameters cause the COURSE table doesn't reference any other table
	public static void addBlankCourse() throws SQLException {
		PreparedStatement query = connection.prepareStatement("INSERT INTO COURSE(ID) VALUES('');");
		query.executeUpdate();
	}

	// Analogous to updateClass()
	public static void updateCourse(String id, Course info) throws SQLException {
		//Prepare sql statement to update course with matching id, using values in given course object
		PreparedStatement update = connection.prepareStatement("UPDATE COURSE SET ID=?, NAME=?, RATING=? WHERE ID=?;");
		update.setString(1, info.ID);
		update.setString(2, info.name);
		update.setInt(3, info.rating);
		update.setString(4, id);
		update.executeUpdate();	
	}
	// Analogous to removeClass()
	public static void removeCourse(String id) throws SQLException {
		PreparedStatement query = connection.prepareStatement("DELETE FROM COURSE WHERE ID=?;");
		query.setString(1, id);
		query.executeUpdate();
	}
	 
	public static Period getPeriod(int id) throws SQLException {
		 //Pull records with a matching id
		 PreparedStatement query = connection.prepareStatement("SELECT * FROM PERIOD WHERE ID = ?;");
		 query.setInt(1, id);
		 //Setup a result set of these records
		 ResultSet periodInfo = query.executeQuery();
		 periodInfo.next();
		 //Initialize some boolean arrays to fill with result set info.
		 //Can't just pull a boolean array from the result set directly
		 boolean[] terms=new boolean[3];
		 boolean[] days=new boolean[5];
		 //Fill the boolean arrays with values from resultset.
		 for (int i=0; i<3; i++)
			 terms[i]= periodInfo.getBoolean(i+1);
		 for (int i=0; i<5; i++)
			 days[i]= periodInfo.getBoolean(i+6);
		 //Create new period object with given values in resultset, return it
		 Period periodTemp = new Period(periodInfo.getInt(1), terms, periodInfo.getTime(5), periodInfo.getTime(6), days);
		 return periodTemp;
	    }

	public static void updatePeriod(int id, Period info) throws SQLException {
		PreparedStatement update = connection.prepareStatement("UPDATE PERIOD SET FALL=?, WINTER=?, SUMMER=?, TIME_START=?, TIME_END=?, MONDAY=?, TUESDAY=?, WEDNESDAY=?, THURSDAY=?, FRIDAY=? WHERE ID=?;");
		
		//Set booleans for terms
		for (int i=0; i<3; i++)
			update.setBoolean(i+1, info.terms[i]);
		update.setTime(4, info.startTime);
		update.setTime(5, info.endTime);
		//set booleans for days
		for (int i=0; i<5; i++)
			update.setBoolean(i+6, info.days[i]);
		update.setInt(11, id);
		update.executeUpdate();
	}

	// Make a list of Course objects constructed from rows of the COURSE table
	public static ArrayList<Course> getCourses() throws SQLException {	 
		ArrayList<Course> courses = new ArrayList<>();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM COURSE;");
		while (resultSet.next()) { //Create a new course object for every resultset returned, add them to arraylist to return at the end
			Course courseTemp = new Course(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
            courses.add(courseTemp);
        }
        return courses;
    }

	// List of Class objects with a specific course ID - you'll need to again select from CLASS JOIN PERIOD for all info
	public static ArrayList<Class> getClassesForCourse(String courseID) throws SQLException {
		ArrayList<Class> classes = new ArrayList<Class>();
		PreparedStatement prepared = connection.prepareStatement("SELECT * FROM (CLASS JOIN PERIOD ON CLASS.PERIOD = PERIOD.ID) WHERE CLASS.COURSE =?;");
		prepared.setString(1, courseID);
		ResultSet resultSet=prepared.executeQuery();
		while(resultSet.next()){
			Class c=new Class(resultSet.getInt("CRN"), new Course(resultSet.getString("COURSE")), new Period(resultSet.getInt("PERIOD")), resultSet.getInt("CREDIT_HRS"), resultSet.getInt("TYPE"), resultSet.getString("BUILDING"), resultSet.getInt("ROOM"), resultSet.getString("LECTURER"));
			classes.add(c);
		}
		return classes;
	}
	//for testing, will print the full database
	public static void printDatabase(String d) throws SQLException{
		PreparedStatement database=connection.prepareStatement("SELECT * FROM ?;");
		database.setString(1, "COURSE");
		ResultSet result=database.executeQuery();
		while(result.next()){
			if(d.equals("PROGRAM")){
				int id=result.getInt("ID");
				String degree=result.getString("DEGREE");
				int year=result.getInt("YEAR");
				System.out.print("ID: "+id+", ");
				System.out.print("DEGREE: "+degree+", ");
				System.out.println("YEAR: "+year);
			}
			else if(d.equals("COURSE")){
				String id=result.getString("ID");
				String name=result.getString("NAME");
				int rating=result.getInt("RATING");
				System.out.print("ID: "+id+", ");
				System.out.print("NAME: "+name+", ");
				System.out.println("RATING: "+rating);
			}
			else if(d.equals("PERIOD")){
				boolean fall=result.getBoolean("FALL");
				boolean winter=result.getBoolean("WINTER");
				boolean summer=result.getBoolean("SUMMER");
				Date start=result.getDate("TIME_START");
				Date end=result.getDate("TIME_END");
				boolean mon=result.getBoolean("MONDAY");
				boolean tues=result.getBoolean("TUESDAY");
				boolean wed=result.getBoolean("WEDNESDAY");
				boolean thurs=result.getBoolean("THURSDAY");
				boolean fri=result.getBoolean("FRIDAY");
				System.out.print("FALL: "+fall+", ");
				System.out.print("WINTER: "+winter+", ");
				System.out.print("SUMMER: "+summer+", ");
				System.out.print("TIME_START: "+start+", ");
				System.out.print("TIME_END: "+end+", ");
				System.out.print("MONDAY: "+mon+", ");
				System.out.print("TUESDAY: "+tues+", ");
				System.out.print("WEDNESDAY: "+wed+", ");
				System.out.print("THURSDAY: "+thurs+", ");
				System.out.println("FRIDAY: "+fri);
			}
			else if(d.equals("SCHEDULE")){
				int id=result.getInt("ID");
				int program=result.getInt("PROGRAM");
				int fall=result.getInt("FALL_HRS");
				int winter=result.getInt("WINTER_HRS");
				int summer=result.getInt("SUMMER_HRS");
				System.out.print("ID: "+id+", ");
				System.out.print("PROGRAM: "+program+", ");
				System.out.print("FALL_HRS: "+fall+", ");
				System.out.print("WINTER_HRS: "+winter+", ");
				System.out.println("SUMMER_HRS: "+summer);
			}
			else if(d.equals("REQUIREMENT")){
				int program=result.getInt("PROGRAM");
				int course=result.getInt("COURSE");
				System.out.print("PROGRAM: "+program+", ");
				System.out.println("COURSE: "+course);
			}
			else if(d.equals("CLASS")){
				int crn=result.getInt("CRN");
				int course=result.getInt("COURSE");
				int section=result.getInt("SECTION");
				int period=result.getInt("PERIOD");
				int credit=result.getInt("CREDIT_HRS");
				int type=result.getInt("TYPE");
				String building=result.getString("BUILDING");
				int room=result.getInt("ROOM");
				String lecturer=result.getString("LECTURER");
				System.out.print("CRN: "+crn+", ");
				System.out.print("COURSE: "+course+", ");
				System.out.print("SECTION: "+section+", ");
				System.out.print("PERIOD: "+period+", ");
				System.out.print("CREDIT_HRS: "+credit+", ");
				System.out.print("TYPE: "+type+", ");
				System.out.print("BUILDING: "+building+", ");
				System.out.print("ROOM: "+room+", ");
				System.out.println("LECTURER: "+lecturer);

			}
			else if(d.equals("SCHEDULEDCLASS")){
				int schedule=result.getInt("SCHEDULE");
				int c = result.getInt("CLASS");
				System.out.print("SCHEDULE: "+schedule+", ");
				System.out.println("CLASS: "+c);
			}
		}
	}
}

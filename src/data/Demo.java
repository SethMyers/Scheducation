package data;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class Demo extends Main{

	public static void main(String[] args) {
		try {
			java.lang.Class.forName(JDBC_DRIVER);
			connectToDatabase();
			//createTables();
			//insertInfo();
			boolean[] terms=new boolean[3];
			boolean[] days=new boolean[5];
			terms[0]=true;
			terms[1]=false;
			terms[2]=false;
			days[0]=true;
			days[1]=false;
			days[2]=true;
			days[3]=false;
			days[4]=true;
			Period p=new Period(1, terms, Time.valueOf("13:35:00"), Time.valueOf("14:25:00"), days);
			updatePeriod(1, p);
			
			System.out.println("DONE");
		} catch (SQLException | ClassNotFoundException x) {
			x.printStackTrace();
		}
	}

}

package cal;

import java.text.SimpleDateFormat;
import java.util.*;;
public class Calendar1 {
	
	private int[] months = {0,1,2,3,4,5,6,7,8,9,10,11};
	public void createCalendar()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(cal.getTime().toString());
		cal.set(Calendar.MONTH, 0);
		System.out.println(cal.getTime().toString());
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 1; i < 30; i++) {
	        cal.set(Calendar.DAY_OF_MONTH, i);
	       // System.out.println(", " + df.format(cal.getTime()));
	    }
	}
	
	public void checkHolidays(Calendar cal) {
		/*Tuesday, January 1 New Year’s Day
		Monday, January 21 Birthday of Martin Luther King, Jr.
		Monday, February 18 Washington’s Birthday
		Monday, May 27 Memorial Day
		Thursday, July 4 Independence Day
		Monday, September 2 Labor Day
		Monday, October 14 Columbus Day
		Monday, November 11 Veterans Day 
		Thursday, November 28 
		Thanksgiving Day 
		Wednesday, December 25 Christmas Day
		*/
	}

}

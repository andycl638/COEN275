package cal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class EventActions {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	//private Date afterDate = dateFormat.parse("12/31/2018");
	
	// add the event the event arraylist
	public static boolean addEvent(String inputDate, String eventName, Calendar2019 cal) {
		
		if (checkDateFormat(inputDate)) {
			try {
				// convert string date to Date type
				Date eventDate = dateFormat.parse(inputDate);
				if (isValidDate(eventDate)) {
					//add event date and name to arraylist
					cal.setEvent(eventDate);
					cal.setEventName(eventName);
					return true;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return false;
		}
		
		return false;
	}
	
	// check if date is formatted as MM/DD/YYYY
	public static boolean checkDateFormat(String inputDate) {
		// regex is checking for format MM/DD/YYYY
		String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";  
		
		// date input matches pattern
	    if (Pattern.matches(regex, inputDate)) {
	    	System.out.println(inputDate +" : true");
	    	return true;
	    }
	    // date input does not match patter
	    else
	    	System.out.println(inputDate +" : false");
		return false;
	}
	
	// check if the inputed date is within 2019 calendar year
	// return true if it is
	public static boolean isValidDate(Date inputDate) throws ParseException {
		Date afterDate = dateFormat.parse("12/31/2018");
		Date beforeDate = dateFormat.parse("01/01/2020");
		
		if (inputDate.after(afterDate) && inputDate.before(beforeDate)) {
			//System.out.println("isValidDate(): valid date" + inputDate.toString());
			return true;
		}
		
		//System.out.println("isValidDate(): invalid date" + inputDate.toString());
		return false;
	}
}

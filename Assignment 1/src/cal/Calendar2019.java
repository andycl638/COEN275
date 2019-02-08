package cal;

import java.time.YearMonth;
import java.util.*;

public class Calendar2019 {
	
	private Calendar calendar;
	
	public Calendar2019() {
		
	}
	
	public Calendar2019(Calendar calendar) {
		this.calendar = calendar;
	}
	
	Calendar getCalendar() {
		return this.calendar;
	}
	
	public int getDaysInMonth(int month) {
		// Obtain the total amount of days in a given month
		YearMonth yearMonth = YearMonth.of(2019, month);
		int days = yearMonth.lengthOfMonth();
		return days;
	}
	
	// Got the idea from stackoverflow
	public String checkHolidays(Calendar cal) {
		
		//Tuesday, January 1 New Year’s Day
		if (cal.get(Calendar.DAY_OF_WEEK) == 3 && 
				cal.get(Calendar.DAY_OF_MONTH) == 1 && cal.get(Calendar.MONTH) == 0){
			return "New Year's Day";
		}
		//Monday, January 21 Birthday of Martin Luther King, Jr.
		else if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 21 && cal.get(Calendar.MONTH) == 0){
			return "Martin Luther King Jr. Day";
		}
		//Monday, February 18 Washington’s Birthday
		else if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 18 && cal.get(Calendar.MONTH) == 1){
			return "Washington’s Birthday";
		}
		//Monday, May 27 Memorial Day
		else if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 27 && cal.get(Calendar.MONTH) == 4){
			return "Memorial Day";
		}
		//Thursday, July 4 Independence Day
		else if (cal.get(Calendar.DAY_OF_WEEK) == 5 && 
				cal.get(Calendar.DAY_OF_MONTH) == 4 && cal.get(Calendar.MONTH) == 6){
			return "Independence Day";
		}
		//Monday, September 2 Labor Day
		else if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 2 && cal.get(Calendar.MONTH) == 8){
			return "Labor Day";
		}
		//Monday, October 14 Columbus Day
		else if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 14 && cal.get(Calendar.MONTH) == 9){
			return "Columbus Day";
		}
		//Monday, November 11 Veterans Day 
		else if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 11 && cal.get(Calendar.MONTH) == 10){
			return "Veterans Day";
		}
		//Thursday, November 28 Thanksgiving Day 
		else if (cal.get(Calendar.DAY_OF_WEEK) == 5 && 
				cal.get(Calendar.DAY_OF_MONTH) == 28 && cal.get(Calendar.MONTH) == 10){
			return "Thanksgiving Day";
		}
		//Wednesday, December 25 Christmas Day
		else if (cal.get(Calendar.DAY_OF_WEEK) == 4 && 
				cal.get(Calendar.DAY_OF_MONTH) == 25 && cal.get(Calendar.MONTH) == 11){
			return "Christmas Day";
		}
		else 
			return "";
		
	}
	
	public void initCalendar2019() {
		// Initialize the calendar to 2019
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2019);
		this.calendar = cal;
	}
}

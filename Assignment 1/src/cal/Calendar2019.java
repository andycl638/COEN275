package cal;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

import java.awt.*;
import javax.swing.*;

public class Calendar2019 {
	
	private String[] monthsString = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
	private String[] daysinWeeks = { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
	private String month = "";
	private Calendar calendar;
	
	public Calendar2019() {
		
	}
	public Calendar2019(Calendar calendar) {
		this.calendar = calendar;
	}
	
	private int getDaysInMonth(int month) {
		YearMonth yearMonth = YearMonth.of(2019, month);
		int days = yearMonth.lengthOfMonth();
		return days;
	}
	
	private void checkHolidays(Calendar cal) {
		
		//Tuesday, January 1 New Year’s Day
		if (cal.get(Calendar.DAY_OF_WEEK) == 3 && 
				cal.get(Calendar.DAY_OF_MONTH) == 1 && cal.get(Calendar.MONTH) == 0){
			System.out.print("*");
		}
		//Monday, January 21 Birthday of Martin Luther King, Jr.
		if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 21 && cal.get(Calendar.MONTH) == 0){
			System.out.print("*");
		}
		//Monday, February 18 Washington’s Birthday
		if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 18 && cal.get(Calendar.MONTH) == 1){
			System.out.print("*");
		}
		//Monday, May 27 Memorial Day
		if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 27 && cal.get(Calendar.MONTH) == 4){
			System.out.print("*");
		}
		//Thursday, July 4 Independence Day
		if (cal.get(Calendar.DAY_OF_WEEK) == 5 && 
				cal.get(Calendar.DAY_OF_MONTH) == 4 && cal.get(Calendar.MONTH) == 6){
			System.out.print("*");
		}
		//Monday, September 2 Labor Day
		if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 2 && cal.get(Calendar.MONTH) == 8){
			System.out.print("*");
		}
		//Monday, October 14 Columbus Day
		if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 14 && cal.get(Calendar.MONTH) == 9){
			System.out.print("*");
		}
		//Monday, November 11 Veterans Day 
		if (cal.get(Calendar.DAY_OF_WEEK) == 2 && 
				cal.get(Calendar.DAY_OF_MONTH) == 11 && cal.get(Calendar.MONTH) == 10){
			System.out.print("*");
		}
		//Thursday, November 28 Thanksgiving Day 
		if (cal.get(Calendar.DAY_OF_WEEK) == 5 && 
				cal.get(Calendar.DAY_OF_MONTH) == 28 && cal.get(Calendar.MONTH) == 10){
			System.out.print("*");
		}
		//Wednesday, December 25 Christmas Day
		if (cal.get(Calendar.DAY_OF_WEEK) == 4 && 
				cal.get(Calendar.DAY_OF_MONTH) == 25 && cal.get(Calendar.MONTH) == 11){
			System.out.print("*");
		}
		
	}
	
	private void setCalendar(Calendar c) {
		this.calendar = c;
	}
	
	private Calendar getCalendar() {
		return this.calendar;
	}
	
	public void createCalendar()
	{
		int count = 0;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2019);
		SimpleDateFormat df = new SimpleDateFormat("E-MM-dd");
		
		for (int i = 0; i < monthsString.length; i++) {
			cal.set(Calendar.MONTH, i);
			int days = getDaysInMonth(i + 1);
			System.out.println("Month: " + monthsString[i]+ " days: " + days);
			
			for (int j = 1; j <= days; j++) {
		        cal.set(Calendar.DAY_OF_MONTH, j);
		        checkHolidays(cal);
		        System.out.print(df.format(cal.getTime()) + " ");
		        count++;
		        if (count == 7) {
		        	count = 0;
		        	System.out.println("");
		        }
		    }
			count = 0;
			System.out.println("");
		}
	
		cal.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(cal.getTime().toString());
		
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		
	}

}

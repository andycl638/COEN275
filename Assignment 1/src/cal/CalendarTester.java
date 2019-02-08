package cal;

public class CalendarTester {

	public static void main(String[] args) {
		// Creating the calendar 2019 object and initializing it
		Calendar2019 cal = new Calendar2019();
		cal.initCalendar2019();
		
		// building the calendar GUI 
		LayoutManagerCalendar gui = new LayoutManagerCalendar();
		gui.constructCalendarFrame(cal);
	}

}

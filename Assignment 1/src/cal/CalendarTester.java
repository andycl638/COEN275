package cal;

public class CalendarTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calendar2019 cal = new Calendar2019();
		cal.createCalendar();
		//cal.constructCalendarFrame();
		
		LayoutManagerCalendar ui = new LayoutManagerCalendar();
		ui.constructCalendarFrame(cal);
	}

}

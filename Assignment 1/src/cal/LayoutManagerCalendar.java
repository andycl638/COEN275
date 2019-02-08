package cal;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class LayoutManagerCalendar extends JFrame {
	private static final String logo = "sculogo.png";
	private String[] daysInWeek = { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
	private String[] monthsString = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
	
	private static Color scuRed = new Color(181,0,67); // scu red color
	private static Color bgGray = new Color(77, 77, 77); // darker gray
	private static Color contentGray = new Color(179, 179, 179); // lighter grey
	
	public void constructCalendarFrame(Calendar2019 cal) {
		
		// creating three JPanel for the Calendar
		JPanel topPanel = new JPanel(new FlowLayout());
		
		JPanel midPanel = new JPanel(new FlowLayout());
		midPanel.setBackground(bgGray);
		
		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(bgGray);
		
		// setting the frame with certain attributes
		setTitle("SCU Calendar 2019");
		setLayout(new BorderLayout());
		setSize(1000, 900);
		setResizable(false);
		
		// Calling functions to construct content in the panels
		setTop(topPanel);
		setWeekGrid(midPanel);
		setDaysGrid(bottomPanel, cal);
		
		// Adding the panels to the frame
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	private void setTop(JPanel topPanel) {
		// Creating JLabel for SCU logo and date 2019
		JLabel imageL = new JLabel();
		// User html <br> to create a new line so it can align with image. Idea from stackoverflow
		JLabel dateL = new JLabel("<html> <br>2019</html>", SwingConstants.CENTER);
		
		// Customizing the image
		imageL.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(logo)).getImage().getScaledInstance(550, 104, Image.SCALE_SMOOTH)));
		imageL.setPreferredSize(new Dimension(560, 110));
		
		// Customizing the date
    	dateL.setFont(new Font("Trajan", Font.BOLD, 46));
    	dateL.setForeground(scuRed);
    	
    	// Adding the image and date to the top panel
		topPanel.add(imageL);
		topPanel.add(dateL);
	}
	
	private void setWeekGrid(JPanel midPanel) {
		// Using a grid layout to create the weeks column. Rows are not needed
		JPanel weekGridP = new JPanel(new GridLayout(0,7));
		
		// Looking through the week to created each day of the week
		for (int i = 0; i < daysInWeek.length; i++) {
	    	JLabel dayOfWeekL = new JLabel(daysInWeek[i], SwingConstants.CENTER);
	    	
			dayOfWeekL.setPreferredSize(new Dimension(170, 20));
			dayOfWeekL.setFont(new Font("Trajan", Font.PLAIN, 14));
			dayOfWeekL.setForeground(Color.white);
			dayOfWeekL.setBackground(bgGray);
			dayOfWeekL.setOpaque(true);
			weekGridP.add(dayOfWeekL);
	    }
		// Once the grid is created with the week, it is added to the mid panel
		midPanel.add(weekGridP);
	
	}
	
	private void setDayContent(JPanel bottomGrid, Calendar2019 cal) {
		Calendar c = cal.getCalendar();
		SimpleDateFormat df = new SimpleDateFormat("MMM-dd");
		
		//blocking first two cell in the grid since Jan 1 starts on Tuesday
		JPanel blockPanel1 = new JPanel();
		blockPanel1.setBackground(bgGray);
		bottomGrid.add(blockPanel1);
		
		JPanel blockPanel2 = new JPanel();
		blockPanel2.setBackground(bgGray);
		bottomGrid.add(blockPanel2);
		
		// Looking through each month
		for (int i = 0; i < monthsString.length; i++) {
			// Set the calendar to the right month
			c.set(Calendar.MONTH, i);
			
			// Getting the amount of days in the month
			int days = cal.getDaysInMonth(i + 1);
			
			// Looping through each day of the month to create components
			for (int j = 1; j <= days; j++) {
				// Each call in the grid will have 3 rows.
				JPanel dayContentGrid = new JPanel(new GridLayout(3,0));
				
				// Set the day of the calendar
		        c.set(Calendar.DAY_OF_MONTH, j);
		        
		        // check each day to see if it is a holiday and return the name
		        String holiday = cal.checkHolidays(c);
		        
		        // Create the Date and Content label. Used to populate the grid cell
		        JLabel dateL = new JLabel(df.format(c.getTime()), SwingConstants.CENTER);
		        JLabel contentL = new JLabel();
		        
		        // There is a holiday add context to the Content Label
				if (holiday.length() > 0)
				{	contentL.setFont(new Font("Trajan", Font.PLAIN, 12));
					contentL.setForeground(scuRed);
					contentL.setText(holiday);
				}
				
				// Change the date color for the first of the month
				if (c.get(Calendar.DAY_OF_MONTH) == 1){
					dateL.setForeground(Color.yellow);
				}
				
				// Customize the date label
				Color dateGray = new Color(166, 166, 166);
				dateL.setFont(new Font("Trajan", Font.PLAIN, 12));
				dateL.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
				dateL.setBackground(dateGray);
				dateL.setOpaque(true);
		
				// customize the cell grid
				dayContentGrid.setBackground(contentGray);
				dayContentGrid.setBorder(new LineBorder(Color.black, 1));
				dayContentGrid.setPreferredSize(new Dimension(150, 150));
				
				// adding 3 labels to the cell grid
				dayContentGrid.add(dateL);
				dayContentGrid.add(contentL);
				dayContentGrid.add(new JLabel());
				
				// adding the individual cell to the main grid
				bottomGrid.add(dayContentGrid);		
		    }
		}
	}
	
	private void setDaysGrid(JPanel bottomPanel, Calendar2019 cal) {
		// Using a grid layout to display each day of the month. Not setting Rows so it can grow as needed 
		JPanel bottomGridP = new JPanel(new GridLayout(0,7));
		bottomGridP.setBackground(bgGray);
		
		// Calling the function to populate the grid with days
		setDayContent(bottomGridP, cal);
		
		// Creating a scroll component for the calendar
		JScrollPane scroll = new JScrollPane(bottomGridP);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(1200,750));
		scroll.setBackground(bgGray);
		bottomPanel.add(scroll);

	}
}


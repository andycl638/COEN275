package cal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;


public class WeekDisplay {
	
	public void setWeekGrid(JPanel dateViewPanel, Calendar2019 cal) {
		// Using a grid layout to display each day of the month. Not setting Rows so it can grow as needed 
		JPanel dateGridP = new JPanel(new GridLayout(0,7));
		dateGridP.setBackground(LayoutManagerCalendar.bgGray);
		
		// Calling the function to populate the grid with days
		setDayContent(dateGridP, cal);
		
		// Creating a scroll component for the calendar
		JScrollPane scroll = new JScrollPane(dateGridP);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(15,750));
		scroll.setBackground(LayoutManagerCalendar.bgGray);
		
		dateViewPanel.add(scroll);	
	}
	
	private void setDayContent(JPanel dateGridP, Calendar2019 cal) {
		Calendar c = cal.getCalendar();
		SimpleDateFormat df = new SimpleDateFormat("MMM-dd");
		
		//blocking first two cell in the grid since Jan 1 starts on Tuesday
		JPanel blockPanel1 = new JPanel();
		blockPanel1.setBackground(LayoutManagerCalendar.bgGray);
		dateGridP.add(blockPanel1);
		
		JPanel blockPanel2 = new JPanel();
		blockPanel2.setBackground(LayoutManagerCalendar.bgGray);
		dateGridP.add(blockPanel2);
		
		// Looking through each month
		for (int i = 0; i < LayoutManagerCalendar.monthsString.length; i++) {
			// Set the calendar to the right month
			c.set(Calendar.MONTH, i);
			
			// Getting the amount of days in the month
			int days = cal.getDaysInMonth(i + 1);
			
			// Looping through each day of the month to create components
			for (int j = 1; j <= days; j++) {
				// Each call in the grid will have 3 rows.
				JPanel dateContentPanel = new JPanel();
				dateContentPanel.setLayout(new BoxLayout(dateContentPanel, BoxLayout.Y_AXIS));
				// Set the day of the calendar
		        c.set(Calendar.DAY_OF_MONTH, j);
		        
		        // check each day to see if it is a holiday and return the name
		        String holiday = cal.checkHolidays(c);
		        Color dateGray = new Color(166, 166, 166);
		        
		        String event = "";
		        if (!cal.getEventList().isEmpty()) {
		        	event = cal.checkEvents(c);
		        }
		        
		        // creating top JPanel for the Calendar
				JPanel datePanel = new JPanel(new FlowLayout());
				datePanel.setAlignmentY(Component.LEFT_ALIGNMENT);
				datePanel.setBackground(dateGray);	
				datePanel.setSize(new Dimension(150, 50));
				
				JPanel contentPanel = new JPanel(new FlowLayout());
				contentPanel.setAlignmentY(Component.LEFT_ALIGNMENT);
				contentPanel.setBackground(LayoutManagerCalendar.contentGray);	
				contentPanel.setPreferredSize(new Dimension(150, 700));
				
		        // Create the Date and Content label. Used to populate the grid cell
		        JLabel dateL = new JLabel(df.format(c.getTime()), SwingConstants.CENTER);
		        dateL.setFont(new Font("Trajan", Font.PLAIN, 12));
				dateL.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
				dateL.setBackground(dateGray);
				dateL.setOpaque(true);	
				dateL.setAlignmentY(Component.CENTER_ALIGNMENT);
				
		        JLabel contentL = new JLabel();	  
				contentL.setAlignmentY(Component.LEFT_ALIGNMENT);
				
		        // There is a holiday add context to the Content Label
				if (holiday.length() > 0)
				{	contentL.setFont(new Font("Trajan", Font.PLAIN, 12));
					contentL.setForeground(LayoutManagerCalendar.scuRed);
					contentL.setText(holiday);
				}
				
				// There is a holiday add context to the Content Label
				if (event.length() > 0)
				{	contentL.setFont(new Font("Trajan", Font.PLAIN, 12));
					contentL.setForeground(LayoutManagerCalendar.selectedColor);
					contentL.setText(event);
				}
				
				// Change the date color for the first of the month
				if (c.get(Calendar.DAY_OF_MONTH) == 1){
					dateL.setForeground(Color.yellow);
				}
				
				// customize the cell grid
				dateContentPanel.setBackground(LayoutManagerCalendar.contentGray);
				dateContentPanel.setBorder(new LineBorder(Color.black, 1));
				dateContentPanel.setPreferredSize(new Dimension(150, 750));
				
				// adding 3 labels to the cell grid
				datePanel.add(dateL);
				contentPanel.add(contentL);

				dateContentPanel.add(datePanel);
				dateContentPanel.add(contentPanel);

				// adding the individual cell to the main grid
				dateGridP.add(dateContentPanel);		
		    }
		}
	}
}

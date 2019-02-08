package cal;
import java.awt.*;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class LayoutManagerCalendar extends JFrame {
	private static final String logo = "sculogo.png";
	private String[] daysInWeek = { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
	private String[] monthsString = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
	
	public void constructCalendarFrame(Calendar2019 cal) {
		
		JPanel topPanel = new JPanel(new FlowLayout());
		JPanel midPanel = new JPanel(new FlowLayout());
		JPanel bottomPanel = new JPanel(new FlowLayout());
		
		setTitle("SCU Calendar 2019");
		setLayout(new BorderLayout());
		setSize(1000, 800);
		
		setTop(topPanel);
		setWeekGrid(midPanel);
		setDaysGrid(bottomPanel, cal);
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	private void setTop(JPanel topPanel) {
		JLabel top = new JLabel();
		topPanel.setBackground(Color.blue);
		top.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(logo)).getImage().getScaledInstance(100, 40, Image.SCALE_SMOOTH)));
    	top.setPreferredSize(new Dimension(100,50));
		top.setBorder(new LineBorder(Color.black, 2));
		topPanel.add(top);
	}
	
	private void setWeekGrid(JPanel midPanel) {
		JPanel midContent = new JPanel(new GridLayout(0,7));
		
		midPanel.setBackground(Color.red);
		
		for (int i = 0; i < daysInWeek.length; i++) {
	    	JLabel middle = new JLabel(daysInWeek[i]);
			middle.setPreferredSize(new Dimension(170, 20));
			middle.setBorder(new LineBorder(Color.black, 1));
			midContent.add(middle);
	    }
		midPanel.add(midContent);
	
	}
	
	private void setDaysGrid(JPanel bottomPanel, Calendar2019 cal) {
		JPanel bottomContent = new JPanel(new GridLayout(0,7));
		
		bottomPanel.setBackground(Color.green);
		
		for (int i = 0; i < monthsString.length; i++) {
	    	JLabel bottom = new JLabel(i + " ");
			
			bottom.setPreferredSize(new Dimension(170,150));
			bottom.setBorder(new LineBorder(Color.black, 1));
			bottomContent.add(bottom);
		}
		
		JScrollPane scroll = new JScrollPane(bottomContent);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(1200,750));
		bottomPanel.add(scroll);

	}

}

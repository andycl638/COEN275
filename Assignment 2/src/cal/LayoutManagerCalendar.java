package cal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class LayoutManagerCalendar extends JFrame {
	private static final String logo = "/sculogo.png";
	private static String[] daysInWeek = { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
	static String[] monthsString = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
	
	// Colors used in GUI
	static protected Color scuRed = new Color(181,0,67); // scu red color
	static protected Color bgGray = new Color(77, 77, 77); // darker gray
	static protected Color contentGray = new Color(179, 179, 179); // lighter grey
	static protected Color selectedColor = scuRed;
	
	private MonthDisplay monthDisplay; 
	private WeekDisplay weekDisplay;
	private DayDisplay dayDisplay;
	
	private int displayType = 0;
	
	// panels
	private JList<Object> eventList = new JList<Object>();
	private JComboBox<String> comboBox = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"red", "green", "white", "blue", "pink"}));
	JPanel dateViewPanel = new JPanel(new FlowLayout());
	JPanel weekPanel = new JPanel(new FlowLayout());
	JPanel titlePanel = new JPanel(new FlowLayout());
	
	LayoutManagerCalendar(){
		this.monthDisplay = new MonthDisplay();
		this.weekDisplay = new WeekDisplay();
		this.dayDisplay = new DayDisplay();
	}
	
	public void constructCalendarFrame(Calendar2019 cal) {
		
		// setting the frame with certain attributes
		setTitle("SCU Calendar 2019");
		setSize(1300, 970);
		setResizable(false);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		// Creating overall panel for calendar section
		JPanel calendarBoxLayout = new JPanel();
		calendarBoxLayout.setAlignmentX(Component.LEFT_ALIGNMENT);
		calendarBoxLayout.setPreferredSize(new Dimension(1100, 970));
		calendarBoxLayout.setLayout(new BoxLayout(calendarBoxLayout, BoxLayout.Y_AXIS));
		
		getContentPane().add(calendarBoxLayout);
		
		// creating title JPanel for the Calendar
		JPanel titlePanel = new JPanel(new FlowLayout());
		titlePanel.setAlignmentY(Component.LEFT_ALIGNMENT);
		calendarBoxLayout.add(titlePanel);
		setTitle(titlePanel);
		
		// Creating week panel
		
		weekPanel.setBackground(bgGray);
		weekPanel.setAlignmentY(Component.LEFT_ALIGNMENT);
		calendarBoxLayout.add(weekPanel);
		setWeekGrid(weekPanel);
		
		// creating date view panel
		dateViewPanel.setBackground(bgGray);
		dateViewPanel.setAlignmentY(Component.LEFT_ALIGNMENT);
		calendarBoxLayout.add(dateViewPanel);
		dateViewPanel.setLayout(new BoxLayout(dateViewPanel, BoxLayout.X_AXIS));
		this.monthDisplay.setMonthGrid(dateViewPanel, cal);
		
		// right panel
		JPanel eventLayout = new JPanel();
		eventLayout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		getContentPane().add(eventLayout);
		eventLayout.setLayout(new BorderLayout());
		
		// radio panel section
		JPanel togglePanel = new JPanel(new BorderLayout());
		eventLayout.add(togglePanel, BorderLayout.NORTH);
		setRadioBtn(togglePanel, cal);
		
		// event panel section
		JPanel customEventPanel = new JPanel();
		customEventPanel.setLayout(new BoxLayout(customEventPanel, BoxLayout.Y_AXIS));
		eventLayout.add(customEventPanel, BorderLayout.CENTER);
		setEvents(customEventPanel, cal);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private void setEvents (JPanel customEventPanel, Calendar2019 cal) {
		
		JPanel clearLbl2 = new JPanel();
		clearLbl2.setPreferredSize(new Dimension(300, 20));
		customEventPanel.add(clearLbl2);
		
		JLabel lblCustomizeEvent = new JLabel("Customize Event");
		lblCustomizeEvent.setAlignmentX(Component.CENTER_ALIGNMENT);
		customEventPanel.add(lblCustomizeEvent);
		
		JPanel clearLbl3 = new JPanel();
		clearLbl3.setPreferredSize(new Dimension(300, 20));
		customEventPanel.add(clearLbl3);
		
		JPanel eventCreationP = new JPanel();
		customEventPanel.add(eventCreationP);
		eventCreationP.setLayout(new BoxLayout(eventCreationP, BoxLayout.X_AXIS));
		
		JPanel eventNameP = new JPanel();
		eventCreationP.add(eventNameP);
		eventNameP.setLayout(new BoxLayout(eventNameP, BoxLayout.Y_AXIS));
		
		JLabel eventName = new JLabel("Event Name:");
		eventNameP.add(eventName);
		
		JPanel eventNameTxtPanel = new JPanel();
		eventNameP.add(eventNameTxtPanel);
		
		JTextField eventNameTxt = new JTextField();
		eventNameTxtPanel.add(eventNameTxt);
		eventNameTxt.setColumns(10);
		
		JPanel eventDateP = new JPanel();
		eventCreationP.add(eventDateP);
		eventDateP.setLayout(new BoxLayout(eventDateP, BoxLayout.Y_AXIS));
		
		JLabel eventDate = new JLabel("Event Date:");
		eventDateP.add(eventDate);
		
		JPanel eventDateTxtPanel = new JPanel();
		eventDateP.add(eventDateTxtPanel);
		
		JTextField eventDateTxt = new JTextField();
		eventDateTxtPanel.add(eventDateTxt);
		eventDateTxt.setColumns(10);
		
		JPanel eventBtnP = new JPanel();
		eventCreationP.add(eventBtnP);
		eventBtnP.setLayout(new BoxLayout(eventBtnP, BoxLayout.Y_AXIS));
		
		JLabel clearLbl4 = new JLabel(" ");
		eventBtnP.add(clearLbl4);
		
		JPanel eventBtnPanel2 = new JPanel();
		eventBtnP.add(eventBtnPanel2);
		
		JButton btnAddEvent = new JButton("Add Event");
		eventBtnPanel2.add(btnAddEvent);
		
		JPanel clearLbl5 = new JPanel();
		clearLbl5.setPreferredSize(new Dimension(300, 20));
		customEventPanel.add(clearLbl5);
		
		JPanel changeTextP = new JPanel();
		customEventPanel.add(changeTextP);
		changeTextP.setLayout(new BoxLayout(changeTextP, BoxLayout.Y_AXIS));
		
		JLabel changeTextLbl = new JLabel("Change Text Color:");
		changeTextLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
		changeTextP.add(changeTextLbl);
		
		changeTextP.add(comboBox);
		
		JPanel clearLbl6 = new JPanel();
		clearLbl6.setPreferredSize(new Dimension(300, 20));
		customEventPanel.add(clearLbl6);
		
		JPanel historyP = new JPanel();
		customEventPanel.add(historyP);
		historyP.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHistory = new JLabel("History:");
		historyP.add(lblHistory, BorderLayout.NORTH);
		
		eventList.setListData(cal.getEventNameList().toArray());
		historyP.add(eventList, BorderLayout.CENTER);
		//JTextPane textPane = new JTextPane();
		//historyP.add(textPane, BorderLayout.CENTER);
		
		JButton btnRemove = new JButton("Remove");
		historyP.add(btnRemove, BorderLayout.SOUTH);
		
		addEventAction(btnAddEvent, eventNameTxt, eventDateTxt, cal);
		removeEventAction(btnRemove, eventNameTxt, eventDateTxt, cal);

	}
	
	// pick the user selected color
	private void getPickedColor() {
		String color = comboBox.getSelectedItem().toString();
		if (color.equalsIgnoreCase("red")) {
			selectedColor = scuRed;
		}
		if (color.equalsIgnoreCase("green")) {
			selectedColor = Color.GREEN;
		}
		if (color.equalsIgnoreCase("white")) {
			selectedColor = Color.WHITE;
		}
		if (color.equalsIgnoreCase("blue")) {
			selectedColor = Color.BLUE;
		}
		if (color.equalsIgnoreCase("pink")) {
			selectedColor = Color.PINK;
		}
	}
	
	// On mouse click, it will remove the event selected by the user
	private void removeEventAction(JButton btnRemove, JTextField eventNameTxt, JTextField eventDateTxt, Calendar2019 cal) {
		btnRemove.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("remove btn clicked.");
				
				// check that an event is selected before removing it
				if (!eventList.isSelectionEmpty()) {
					// repaint month display
					if (displayType == 0) {
						cal.getEventNameList().remove(eventList.getSelectedIndex());
						cal.getEventList().remove(eventList.getSelectedIndex());
						eventList.setListData(cal.getEventNameList().toArray());
						repaintMonth(cal);
					}
					// repaint week display
					else if (displayType == 1) {
						cal.getEventNameList().remove(eventList.getSelectedIndex());
						cal.getEventList().remove(eventList.getSelectedIndex());
						eventList.setListData(cal.getEventNameList().toArray());
						repaintWeek(cal);
					}
					// repaint day display
					else if (displayType == 2) {
						cal.getEventNameList().remove(eventList.getSelectedIndex());
						cal.getEventList().remove(eventList.getSelectedIndex());
						eventList.setListData(cal.getEventNameList().toArray());
						repaintDay(cal);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select an event from the history list to remove.");
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
	}
	
	// On mouse click, add event to calendar and history
	private void addEventAction(JButton btnAddEvent, JTextField eventNameTxt, JTextField eventDateTxt, Calendar2019 cal) {
		btnAddEvent.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("add btn clicked.");
				
				// get the color picked by user
				getPickedColor();
				
				if (eventNameTxt.getText() == null || eventNameTxt.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "The event name field is empty");
				else if (eventNameTxt.getText() == null || eventDateTxt.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "The event date field is empty");
				// check if user inputed date is valid and within 2019 calendar year
				else if (EventActions.addEvent(eventDateTxt.getText(), eventNameTxt.getText(), cal)) {
					
					// repaint month display
					if (displayType == 0) {
						eventList.setListData(cal.getEventNameList().toArray());
						repaintMonth(cal);
					}
					// repaint week display
					else if (displayType == 1) {
						eventList.setListData(cal.getEventNameList().toArray());
						repaintWeek(cal);
					}
					// repaint day display
					else if (displayType == 2) {
						eventList.setListData(cal.getEventNameList().toArray());
						repaintDay(cal);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Could not add event");
				
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
	}
	
	// Create the radio buttons for month, week, and day
	private void setRadioBtn(JPanel togglePanel, Calendar2019 cal) {
		JLabel clearLbl = new JLabel();
		clearLbl.setPreferredSize(new Dimension(300, 150));
		togglePanel.add(clearLbl, BorderLayout.NORTH);
		
		JPanel radioGrid = new JPanel();
		togglePanel.add(radioGrid, BorderLayout.SOUTH);
		radioGrid.setLayout(new GridLayout(1, 0, 0, 0));
		
		JRadioButton radioMonth = new JRadioButton("Month");
		radioGrid.add(radioMonth);
		radioMonth.setSelected(true);
		
		JRadioButton radioWeek = new JRadioButton("Week");
		radioGrid.add(radioWeek);
		
		JRadioButton radioDay = new JRadioButton("Day");
		radioGrid.add(radioDay);

		radioMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("month");
				radioMonth.setSelected(true);
				radioWeek.setSelected(false);
				radioDay.setSelected(false);
				repaintMonth(cal);
			}
		});
		
		radioWeek.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("week");
				radioMonth.setSelected(false);
				radioWeek.setSelected(true);
				radioDay.setSelected(false);
				repaintWeek(cal);
			}
		});
		radioDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("day");
				radioMonth.setSelected(false);
				radioWeek.setSelected(false);
				radioDay.setSelected(true);
				repaintDay(cal);
			}
		});
		
		togglePanel.add(clearLbl, BorderLayout.NORTH);
		togglePanel.add(radioGrid, BorderLayout.SOUTH);	
	}
	
	// Create the title
	private void setTitle(JPanel topPanel) {
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
	
	// repaints the date view to display month
	private void repaintMonth(Calendar2019 cal) {
		dateViewPanel.removeAll();
		dateViewPanel.revalidate();
		dateViewPanel.repaint();
		weekPanel.removeAll();
		weekPanel.revalidate();
		weekPanel.repaint();
		setWeekGrid(weekPanel);
		displayType = 0;
		LayoutManagerCalendar.this.monthDisplay.setMonthGrid(dateViewPanel, cal);
	}
	
	// repaints the date view to display it in month
	private void repaintWeek(Calendar2019 cal) {
		dateViewPanel.removeAll();
		dateViewPanel.revalidate();
		dateViewPanel.repaint();
		weekPanel.removeAll();
		weekPanel.revalidate();
		weekPanel.repaint();
		setWeekGrid(weekPanel);
		displayType = 1;
		LayoutManagerCalendar.this.weekDisplay.setWeekGrid(dateViewPanel, cal);
	}
	
	// repaints the date view to display it in month
	private void repaintDay(Calendar2019 cal) {
		dateViewPanel.removeAll();
		dateViewPanel.revalidate();
		dateViewPanel.repaint();
		weekPanel.removeAll();
		weekPanel.revalidate();
		weekPanel.repaint();
		displayType = 2;
		LayoutManagerCalendar.this.dayDisplay.setDayGrid(dateViewPanel, cal);
	}
	
}


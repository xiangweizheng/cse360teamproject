import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.*;
import cse360teamproject.*;
import cse360teamproject.networkanalyzer.Task;
/*
 * Class 360: Intro to Software Engineering
 * Contributors: 
 * Emanuel Garcia
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class UserInterface {
	private JFrame frame;
	private JTextField task, duration, preInput;
	private JTextArea output;
	private JLabel tskLb, timeLb, preLb;
	private JButton enter, reset, quit;
	private JPanel Button_pnl, Input_pnl;
	private Choice units;
	private JSplitPane split;
	private JRadioButton predecessor;
	
	public UserInterface(){
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(500,500); // sets application size with width and height of 500px
		frame.setResizable(false); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HashSet<Task> allTasks = new HashSet<Task>();
		
		split = new JSplitPane(); // splits the bottom half of the frame into an output group and button group
		output = new JTextArea(20, 10);
			output.setEditable(false);
			
		Button_pnl = new JPanel();// create a panel for two buttons named reset and quit
			reset = new JButton("Reset");
			quit = new JButton("Quit");
				quit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == quit) { // if user hits quit it terminates the program
							System.exit(0); // terminates program
						}
					}
				});
			
			
		split.setLeftComponent(output);
		split.setRightComponent(Button_pnl);
		split.setDividerLocation(frame.getWidth()/2+50);
		split.setDividerSize(0);
		split.setOneTouchExpandable(false);
			Button_pnl.add(reset);
			Button_pnl.add(quit);
			
			
		Input_pnl = new JPanel(); //is the upper half of the frame for user input
		Input_pnl.setSize(new Dimension(frame.getWidth()/2, frame.getHeight()/2));
		Input_pnl.setBackground(new Color(0,255,255,40));
			tskLb = new JLabel("Enter Activity Name Here");
			
			task = new JTextField(20); //textfield for activity input
				task.setMargin(new Insets(0,10,10,5));
				
			timeLb = new JLabel("Enter time of Activity (as integer)");
			preLb = new JLabel("Enter predecessors (sepereate by '/') ");
			
			duration = new JTextField(10);
				duration.setMargin(new Insets(10,10,10,10));
			units = new Choice();
			units.add("sec");
			units.add("min");
			units.add("hrs");
			units.add("days");	// units of duration
			
			
			enter = new JButton("Enter");
				enter.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(e.getSource() == enter) {
							boolean TaskisEmpty = task.getText().trim().isEmpty();
							if(TaskisEmpty == true) { // checks to see if user left Activity name empty
								System.out.println("String is Empty");
							}
							boolean DurationisNumber = isNumber(duration.getText());
							if(!DurationisNumber == true) {
								System.out.println("Input valid number in Duration textfield"); //checks if text is an integer
							}
						
							boolean PredecessorisEmpty = preInput.getText().trim().isEmpty();
							if(preInput.isEnabled() && PredecessorisEmpty) {
								System.out.println("Enter Predecessor");
							}
							
							if(!TaskisEmpty && DurationisNumber && !PredecessorisEmpty) {
								
							}
						}
					}
				}); // checks to see if duration is an integer and if user input activity name
			
				
			preInput = new JTextField(10); // Textfield to input predecessors of activity
				preInput.setEnabled(false);
				preInput.setForeground(Color.GRAY);
				
			predecessor =  new JRadioButton("Any predecessors?");
			predecessor.isSelected(); // checks to see if radio button is selected or not
				predecessor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == predecessor) {
							if(predecessor.isSelected() == true && preInput.isEnabled() == false) {
								preInput.setEnabled(true); 
							}
							else
								preInput.setEnabled(false);
								preInput.setText("");
						}
					}
				}); 
				/* checks if predecessor is toggled on/off. If it is off it is assumed the activity
				has 0 predecessors. 
				*/
				
			Input_pnl.add(tskLb);
			Input_pnl.add(task);
			Input_pnl.add(timeLb);
			Input_pnl.add(duration);
			Input_pnl.add(units);
			Input_pnl.add(predecessor);
			Input_pnl.add(preLb);
			Input_pnl.add(preInput);
			Input_pnl.add(enter);
			
		frame.add(Input_pnl, BorderLayout.CENTER);
		frame.add(split,BorderLayout.SOUTH);
		frame.setVisible(true);
		
	}
	
	public boolean isNumber(String str) {
		char[] ch = str.toCharArray();
		for(int i = 0; i < ch.length; i++) {
			if(Character.isDigit(ch[i])) {
				return true;
			}
		}
		return false;
	}
}

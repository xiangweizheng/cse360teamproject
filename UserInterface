import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.*;
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
	
	public UserInterface(){
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(500,500);
		frame.setResizable(false);
		
		split = new JSplitPane();
		output = new JTextArea(20, 10);
		Button_pnl = new JPanel();
			reset = new JButton("Reset");
			quit = new JButton("Quit");
		split.setLeftComponent(output);
		split.setRightComponent(Button_pnl);
		split.setDividerLocation(frame.getWidth()/2+50);
		split.setDividerSize(0);
		split.setOneTouchExpandable(false);
			Button_pnl.add(reset);
			Button_pnl.add(quit);
			
			
		Input_pnl = new JPanel();
		Input_pnl.setSize(new Dimension(frame.getWidth()/2, frame.getHeight()/2));
		Input_pnl.setBackground(new Color(0,255,255,40));
			tskLb = new JLabel("Enter Activity Name Here");
			task = new JTextField(20);
				task.setMargin(new Insets(0,10,10,5));
			timeLb = new JLabel("Enter time of Activity");
			preLb = new JLabel("Enter predecessors (sepereate by '/') ");
			duration = new JTextField(10);
				duration.setMargin(new Insets(10,10,10,10));
			units = new Choice();
			enter = new JButton("Enter");
			preInput = new JTextField(10);
				units.add("sec");
				units.add("min");
				units.add("hrs");
				units.add("days");	
			Input_pnl.add(tskLb);
			Input_pnl.add(task);
			Input_pnl.add(timeLb);
			Input_pnl.add(duration);
			Input_pnl.add(units);
			Input_pnl.add(preLb);
			Input_pnl.add(preInput);
			Input_pnl.add(enter);
			
		frame.add(Input_pnl, BorderLayout.CENTER);
		frame.add(split,BorderLayout.SOUTH);
		frame.setVisible(true);
		
	}
}

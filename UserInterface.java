package cse360project;
import java.awt.BorderLayout;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.*;
import cse360project.*;
import cse360project.networkanalyzer.Task;
import cse360project.wnetdiag.Graph;
import cse360project.networkanalyzer;
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
	private JButton enter, reset, quit, print, clear,path,demo,demo_cycle,demo_connect,FinishEnter,help,about;
	private JPanel Button_pnl, Input_pnl;
	private Choice units;
	private JSplitPane split;
	private JRadioButton predecessor;
	private Integer counter = 0;
	
	public UserInterface(){
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(1000,1000); // sets application size with width and height of 500px
		frame.setResizable(false); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HashSet<Task> allTasks = new HashSet<Task>();
		HashSet<Task> allTasksFinished = new HashSet<Task>();
		
		
		FinishEnter = new JButton("FinishEnter");
		FinishEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				 
				
				

				      //find a new task to add into graph
				      for(Iterator<Task> it = allTasks.iterator();it.hasNext();){
				        Task task = it.next();
				        System.out.println(task.depstring);
				        for(int i=0;i<task.depstring.size();i++) {
				        	if(getTask(allTasks,task.depstring.get(i))!=null)
				        	task.adddep(getTask(allTasks,task.depstring.get(i)));
				        	System.out.println(task.depstring.get(i));
				        	System.out.println(getTask(allTasks,task.depstring.get(i)));
				        }
				        allTasksFinished.add(task);
			
			        
				        
				      }   
				        
				    
			}
		});
		
		cpath= new JButton("Critical Path");
		cpath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				allpathf.clear();
				Pathgen( allTasks, allTasksFinished,allpathf, hmap);
				Integer max=allpathf.get(0).get(0);
				
				for(int k=0;k<allpathf.size();k++) {
			    	ArrayList<Integer> tpal=allpathf.get(k);
			    	if(tpal.get(0)>=max) {
			    	//int tp=tpal.get(0);
			    	//tpal.set(0,tp+descost);
			    	output.append("Critical path cost is "+tpal.get(0)+" path sequence is:");
			    	for(int m=1;m<tpal.size();m++)
			    	          {output.append(hmap.get(tpal.get(m)));
			    	          output.append(" ");}
			    	output.append("\n");
			    	}
			    	else return;
			    	
			    }
				
			}
			
		});
		
		
		print = new JButton("Print");
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!allTasksFinished.isEmpty()) {
					//System.out.print(allTasks.size());
					for(Iterator<Task> i = allTasksFinished.iterator(); i.hasNext();) {
						Task nextTask = i.next();
						output.append(nextTask.name + "\t" + nextTask.cost + " " + units.getSelectedItem() + "\n"
								+ "Dependencies: " +  nextTask.dependencies + "Deps"+nextTask.depstring+"\n");
					}
				}
			}
			
		});
		
		
		
		help = new JButton("Help");
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				output.append("To start, enter activity name/duration/dependency and press entern when finish one activity\n");
				output.append("When all activity are entered,press finishenter button\n");
				output.append("press print botton to show the activties\n");
				output.append("press path botton to sho all the paths\n");
				output.append("press clear text, to clear output text\n");
				output.append("press quit, to quit program\n");
				output.append("press different demo to load different stype demo\n");
				output.append("To restart, press restart button\n");
			}
			
		});
		
		
		about = new JButton("About");
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				output.append("This program is to analyze path in a network diagram\n");
				
			}
			
		});
		
	
		split = new JSplitPane(); // splits the bottom half of the frame into an output group and button group
		output = new JTextArea(20, 10);
			output.setEditable(false);
			
		Button_pnl = new JPanel();// create a panel for two buttons named reset and quit
			reset = new JButton("Restart");
				reset.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						counter=0;
						allTasks.removeAll(allTasks);
						allTasksFinished.removeAll(allTasksFinished);
					}
					
				});
			
			
		clear = new JButton("Clear Text");// clear JTextArea
			clear.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					output.setText(""); 
				}
				
			});
			
			quit = new JButton("Quit");
				quit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == quit) { // if user hits quit it terminates the program
							System.exit(0); // terminates program
						}
					}
				});
			
				demo_cycle = new JButton("Demo_cycle");
				demo_cycle.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int i=0;//task id
						  
						Task E = new Task("E",i++,20);
					    Task G = new Task("G",i++,5,E);
					    Task F = new Task("F",i++,15,G);
					    Task H = new Task("H",i++,15,E); 
					    Task D = new Task("D", i++,10, E);
					    Task C = new Task("C",i++, 5, D, G);
					    Task B = new Task("B",i++, 20, C);
					    Task A = new Task("A",i++, 10, F,B,H);
					    C.adddep(B);//make cycle

					    allTasks.add(A);
					    allTasks.add(B);
					    allTasks.add(C);
					    allTasks.add(D);
					    allTasks.add(G);
					    allTasks.add(E);
					    allTasks.add(F);
					    allTasks.add(H);
					}
				});
				demo_connect = new JButton("Demo_connect");
				demo_connect.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int i=0;//task id
						  
						Task E = new Task("E",i++,20);
					    Task G = new Task("G",i++,5,E);
					    Task F = new Task("F",i++,15,G);
					    Task H = new Task("H",i++,15,E); 
					    Task D = new Task("D", i++,10, E);
					    Task C = new Task("C",i++, 5,D,G);//disconnect
					    Task B = new Task("B",i++, 20, C);
					    Task A = new Task("A",i++, 10, F,B,H);
					    Task K = new Task("K",i++, 10, F,B,H);

					    allTasks.add(A);
					    allTasks.add(B);
					    allTasks.add(C);
					    allTasks.add(D);
					    allTasks.add(G);
					    allTasks.add(E);
					    allTasks.add(F);
					    allTasks.add(H);
					    allTasks.add(K);
					}
				});
				
			path  = new JButton("Process");
			path.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					  HashMap<Integer, String> hmap = new HashMap<Integer, String>(); 	   
					    System.out.println(allTasksFinished);
					  
					    
					    
					    //initial value of source and destination task
					    Integer src=99,des=99;
					    Integer descost=0;
					    ArrayList<Integer> desa=new ArrayList<Integer>();
					    ArrayList<Integer> desacost=new ArrayList<Integer>();
					    //process to generate graph from task
					    HashSet<Task> dependtask = new HashSet<Task>();

					    HashSet<Task> remaining = new HashSet<Task>(allTasksFinished);//load tasks into remaining
					    Graph graph = new Graph(allTasks.size());
                    
					  
					    while(!remaining.isEmpty()){

					      //find a new task to add into graph
					      for(Iterator<Task> it = remaining.iterator();it.hasNext();){
					    	  
					        Task task = it.next();
					    
					        hmap.put(task.id,task.name);//store task id and name into hashmap
					        //find des node by dependencies empty
					        if(task.dependencies.isEmpty()) {
					        	des=task.id;
					        	desa.add(des);
					        	desacost.add(task.cost);
					        	//System.out.println("des"+des);
					        	descost=task.cost;}
					        for(Task t : task.dependencies){
					        	graph.addEgde(t.id, task.id,task.cost);
					        	dependtask.add(t);
					          }
				        
					        it.remove();
					   
					        
					      }   
					        
					    }
					    
					    //calculate src node by defination it is not any task's dependency
					    HashSet<Task> tmp = new HashSet<Task>(allTasksFinished);
					    tmp.removeAll(dependtask);
					    //System.out.println(tmp);
					   // ArrayList<Integer> srcs=new ArrayList<Integer>();
					    ArrayList<Integer> srcs=new ArrayList<Integer>();
					    //int si=0;
					    for(Iterator<Task> it = tmp.iterator();it.hasNext();){
					    	Task task = it.next();
					    	src=task.id;
					    	srcs.add(src);
					    }
					     
					    int flag=0;//
					    if(graph.isCyclic()) {
					    	 output.append("ERROR:Is Cyclic\n");
					    	 flag=1;
					     }
					    // else {output.append("Not Cyclic\n");}
					    
					    if(flag==0) {
					     if(!graph.isConnecttoEnd(src)) {
					    	 output.append("ERROR:Not Connected\n");
					    	 flag=1;
					     }
					    // else {output.append("Connected\n");}
					     
					    }
					   
					
					     if(flag==0) {
					     
					    	
					    	 
					    //	 System.out.println(srcs);	//destination node array
					    	// System.out.println(desa);	//source node array//in this requirement there is multiple desa
					    	 
					    	 
					    	 ArrayList<ArrayList<ArrayList<Integer>>>allpathm=new ArrayList<ArrayList<ArrayList<Integer>>>();
					    	 for(int jj=0;jj<desa.size();jj++) {  
					    		 allpathm.add(graph.allpath(desa.get(jj),src));
					    	 }
					    	 
					    	 ArrayList<ArrayList<Integer>>allpathf=new ArrayList<ArrayList<Integer>>();
					    	 for(int k=0;k<allpathm.size();k++) {
					    		 ArrayList<ArrayList<Integer>> tpaa=allpathm.get(k);
					    		 for(int jj=0;jj<tpaa.size();jj++) {
					    			 ArrayList<Integer> tpa=tpaa.get(jj);
					    			 tpa.set(0,tpa.get(0)+desacost.get(k));
					    			 allpathf.add(tpa);
					    		 }
							    	
							    }
					    	 Collections.sort(allpathf, new Comparator<ArrayList<Integer>>() {    
							        @Override
							        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
							            return o2.get(0).compareTo(o1.get(0));
							        }               
							});
					    	 for(int k=0;k<allpathf.size();k++) {
							    	ArrayList<Integer> tpal=allpathf.get(k);
							    	//int tp=tpal.get(0);
							    	//tpal.set(0,tp+descost);
							    	output.append("path cost is "+tpal.get(0)+" path sequence is:");
							    	for(int m=1;m<tpal.size();m++)
							    	          {output.append(hmap.get(tpal.get(m)));
							    	          output.append(" ");}
							    	output.append("\n");
							    	
							    }
					    	 
					    	// System.out.println(allpathf);	 	 
					    	 
					/*    	 
					ArrayList<ArrayList<Integer>>allpath= graph.allpath(des,src);
					System.out.println(allpath);	 
					
				    Collections.sort(allpath, new Comparator<ArrayList<Integer>>() {    
				        @Override
				        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				            return o2.get(0).compareTo(o1.get(0));
				        }               
				});
					 for(int k=0;k<allpath.size();k++) {
					    	ArrayList<Integer> tpal=allpath.get(k);
					    	int tp=tpal.get(0);
					    	tpal.set(0,tp+descost);
					    	output.append("path cost is "+tpal.get(0)+" path sequence is:");
					    	for(int m=1;m<tpal.size();m++)
					    	          {output.append(hmap.get(tpal.get(m)));
					    	          output.append(" ");}
					    	output.append("\n");
					    	
					    }*/
				}
				}
			});
			
			
			
			
			demo = new JButton("Demo");
			demo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int i=0;//task id
					  
					Task E = new Task("E",i++,20);
				    Task G = new Task("G",i++,5,E);
				    Task F = new Task("F",i++,15,G);
				    Task H = new Task("H",i++,15,E); 
				    Task D = new Task("D", i++,10, E);
				    Task C = new Task("C",i++, 5, D, G);
				    Task B = new Task("B",i++, 20, C);
				    Task A = new Task("A",i++, 10, F,B,H);

				    allTasks.add(A);
				    allTasks.add(B);
				    allTasks.add(C);
				    allTasks.add(D);
				    allTasks.add(G);
				    allTasks.add(E);
				    allTasks.add(F);
				    allTasks.add(H);
				}
				
			});
			
		split.setLeftComponent(output);
		split.setRightComponent(Button_pnl);
		split.setDividerLocation(frame.getWidth()/2+50);
		split.setDividerSize(0);
		split.setOneTouchExpandable(false);
			Button_pnl.add(reset);
			Button_pnl.add(quit);
			Button_pnl.add(print);
			Button_pnl.add(clear);
			Button_pnl.add(path);
			Button_pnl.add(demo);
			Button_pnl.add(demo_cycle);
			Button_pnl.add(demo_connect);
			Button_pnl.add(FinishEnter);
			Button_pnl.add(help);
			Button_pnl.add(about);
		Input_pnl = new JPanel(); //is the upper half of the frame for user input
		Input_pnl.setSize(new Dimension(frame.getWidth()/2, frame.getHeight()/2));
		Input_pnl.setBackground(new Color(0,255,255,40));
			tskLb = new JLabel("Enter Activity Name Here");
			task = new JTextField(20); //textfield for activity input
				task.setMargin(new Insets(0,10,10,5));
				
			timeLb = new JLabel("Enter time of Activity (as integer)");
			preLb = new JLabel("Enter dependencies (sepereate by '/') ");
			
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
								output.append("String is Empty\n");
							}
							boolean DurationisNumber = isNumber(duration.getText());
							int number = 0;
							if(!DurationisNumber == true) {
								output.append("Input valid number in Duration textfield\n"); //checks if text is an integer
							}
							else {
								number = Integer.parseInt(duration.getText());
							}
						
							boolean PredecessorisEmpty = preInput.getText().trim().isEmpty();
							if(preInput.isEnabled() && PredecessorisEmpty) {
								output.append("Enter Dependencies\n");
							}
							//test
							
							
							
							//task has dependecy
							if(!TaskisEmpty && !PredecessorisEmpty && predecessor.isSelected() && DurationisNumber) {
								
								//System.out.println(preInput.getText().trim());
								String test=preInput.getText().trim();
								ArrayList<String> tp=new ArrayList<String>();
								int start=0;
								for (int i=0;i<test.length();i++) {
									char c=test.charAt(i);
									
									if(c=='/') {		
										
										if(start!=i-1) {
										tp.add(test.substring(start,i-1+1));
										//System.out.println(tp);
										}
										else {
											tp.add(Character.toString(test.charAt(start)));
											//System.out.println(tp);
										}
												
										start=i+1;
									}
									else if(i==test.length()-1) {
										if(start!=i) {
											tp.add(test.substring(start,i+1));//substring bug: Start index is inclusiveEnd index is exclusive
											//System.out.println(tp);
											}
											else {
												tp.add(Character.toString(test.charAt(start)));
												//System.out.println(tp);
											}
									}
								}
								
								
								
								
								Task p = new Task(task.getText().trim(), counter, number);//
								
								p.replacedepstring(tp);
								//System.out.println(p.depstring);
								allTasks.add(p);//returns 1 predecessor
								output.append("Activity Entered!\n");
								counter++;
								
							}
							//task 
							else if(!TaskisEmpty && DurationisNumber) {
								output.append("Activity Entered!\n");
								allTasks.add(new Task(task.getText().trim(), counter, number));
								counter++;
							}
							
							
							task.setText(""); //set field empty afterwards
							duration.setText("");//set field empty afterwards
							preInput.setText("");//set predecessor Input afterwards
						}
					}
				}); // checks to see if duration is an integer and if user input activity name
			
				
			
			preInput = new JTextField(10); // Textfield to input predecessors of activity
				preInput.setEnabled(false);
				preInput.setForeground(Color.GRAY);
				
			predecessor =  new JRadioButton("Any dependencies?");
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
		tskLb2 = new JLabel("Enter Activity Name to change Here");
				task2 = new JTextField(20); //textfield for activity input
					task2.setMargin(new Insets(0,10,10,5));
					
				timeLb2 = new JLabel("Enter time of Activity to change (as integer)");

				
				duration2 = new JTextField(10);
					duration.setMargin(new Insets(10,10,10,10));
				units2 = new Choice();
				units2.add("sec");
				units2.add("min");
				units2.add("hrs");
				units2.add("days");	// units of duration
				
				change = new JButton("Change Duration");
				change.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						
						
						if(e.getSource() == change) {
							boolean TaskisEmpty = task2.getText().trim().isEmpty();
							if(TaskisEmpty == true) { // checks to see if user left Activity name empty
								output.append("String is Empty\n");
							}
							boolean DurationisNumber = isNumber(duration2.getText());
							int number = 0;
							if(!DurationisNumber == true) {
								output.append("Input valid number in Duration textfield\n"); //checks if text is an integer
							}
							else {
								number = Integer.parseInt(duration2.getText());
							}
						
						
							//test
							
							
							int change=0;
						 if(!TaskisEmpty && DurationisNumber) {
							 
						      for(Iterator<Task> it = allTasks.iterator();it.hasNext();){
							        Task taskc = it.next();
							        System.out.println(taskc.toString()+"empty"+task.getText().trim());
							         if(task2.getText().trim().equals(taskc.name))			
						                {taskc.changecost(number);
						                change=1;
						                output.append("Activity Duration Changed!\n");
						                allpathf.clear();
						                output.append("Clear previous path!\n");
						                }
							        
							      }  
							 
							   if(change==0)
								output.append("Activity Not find!\n");
							
							}
							
							
							task2.setText(""); //set field empty afterwards
							duration2.setText("");//set field empty afterwards

						}
					
					}
					});
				
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
	
	public boolean isNumber(String str) {//determines if input is a number
		char[] ch = str.toCharArray();
		for(int i = 0; i < ch.length; i++) {
			if(Character.isDigit(ch[i])) {
				return true;
			}
		}
		return false;
	}
	
	public static Task getTask(HashSet<Task> set, String t) {//return prdecessor
		return SearchforTask(set, t);
	}
	
	public static Task SearchforTask(HashSet<Task> set, String t) {//search for predecessor
		//Task nt = null;
		if(!set.isEmpty()) {
			for(Iterator<Task> it = set.iterator(); it.hasNext();) {
				Task nt = it.next();
				System.out.println(t+nt.name);
				if(t.equals(nt.name)) {
					System.out.println("t+=nt.name");
					return nt;
				}
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


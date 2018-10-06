package cse360project;

//package cse360project;


//package cse360teamproject;
import java.util.*;
//import cse360project.wnetdiag.Graph; 
//import cse360teamproject.wnetdiag.Graph; 
//import cse360teamproject.networkanalyzer.Task;


//package cse360teamproject;

import cse360project.wnetdiag.Graph; 
//import cse360teamproject.wnetdiag.Graph; 
import cse360project.networkanalyzer.Task;


public class networkanalyzer {
//public class networkanalyzer {


		public static void main(String[] args) {
		    //Create an HashSet to store tasks
			HashSet<Task> allTasks = new HashSet<Task>();
			//demo example
			int i=0;//task id
	  
			Task E = new Task("E",i++,20);
		    Task G = new Task("G",i++,5,E);
		    Task F = new Task("F",i++,15,G);
		    Task H = new Task("H",i++,15,E); 
		    Task D = new Task("D", i++,10, E);
		    Task C = new Task("C",i++, 5, D, G);
		    Task B = new Task("B",i++, 20, C);
		    Task A = new Task("A",i++, 10, F,B,H);
		    //C.adddep(B);
		    /*
		    A.adddepstring("a");
		    A.adddepstring("b");
		    ArrayList<String> test=new ArrayList<String>();
		    test.add("c");
		    A.printdepstring();
		    A.replacedepstring(test);
		    A.printdepstring();
		    */
		   // A.adddep(C);
		    
		    allTasks.add(A);
		    allTasks.add(B);
		    allTasks.add(C);
		    allTasks.add(D);
		    allTasks.add(G);
		    allTasks.add(E);
		    allTasks.add(F);
		    allTasks.add(H);
	  
		    

		    
		    
		    
		    //Create an hashmap to store pair between task id and name  
		    HashMap<Integer, String> hmap = new HashMap<Integer, String>(); 	   
		  //  System.out.println(allTasks);
		  
		    
		    
		    //initial value of source and destination task
		    int src=99,des=99;
		    int descost=0;
		    
		    //process to generate graph from task
		    HashSet<Task> dependtask = new HashSet<Task>();

		    HashSet<Task> remaining = new HashSet<Task>(allTasks);//load tasks into remaining
		    Graph graph = new Graph(allTasks.size());

		    while(!remaining.isEmpty()){

		      //find a new task to add into graph
		      for(Iterator<Task> it = remaining.iterator();it.hasNext();){
		        Task task = it.next();
		        hmap.put(task.id,task.name);//store task id and name into hashmap
		        //find des node by dependencies empty
		        if(task.dependencies.isEmpty()) {
		        	des=task.id;
		        	//System.out.println("des"+des);
		        	descost=task.cost;}
		        for(Task t : task.dependencies){
		        	graph.addEgde(task.id, t.id, task.cost);
		        	dependtask.add(t);
		          }
	        
		        it.remove();
		      }   
		        
		    }
		    
		    //calculate src node by defination it is not any task's dependency
		    HashSet<Task> tmp = new HashSet<Task>(allTasks);
		    tmp.removeAll(dependtask);
		    //System.out.println(tmp);
		    for(Iterator<Task> it = tmp.iterator();it.hasNext();){
		    	Task task = it.next();
		    	src=task.id;
		    }
		    
		     if(graph.isConnecttoEnd(des)) {
		    	 System.out.print(" Connected\n");
		     }
		    
		    
		    //generate path from source to des// need detect source and des
		    ArrayList<ArrayList<Integer>> allpath = graph.allpath(src,des);
		    //System.out.println(allpath);
		    //
		    Collections.sort(allpath, new Comparator<ArrayList<Integer>>() {    
		        @Override
		        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
		            return o2.get(0).compareTo(o1.get(0));
		        }               
		});
		   // System.out.println(allpath);//test path print
		    //System.out.println(allpath.get(0));//test path print
		    

		    //print path of task name 
		    for(int k=0;k<allpath.size();k++) {
		    	ArrayList<Integer> tpal=allpath.get(k);
		    	int tp=tpal.get(0);
		    	tpal.set(0,tp+descost);
		    	System.out.println("path cost is "+tpal.get(0)+" path sequence is:");
		    	for(int m=1;m<tpal.size();m++)
		    	          {System.out.print(hmap.get(tpal.get(m)));
		    	          System.out.print(" ");}
		    	 System.out.print("\n");
		    	
		    }
		   
		    
		
	}
		
		public static ArrayList<ArrayList<Integer>> pathgen(HashSet<Task> allTasks){
			 //Create an hashmap to store pair between task id and name  
		    HashMap<Integer, String> hmap = new HashMap<Integer, String>(); 	   
		  //  System.out.println(allTasks);
		  
		    
		    
		    //initial value of source and destination task
		    Integer src=99,des=99;
		    int descost=0;
		    
		    //process to generate graph from task
		    HashSet<Task> dependtask = new HashSet<Task>();

		    HashSet<Task> remaining = new HashSet<Task>(allTasks);//load tasks into remaining
		    Graph graph = new Graph(allTasks.size());

		    while(!remaining.isEmpty()){

		      //find a new task to add into graph
		      for(Iterator<Task> it = remaining.iterator();it.hasNext();){
		        Task task = it.next();
		        hmap.put(task.id,task.name);//store task id and name into hashmap
		        //find des node by dependencies empty
		        if(task.dependencies.isEmpty()) {
		        	des=task.id;
		        	//System.out.println("des"+des);
		        	descost=task.cost;}
		        for(Task t : task.dependencies){
		        	graph.addEgde(task.id, t.id, task.cost);
		        	dependtask.add(t);
		          }
	        
		        it.remove();
		      }   
		        
		    }
		    
		    //calculate src node by defination it is not any task's dependency
		    HashSet<Task> tmp = new HashSet<Task>(allTasks);
		    tmp.removeAll(dependtask);
		    //System.out.println(tmp);
		    for(Iterator<Task> it = tmp.iterator();it.hasNext();){
		    	Task task = it.next();
		    	src=task.id;
		    }
		    
		    
		    
		    //generate path from source to des// need detect source and des
		    ArrayList<ArrayList<Integer>> allpath = graph.allpath(src,des);
		    //System.out.println(allpath);
		    //
		    Collections.sort(allpath, new Comparator<ArrayList<Integer>>() {    
		        @Override
		        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
		            return o2.get(0).compareTo(o1.get(0));
		        }               
		});
		   // System.out.println(allpath);//test path print
		    //System.out.println(allpath.get(0));//test path print
		    

	       return allpath;
			
		}
		
		
		
		
		
		
		
		public static class Task{
		    //the actual cost of the task
		    public Integer cost;
		    //a name of the task
		    public  String name;
		    public  Integer id;
		    // a list of dependencies of the tasks
		    public HashSet<Task> dependencies = new HashSet<Task>();
		    public ArrayList<String> depstring= new ArrayList<String>();
		    public Task(String name, int id, int cost, Task... dependencies) {
		      this.name = name;
		      this.id=id;
		      this.cost = cost;
		      for(Task t : dependencies){
		        this.dependencies.add(t);
		      }
		    }
		    @Override
		    public String toString() {
		      return name+id;
		    }
		    public void adddep(Task d) {
		    	this.dependencies.add(d);
		    }
		    public void adddepstring(String d) {
		    	this.depstring.add(d);
		    }
		    public void replacedepstring(ArrayList<String> d) {
		    	this.depstring=d;
		    }
		    public void printdepstring() {
		    	 System.out.println(depstring);
		    }
		  }
	}

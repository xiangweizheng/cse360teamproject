package cse360project;


//package cse360teamproject;
import java.util.*;
import cse360project.wnetdiag.Graph; 
//import cse360teamproject.wnetdiag.Graph; 
//import cse360teamproject.networkanalyzer.Task;


public class netaz {
//public class networkanalyzer {

	public static void main(String[] args) {
	    //Create an HashSet to store tasks
		HashSet<Task> allTasks = new HashSet<Task>();
		//demo example
		int i=0;//task id
	    Task end = new Task("End",i++, 0);    
	    Task E = new Task("E",i++,20,end);   
	    Task G = new Task("G",i++,5,E);
	    Task F = new Task("F",i++,15,G);
	    Task H = new Task("H",i++,15,E); 
	    Task D = new Task("D", i++,10, E);
	    Task C = new Task("C",i++, 5, D, G);
	    Task B = new Task("B",i++, 20, C);
	    Task A = new Task("A",i++, 10, F,B,H);
	    Task start = new Task("Start",9, 0, A);
	    allTasks.add(end);
	    allTasks.add(A);
	    allTasks.add(B);
	    allTasks.add(C);
	    allTasks.add(D);
	    allTasks.add(G);
	    allTasks.add(E);
	    allTasks.add(F);
	    allTasks.add(H);
	    allTasks.add(start);//    
	    
	    //Create an hashmap to store pair between task id and name  
	    HashMap<Integer, String> hmap = new HashMap<Integer, String>(); 	   
	    System.out.println(allTasks);
	  
	    
	    
	    
	    
	    
	    //process to generate graph from task
	    HashSet<Task> remaining = new HashSet<Task>(allTasks);//load tasks into remaining
	    Graph graph = new Graph(allTasks.size());

	    while(!remaining.isEmpty()){

	      //find a new task to add into graph
	      for(Iterator<Task> it = remaining.iterator();it.hasNext();){
	        Task task = it.next();
	        hmap.put(task.id,task.name);//store task id and name into hashmap
	        for(Task t : task.dependencies){
	        	graph.addEgde(task.id, t.id, task.cost);
	        	//System.out.println(t);
	          }
        
	        it.remove();
	      }   
	        
	    }
	    //generate path from source to des// need detect source and des
	    ArrayList<ArrayList<Integer>> allpath = graph.allpath(9,0);
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
	    	System.out.println("path cost is "+tpal.get(0)+" path sequence is:");
	    	for(int m=1;m<tpal.size();m++)
	    	          {System.out.print(hmap.get(tpal.get(m)));
	    	          System.out.print(" ");}
	    	 System.out.print("\n");
	    	
	    }
	   
	    
	
}
	
	
	
	
	
	
	
	
	
	public static class Task{
	    //the actual cost of the task
	    public int cost;
	    //a name of the task
	    public  String name;
	    public  int id;
	    // a list of dependencies of the tasks
	    public HashSet<Task> dependencies = new HashSet<Task>();
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
	      return name;
	    }
	
	  }
}

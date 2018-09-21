package cse360teamproject;

public class networkanalyzer {

	public static void main(String[] args) {
	    //The example dependency graph from
		HashSet<Task> allTasks = new HashSet<Task>();
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

	    
	    //Create an Array List 
	    HashMap<Integer, String> hmap = new HashMap<Integer, String>();

	    
	    

	    System.out.println(allTasks);
	
	
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

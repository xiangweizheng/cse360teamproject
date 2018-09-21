package cse360teamproject;

public class networkanalyzer {

	
	
	
	
	
	
	
	
	
	
	
	
	
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

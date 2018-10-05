//package cse360teamproject;
package cse360project;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
public class wnetdiag {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        LinkedList<Edge> [] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEgde(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge); //for directed graph
        }

        public void printGraph(){
            for (int i = 0; i <vertices ; i++) {
                LinkedList<Edge> list = adjacencylist[i];
                for (int j = 0; j <list.size() ; j++) {
                    System.out.println("vertex-" + i + " is connected to " +
                            list.get(j).destination + " with weight " +  list.get(j).weight);
                }
            }
        }


        public ArrayList<ArrayList<Integer>> allpath (int s,int d) { 
        	//arraylist result to store all the paths
        	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        	      	
        	LinkedList<Edge> listad = adjacencylist[s];
            for (int j = 0; j <listad.size() ; j++) {
            	   //arraylist list to store current path 
             		ArrayList<Integer> list = new ArrayList<Integer>();
             		//update list and list weight
            		list.add(listad.get(j).weight);                	
                	list.add(s);
                	
                	//DFS
                if(d!=listad.get(j).destination) {
            		helpallpath(listad.get(j).destination,d,result,list);
            	}
            	else {
                    //if dependency is des just add current list to result
            		list.add(d);
            		result.add(new ArrayList<Integer>(list));
            	}
            }
                 	

        	return result;
        }
 
        
        
        
        
    
    public void helpallpath(int s,int d,ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list) {
    	
            list.add(s);
            LinkedList<Edge> listad = adjacencylist[s];
            //DFS
            for (int j = 0; j <listad.size() ; j++) {
            	if(d!=listad.get(j).destination) {
            		//list[0]+=listad.get(j).weight;
            		ArrayList<Integer> listtp = new ArrayList<Integer>(list);
            		int tp=listtp.get(0);
            		listtp.set(0,listad.get(j).weight+ tp);
            		helpallpath(listad.get(j).destination,d,result,listtp);
            	}
            	else {
            		list.add(d);
            		int tp=list.get(0);
            		list.set(0,listad.get(j).weight+ tp);
            		result.add(new ArrayList<Integer>(list));
            	}
                    
            }
            
            
        }
    

    
    
     public boolean isCyclic(){
         

 
        boolean[] visited = new boolean[vertices];// Mark all the vertices as not visited 
        boolean[] recStack = new boolean[vertices]; //not part of recursion stack
         
         
        // Call the recursive helper function to detect cycle in different DFS trees
        for (int i = 0; i < vertices; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;
 
        return false;
    }
    
    
    private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
         if (recStack[i])
            return true;

         if (visited[i])
           return false;

          visited[i] = true;

         recStack[i] = true;
         LinkedList<Edge> list = adjacencylist[i];

       for (int j = 0; j <list.size() ; j++) {
	        if(isCyclicUtil(list.get(j).destination, visited, recStack))
	          return true;
                }
               recStack[i] = false;
               return false;
}
    
    
    
    
    
    public boolean isConnect(){
    	for(int i=0;i < vertices; i++) 
    		for(int j=i+1;j<vertices-1;j++)
    			//if any node pair cannot be reached return not connected
    			//for directed graph need check both ij and ji
    			if(allpath(i,j).isEmpty()&&allpath(j,i).isEmpty()) 
    				{//System.out.println("i"+i+"j"+j+"\n");
    				return false;}
    			
    	//if all nodes can reach each other return connected	
    	return true;
    }
    

    public boolean isConnecttoEnd(int end){
    	for(int i=0;i < vertices; i++) 
    		 	//if any node pair cannot be reached return not connected
    			//for directed graph need check both ij and ji
    		if(i!=end)
    			if(allpath(i,end).isEmpty()) 
    				{//System.out.println("i"+i+"end"+end+"\n");
    				return false;}
    			
    	//if all nodes can reach each other return connected	
    	return true;
    }
    
    
    
    
    
    
    
    
    
    
    

 }   
    
    
 
   
    
      public static void main(String[] args) {
            int vertices = 7;
            Graph graph = new Graph(vertices);
            graph.addEgde(0, 1, 4);
            graph.addEgde(0, 2, 4);
            graph.addEgde(1, 3, 2);
            graph.addEgde(1, 2, 2);
            graph.addEgde(2, 3, 7);
            graph.addEgde(3, 4, 2);
            graph.addEgde(5, 6, 6);//test connect
            graph.addEgde(4, 1, 2);//test circle//something wrong when test 
            
            
            
            
            
            if(graph.isCyclic())
            	System.out.println("there is cycle");
            else 
            	System.out.println("there is no cycle");
            
            
            
          
           graph.printGraph();
            ArrayList<ArrayList<Integer>> allpath = graph.allpath(0,6);
            Collections.sort(allpath, new Comparator<ArrayList<Integer>>() {    
                @Override
                public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                    return o1.get(0).compareTo(o2.get(0));
                }               
        });
            //System.out.println(allpath);
            //graph.allpath(3, 4);
            if(graph.isConnect())
            	System.out.println("is connected");
            else
            	System.out.println("not connected");
            if(graph.isConnecttoEnd(4))
            	System.out.println("is connected");
            else
            	System.out.println("not connected");
        }
      
      
    
      
      
      
      
      
      
      
      
      
      
      
}
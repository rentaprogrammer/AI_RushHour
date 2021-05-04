package at.fhooe.ai.projectCode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

//import AStar.HNode;
import at.fhooe.ai.rushhour.Heuristic;
import at.fhooe.ai.rushhour.Node;
import at.fhooe.ai.rushhour.Puzzle;
import at.fhooe.ai.rushhour.State;





/**
 * This is the template for a class that performs A* search on a given rush hour
 * puzzle with a given heuristic. The main search computation is carried out by
 * the constructor for this class, which must be filled in. The solution (a path
 * from the initial state to a goal state) is returned as an array of
 * <tt>State</tt>s called <tt>path</tt> (where the first element
 * <tt>path[0]</tt> is the initial state). If no solution is found, the
 * <tt>path</tt> field should be set to <tt>null</tt>. You may also wish to
 * return other information by adding additional fields to the class.
 */
public class AStar {

    /** The solution path is stored here */
    public State[] path;
    
    private SortableList< ComparableNode> openList = new SortableList< ComparableNode>();    //  Sort zu PriorityQueue<ComparableNode> noch ersetzen... Hatte Fehler mit PriorityQu.  
    private HashSet< ComparableNode> closedList = new HashSet< ComparableNode>(); //!!!! HashSET zirka 5* Schneller als ArrayList!!!!!!
    //private List<ComparableNode> closedList = new ArrayList<ComparableNode>();


    /**
     * This is the constructor that performs A* search to compute a
     * solution for the given puzzle using the given heuristic.
     */
    public AStar(Puzzle puzzle, Heuristic heuristic) {
   
    	int h = heuristic.getValue(puzzle.getInitNode().getState());
    	 ComparableNode root = new  ComparableNode(puzzle.getInitNode(), h);
    	openList.add(root);
    	
    	while(!openList.isEmpty()) {
    		openList.sort();
    		 ComparableNode currentNode = openList.remove(0);//Take from the open list the node node_current with the lowest
    	
    		
    		if(currentNode.getState().isGoal()) { // if node is goal State we have found the solution;
    			System.out.print("Goal is Found");
    			int depth = currentNode.getDepth();
    			path = new State[depth + 1];   			
    			while(currentNode != null) {
    				path[depth--] = currentNode.getState();
    				currentNode = ( ComparableNode) currentNode.getParent();
    			}
    			return;
    		}  		
    		closedList.add(currentNode);
    		
    		for (Node successorNode : currentNode.expand()) {  //Expand all Successor Nodes 
    			
    			h = heuristic.getValue(successorNode.getState());
    			 ComparableNode compSuccessor = new  ComparableNode(successorNode, h);    			  			
    			if(openList.contains(compSuccessor)) {  
    				ComparableNode savedNode =  openList.get(compSuccessor);
    				if (savedNode.compareTo(compSuccessor) > 0) {
    					openList.remove(savedNode);
    	    			openList.add(compSuccessor);
    				}
    				
    			}else if(!closedList.contains(compSuccessor)) {   						
    				openList.add(compSuccessor);
    			}
    		}
    	}// while(!openListisEmpty)

    }   
}

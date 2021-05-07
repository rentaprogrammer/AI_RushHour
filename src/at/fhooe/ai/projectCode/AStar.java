package at.fhooe.ai.projectCode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import at.fhooe.ai.rushhour.Heuristic;
import at.fhooe.ai.rushhour.Node;
import at.fhooe.ai.rushhour.Puzzle;
import at.fhooe.ai.rushhour.State;

public class AStar {

	public State[] path;
	private PriorityQueue<ComparableNode> openList = new PriorityQueue<ComparableNode>(new QueueComperator());
	private HashSet<ComparableNode> closedList = new HashSet<ComparableNode>();

	public AStar(Puzzle puzzle, Heuristic heuristic) {

		ComparableNode root = new ComparableNode(puzzle.getInitNode(), heuristic);
		openList.add(root);

		while (!openList.isEmpty()) {

			ComparableNode currentNode = openList.remove();

			if (currentNode.getState().isGoal()) { 
				int depth = currentNode.getDepth();
				path = new State[depth + 1];
				while (currentNode != null) {
					path[depth--] = currentNode.getState();
					currentNode = (ComparableNode) currentNode.getParent();
				}				
				return;
			}
			closedList.add(currentNode);

			for (Node successorNode : currentNode.expand()) {
				ComparableNode compSuccessor = new ComparableNode(successorNode, heuristic);
				if (openList.contains(compSuccessor)) { 
					
					if(hasSavedNodeLowerFCost(compSuccessor)) {
						openList.remove(compSuccessor); 
						openList.add(compSuccessor);
					}					
				} else if (!closedList.contains(compSuccessor)) {
					openList.add(compSuccessor);
				}
			}
		} 
	}
	
	public boolean hasSavedNodeLowerFCost(ComparableNode compSuccessor ) {
		Iterator<ComparableNode> iterator = openList.iterator();
		ComparableNode nodeInList = null;
        while (!compSuccessor.equals(nodeInList) && iterator.hasNext()) {
        	nodeInList =  iterator.next();
        }
        	 if(nodeInList.compareTo(compSuccessor) > 0) {
        		 return true;
        	 }      	
        return false;
	}
}

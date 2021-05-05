package at.fhooe.ai.projectCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
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
	private PriorityQueue<ComparableNode> openList = new PriorityQueue<ComparableNode>();
	private HashSet<ComparableNode> closedList = new HashSet<ComparableNode>();

	/**
	 * This is the constructor that performs A* search to compute a solution for the
	 * given puzzle using the given heuristic.
	 */
	public AStar(Puzzle puzzle, Heuristic heuristic) {

		int h = heuristic.getValue(puzzle.getInitNode().getState());
		ComparableNode root = new ComparableNode(puzzle.getInitNode(), h);
		openList.add(root);

		while (!openList.isEmpty()) {

			ComparableNode currentNode = openList.remove();

			if (currentNode.getState().isGoal()) { 
				System.out.print("Goal is Found");
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
				h = heuristic.getValue(successorNode.getState());
				ComparableNode compSuccessor = new ComparableNode(successorNode, h);
				if (openList.contains(compSuccessor)) { 
					List<ComparableNode> list = new ArrayList<>(openList);
					int nodeId = list.indexOf(compSuccessor);
					if (list.get(nodeId).compareTo(compSuccessor) > 0) {
						openList.remove(compSuccessor); 
						openList.add(compSuccessor);
					}
				} else if (!closedList.contains(compSuccessor)) {
					openList.add(compSuccessor);
				}
			}
		} 
	}
}
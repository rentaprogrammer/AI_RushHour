package at.fhooe.ai.projectCode;

import at.fhooe.ai.rushhour.Heuristic;
import at.fhooe.ai.rushhour.Node;

public class ComparableNode extends Node implements Comparable<ComparableNode> {

	//InstanceCounter to break Tie into FIFO!!!! Otherwise PriorityQeue choose random tie version.
	private int fCost;
    static int instanceCounter = 0;	
    private int counter;
	public ComparableNode(Node node, Heuristic heuristic) {
		super(node.getState(), node.getDepth(), node.getParent());
		this.fCost = node.getDepth() + heuristic.getValue(node.getState());
		counter = instanceCounter;
		instanceCounter++;
		
	}
		
	public ComparableNode(Node node, int hCost) {
		super(node.getState(), node.getDepth(), node.getParent());
		this.fCost = node.getDepth() + hCost;
	}

	@Override
	public int compareTo(ComparableNode node) {
		// TODO Auto-generated method stub
		if(fCost == node.getFCost()) {
			if(this.counter > node.counter) {
				return 1;
			}
			return 0;
		}else if (fCost > node.getFCost()) {
			if(this.counter < node.counter) {
				return 0;
			}
			return 1;
		}
		return -1;
	}

	
	public int getFCost() {		
		return fCost;
	}
	
	@Override
	public int hashCode() {
		return this.getState().hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		
		if (this == other) {
			return true;
		}
		
		
		if (other instanceof Node || other instanceof ComparableNode) {
			return ((Node) other).getState().equals(this.getState());
		}
		
		return false;
	}
	
}

package at.fhooe.ai.projectCode;

import at.fhooe.ai.rushhour.Heuristic;
import at.fhooe.ai.rushhour.Node;

public class ComparableNode extends Node implements Comparable<ComparableNode> {
	private int fCost;
	static int instanceCounter = 0;
	private int instanceNr;

	public ComparableNode(Node node, Heuristic heuristic) {
		super(node.getState(), node.getDepth(), node.getParent());
		this.fCost = node.getDepth() + heuristic.getValue(node.getState());
		instanceNr = instanceCounter;
		instanceCounter++;
	}

	@Override
	public int compareTo(ComparableNode node) {
		if (this.fCost == node.getFCost()) {
			if (this.instanceNr > node.getOrder()) {
				return 1;
			} else {
				return -1;
			}
		} else if (this.fCost > node.getFCost()) {
			return 1;
		}
		return -1;
	}

	public int getFCost() {
		return this.fCost;
	}

	public int getOrder() {
		return this.instanceNr;
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

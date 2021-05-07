package at.fhooe.ai.projectCode;

import java.util.Comparator;

public class QueueComperator implements Comparator<ComparableNode> {

	@Override
	public int compare(ComparableNode node_0, ComparableNode node_1) {		
		return node_0.compareTo(node_1);		
	}

}

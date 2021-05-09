package at.fhooe.ai.projectCode;

import at.fhooe.ai.rushhour.*;

/**
 * This is a template for the class corresponding to the blocking heuristic.
 * This heuristic returns zero for goal states, and otherwise returns one plus
 * the number of cars blocking the path of the goal car to the exit. This class
 * is an implementation of the <tt>Heuristic</tt> interface, and must be
 * implemented by filling in the constructor and the <tt>getValue</tt> method.
 */
public class BlockingHeuristic implements Heuristic {

	private Puzzle puzzle;
  
	public BlockingHeuristic(Puzzle puzzle) {
	this.puzzle = puzzle;
	}

	
	public int getValue(State state) {
		if (state.isGoal()) {
			return 0;
		}

		int hCost = 1;
	  	int goalCarInitPosFixed = puzzle.getFixedPosition(0);
	  	int curCarInitSizeVar = state.getVariablePosition(0) + puzzle.getCarSize(0)-1;

	  	for(int i = 1 ; i < puzzle.getNumCars(); i++) {
			if(puzzle.getCarOrient(i) && puzzle.getFixedPosition(i) > curCarInitSizeVar) {

				if (state.getVariablePosition(i) >= goalCarInitPosFixed-(puzzle.getCarSize(i)-1) && 
						state.getVariablePosition(i) <= goalCarInitPosFixed) {
					hCost++;
				}
			}
		}
	return hCost;
	}
}

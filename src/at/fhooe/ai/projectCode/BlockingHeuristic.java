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
  
	
  /**
   * This is the required constructor, which must be of the given form.
   */
  public BlockingHeuristic(Puzzle puzzle) {
    this.puzzle = puzzle;
  }

  /**
   * This method returns the value of the heuristic function at the given state.
   */
  public int getValue(State state) {
	  if (state.isGoal()) {
			return 0;
		}
		
		int blockedNr = 1; 
		int goalCarInitPos = state.getVariablePosition(0) + puzzle.getCarSize(0); //Goal Car Size 2, Get right front;
		for(int i = 1 ; i < puzzle.getNumCars(); i++) {
			if(puzzle.getCarOrient(i)) {
				if(puzzle.getFixedPosition(i) >= goalCarInitPos ) {
					
					/*if (puzzle.getFixedPosition(0) >= state.getVariablePosition(i) && puzzle.getFixedPosition(0) < state.getVariablePosition(i)+puzzle.getCarSize(i)) {
						blockedNr++;
					}*/
					
					if(puzzle.getCarSize(i) == 3 && state.getVariablePosition(i)<= 2) {
						blockedNr++;}
					
					if((puzzle.getCarSize(i) == 2) && ((state.getVariablePosition(i)<= 2) && (state.getVariablePosition(i) >= 1 ))) {
						blockedNr++;
					}
					/*if(puzzle.getCarSize(i) == 2 && state.getVariablePosition(i) >= 1 ) {
						blockedNr++;
					}	*/		
				}
				
			}
		}
	  
	  System.out.print("blockedNR: "+ blockedNr);
    return blockedNr;
  }

}

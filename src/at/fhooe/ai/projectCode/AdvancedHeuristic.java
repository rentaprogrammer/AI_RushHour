package at.fhooe.ai.projectCode;

import at.fhooe.ai.rushhour.Heuristic;
import at.fhooe.ai.rushhour.Puzzle;
import at.fhooe.ai.rushhour.State;

import java.util.LinkedList;
import java.util.List;

/**
 * This is a template for the class corresponding to your original
 * advanced heuristic.  This class is an implementation of the
 * <tt>Heuristic</tt> interface.  After thinking of an original
 * heuristic, you should implement it here, filling in the constructor
 * and the <tt>getValue</tt> method.
 */
public class AdvancedHeuristic implements Heuristic {

    private Puzzle puzzle;
    private LinkedList<Integer> openedIndices;


    /**
     * This is the required constructor, which must be of the given form.
     */
    public AdvancedHeuristic(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.openedIndices = new LinkedList<>();
    }
    
    /**
     * This method returns the value of the heuristic function at the
     * given state.
     */
    public int getValue(State state) {
        openedIndices.clear();

        if (state.isGoal()) {
            return 0;
        }

        return  advHeuristicBlocking(state);
    }

    private int advHeuristicBlocking(State state) {
        int hVal = 1;

        openedIndices.add(0);

        int goalCarInitPosFixed = puzzle.getFixedPosition(0);
        int curCarInitSizeVar = state.getVariablePosition(0) + puzzle.getCarSize(0)-1;

        for (int i = 1; i < puzzle.getNumCars(); i++) {

            if(puzzle.getCarOrient(i) && puzzle.getFixedPosition(i) > curCarInitSizeVar) {

                if (state.getVariablePosition(i) >= goalCarInitPosFixed-(puzzle.getCarSize(i)-1) && state.getVariablePosition(i) <= goalCarInitPosFixed) {
                    // --> car is blocking
                    hVal += isBlockingCarBlocked(state, i);
                }
            }
        }

        return hVal;
    }

    private int isBlockingCarBlocked(State state, int index) {

        if (openedIndices.contains(index)) return 0;

        openedIndices.add(index);

        int hVal = 1;
        int curCarInitPosFixed = puzzle.getFixedPosition(index);
        int curCarInitPosVar = state.getVariablePosition(index);
        int curCarInitSizeVar = state.getVariablePosition(index) + puzzle.getCarSize(0)-1;

        for (int i = 0; i < puzzle.getNumCars(); i++) {

            if (index == i) continue;

            if ((puzzle.getCarOrient(index) && !puzzle.getCarOrient(i))// if index car is vertical - only check horizontal cars
                    || (!puzzle.getCarOrient(index) && puzzle.getCarOrient(i))) { // if index car is horizontal - only check vertical cars

                if (puzzle.getFixedPosition(i) >= curCarInitSizeVar || puzzle.getFixedPosition(i) <= curCarInitPosVar) {

                    if (state.getVariablePosition(i) >= curCarInitPosFixed - puzzle.getCarSize(i) - 1 && state.getVariablePosition(i) <= curCarInitPosFixed) {

                        hVal += isBlockingCarBlocked(state, i);
                    }
                }
            }
        }

        return hVal;
    }
}

package edu.iastate.cs228.hw3;

import java.util.ArrayList;

/**
 * 
 * @author
 *
 */

public class EightPuzzle {
	/**
	 * This static method solves an 8-puzzle with a given initial state using
	 * two heuristics which compare the board configuration with the goal
	 * configuration by the number of mismatched tiles, and by the Manhattan
	 * distance, respectively. The goal configuration is set for all puzzles as
	 * 
	 * 1 2 3 8 4 7 6 5
	 * 
	 * @param s0
	 * @return
	 */
	public static String solve8Puzzle(State s0) {

		// TO DO

		// 1) Return null if the puzzle is not solvable.

		// 2) Otherwise, solve the puzzle with two heuristics. The two solutions
		// may be different
		// but must have the same length for optimality.

		Heuristic h[] = { Heuristic.TileMismatch, Heuristic.ManhattanDist };
		String[] moves = new String[2];

		for (int i = 0; i < 2; i++) {
			moves[i] = AStar(s0, h[i]);
		}
		String sol = "Tile MisMatch" + "\n" + moves[0] + "\n" + "ManhattanDist" + "\n" + moves[1];
		return sol;

		// 3) Combine the two solution strings into one that would print out in
		// the
		// output format specified in Section 5 of the project description.

	}

	/**
	 * This method implements the A* algorithm to solve the 8-puzzle with an
	 * input initial state s0. The algorithm is described in Section 3 of the
	 * project description.
	 * 
	 * Precondition: the puzzle is solvable with the initial state s0.
	 * 
	 * @param s0
	 *            initial state
	 * @param h
	 *            heuristic
	 * @return solution string
	 */
	public static String AStar(State s0, Heuristic h) {

		// Initialize the two lists used by the algorithm.
		OrderedStateList OPEN = new OrderedStateList(h, true);
		OrderedStateList CLOSE = new OrderedStateList(h, false);

		OPEN.addState(s0);


		
		s0.numMoves = 0;
		if (OPEN.size() == 0) {
			System.exit(0);
		}

		CLOSE.addState(OPEN.remove());
		
		System.out.println(OPEN.findState(s0));


		while (!s0.isGoalState()) {

			System.out.println("test");
			
			
			ArrayList<Move> m = new ArrayList<Move>();

			int x = 0;
			int y = 0;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {

					if (s0.board[i][j] == 0) {
						x = i;
						y = j;
						break;
					}
				}
			}
			
		
				if(y!=2) {
			m.add(Move.LEFT);
				}
				if(x!=0){
			m.add(Move.DOWN);
				}
				if(x!=2) {
			m.add(Move.UP);
				}
				if(y!=0) {
			m.add(Move.RIGHT);
				}

			State pre = (State) s0.clone();
			
			try {
			
			for (int c = 0; c < m.size(); c++) {
				

				s0 = s0.successorState(m.get(c));

				if (CLOSE.findState(s0) == null && OPEN.findState(s0) == null) {
					OPEN.addState(s0);
					
				}

				else if (OPEN.findState(s0) == s0) {

					if (s0.compareTo(OPEN.findState(s0)) < 0) {

						OPEN.findState(s0).predecessor = pre;
					}
				}

				else if (CLOSE.findState(s0) == s0 && s0.compareTo(CLOSE.findState(s0)) > 0) {

					OPEN.addState(CLOSE.findState(s0));

				}

			}
			}
			catch(IllegalStateException e) {
				
			}

		}

		EightPuzzle temp = new EightPuzzle();
		String test = temp.solutionPath(s0);
		return test;

	}

	/**
	 * From a goal state, follow the predecessor link to trace all the way back
	 * to the initial state. Meanwhile, generate a string to represent board
	 * configurations in the reverse order, with the initial configuration
	 * appearing first. Between every two consecutive configurations is the move
	 * that causes their transition. A blank line separates a move and a
	 * configuration. In the string, the sequence is preceded by the total
	 * number of moves and a blank line.
	 * 
	 * See Section 5 in the projection description for an example.
	 * 
	 * Call the toString() method of the State class.
	 * 
	 * @param goal
	 * @return
	 */
	private String solutionPath(State goal) {

		ArrayList<State> tempStates = new ArrayList<State>();

		String temp = "goal.heu";

		while (goal.predecessor != null) {
			goal = goal.predecessor;

			tempStates.add(goal);
		}

		for (int i = tempStates.size() - 1; i > -1; i--) {

			temp = temp + tempStates.get(i).toString();

		}

		return temp;
	}

}

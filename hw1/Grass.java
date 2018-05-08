package edu.iastate.cs228.hw1;

/**
 *  
 * @author joshuabump
 *
 */

/**
 * Grass may be eaten out or taken over by deers.
 *
 */
public class Grass extends Living {
	public Grass(Jungle j, int r, int c) {
		this.jungle = j;
		this.row = r;
		this.column = c;
	}

	public State who() {

		return State.GRASS;
	}

	/**
	 * Grass can be eaten out by too many deers in the neighborhood. Deers may
	 * also multiply fast enough to take over Grass.
	 * 
	 * @param jNew
	 *            jungle in the next cycle
	 * @return Living life form occupying the square in the next cycle.
	 */
	public Living next(Jungle jNew) {

		// number of live forms

		int[] pop = new int[Living.NUM_LIFE_FORMS];

		int i = row;
		int j = column;

		census(pop);

		if (pop[DEER] >= 3*pop[GRASS] && pop[DEER] !=0) {
			return new Empty(jNew, i, j);
		} else if (pop[DEER] >= 4) {
			return new Deer(jNew, j, j, 0);
		}

		//
		// See Living.java for an outline of the function.
		// See Section 2.4 in the project description for the survival rules for
		// grass.
		return new Grass(this.jungle, i, j);
	}
}

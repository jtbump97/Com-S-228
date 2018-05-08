package edu.iastate.cs228.hw1;

/**
 *  
 * @author joshuabump
 *
 */

/**
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living {
	public Empty(Jungle j, int r, int c) {
		this.jungle = j;
		this.row = r;
		this.column = c;
	}

	public State who() {

		return State.EMPTY;
	}

	/**
	 * An empty square will be occupied by a neighboring Deer, Grass, Jaguar, or
	 * Puma, or remain empty.
	 * 
	 * @param jNew
	 *            jungle in the next life cycle.
	 * @return Living life form in the next cycle.
	 */
	public Living next(Jungle jNew) {

		// number of life forms

		int[] pop = new int[Living.NUM_LIFE_FORMS];

		int i = this.row;
		int j = this.column;

		this.census(pop);

		if (pop[DEER] > 1) {
			return new Deer(jNew, i, j, 0);

		} else if (pop[PUMA] > 1) {
			return new Puma(jNew, i, j, 0);
		} else if (pop[JAGUAR] > 1) {
			return new Jaguar(jNew, i, j, 0);
		} else if (pop[GRASS] >= 1) {
			return new Grass(jNew, i, j);
		}

		//
		// See Living.java for an outline of the function.
		// See Section 2.4 in the project description for the survival rules for
		// grass.
		return new Empty(this.jungle, i, j);
	}
}

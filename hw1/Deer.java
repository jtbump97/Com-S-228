package edu.iastate.cs228.hw1;

/**
 * 
 * @author joshuabump
 *
 */

/*
 * A deer eats grass and lives no more than six years.
 */
public class Deer extends Animal {
	/**
	 * Creates a Deer object.
	 * 
	 * @param j:
	 *            jungle
	 * @param r:
	 *            row position
	 * @param c:
	 *            column position
	 * @param a:
	 *            age
	 */
	public Deer(Jungle j, int r, int c, int a) {
		jungle = j;
		row = r;
		column = c;
		age = a;
	}

	// Deer occupies the square.
	public State who() {

		return State.DEER;
	}

	/**
	 * @param jNew
	 *            jungle in the next cycle
	 * @return Living new life form occupying the same square
	 */
	public Living next(Jungle jNew) {

		// number of live forms

		int[] pop = new int[Living.NUM_LIFE_FORMS];
		int i = this.row;
		int j = this.column;

		jNew.grid[i][j].census(pop);

		if (age == DEER_MAX_AGE || pop[GRASS] == 0) {
			return new Empty(jNew, i, j);
		}
		else if (pop[PUMA] + pop[JAGUAR] > pop[DEER] && pop[PUMA] >= 2 * pop[JAGUAR]) {
			return new Puma(jNew, i, j, 0);

		} 
		else if (pop[PUMA] + pop[JAGUAR] > pop[DEER]) {
			return new Jaguar(jNew, i, j, 0);
		}

		return new Deer(jungle, i, j, age + 1);

		//
		// See Living.java for an outline of the function.
		// See Section 2.3 in the project description for the survival rules for
		// a deer.

	}
}

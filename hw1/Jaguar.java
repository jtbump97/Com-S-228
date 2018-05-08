package edu.iastate.cs228.hw1;

/**
 *  
 * @author joshuabump
 *
 */

/**
 * A jaguar eats a deer and competes against a puma.
 */
public class Jaguar extends Animal {
	/**
	 * Constructor
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
	public Jaguar(Jungle j, int r, int c, int a) {
		jungle = j;
		row = r;
		column = c;
	
		age = a;
	}

	/**
	 * A jaguar occupies the square.
	 */
	public State who() {

		return State.JAGUAR;
	}

	/**
	 * A jaguar dies of old age or hunger, from isolation and attack by more
	 * numerous pumas.
	 * 
	 * @param jNew
	 *            jungle in the next cycle
	 * @return Living life form occupying the square in the next cycle.
	 */
	public Living next(Jungle jNew) {
		// number of live forms

		int[] pop = new int[5];

		int i = this.row;
		int j = this.column;

		this.census(pop);

		if (age == JAGUAR_MAX_AGE) {
			return new Empty(jNew, i, j);
		}

		else if (2*pop[JAGUAR] <= pop[PUMA] && pop[JAGUAR]!=0) {
			return new Puma(jNew, i, j, 0);
		}
		else if (pop[DEER] < pop[JAGUAR] + pop[PUMA]) {
			return new Empty(jNew, i, j);
		}

		//
		// See Living.java for an outline of the function.
		// See Section 2.4 in the project description for the survival rules for
		// grass.
		return new Jaguar(jNew, i, j, age + 1);
	}
}

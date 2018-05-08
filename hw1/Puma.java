package edu.iastate.cs228.hw1;

/**
 *  
 * @author joshuabump
 *
 */

/**
 * A puma eats deers and competes against a jaguar.
 */
public class Puma extends Animal {
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
	public Puma(Jungle j, int r, int c, int a) {
		this.jungle = j;
		this.row = r;
		this.column = c;
		this.age = a;
	}

	/**
	 * A puma occupies the square.
	 */
	public State who() {
		return State.PUMA;
	}

	/**
	 * A puma dies of old age or hunger, or from attack by a jaguar.
	 * 
	 * @param jNew
	 *            jungle in the next cycle
	 * @return Living life form occupying the square in the next cycle.
	 */
	public Living next(Jungle jNew) {
		// number of live forms

		int[] pop = new int[Living.NUM_LIFE_FORMS];
		int i = this.row;
		int j = this.column;

		this.census(pop);

		if (age == PUMA_MAX_AGE) {
			return new Empty(this.jungle, i, j);

		}

		else if (pop[PUMA] < pop[JAGUAR]) {
			return new Jaguar(this.jungle, i, j, 0);
		}

		else if ((pop[JAGUAR] + pop[PUMA]) > pop[DEER]) {
			return new Empty(this.jungle, i, j);
		}

		//
		// See Living.java for an outline of the function.
		// See Section 2.4 in the project description for the survival rules for
		// grass.
		return new Puma(this.jungle, i, j, age + 1);
	}
}

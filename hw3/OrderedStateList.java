package edu.iastate.cs228.hw3;

/**
 *  
 * @author joshuabump
 *
 */

/**
 * This class describes a circular doubly-linked list of states to represent
 * both the OPEN and CLOSED lists used by the A* algorithm. The states on the
 * list are sorted in the
 * 
 * a) order of non-decreasing cost estimate for the state if the list is OPEN,
 * or b) lexicographic order of the state if the list is CLOSED.
 * 
 */
public class OrderedStateList {
	

	/**
	 * Implementation of a circular doubly-linked list with a dummy head node.
	 */
	private State head; // dummy node as the head of the sorted linked list
	private int size = 0;

	private boolean isOPEN; // true if this OrderedStateList object is the list
							// OPEN and false
							// if the list CLOSED.

	/**
	 * Default constructor constructs an empty list. Initialize heuristic. Set
	 * the fields next and previous of head to the node itself. Initialize
	 * instance variables size and heuristic.
	 * 
	 * @param h
	 * @param isOpen
	 */
	public OrderedStateList(Heuristic h, boolean isOpen) {

		head = new State(new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } });
		int[][] r = new int[3][3];
		
		head.next = head;
		head.previous = head;
		size = 0;

		State.heu = h; // initialize heuristic used for evaluating all State
						// objects.

	}

	public int size() {
		return size;
	}

	/**
	 * A new state is added to the sorted list. Traverse the list starting at
	 * head. Stop right before the first state t such that compareStates(s, t)
	 * <= 0, and add s before t. If no such state exists, simply add s to the
	 * end of the list.
	 * 
	 * Precondition: s does not appear on the sorted list.
	 * 
	 * @param s
	 */
	public void addState(State s) {
		
		if(this.findState(s)==null) {

			size++;
			State temp = head.next;
			
			while (temp!=head) {

				if (compareStates(s, temp) <= 0) {

					temp.previous.next = s;
					temp.previous = s;
					s.previous = temp.previous;
					s.next = temp;
					
					
					return;
				}
				temp = temp.next;
				
			}
			s.next = temp;
			s.previous = temp.previous;
			temp.previous.next= s;
			temp.previous = s;
		}
		
	}

	/**
	 * Conduct a sequential search on the list for a state that has the same
	 * board configuration as the argument state s.
	 * 
	 * Calls equals() from the State class.
	 * 
	 * @param s
	 * @return the state on the list if found null if not found
	 */
	public State findState(State s) {

		
		
		State temp = head.next;

		for (int i = 0; i < size; i++) {

			if (s.equals(temp)&&temp!=head) {
				return s;
			}

		temp = temp.next;
		}
		return null;
	}

	/**
	 * Remove the argument state s from the list. It is used by the A* algorithm
	 * in maintaining both the OPEN and CLOSED lists.
	 * 
	 * @param s
	 * @throws IllegalStateException
	 *             if s is not on the list
	 */
	public void removeState(State s) throws IllegalStateException {

		if (findState(s) != null) {

			size--;
			
			State temp = head.next;

			for (int i = 0; i < this.size; i++) {

				if (s.equals(temp)) {
					temp.next.previous = temp.previous;
					temp.previous.next = temp.next;
					break;
				}
				temp=temp.next;

			}
		}

		else {
			throw new IllegalStateException();
		}

	}

	/**
	 * Remove the first state on the list and return it. This is used by the A*
	 * algorithm in maintaining the OPEN list.
	 * 
	 * @return
	 */
	public State remove() {
		
		
		if(this.size()==0){
			return null;
		}
		
		State ret = head.next;
		State temp = head.next.next;
		
		
		size--;
		
		temp.previous = head;
		head.next = temp;
		
		
		return ret;
		}
		

	/**
	 * Compare two states depending on whether this OrderedStateList object is
	 * the list OPEN or the list CLOSE used by the A* algorithm. More
	 * specifically,
	 * 
	 * a) call the method compareTo() of the State if isOPEN == true, or b)
	 * create a StateComparator object to call its compare() method if isOPEN ==
	 * false.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private int compareStates(State s1, State s2) {

		if (isOPEN == true) {
			return s1.compareTo(s2);

		} else {
			StateComparator temp = new StateComparator();

			return temp.compare(s1, s2);
		}

	}

	public String print() {
		String ret = "";
		State comp = head;
		ret += head.toString();
		for (int i = 0; i < size; i += 1) {
			ret += comp.next.toString();
			comp = comp.next;
		}
		return ret;
	}
}

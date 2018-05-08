package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * An implementation of {@link Sorter} that performs insertion sort to sort the
 * list.
 * 
 * @author joshuabump
 */
public class InsertionSorter extends Sorter {
	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException {
		Comparator<String> cmp = comp;

		int length = toSort.length();

		for (int i = 1; i < length; i++) {

			String temp = toSort.get(i);
			int j = 0;

			//comparing words and changing words
			for (j=i-1; j>=0 && 0>cmp.compare(temp, toSort.get(j));j--) 
				
				toSort.set(j+1, toSort.get(j));
			
				toSort.set(j+1, temp);
				
			}
			
		}
		
		
	}


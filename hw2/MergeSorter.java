package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * An implementation of {@link Sorter} that performs merge sort to sort the
 * list.
 * 
 * @author joshuabump
 */
public class MergeSorter extends Sorter {
	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException {

		if (toSort == null) {
			return;
		}

		if (toSort.length() > 1) {
			int mid = toSort.length() / 2;
			

			// Split left part
			String[] left = new String[mid];
			WordList leftWords = new WordList(left);
			for (int i = 0; i < mid; i++) {
				leftWords.set(i,toSort.get(i));
			}

			// Split right part
			String[] right = new String[toSort.length() - mid];
			WordList rightWords = new WordList(right);
			for (int i = mid; i < toSort.length(); i++) {
				rightWords.set(i-mid, toSort.get(i));
			}
			sort(leftWords,comp);
			sort(rightWords,comp);

			int i = 0;
			int j = 0;
			int k = 0;

			// Merge left and right arrays
			while (i < left.length && j < right.length) {
				if (0>comp.compare(leftWords.get(i), rightWords.get(j))) {
					
					toSort.set(k, leftWords.get(i));
					
					i++;
				} else {
					toSort.set(k, rightWords.get(j));
					j++;
				}
				k++;
			}
			// Collect remaining elements
			while (i < left.length) {
				toSort.set(k, leftWords.get(i));
				
				i++;
				k++;
			}
			while (j < right.length) {
				toSort.set(k, rightWords.get(j));
				
				j++;
				k++;
			}
		}
	}

	

	//private void

	//mergeSortRec(WordList list, Comparator<String> comp, int start, int end)
  {

		//NOT USED
  }
}

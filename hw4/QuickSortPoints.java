package edu.iastate.cs228.hw4;

/**
 * @author joshuabump
 */

import java.util.Comparator;

/**
 * This class sorts an array of Point objects using a provided Comparator. For
 * the purpose you may adapt your implementation of quicksort from Project 2.
 */

public class QuickSortPoints {
	private Point[] points; // Array of points to be sorted.

	/**
	 * Constructor takes an array of Point objects.
	 * 
	 * @param pts
	 */
	QuickSortPoints(Point[] pts) {
		points = pts;
		getSortedPoints(pts);
	}

	/**
	 * Copy the sorted array to pts[].
	 * 
	 * @param pts
	 *            array to copy onto
	 */
	void getSortedPoints(Point[] pts) {
		
		for(int i = 0;i<points.length;i++) {
			pts[i] = points[i];
		}

	}

	/**
	 * Perform quicksort on the array points[] with a supplied comparator.
	 * 
	 * @param comp
	 */
	public void quickSort(Comparator<Point> comp) {
		int low = 0;
		int high = points.length - 1;

		quickSortRec(low, high, comp);
		
		Point[] fix = new Point[points.length];
		for(int i = 0; i<points.length;i++) {
		 fix[i] = points[i];
		}
		int helper = points.length - 1;
		for (int x = 0; x < points.length; x++) {

			points[x] = fix[helper];
			helper--;
		}
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 *            starting index of the subarray
	 * @param last
	 *            ending index of the subarray
	 */
	private void quickSortRec(int first, int last, Comparator<Point> comp) {
		if (points == null || points.length == 0)
			return;

		if (first >= last)
			return;

		int middle = first + (last - first) / 2;
		Point pivot = points[middle];

		int i = first, j = last;
		while (i <= j) {
			while (comp.compare(points[i], pivot) > 0) {
				i++;
			}

			while (comp.compare(points[j], pivot) < 0) {
				j--;
			}

			if (i <= j) {
				Point temp = points[i];
				points[i] = points[j];
				points[j] = temp;
				points[j] = temp;
				i++;
				j--;
			}
		}

		if (first < j)
			quickSortRec(first, j, comp);

		if (last > i)
			quickSortRec(i, last, comp);

	}

	// NOT USED, this code is implemented inside of quickSortRec
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last, Comparator<Point> comp) {
		return 0;
		// TODO
	}
}

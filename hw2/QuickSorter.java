package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs quick sort
 * to sort the list.
 * 
 * @author joshuabump
 */
public
class
QuickSorter
  extends Sorter
{
  @Override
  public
  void
  sort(WordList toSort, Comparator<String> comp)
    throws NullPointerException
  {
	  int low = 0;
	  int high = toSort.length() - 1;

		quickSortRec(toSort,comp, low, high);
		WordList fix = toSort.clone();
		int helper = toSort.length()-1;
		for(int x=0;x<toSort.length();x++) {
			
			toSort.set(x, fix.get(helper));
			helper--;
		}
  }

  private
  void
  quickSortRec(WordList list, Comparator<String> comp, int start, int end)
  {
	  if (list == null || list.length() == 0)
			return;

		if (start >= end)
			return;

		
		int middle = start + (end - start) / 2;
		String pivot = list.get(middle);

		
		int i = start, j = end;
		while (i <= j) {
			while (comp.compare(list.get(i), pivot)>0) {
				i++;
			}

			while (comp.compare(list.get(j), pivot)<0) {
				j--;
			}

			if (i <= j) {
				String temp = list.get(i);
				list.swap(i,j);
				list.set(j, temp);
				i++;
				j--;
			}
		}

		
		if (start < j)
			quickSortRec(list,comp, start, j);

		if (end > i)
			quickSortRec(list,comp, i, end);
    
    
		
  }
//not used
  private
  int
  partition(WordList list, Comparator<String> comp, int start, int end)
  {
	return 0; 
	  }
	 
  }


package edu.iastate.cs228.hw2;


import java.io.IOException;
import java.util.Comparator;


/**
 * An class that compares various methods of sorting.
 * 
 * @author Mike Petersen
 */
public
class
SorterFramework
{
  /**
   * Loads data necessary to run the sorter statistics output, and runs it.
   * The only logic within this method should be that necessary to use the
   * given file names to create the {@link AlphabetComparator},
   * {@link WordList}, and sorters to use, and then using them to run the
   * sorter statistics output.
   * 
   * @param args
   *   an array expected to contain two arguments:
   *    - the name of a file containing the ordering to use to compare
   *      characters
   *    - the name of a file containing words containing only characters in the
   *      other file
 * @throws FileNotFoundException 
 * @throws NullPointerException 
   */
	
	
	
	  //variable made to keep track of which sort to use
	private static int sorterNum = 0;

	public
  static
  void
  main(String[] args) throws NullPointerException, IllegalArgumentException, IOException
  {
	  
    if(args.length!=2) {
    	System.out.print("too many arguments");
    	
    	return;
    }

    Alphabet alphabet;
    AlphabetComparator comparator;
    WordList words;
    Sorter[] sorters;
    int totalToSort = 1000000;

    
   
    alphabet = new Alphabet(args[1]);
    comparator = new AlphabetComparator(alphabet);
    words = new WordList(args[0]);
    
    sorters = new Sorter[3];
    sorters[0] = new InsertionSorter();
    sorters[1] = new MergeSorter();
    sorters[2] = new QuickSorter();
    
    //words per second calculator
    double timeToSort = 0;
    //sorter framework used in this class
    SorterFramework running = new SorterFramework(sorters, comparator, words, totalToSort);
    
    //helps to make sure to not sort an already sorted list
    WordList x = words.clone();
    
    int timeSorts = totalToSort/words.length();
   
    
    //labels for statistics
   
    System.out.printf("Sorter           ");
    System.out.print( "    Time in Seconds       "    );
    System.out.print("Total Sorted" + "         ");
    System.out.print("Length of List" + "      "   );
    
    System.out.print("Words per Second     ");
    System.out.print("Avg time per Sort in Seconds     ");
    System.out.println("Total Comparisons");
    
    
    
   //insertion
   words = x.clone();
    running = new SorterFramework(sorters, comparator, words, totalToSort);
    sorterNum = 0;
    running.run();
    System.out.printf("Insertion Sorter       ");
    System.out.printf(sorters[0].getTotalSortingTime()/1000.000 + "            ");
    System.out.print(sorters[0].getTotalWordsSorted()+ "              ");
    System.out.print(words.length()+"                  ");
    timeToSort = sorters[0].getTotalWordsSorted()/sorters[0].getTotalSortingTime()*1000.000;
    System.out.print(timeToSort +"                       ");
    System.out.print(sorters[0].getTotalSortingTime()/timeSorts/1000.000+"                           ");
    System.out.println(sorters[0].getTotalComparisons());
    
    
    //Merge
    words = x.clone();
    running = new SorterFramework(sorters, comparator, words, totalToSort);
    sorterNum = 1;
    running.run();
    System.out.printf("Merge Sorter           ");
    System.out.printf((sorters[1].getTotalSortingTime()/1000.000) +"            "    );
    System.out.print(sorters[1].getTotalWordsSorted() + "                 "   );
    System.out.print(words.length()+"                 ");
    timeToSort = sorters[1].getTotalWordsSorted()/sorters[1].getTotalSortingTime()*1000.000;
    System.out.print(timeToSort+"                    ");
    System.out.print(sorters[1].getTotalSortingTime()/timeSorts/1000.000+"                            ");
    System.out.println(sorters[1].getTotalComparisons());
    
    //Quick
    sorterNum = 2;
    words = x.clone();
    running = new SorterFramework(sorters, comparator, words, totalToSort);
    running.run();
    System.out.printf("Quick Sorter" + "           "   );
    
    System.out.printf(sorters[2].getTotalSortingTime()/1000.000+ "            ");
    System.out.print(sorters[2].getTotalWordsSorted() + "               "   );
    System.out.print(words.length()+"                  ");
    timeToSort = sorters[2].getTotalWordsSorted()/sorters[2].getTotalSortingTime()*1000.000;
    System.out.print(timeToSort+ "                    ");
    System.out.print(sorters[2].getTotalSortingTime()/timeSorts/1000.000+"                           ");
    System.out.println(sorters[2].getTotalComparisons());
    }
   

  /**
   * The comparator to use for sorting.
   */
  private
  Comparator<String>
  comparator;

  /**
   * The words to sort.
   */
  private
  WordList
  words;

  /**
   * The array of sorters to use for sorting.
   */
  private
  Sorter[]
  sorters;

  /**
   * The total amount of words expected to be sorted by each sorter.
   */
  private
  int
  totalToSort;


  /**
   * Constructs and initializes the SorterFramework.
   * 
   * @throws NullPointerException
   *   if any of {@code sorters}, {@code comparator}, {@code words}, or
   *   elements of {@code sorters} are {@code null}
   * @throws IllegalArgumentException
   *   if {@code totalToSort} is negative
   */
  public
  SorterFramework(Sorter[] sorters, Comparator<String> comparator,
          WordList words, int totalToSort)
    throws NullPointerException,
           IllegalArgumentException
  {
	  this.sorters = sorters;
	  this.comparator = comparator;
	  this.words = words;
		
			if (sorters == null || comparator == null || words == null) {
				throw new NullPointerException("null objects");
			} else if (totalToSort < 0) {
				throw new IllegalArgumentException("total is negative");
			}

			
		}
  


  /**
   * Runs all sorters using
   * {@link Sorter#sortWithStatistics(WordList, Comparator, int)
   * sortWithStatistics()}, and then outputs the following information for each
   * sorter:
   *  - the name of the sorter
   *  - the length of the word list sorted each time
   *  - the total number of words sorted
   *  - the total time used to sort words
   *  - the average time to sort the word list
   *  - the number of elements sorted per second
   *  - the total number of comparisons performed
 * @throws IOException 
 * @throws IllegalArgumentException 
 * @throws NullPointerException 
   */
  public
  void
  run() throws NullPointerException, IllegalArgumentException, IOException
  {
  
	  
	  
	  sorters[sorterNum].sortWithStatistics(words, comparator, totalToSort);
	  
  }
}

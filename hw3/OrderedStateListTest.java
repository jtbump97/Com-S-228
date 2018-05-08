package edu.iastate.cs228.hw3;

import org.junit.Test;
import java.io.FileNotFoundException;
import org.junit.Before; 
import static org.junit.Assert.assertEquals;

/**
 * 
 * @author katelynlamison
 *
 */
public class OrderedStateListTest {
	
	private OrderedStateList closed;
	private State sSame;
	private State head;
	private State s0; 
	private State s1;
	private State s2;
	private State s3;
	StateComparator sc;
	String ret;
	
	@Before
	public void setUp() throws FileNotFoundException
	{
/*		Need to add this method to your OrderedStateListClass and make it public
	  //DELETE AFTER TESTING
	  public String print()
	  {
		  String ret = "";
		  State comp = head;
		  ret += head.toString();
		  for (int i = 0; i < size; i += 1)
		  {
			  ret += comp.next.toString();
			  comp = comp.next;
		  }
		  return ret;
	  }*/
		
		
		// will compare states in the lexicographical order
		closed = new OrderedStateList(Heuristic.TileMismatch, false);
		sSame = new State(new int[][] { {0,1,2}, {3,4,5}, {6,7,8} });
		head = new State(new int[][] { {0,1,2}, {3,4,5}, {6,7,8} });
		s0 = new State(new int[][] { {0,1,2}, {3,4,5}, {6,7,8} });
		s1 = new State(new int[][] { {1,0,2}, {3,4,5}, {6,7,8} });
		s2 = new State(new int[][] { {2,1,0}, {3,4,5}, {6,7,8} });
		s3 = new State(new int[][] { {3,1,2}, {0,4,5}, {6,7,8} });
		sc = new StateComparator();
		ret = "";
	}
	
	
	@Test
	public void compareStateNeg()
	{
		assertEquals(sc.compare(s0, s1), -1);
	}
	
	@Test
	public void compareStatePos()
	{
		assertEquals(sc.compare(s1, s0), 1);
	}
	
	@Test
	public void compareStateZero()
	{
		assertEquals(sc.compare(s0, sSame), 0);
	}
	
	@Test
	public void initialSize()
	{
		assertEquals(closed.size(), 0);
	}
	
	@Test
	public void findStateNull()
	{
		assertEquals(closed.findState(s2), null);
	}
	
	@Test
	public void adds0()
	{
		closed.addState(s0);
		assertEquals(closed.print(), head.toString() +  s0.toString());
	}
	
	@Test
	public void adds0size()
	{
		closed.addState(s0);
		assertEquals(closed.size(), 1);
	}
	
	@Test
	public void adds0Find()
	{
		closed.addState(s0);
		assertEquals(closed.findState(s0), s0);
	}
	
	@Test
	public void removes0()
	{
		closed.addState(s0);
		closed.removeState(s0);
		assertEquals(closed.size(), 0);
	}
	
	@Test
	public void adds1()
	{
		closed.addState(s0);
		closed.addState(s1);
		assertEquals(closed.print(), (head.toString() + s0.toString() + s1.toString()));
		assertEquals(closed.size(), 2);
		assertEquals(closed.findState(s1), s1);
	}
	
	@Test
	public void adds2()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		assertEquals(closed.print(), (head.toString() + s0.toString() + s1.toString() + s2.toString()));
		assertEquals(closed.size(), 3);
		assertEquals(closed.findState(s2), s2);
	}
	
	@Test
	public void adds3()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		assertEquals(closed.print(), (head.toString() + s0.toString() + s1.toString() + s2.toString() + s3.toString()));
		assertEquals(closed.size(), 4);
		assertEquals(closed.findState(s3), s3);
	}
	
	@Test
	public void removeStateTestPrint()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		closed.removeState(s2);
		assertEquals(closed.print(), (head.toString() + s0.toString() + s1.toString() + s3.toString()));
	}
	
	@Test
	public void removeStateTestSize()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		closed.removeState(s2);
		assertEquals(closed.size(), 3);
	}
	
	
	@Test
	public void removeStateTestFinder()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		closed.removeState(s2);
		assertEquals(closed.findState(s2), null);
	}
	
	@Test
	public void removeTestReturn()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		assertEquals(closed.remove(), s0);
	}
	
	@Test
	public void removeTestPrint()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		closed.remove();
		assertEquals(closed.print(), (head.toString() + s1.toString() + s2.toString() + s3.toString()));
	}
	
	@Test
	public void removeTestSize()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		closed.remove();
		assertEquals(closed.size(), 3);
	}
	
	@Test
	public void removeTestFinder()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		closed.remove();
		assertEquals(closed.findState(s0), null);
	}
	
	@Test
	public void removeOutOfOrder1()
	{
		closed.addState(s2);
		closed.addState(s0);
		closed.addState(s1);
		closed.remove();
		assertEquals(closed.findState(s0), null);
	}
	
	@Test
	public void removeOutOfOrder2()
	{
		closed.addState(s2);
		closed.addState(s0);
		closed.addState(s1);
		assertEquals(closed.remove(), s0);
	}
	
	@Test
	public void removeOutOfOrder3()
	{
		closed.addState(s2);
		closed.addState(s0);
		closed.addState(s1);
		closed.remove();
		assertEquals(closed.print(), (head  + s1.toString() + s2.toString()));
	}
	
	@Test
	public void removeEmptyTest()
	{
		assertEquals(closed.remove(), null);
	}
	
	@Test(expected = IllegalStateException.class)
	public void removeNotOnNode()
	{
		closed.removeState(s2);
	}
	
	@Test
	public void addInDifferentOrder()
	{
		closed.addState(s0);
		closed.addState(s2);
		closed.addState(s3);
		closed.addState(s1);
		assertEquals(closed.print(), (head.toString() + s0.toString() + s1.toString() + s2.toString() + s3.toString()));
		assertEquals(closed.size(), 4);
	}
	
	@Test
	public void addAlreadtInList()
	{
		closed.addState(s0);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		closed.addState(s1);
		assertEquals(closed.print(), (head.toString() + s0.toString() + s1.toString() + s2.toString() + s3.toString()));
		assertEquals(closed.size(), 4);
	}
	
	@Test
	public void wild()
	{
		closed.addState(s0);
		closed.addState(s3);
		closed.addState(s1);
		closed.addState(s1);
		closed.addState(s2);
		closed.addState(s3);
		closed.addState(s2);
		closed.remove();
		closed.removeState(s3);
		
		assertEquals(closed.print(), (head.toString() + s1.toString() + s2.toString() ));
		assertEquals(closed.size(), 2);
	}
	
}



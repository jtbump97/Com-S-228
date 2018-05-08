package edu.iastate.cs228.hw3;

import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException {

		int[][] goalBoard = new int[3][3];
		goalBoard[0][0] = 1;
		goalBoard[0][1] = 2;
		goalBoard[0][2] = 3;
		goalBoard[1][0] = 8;
		goalBoard[1][1] = 0;
		goalBoard[1][2] = 4;
		goalBoard[2][0] = 7;
		goalBoard[2][1] = 6;
		goalBoard[2][2] = 5;

		int[][] Board = new int[3][3];
		Board[0][0] = 1;
		Board[0][1] = 2;
		Board[0][2] = 3;
		Board[1][0] = 6;
		Board[1][1] = 5;
		Board[1][2] = 7;
		Board[2][0] = 8;
		Board[2][1] = 4;
		Board[2][2] = 0;

		State in = new State(Board);

		State tester = (State)in.clone();
		
		System.out.println(tester.toString());
		
		in.successorState(Move.RIGHT);
		System.out.println(in.toString());
		in.successorState(Move.DOWN);
		System.out.println(in.toString());
		in.successorState(Move.LEFT);
		System.out.println(in.toString());
		
		in.successorState(Move.UP);
		System.out.println(in.toString());
		in.successorState(Move.RIGHT);
		System.out.println(in.toString());
		in.successorState(Move.DOWN);
		System.out.println(in.toString());
		
		in.successorState(Move.RIGHT);
		System.out.println(in.toString());

		in.successorState(Move.UP);
		System.out.println(in.toString());
		in.successorState(Move.LEFT);
		System.out.println(in.toString());
		in.successorState(Move.DOWN);
		
		
	
		System.out.println(in.toString());

	}

}

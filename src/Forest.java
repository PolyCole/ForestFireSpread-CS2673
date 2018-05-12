import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import edu.princeton.cs.introcs.StdDraw;
import java.util.Stack;

/*
 * Author: Cole Polyak
 * Assignment 3
 * 27 April 2018
 * 
 * Forest.java
 * 
 * This class creates a 2D array that represents a forest.
 */

public class Forest 
{
	private int[][] forest;
	private int width, height;
	private double density;

	// Constructor
	public Forest(int width, int height, double d)
	{
		density = d;
		Random rng = new Random();
		forest = new int[height][width];
		
		this.width = width;
		this.height = height;
		
		// Randomly creates the forest.
		for(int i = 0; i < forest.length; ++i)
		{
			for(int j = 0; j < forest[i].length; ++j)
			{
				double rando = rng.nextDouble();
				
				// Tree
				if(rando <= density)
				{
					forest[i][j] = 1;
				}
				
				// No tree.
				else
				{
					forest[i][j] = 0;
				}
			}
		}
		
	}
	
	// Outputs forest.
	public String toString()
	{
		String result = "";
		
		for(int i = 0; i < forest.length; ++i)
		{
			for(int j = 0; j < forest[i].length; ++j)
			{
				// Empty space.
				if(forest[i][j] == 0)
				{
					result += "-" + " ";
				}
				
				// Tree
				else if(forest[i][j] == 1)
				{
					result += "1" + " ";
				}
				
				// Tree's on fire!
				else if(forest[i][j] == 2)
				{
					result += "*" + " ";
				}
			}
			result += "\n";
		}
		
		return result;
	}
	
	// Checks surrounding trees for paths by drilling straight down.
	public boolean depthFirstSearch()
	{
		Stack<Position> cellsToExplore = new Stack<>();
		
		// Finding all the cells in the first row that have trees.
		for(int j = 0; j < forest[0].length; ++j)
		{
			// Adding trees in top row to stack.
			if(forest[0][j] == 1)
			{
				forest[0][j] = 2;
				cellsToExplore.push(new Position(0,j));
			}
		}

		Position a = null;
		
		while(!(cellsToExplore.isEmpty()))
		{
			// Selecting the current tree.
			Position current = cellsToExplore.pop();
			forest[current.i][current.j] = 2;
			
			
			if(current.i == height -1) { return true;}
			
			// Checking around the tree.
			for(int j = 0; j < 4; ++j)
			{
				switch(j)
				{
				case 0:
					a = lookAbove(current.i, current.j);
					break;
				case 1:
					a = lookLeft(current.i, current.j);
					break;
				case 2:
					a = lookRight(current.i, current.j);
					break;
				case 3:
					a = lookBelow(current.i, current.j);
					break;
				}
				
				// Adding surrounding trees to stack.
				if(a != null)
				{
					cellsToExplore.push(a);
				}
			}
		}
		
		return false;
	}

	// Checks the surrounding trees for paths in a ripple pattern.
	public boolean breadthFirstSearch()
	{
		Queue<Position> cellsToExplore = new LinkedList<>();

		// Finding all the cells in the first row that have trees.
		for(int j = 0; j < forest[0].length; ++j)
		{
			// Adding trees in top row to stack.
			if(forest[0][j] == 1)
			{
				forest[0][j] = 2;
				
				// Adds position to end of list.
				cellsToExplore.add(new Position(0,j));
			}
		}

		Position a = null;

		while(!(cellsToExplore.isEmpty()))
		{
			// Selecting the current tree.
			Position current = cellsToExplore.remove();
			forest[current.i][current.j] = 2;


			if(current.i == height -1) { return true;}

			// Checking around the tree.
			for(int j = 0; j < 4; ++j)
			{
				switch(j)
				{
				case 0:
					a = lookAbove(current.i, current.j);
					break;
				case 1:
					a = lookLeft(current.i, current.j);
					break;
				case 2:
					a = lookRight(current.i, current.j);
					break;
				case 3:
					a = lookBelow(current.i, current.j);
					break;
				}

				// If the tree exists, add it to the queue.
				if(a != null)
				{
					cellsToExplore.add(a);
				}
			}
		}

		return false;
	}
	
	// Checks if the index about to be accessed is valid and is in bounds.
	public boolean isValid(Position p)
	{
		try
		{
			int t = forest[p.i][p.j];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		
		return true;
	}

	// Looks above current.
	public Position lookAbove(int i, int j)
	{
		Position p = new Position(i+1,j);
		if(isValid(p) && forest[i+1][j] == 1) { return p; }
		else { return null; }
	}
	
	// Looks below current.
	public Position lookBelow(int i, int j)
	{
		Position p = new Position(i-1,j);
		if(isValid(p)&& forest[i-1][j] == 1) { return p; }
		else { return null; }
	}
	
	// Looks right of current.
	public Position lookRight(int i, int j)
	{
		Position p = new Position(i,j+1);
		if(isValid(p)&& forest[i][j+1] == 1) { return p; }
		else { return null; }
	}
	
	// Looks left of current.
	public Position lookLeft(int i, int j)
	{
		Position p = new Position(i,j-1);
		if(isValid(p)&& forest[i][j-1] == 1) { return p; }
		else { return null; }
	}
	
	// Creates objects that are coordinate pairs representing locations in the array.
	private class Position
	{
		private int i, j;
		
		public Position(int i, int j)
		{
			this.i = i; this.j = j;
		}
	}
	
}





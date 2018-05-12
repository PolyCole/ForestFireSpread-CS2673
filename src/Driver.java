import edu.princeton.cs.introcs.StdDraw;

/*
 * Author: Cole Polyak
 * Assignment 3
 * Driver.java
 * 
 * 27 April 2018
 * 
 * This class exercises the FireProbability and Forest classes.
 */

// Final density: Approximately 0.59 for both DPS and BFS.

public class Driver 
{
	
	public static void main(String[] args)
	{
		oneIterationTimes();
		
		System.out.println("-----------------------------------------------------");
		System.out.println("Final DENSITY: " + FireProbability.highestDensityDFS());
		System.out.println("-----------------------------------------------------");
		System.out.println("Final DENSITY: " + FireProbability.highestDensityBFS());
	}
	
	// Reports one iteration times for an array of size 100x100.
	public static void oneIterationTimes()
	{
		Forest f = new Forest(100, 100, 0.50);

		long start = System.currentTimeMillis();
		f.breadthFirstSearch();
		long stop = System.currentTimeMillis();

		System.out.println("1 Breadth First Iteration time: " + (stop - start));

		Forest g = new Forest(100, 100, 0.50);
		long start2 = System.currentTimeMillis();
		f.depthFirstSearch();
		long stop2 = System.currentTimeMillis();

		System.out.println("1 Depth First Iteration time: " + (stop - start));
	}
}

/*
 * The Depth First Search was found to be faster than the Breadth First Search. There are two
 * main factors that attribute to this time difference. First, since Breadth first is using a
 * singly linked list, whenever you want to insert a value into the queue you have to insert
 * it at the tail of the list. To get there you must walk the list, which takes some time
 * as the queue gets larger.
 * 
 * Additionally, since breadth first search expands out in a ripple form, it is addressing many
 * more possible routes than the DPS. This difference in sight range adds to the time difference.
 * 
 * Since we know that our final objective is always directly below us, DPS can sometimes get the jump
 * on BFS. 
 */

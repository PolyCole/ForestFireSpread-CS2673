
/*
 * Author: Cole Polyak
 * Assignment 3
 * 27 April 2018
 * 
 * FireProbability.java
 * 
 * This class evaluates the densities needed to have a 0.50
 * probability of the fire spreading.
 */

public class FireProbability 
{
	
	// Testing the highest density for 0.50 probability using Depth First Search.
	public static double highestDensityDFS()
	{
		double lowDensity = 0.0;
		double highDensity = 1.0;
		
		double density = 0.0;
		
		long startTime = System.currentTimeMillis();
		
		// Performs binary search 20 times to further narrow the ideal density.
		for(int i = 0; i < 20; ++i)
		{
			// Timing iteration.
			System.out.println("Iteration: " + i);
			long start = System.currentTimeMillis();

			// Halving density.
			density = (highDensity + lowDensity) / 2.0;
			
			// Executing Depth First Search.
			double p = probabilityOfFireSpreadDFS(density);
			
			
			long stop = System.currentTimeMillis();
			System.out.println("Time: " +  (stop - start) + "\n");
			
			// Binary Search.
			if(p < 0.5) { lowDensity = density; }
			else { highDensity = density; }
		}
		
		long stopTime = System.currentTimeMillis();
		
		System.out.println("***** TOTAL TIME: " + (stopTime-startTime) + "*****");
		
		return density;
	}
	
	// Testing the highest density for 0.50 probability using Breadth First Search.
	public static double highestDensityBFS()
	{
		double lowDensity = 0.0;
		double highDensity = 1.0;
		double density = 0.0;
		
		long startTime = System.currentTimeMillis();
		
		// Performs a binary search 20 times to further narrow the ideal density.
		for(int i = 0; i < 20; ++i)
		{
			
			long start = System.currentTimeMillis();
			System.out.println("Iteration: " + i);
			
			// Halving density.
			density = (highDensity + lowDensity) / 2.0;
			
			// Executing Breadth First Search.
			double p = probabilityOfFireSpreadBFS(density);
			
			
			long stop = System.currentTimeMillis();
			System.out.print("Time: " + (stop - start) + "\n");

			// Binary Search.
			if(p < 0.5) { lowDensity = density; }
			else { highDensity = density; }
		}
		

		long stopTime = System.currentTimeMillis();

		System.out.println("***** TOTAL TIME: " + (stopTime-startTime) + "*****");
		
		return density;
	}
	
	// Gets the approximate density for a Depth First Search.
	public static double probabilityOfFireSpreadDFS(double d)
	{
		// Counts number of spreads.
		int fireSpreadCount = 0;
	
		for(int i =0; i < 10000; ++i)
		{
			Forest f = new Forest(20, 20, d);
			
			// Performs search.
			boolean bool = f.depthFirstSearch();
			
			// Checks success.
			if(bool)
			{
				fireSpreadCount++;
			}
		}
		
		return fireSpreadCount / 10000.00;
	}
	
	// Gets the approximate density for a Breadth First Search.
	public static double probabilityOfFireSpreadBFS(double d)
	{
		// Counts number of spreads.
		int fireSpreadCount = 0;
		
		for(int i =0; i < 10000; ++i)
		{
			Forest f = new Forest(20, 20, d);
			
			// Performs search.
			boolean bool = f.breadthFirstSearch();
			
			// Checks success
			if(bool)
			{
				fireSpreadCount++;
			}
			
		}
		
		return fireSpreadCount / 10000.00;
	}
	
}

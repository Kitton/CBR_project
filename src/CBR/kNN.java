package CBR;

import java.util.Collections;
import java.util.List;
import java.util.Vector;


/*
 * k nearest neighbors Classifier
 * 
 */

public class kNN {
	
	//variables
	private  CBR_Library PastCases;	
	private int k;
	
	//Constructor
	public kNN(int k)
	{		
		if (k <= 0)
			 throw new IllegalArgumentException("The parameter K must be greater than zero!");
		this.k = k;	
	}
	
	
	//Get Nearest Neighbors
	public Vector<Case> getNearestNeighbors(Case NewCase)
	{
		Vector<Case> neighbors = new Vector<Case>();
		//Vector<Double> distances = new Vector<Double>();
		List<Case> LibraryPastCases;
		
		LibraryPastCases = PastCases.getLibraryCases();
		//LibraryPastCases = PastCases.retriveClosestCases(NewCase);
		Vector<DistanceCase> sortingCases = new Vector<DistanceCase>();
		
		for (int i=0; i< LibraryPastCases.size(); i++)
		{
			Case instance = LibraryPastCases.get(i);
			double distance = getDistance(instance,NewCase);
			sortingCases.add(new DistanceCase(instance,distance));
			/*for (int j = 0; j < neighbors.size(); j++) 
			{
                if (distances.get(j) > distance)
                {
                        neighbors.insertElementAt(instance, j);
                        distances.insertElementAt(distance, j);
                        break;
                }
			}
			if (neighbors.size() < k) 
			{
                neighbors.add(instance);
                distances.add(distance);
			}
			if (neighbors.size() > k) 
			{
                neighbors.remove(k);
                distances.remove(k);
			}*/
		}
		Collections.sort(sortingCases);

		for (int i=0; i<k; i++){
			neighbors.add(sortingCases.get(i).getCase());
		}
		
        //return the k nearest neighbors
        return neighbors;
		
		
	}
	
	
	
	 /**
     * Caculate the Eucidean Distance between two distinct cases
     * 
     * @param a
     *            a case
     * @param b
     *            another case
     * @return the Euclidean Distance between the two
     */
	public double getDistance(Case a, Case b)
	{
		double distance = 0;
		int attr_number = a.getAttributes().size();
		
		for (int i = 0; i < attr_number; i++) 
		{
             double x = a.getAttributes().get(i);
             double y = b.getAttributes().get(i);
             distance += (x - y) * (x - y);
		}
		
		return Math.sqrt(distance);
		
	}
	
	//Get the parameter k
	public int getk()
	{
		return this.k;
	}
	
	
	

}
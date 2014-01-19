package CBR;

import java.util.ArrayList;
import java.util.List;

public class CBR_Retrieve {
	
	
	private Case NewCase;	
	private int K;
	
	
	
	//Constructor
	public CBR_Retrieve(int k)
	{
		this.K = k;
				
	}
	
	//get K Nearest Neighbors
	public List<Case> getkNN(List<Case> PastCases,Case TestCase,int k)
	{
		List<Case> kNearestNeighbors;
		kNN algorithm = new kNN(PastCases,K);		
		
		kNearestNeighbors = algorithm.getNearestNeighbors(TestCase);
		
		return kNearestNeighbors;
	}
	
	
	
	
	
	
	

}

package CBR;

import java.util.ArrayList;
import java.util.List;

public class CBR_Retrieve {
	
	
	private Case NewCase;	
	private int K;
	private String DistanceMeasure;
	private double [] AttributeWeights;
	
	
	
	//Constructor
	public CBR_Retrieve(int k)
	{
		this.K = k;
				
	}
	
	public CBR_Retrieve(int k, String type)
	{
		this.K = k;
		this.DistanceMeasure = type;
		
	}
	
	public CBR_Retrieve(int k, String type, double []weights)
	{
		this.K = k;
		this.DistanceMeasure = type;
		this.AttributeWeights = weights;
	}
	
	//get K Nearest Neighbors
	public List<Case> getkNN(List<Case> PastCases,Case TestCase,int k)
	{
		List<Case> kNearestNeighbors;
		kNN algorithm = new kNN(PastCases,K);		
		
		kNearestNeighbors = algorithm.getNearestNeighbors(TestCase,DistanceMeasure,AttributeWeights);
		
		return kNearestNeighbors;
	}
	
	
	
	
	
	
	

}

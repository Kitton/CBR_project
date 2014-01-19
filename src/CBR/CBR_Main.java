package CBR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CBR_Main {
	public static void main(String[] args) throws IOException 
	{
		List<Case> trainingData = DataReader.readData("./data/pendigits.tra");		
        	System.out.println(trainingData.size() + " cases were read ");
        
        	//Store Cases to the CBR Library
        	CBR_Library library = new CBR_Library(trainingData);
        
       
        	List<Case> testingData = DataReader.readData("./data/pendigits.tes");
        	int TestDataSize = testingData.size();
        	System.out.println(TestDataSize + " cases were read");
        
        	int k =3;
        	CBR_Retrieve retrieval = new CBR_Retrieve(k);
        	CBR_Reuse reuse = new CBR_Reuse();
       
        
        	for (int i=0; i<TestDataSize; i++)
        	{
        	
        		Case NewCase = testingData.get(i);
        		List<Case> PastCases;
        		List<Case> NearestNeighbors;
        		double []Attributesweights;
        		int classPredicted;
        	
        		System.out.println("######################################");
        		System.out.println(NewCase);
        	
        		//Retrieve Phase
        		PastCases = library.getWholeList();
        	
        		NearestNeighbors = retrieval.getkNN(PastCases, NewCase, k);
        	
        		//Reuse Phase
        		classPredicted = reuse.getNewCaseClassLabel(NearestNeighbors, NewCase);
        	
        		NewCase.setPredictedClassLabel(classPredicted);
        	
        		System.out.println("-------------------------------------------");
        		System.out.println("Label Predicted : ");
        		System.out.println(NewCase.getPredictedClassLabel());
        		System.out.println("-------------------------------------------");
        		
        		
        		//Evaluate-Retain
        	
        	
        	
        	}
        
		
	

	}
}

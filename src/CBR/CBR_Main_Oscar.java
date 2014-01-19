package CBR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CBR_Main_Oscar {
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
        	String DistanceMeasure;
        	/* Distance Similarity Measures:
        		"Euclidean Distance"
        		"Euclidean Distance with attribute weights"
        		
        	*/        
        	//Choose one type from the above ones
        	DistanceMeasure = "Euclidean Distance";
        	double []Attributesweights;
        	Attributesweights = library.getAttributesWeights();
        
        
        	CBR_Retrieve retrieval = new CBR_Retrieve(k,DistanceMeasure,Attributesweights);
        	CBR_Reuse reuse = new CBR_Reuse();
        	CBR_EvaluateRetain evRet = new CBR_EvaluateRetain("FullRetain",library);
       
        
        
        	for (int i=0; i<TestDataSize; i++)
        	{
        	
        		Case NewCase = testingData.get(i);
        		List<Case> PastCases;
        		List<Case> NearestNeighbors;
        	
        		int classPredicted;
        	
        	      	
        		//Retrieve Phase      	      	
        		PastCases = library.getWholeList();
        	
        	        	
        		NearestNeighbors = retrieval.getkNN(PastCases, NewCase, k);
        	
        		//Reuse Phase
        		classPredicted = reuse.getNewCaseClassLabel(NearestNeighbors, NewCase);
        	
        		NewCase.setPredictedClassLabel(classPredicted);
        	
        	
        	
        	
        	
        		//Evaluate-Retain
        		
        		evRet.doYourjob(NewCase);
        	
        	
        	}

        	
        	ArrayList<Double> accuracies = evRet.getAccuracies();
        	ArrayList<ArrayList<Integer>> labelPairs = evRet.getLabelPairs();
        
        	System.out.println("Accuracies = "+accuracies);
        	System.out.println("Final accuracy = "+evRet.getAccuracy());

		
        	System.out.println("Finished!!!!");

	}
}

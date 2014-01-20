package CBR;

import java.util.ArrayList;
import java.util.List;

public class Test_OneConfig {

	private ArrayList<String> config;
	
	public Test_OneConfig(List<Case> trainingData,List<Case> testingData, ArrayList<String> config){
		
		this.config=config;
		
		double []Attributesweights;
		
		// Set the retrieval k
		int k = Integer.valueOf(this.config.get(0));
		// Set the retrieval distances
		String DistanceMeasure = this.config.get(1);
		// Set the reain policy
		String policy = this.config.get(2);
		
		//Data treatment ##################
	    CBR_Library library = new CBR_Library(trainingData);
	    int TestDataSize = testingData.size();
	    System.out.println(TestDataSize + " cases were read");
		Attributesweights = library.getAttributesWeights();
	    
		// Init Retrieve, Reuse, Evaluation and Retain ###################
	    CBR_Retrieve retrieval = new CBR_Retrieve(k,DistanceMeasure,Attributesweights);
		CBR_Reuse reuse = new CBR_Reuse();
		CBR_EvaluateRetain evRet = new CBR_EvaluateRetain(policy,library,Attributesweights);
		
		// Set the variable Retain policy parameters
		int kSimilar=0;
		double simThreshold=0;
		double simRemoveThres=0;

		if (!(policy=="FullRetain")) {
			if (policy=="Similarity"){
				kSimilar = Integer.valueOf(this.config.get(3));
				simThreshold = Double.valueOf(this.config.get(4));
				evRet.setKSimilar(kSimilar);
				evRet.setSimThreshold(simThreshold);
			}
			else if (policy=="NegativeCases"){
				kSimilar = Integer.valueOf(this.config.get(3));
				simThreshold = Double.valueOf(this.config.get(4));
				simRemoveThres = Double.valueOf(this.config.get(5));
				evRet.setKSimilar(kSimilar);
				evRet.setSimThreshold(simThreshold);
				evRet.setSimDelThrs(simRemoveThres);
			}	
		}
	
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

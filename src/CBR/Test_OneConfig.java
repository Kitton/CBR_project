package CBR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test_OneConfig {

	private ArrayList<String> config;
	private String testNum;
	private CBR_EvaluateRetain evRet;
	
	public Test_OneConfig(List<Case> trainingData,List<Case> testingData, ArrayList<String> config, int testNum){
		
		this.config=config;
		this.testNum = String.valueOf(testNum);
		
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
	    //System.out.println(TestDataSize + " cases were read");
		Attributesweights = library.getAttributesWeights();
	    
		// Init Retrieve, Reuse, Evaluation and Retain ###################
	    CBR_Retrieve retrieval = new CBR_Retrieve(k,DistanceMeasure,Attributesweights);
		CBR_Reuse reuse = new CBR_Reuse();
		this.evRet = new CBR_EvaluateRetain(policy,library,Attributesweights);
		
		// Set the variable Retain policy parameters
		int kSimilar=0;
		double simThreshold=0;
		double simRemoveThres=0;

		if (!(policy=="FullRetain")) {
			if (policy=="Similarity"){
				kSimilar = Integer.valueOf(this.config.get(3));
				simThreshold = Double.valueOf(this.config.get(4));
				this.evRet.setKSimilar(kSimilar);
				this.evRet.setSimThreshold(simThreshold);
			}
			else if (policy=="NegativeCases"){
				kSimilar = Integer.valueOf(this.config.get(3));
				simThreshold = Double.valueOf(this.config.get(4));
				simRemoveThres = Double.valueOf(this.config.get(5));
				this.evRet.setKSimilar(kSimilar);
				this.evRet.setSimThreshold(simThreshold);
				this.evRet.setSimDelThrs(simRemoveThres);
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
			
			this.evRet.doYourjob(NewCase);
		
		
		}
	
		
		//ArrayList<Double> accuracies = this.evRet.getAccuracies();
		//ArrayList<ArrayList<Integer>> labelPairs = this.evRet.getLabelPairs();
	
		//System.out.println("Accuracies = "+accuracies);
		System.out.println("Final accuracy = "+this.evRet.getAccuracy());
		System.out.println("Storing...");
		try {
			this.storeResults();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished!!!!");
	}
	
	public double getFinalAccuracy(){
		return this.evRet.getAccuracy();
	}
	
	public void storeResults() throws IOException{
		int params = this.config.size();
		BufferedWriter writer = null;
		File logFile = null;
		if (params==3){
			logFile = new File("Test"+this.testNum+"_"+this.config.get(0)+"_"+this.config.get(1)+"_"+this.config.get(2)+".txt");
		}
		else if (params==5) {
			logFile = new File("Test"+this.testNum+"_"+this.config.get(0)+"_"+this.config.get(1)+"_"+this.config.get(2)+"_"+this.config.get(3)+"_"+this.config.get(4)+".txt");
		}
		else if (params==6) {
			logFile = new File("Test"+this.testNum+"_"+this.config.get(0)+"_"+this.config.get(1)+"_"+this.config.get(2)+"_"+this.config.get(3)+"_"+this.config.get(4)+"_"+this.config.get(5)+".txt");
		}
		writer = new BufferedWriter(new FileWriter(logFile));
		this.writeAccuracies(writer);
	}
	
	private void writeAccuracies(BufferedWriter writer) throws IOException{
		ArrayList<Double> accuracies =  this.evRet.getAccuracies();
        int accSize = accuracies.size();
        for (int i=0; i<accSize;i++){
			writer.write(String.valueOf(accuracies.get(i)));
			if (i+1==accSize){
				writer.write("\n");
			}
			else{
				writer.write(",");
			}
        }
	}
	
	public int getFinalNumLibCases(){
		
		return this.evRet.getNumCasesOnLibr();
		
	}
	
}

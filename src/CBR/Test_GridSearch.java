package CBR;

import java.util.ArrayList;
import java.util.List;

public class Test_GridSearch {
	
	private String nameTestAccuracies;
	private String nameTestParameters;
	private String[] retainPolicies = {"FullRetain","Similarity","NegativeCases"};
	private String[] kSimVals = {"3", "5", "10"};
	// As the features hasve values from 0 to 100 and there are 16 feature values, max euclidean distance is 400 and min 0
	// For weighted euclidean distance, max distance is 314, so we keep the values the same.
	private String[] simThresholds = {"0.01","1","10"};
	private String[] simRemvThresholds = {"0.01","1","10"};
	private String[] distances = {"Euclidean Distance","Euclidean Distance with attribute weights"};
	private String[] ks = {"3", "5", "10"};
	
	
	public Test_GridSearch(List<Case> trainingData,List<Case> testingData, int numTest){
	    
	 	this.nameTestAccuracies = "gridAccuracies"+String.valueOf(numTest)+".txt";
	 	this.nameTestParameters = "gridParameters"+String.valueOf(numTest)+".txt";
		
	 	ArrayList<ArrayList<String>> configs = new ArrayList<ArrayList<String>>();
	 	
	 	for (int i=0;i<this.ks.length ;i++) {
	 		for (int j=0;j<this.distances.length ;j++) {
	 			for (int k=0;k<this.retainPolicies.length ;k++) {
	 				if (!(this.retainPolicies[k]=="FullRetain")) {
	 					for (int l=0;l<this.kSimVals.length ;l++) {
		 					for (int m=0;m<this.simThresholds.length ;m++) {
		 						if (this.retainPolicies[k]=="NegativeCases"){
		 							for (int n=0;n<this.simRemvThresholds.length ;n++) {
		 								
		 								ArrayList<String> config = new ArrayList<String>();
		 								
		 								config.add(this.ks[i]);
		 								config.add(this.distances[j]);
		 								config.add(this.retainPolicies[k] );
		 								config.add(this.kSimVals[l]);
		 								config.add(this.simThresholds[m]);
		 								config.add(this.simRemvThresholds[n]);
		 								
		 								configs.add(config);
		 							}
		 						}
		 						else{
		 							ArrayList<String> config = new ArrayList<String>();
	 								
	 								config.add(this.ks[i]);
	 								config.add(this.distances[j]);
	 								config.add(this.retainPolicies[k] );
	 								config.add(this.kSimVals[l]);
	 								config.add(this.simThresholds[m]);
	 								
	 								configs.add(config);
		 						}
		 					}
		 				}
	 				}
	 				else{
	 					ArrayList<String> config = new ArrayList<String>();
							
							config.add(this.ks[i]);
							config.add(this.distances[j]);
							config.add(this.retainPolicies[k] );
							
							configs.add(config);
	 				}
	 			}
	 		}
	 	}
	 	
	 	ArrayList<Test_OneConfig> tests = new ArrayList<Test_OneConfig>();
	 	for (int i=0;i<configs.size();i++) {
	 		tests.add(new Test_OneConfig(trainingData,testingData,configs.get(i)));
	 	}
	 	
	 	
	
	}
	
}

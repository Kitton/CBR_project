package CBR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test_GridSearch {
	
	private String nameTestAccuracies;
	private String nameTestParameters;
	private String nameTestSizes;
	private String[] retainPolicies = {"FullRetain","Similarity","NegativeCases"};
	private String[] kSimVals = {"3", "5", "10"};
	// As the features hasve values from 0 to 100 and there are 16 feature values, max euclidean distance is 400 and min 0
	// For weighted euclidean distance, max distance is 314, so we keep the values the same.
	private String[] simThresholds = {"0.01","1","10"};
	private String[] simRemvThresholds = {"0.01","1","10"};
	private String[] distances = {"Euclidean Distance","Euclidean Distance with attribute weights"};
	private String[] ks = {"3", "5", "10"};
	private ArrayList<Test_OneConfig> tests;
	private ArrayList<ArrayList<String>> configs;
	
	
	public Test_GridSearch(List<Case> trainingData,List<Case> testingData, int numTest){
	    
	 	this.nameTestAccuracies = "gridAccuracies"+String.valueOf(numTest)+".txt";
	 	this.nameTestParameters = "gridParameters"+String.valueOf(numTest)+".txt";
	 	this.nameTestSizes = "gridLibSizes"+String.valueOf(numTest)+".txt";
	 	this.tests = new ArrayList<Test_OneConfig>();
		
	 	this.configs = new ArrayList<ArrayList<String>>();
	 	
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
		 								
		 								this.configs.add(config);
		 							}
		 						}
		 						else{
		 							ArrayList<String> config = new ArrayList<String>();
	 								
	 								config.add(this.ks[i]);
	 								config.add(this.distances[j]);
	 								config.add(this.retainPolicies[k] );
	 								config.add(this.kSimVals[l]);
	 								config.add(this.simThresholds[m]);
	 								
	 								this.configs.add(config);
		 						}
		 					}
		 				}
	 				}
	 				else{
	 					ArrayList<String> config = new ArrayList<String>();
							
							config.add(this.ks[i]);
							config.add(this.distances[j]);
							config.add(this.retainPolicies[k] );
							
							this.configs.add(config);
	 				}
	 			}
	 		}
	 	}
	 	
	 	System.out.println("Number of tests to do "+String.valueOf(this.configs.size()));
	 	for (int i=0;i<this.configs.size();i++) {
	 		System.out.println("Test number "+String.valueOf(i));
	 		this.tests.add(new Test_OneConfig(trainingData,testingData,this.configs.get(i),numTest));
	 	}
	 	
	 	System.out.println("Storing all multiclass accuracies...");
	 	try {
			this.storeResults();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	
	 	System.out.println("Test "+String.valueOf(numTest)+" finished!!");
	}
	public void storeResults() throws IOException{
		
		File accFile = new File(this.nameTestAccuracies);
		File paramFile = new File(this.nameTestParameters);
		File sizeFile = new File(this.nameTestSizes);
		BufferedWriter writer = new BufferedWriter(new FileWriter(accFile));
		this.writeAccuracies(writer);
		writer.close();
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(paramFile));
		this.writeParameters(writer2);
		writer2.close();
		BufferedWriter writer3 = new BufferedWriter(new FileWriter(sizeFile));
		this.writeLibSizes(writer3);
		writer3.close();
		
	}
	
	private void writeParameters(BufferedWriter writer) throws IOException{
		int paramSize = this.configs.size();
        for (int i=0; i<paramSize;i++){
        	int params=this.configs.get(i).size();
        	if (params==3){
    			writer.write(this.configs.get(i).get(0)+","+this.configs.get(i).get(1)+","+this.configs.get(i).get(2));
    		}
    		else if (params==5) {
    			writer.write(this.configs.get(i).get(0)+","+this.configs.get(i).get(1)+","+this.configs.get(i).get(2)+","+this.configs.get(i).get(3)+","+this.configs.get(i).get(4));
    		}
    		else if (params==6) {
    			writer.write(this.configs.get(i).get(0)+","+this.configs.get(i).get(1)+","+this.configs.get(i).get(2)+","+this.configs.get(i).get(3)+","+this.configs.get(i).get(4)+","+this.configs.get(i).get(5));
    		}
			writer.write("\n");	
        }
	}
	
	private void writeAccuracies(BufferedWriter writer) throws IOException{
		
        int accSize = this.tests.size();
        for (int i=0; i<accSize;i++){
			writer.write( String.valueOf(this.tests.get(i).getFinalAccuracy()) );
			if (i+1==accSize){
				writer.write("\n");
			}
			else{
				writer.write(",");
			}
        }
	}
	private void writeLibSizes(BufferedWriter writer) throws IOException{
		
        int accSize = this.tests.size();
        for (int i=0; i<accSize;i++){
			writer.write( String.valueOf(this.tests.get(i).getFinalNumLibCases()) );
			if (i+1==accSize){
				writer.write("\n");
			}
			else{
				writer.write(",");
			}
        }
        
	}
}

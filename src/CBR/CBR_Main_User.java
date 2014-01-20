package CBR;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CBR_Main_User {
	
	public static void main(String[] args) throws IOException 
	{
	
		Console console = System.console();
		String input;
		boolean in=true;
		
		ArrayList<String> config = new ArrayList<String>();
		int k;
		String distance;
		String policy;
		int kSimVals;
		double simThres;
		double simRemvThres;
		
		List<Case> trainingData;
		List<Case> testingData;
		
		System.out.println("Welcome to the digit recognition CBR");
		
		while (in){
			
			System.out.println("Please select one optio:");
			System.out.println("1 Start using the CBR system");
			System.out.println("2 Exit");
			input = console.readLine("Please enter the option:");
			if (input=="1"){
				input = console.readLine("Please enter the path to the file you want to use for training:");
				trainingData = DataReader.readData(input);
				input = console.readLine("Please enter the path to the file you want to use for testing:");
				testingData = DataReader.readData(input);
				System.out.println("Now select a k value for the kNN algorith (recomended from 3 to 10, never less than 1) ");
				input = console.readLine("Please enter the k:");
				// Convert input to Integer
				k=Integer.valueOf(input);
				if (k<1){
					System.err.println("Bad value for k. It has to be equal or greater than 1");
				}
				config.add(String.valueOf(k));
				
				boolean in2=true;
				while (in2){
					System.out.println("Now select the distance metric: ");
					System.out.println("1 Euclidean Distance");
					System.out.println("2 Wheighted Euclidean Distance");
					System.out.println("3 Exit");
					input = console.readLine("Please enter the option:");
					if (input=="1"){
						distance = "Euclidean Distance";
						config.add(distance);
						in2=false;
					}
					else if(input=="2"){
						distance = "Euclidean Distance with attribute weights";
						config.add(distance);
						in2=false;
					}
					else if(input=="3"){
						in2=false;
						in=false;
					}
					else {
						System.out.println("Bad option, try again");
					}
				}
				in2=true;
				while (in2){
					System.out.println("Select on of the following retain strategies");
					System.out.println("1 Full retain");
					System.out.println("2 Retain positive if new");
					System.out.println("3 Retain positive if new and erase similar cases when negative");
					System.out.println("4 Exit");
					input = console.readLine("Please enter the option:");
					if (input=="1"){
						policy = "FullRetain";
						config.add(policy);
						in2=false;
					}
					else if(input=="2"){
						policy = "Similarity";
						config.add(policy);
						
						input = console.readLine("Please enter the k number of cases to compare for retain (greater than 1):");
						kSimVals= Integer.valueOf(input);
						if (kSimVals<1){
							System.err.println("Bad value for k. It has to be equal or greater than 1");
						}
						config.add(String.valueOf(kSimVals));
						input = console.readLine("Please enter the threshold to consider a case enough similar:");
						config.add(input);
						//simThres = Double.valueOf(input);
							
						
						in2=false;
					}
					else if(input=="3"){
						policy = "NegativeCases";
						config.add(policy);
						input = console.readLine("Please enter the k number of cases to compare for retain (greater than 1):");
						kSimVals= Integer.valueOf(input);
						if (kSimVals<1){
							System.err.println("Bad value for k. It has to be equal or greater than 1");
						}
						config.add(String.valueOf(kSimVals));
						input = console.readLine("Please enter the threshold to consider a case enough similar:");
						//simThres = Double.valueOf(input);
						config.add(input);
						input = console.readLine("Please enter the threshold to consider a negative case enough similar:");
						//simRemvThres = Double.valueOf(input);
						config.add(input);
						in2=false;
					}
					else if(input=="4"){
						in2=false;
						in=false;
					}
					else{
						System.out.println("Bad option, try again");
					}
				}
				System.out.println("The CBR system is classifying");
				// do
				Test_OneConfig test = new Test_OneConfig(trainingData,testingData,config,-1);
				// ask to show or to store in a file with x name
				System.out.println("The classification is done");
				System.out.println("Do you whant to store the results?");
				input = console.readLine("y/n :");
				if (input=="y"){
					String input2 = console.readLine("Enter the name of the file :");
					test.storePredictedLabels(input2);
					System.out.println("Labels stored, returning to the main menu.");
				}
				else {
					System.out.println("Labels not stored, returning to the main menu.");
				}
				
			}
			else if (input=="2"){
				in=false;
			}
			else {
				System.out.println("Vad option, try again\n");
			}
		}
	}
}

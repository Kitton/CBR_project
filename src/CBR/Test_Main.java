package CBR;

import java.io.IOException;
import java.util.List;

public class Test_Main {
	public static void main(String[] args) throws IOException 
	{
		List<Case> trainingData = DataReader.readData("./data/pendigits.tra");		
        System.out.println(trainingData.size() + " Train cases ");
        List<Case> testingData = DataReader.readData("./data/pendigits.tes");
        System.out.println(testingData.size() + " Test cases ");
        
        List<Case> unvalTrainData = DataReader.readData("./data/unbalancedTrainData.tra");	
        
        //Test_GridSearch myTest0 = new Test_GridSearch(trainingData,testingData,0);
        // We use now less data for training inverting the datasets
        //Test_GridSearch myTest1 = new Test_GridSearch(testingData,trainingData,1);
        // We use now a class-unvalanced training dataset
        Test_GridSearch myTest2 = new Test_GridSearch(unvalTrainData,trainingData,2);
        
	}
}

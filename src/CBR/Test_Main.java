package CBR;

import java.io.IOException;
import java.util.List;

public class Test_Main {
	public static void main(String[] args) throws IOException 
	{
		List<Case> trainingData = DataReader.readData("./data/pendigits.tra");		
        System.out.println(trainingData.size() + " cases were read ");
        List<Case> testingData = DataReader.readData("./data/pendigits.tes");
        
        Test_GridSearch myTest = new Test_GridSearch(trainingData,testingData,0);
        
	}
}

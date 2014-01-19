package CBR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CBR_Main {
	public static void main(String[] args) throws IOException {
		List<Case> trainingData = DataReader.readData("./data/pendigits.tra");
		System.out.println(trainingData.size() + " training cases were read");
		CBR_Library library = new CBR_Library(trainingData);
		
		List<Case> testData = DataReader.readData("./data/pendigits.tes");
		System.out.println(testData.size() + " test cases were read");
	}
}

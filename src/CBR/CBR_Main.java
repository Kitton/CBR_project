package CBR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CBR_Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		readData("./data/pendigits_100.tes");
	}
	
	private static List<Case> readData(String filename) throws IOException{
		FileReader fileReader = null;
		fileReader = new FileReader(new File(filename));

		BufferedReader br = new BufferedReader(fileReader);

		String line = null;
		List<Case> cases = new ArrayList<Case>(); 
		while ((line = br.readLine()) != null) {
			// reading lines until the end of the file
			cases.add(parseCase(line));
		}
//		System.out.println(cases.get(10).getClassLabel());
		return cases;
	}
	
	private static Case parseCase(String str){
		String[] strings = str.split(",");
		List<Integer> attr = new ArrayList<Integer>();
		for (int i = 0; i < strings.length - 1; i++)
		{
			attr.add(Integer.parseInt(strings[i].trim()));
		}
		int classValue = Integer.parseInt(strings[strings.length - 1].trim());
		return new Case(attr, classValue, true);
	}
}

package CBR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
	public static List<Case> readData(String filename) throws IOException{
		FileReader fileReader = new FileReader(new File(filename));
		BufferedReader br = new BufferedReader(fileReader);

		String line = null;
		List<Case> cases = new ArrayList<Case>(); 
		while ((line = br.readLine()) != null) {
			cases.add(parseCase(line));
		}
//		System.out.println(cases.get(10).getClassLabel());
		return cases;
	}
	
	public static Case parseCase(String str){
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

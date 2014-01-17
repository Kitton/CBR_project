package CBR;

import java.util.ArrayList;
import java.util.List;

public class CBR_Library {
	private static List<Case> cases;
	
	public static void createTree(List<Case> cases){
		CBR_Library.cases = cases;
	}
	
	public static List<Case> retriveClosestCases(Case inputCase){
		return CBR_Library.cases;
	}
	
	public static void addCase(Case inputCase){
		cases.add(inputCase);
	}
	
	public static void deleteCase(Case inputCase){
		cases.remove(inputCase);
	}
}

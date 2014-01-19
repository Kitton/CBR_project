package CBR;

import java.util.ArrayList;
import java.util.List;

public class CBR_Library {
	private static List<Case> cases;
	int size;
	
	public static void createTree(List<Case> cases){
		CBR_Library.cases = cases;
	}
	
	public static List<Case> retriveClosestCases(Case inputCase){
		return CBR_Library.cases;
	}
	
	/*public static void addCase(Case inputCase){
		cases.add(inputCase);
	}*/
	
	/*public static void deleteCase(Case inputCase){
		cases.remove(inputCase);
	}*/
	
	// Add a new case in the CBR Library
    public  void addCase(Case inputCase){
    	cases.add(inputCase);
    	this.size ++; 
    }
    
    // Delete a case in the CBR Library
    public  void deleteCase(Case inputCase){
    	cases.remove(inputCase);
    	this.size --;
    }
    
    // Return the cases that are in the CBR Library
    public List<Case> getLibraryCases()
    {
    	return CBR_Library.cases;
    }
    
    //Return the number of cases stored in the CBR Library
    public int getNumberCases()
    {
    	return this.size;
    }
}

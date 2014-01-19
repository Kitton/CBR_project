package CBR;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class CBR_Library {
	private CaseTree caseTree;
	private int treeSize;
	
	public CBR_Library(){
		treeSize = -1;
	}
	
	public void createTree(List<Case> cases){
		caseTree = new CaseTree(cases);
		treeSize = cases.size();
	}
	
	public List<Case> retriveClosestCases(Case inputCase){
		return caseTree.getSimilarCases(inputCase);
	}
	
	public void addCase(Case newCase){
		caseTree.addCase(newCase);
		treeSize++;
	}
	
	public void deleteCase(Case deletedCase){
		caseTree.deleteCase(deletedCase);
		treeSize--;
	}
    
    //Return the number of cases stored in the CBR Library
    public int getNumberCases()
    {
    	return treeSize;
    }
}

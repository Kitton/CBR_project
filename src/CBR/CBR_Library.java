package CBR;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class CBR_Library {
	private CaseTree caseTree;
	private int treeSize;
	private double[] attributesWeights;
	
	public CBR_Library(){
		treeSize = -1;
//		Retrieved from Weka/InfoGain
		attributesWeights = new double[]{0.72, 0.647, 0.414, 0.608, 0.776, 0.871, 0.525, 0.983, 0.583, 0.791, 0.673, 0.742, 0.35, 1.253, 0.752, 1.243};
	}
	
	public CBR_Library(List<Case> cases) {
		this();
		createTree(cases);
	}
	
	public List<Case> getWholeList() {
		return caseTree.getWholeList();
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
    
    public double[] getAttributesWeights() {
    	return attributesWeights;
    }
}

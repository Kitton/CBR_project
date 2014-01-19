package CBR;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CaseTreeTest {
	@Test
	public void testGetSimilarCases() throws IOException {
		List<Case> dataset = DataReader.readData("./data/pendigits.tra");
		CaseTree caseTree = new CaseTree(dataset);
		System.out.println(caseTree.getSimilarCases(dataset.get(0)).size());
		assertEquals(caseTree.getSimilarCases(dataset.get(0)).size() > 1, true);
	}
	
	@Test
	public void testAddCase() throws IOException{
		List<Case> dataset = DataReader.readData("./data/pendigits.tra");
		CaseTree caseTree = new CaseTree(dataset);
		Case testCase = dataset.get(0);
		int oldSize = caseTree.getSimilarCases(testCase).size();
		caseTree.addCase(testCase);
		int newSize = caseTree.getSimilarCases(testCase).size();
		assertEquals(oldSize == (newSize -1), true);
	}
	
	@Test
	public void testDeleteCase() throws IOException{
		List<Case> dataset = DataReader.readData("./data/pendigits.tra");
		CaseTree caseTree = new CaseTree(dataset);
		Case testCase = dataset.get(0);
		int oldSize = caseTree.getSimilarCases(testCase).size();
		caseTree.deleteCase(testCase);
		int newSize = caseTree.getSimilarCases(testCase).size();
		assertEquals(oldSize == (newSize + 1), true);
	}
	
	@Test
	public void testGetWholeList() throws IOException{
		List<Case> dataset = DataReader.readData("./data/pendigits.tra");
		CaseTree caseTree = new CaseTree(dataset);
		Case testCase = dataset.get(0);
		caseTree.addCase(testCase);
		assertEquals(caseTree.getWholeList().size(), dataset.size() + 1);
	}
}

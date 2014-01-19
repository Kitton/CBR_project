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
		List<Case> dataset = DataReader.readData("./data/pendigits.tes");
		CaseTree caseTree = new CaseTree(dataset);
		System.out.println(caseTree.getSimilarCases(dataset.get(0)).size());
		assertEquals(caseTree.getSimilarCases(dataset.get(0)).size() > 1, true);
	}

}

package CBR;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class CaseTest extends TestCase{

    private Case caseTest;

    protected void setUp() throws Exception
    {
    	List<Integer> attr = new ArrayList<Integer>();
    	attr.add(5);
    	attr.add(77);
    	this.caseTest = new Case(attr, 1, true);
    }

	@Test
	public void testGetAttributes() {
		List<Integer> attr = new ArrayList<Integer>();
    	attr.add(5);
    	attr.add(77);
		assertEquals(this.caseTest.getAttributes(), attr);
	}

	@Test
	public void testGetClassLabel() {
		assertEquals(this.caseTest.getClassLabel(), 1);
	}

	@Test
	public void testGetIsCorrect() {
		assertEquals(this.caseTest.getIsCorrect(), true);
	}

}

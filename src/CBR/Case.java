package CBR;

import java.util.List;

public class Case { 
	private List<Integer> attributes;
	private int classLabel;
	private boolean isCorrect;
	
	public Case(List<Integer> attributes, int classLabel, boolean isCorrect) {
		this.attributes = attributes;
		this.classLabel = classLabel;
		this.isCorrect = isCorrect;
	}
	
	public List<Integer> getAttributes() { return attributes;}
	public int getClassLabel() { return classLabel;}
	public boolean getIsCorrect() {  return isCorrect;}
}
package CBR;

import java.util.List;

public class Case { 
	private List<Integer> attributes;
	private int classLabel;
	private int predictedClassLabel;
	public int getPredictedClassLabel() {
		return predictedClassLabel;
	}

	public void setPredictedClassLabel(int predictedClassLabel) {
		this.predictedClassLabel = predictedClassLabel;
	}
	
	private boolean isCorrect;
	
	public Case(List<Integer> attributes) {
		this.attributes = attributes;
	}
	
	public Case(List<Integer> attributes, int classLabel, boolean isCorrect) {
		this.attributes = attributes;
		this.classLabel = classLabel;
		this.isCorrect = isCorrect;
		this.predictedClassLabel = -1;
	}
	
	public void setClassLabel(int value){
		this.classLabel = value;
	}
	public void setIscorrect(boolean value){
		this.isCorrect = value;
	}
	
	public List<Integer> getAttributes() { return attributes;}
	public int getClassLabel() { return classLabel;}
	public boolean getIsCorrect() {  return isCorrect;}
}

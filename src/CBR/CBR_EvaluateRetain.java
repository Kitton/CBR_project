package CBR;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CBR_EvaluateRetain {

	// The attributes for:
	private CBR_Library database;
	private String policy;
	private double simThreshold;
	private double simRemvThres;
	private int kSimilar;
	private double accuracy;
	private double f1;
	private double sensitivity;
	private ArrayList<Integer> trueP;
	private ArrayList<Double> accuracies;
	// First known label, second predicted label
	private ArrayList<ArrayList<Integer>> labelPairs;
	
	public CBR_EvaluateRetain(String policy, CBR_Library database) {
		this.policy = policy;
		this.database = database;
	}
	
	public void doYourjob(Case newCase){
		
		int evaluatedCase;
	
		// Evaluate
		evaluatedCase = doEv(newCase);
		//Store the evaluation result
		if (evaluatedCase>-1){
			this.trueP.add(evaluatedCase);
			this.accuracies.add(this.calcAccuracy());
			ArrayList<Integer> pair=new ArrayList<Integer>();
			pair.add(newCase.getClassLabel());
			pair.add(newCase.getPredictedClassLabel());
			this.labelPairs.add(pair);
		}
		else{
			System.out.println("Case not evaluated, it has no Known Label");
		}
		// Retain
		doRetain(newCase);
		
	}
	
	private double calcAccuracy(){
		int sum=0;

		for (Integer i:this.trueP){
	         sum = sum + i;
		}
		
		return sum/this.trueP.size();
		
	}
	
	// Interfaced evaluation
	/*private int doTest(Case newCase){
		// In here we should provide an interface for an expert to visually evaluate the case and manually introduce the label
		Case evaluatedCase;
		
		// Evaluate
			
		return 0;
	}*/
	
	private int doEv(Case newCase){
		// Evaluate the case
		int out;
		if (newCase.getClassLabel()==-1){
			// We show the interface for the expert
			// Case not evaluated, not expert interface yet
			out=-1;
		}
		else if(newCase.getClassLabel()==newCase.getPredictedClassLabel()){
			out=1;
		}
		else {
			out=0;
		}
		
		return out;
	}
	
	public void setSimThreshold(double thrs){
		
		this.simThreshold = thrs;
		
	}
	
	public void setSimDelThrs(double thrs) {
		this.simRemvThres = thrs;
	}
	
	public void setKSimilar(int k){
		this.kSimilar=k;
	}
	
	private void doRetain(Case newCase){
		// Do retain
		if (this.policy=="FullRetain"){
			this.database.addCase(newCase);
		}
		else if (this.policy=="Similarity") {
			// We only store good cases
			List<Case> cases = this.database.retriveClosestCases(newCase);
			kNN nearest = new kNN( cases, this.kSimilar );
			Vector<Case> nearCases = nearest.getNearestNeighbors(newCase);
			
			if (newCase.getPredictedClassLabel()==newCase.getClassLabel()){	
				// According to simThreshold, if all k cases are below it, we don't store the case
				int count=0;
				for (int i=0;i<this.kSimilar;i++){
					if (nearest.getDistance(nearCases.get(i),newCase)<this.simThreshold){
						count++;
					}
				}
				if (count<this.kSimilar){
					this.database.addCase(newCase);
				}
			}
		}
		else if (this.policy=="NegativeCases") {
			// Removing?? Maybe??
			List<Case> cases = this.database.retriveClosestCases(newCase);
			kNN nearest = new kNN(cases, this.kSimilar);
			Vector<Case> nearCases = nearest.getNearestNeighbors(newCase);
			
			if (newCase.getPredictedClassLabel()==newCase.getClassLabel()){	
				// According to simThreshold, if all k cases are below it, we don't store the case
				int count=0;
				for (int i=0;i<this.kSimilar;i++){
					if (nearest.getDistance(nearCases.get(i),newCase)<this.simThreshold){
						count++;
					}
				}
				if (count<this.kSimilar){
					this.database.addCase(newCase);
				}
			}
			else {
				// According to simRemvThres, if all past cases below it will be erased from library
				// We don't store the negative case
				
				for (int i=0;i<this.kSimilar;i++){
					if (nearest.getDistance(nearCases.get(i),newCase)<this.simRemvThres	){
						this.database.deleteCase(nearCases.get(i)); 
					}
				}
				
			}
		}
		
	}

	public double getAccuracy() {
		return accuracy;
	}

	public double getF1() {
		return f1;
	}

	public double getSensitivity() {
		return sensitivity;
	}
	
	public ArrayList<Double> getAccuracies() {
		return this.accuracies;
	}
	
	public ArrayList<Integer> getTruePositives() {
		return this.trueP;
	}
	public ArrayList<ArrayList<Integer>> getLabelPairs() {
		return this.labelPairs;
	}
	
}

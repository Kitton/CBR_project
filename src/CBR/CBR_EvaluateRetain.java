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
	
	private ArrayList<Integer> trueP;
	private ArrayList<Double> accuracies;
	// First known label, second predicted label
	private ArrayList<ArrayList<Integer>> labelPairs;
	private ArrayList<Double> f1s;
	private ArrayList<Double> sensitivities;
	private double []Attributesweights;
	
	public CBR_EvaluateRetain(String policy, CBR_Library database,double []Attributesweights) {
		this.Attributesweights = Attributesweights;
		this.policy = policy;
		this.database = database;
		this.accuracies = new ArrayList<Double>();
		this.f1s = new ArrayList<Double>();
		this.labelPairs = new ArrayList<ArrayList<Integer>>();
		this.sensitivities = new ArrayList<Double>();
		this.trueP = new ArrayList<Integer>();
	}
	
	public void doYourjob(Case newCase){
		
		int evaluatedCase;
	
		// Evaluate
		evaluatedCase = doEv(newCase);
		//Store the evaluation result
		//System.out.printf("evaluatedCase = %d\n",evaluatedCase);
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
		
		return (double)sum/(double)this.trueP.size();
		
	}
	
	private double calcF1(){
		return 0;
	}
	
	private double calcSensitivity(){
		return 0;
	}
		
	private int doEv(Case newCase){
		// Evaluate the case
		int out;
		if (newCase.getClassLabel()==-1){
			// This is the place where we would call the interface for showing the problem to the expert.
			out=-1;
			System.out.println("Case not evaluated, not expert interface implemented. The known label is -1!!!");
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
			List<Case> cases = this.database.getWholeList();
			kNN nearest = new kNN( cases, this.kSimilar );
			Vector<Case> nearCases = nearest.getNearestNeighbors(newCase,"Euclidean Distance",Attributesweights);
			
			if (newCase.getPredictedClassLabel()==newCase.getClassLabel()){	
				// According to simThreshold, if all k cases are below it, we don't store the case
				int count=0;
				for (int i=0;i<this.kSimilar;i++){
					if (nearest.getDistanceEuclidean(nearCases.get(i),newCase)<this.simThreshold){
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
			List<Case> cases = this.database.getWholeList();
			kNN nearest = new kNN(cases, this.kSimilar);
			Vector<Case> nearCases = nearest.getNearestNeighbors(newCase,"Euclidean Distance",Attributesweights);
			
			if (newCase.getPredictedClassLabel()==newCase.getClassLabel()){	
				// According to simThreshold, if all k cases are below it, we don't store the case
				int count=0;
				for (int i=0;i<this.kSimilar;i++){
					if (nearest.getDistanceEuclidean(nearCases.get(i),newCase)<this.simThreshold){
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
					if (nearest.getDistanceEuclidean(nearCases.get(i),newCase)<this.simRemvThres	){
						this.database.deleteCase(nearCases.get(i)); 
					}
				}
				
			}
		}
		
	}

	public double getAccuracy() {
		return this.accuracies.get(this.accuracies.size()-1);
	}

	public double getF1() {
		return this.f1s.get(this.f1s.size()-1);
	}

	public double getSensitivity() {
		return this.sensitivities.get(this.sensitivities.size()-1);
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

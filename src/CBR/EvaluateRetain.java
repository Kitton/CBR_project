package CBR;

import java.util.List;
import java.util.Vector;

public class EvaluateRetain {

	// The attributes for:
	private CBR_Library database;
	private String policy;
	private double simThreshold;
	private double simRemvThres;
	private int kSimilar;
	
	public EvaluateRetain(String policy, CBR_Library database) {
		this.policy = policy;
		this.database = database;
	}
	
	public void doTrain(Case newCase){
		
		Case evaluatedCase;
	
		// Evaluate
		evaluatedCase = doEv(newCase);
		// Retain
		doRetain(evaluatedCase);
		
	}
	
	// Interfaced evaluation
	/*private int doTest(Case newCase){
		// In here we should provide an interface for an expert to visually evaluate the case and manually introduce the label
		Case evaluatedCase;
		
		// Evaluate
			
		return 0;
	}*/
	
	private Case doEv(Case newCase){
		// Evaluate the case
		
		//newCase.
		
		return newCase;
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
			kNN nearest = new kNN(this.kSimilar,this.database);
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
			kNN nearest = new kNN(this.kSimilar,this.database);
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
	
}

package CBR;

import java.util.List;

public class EvaluateRetain {

	// The attributes for:
	private CBR_Library database;
	private String policy;
	private float simThreshold;
	
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
	
	public void setSimThreshold(float thrs){
		
		this.simThreshold = thrs;
		
	}
	
	private void doRetain(Case newCase){
		// Do retain
		if (this.policy=="FullRetain"){
			this.database.addCase(newCase);
		}
		else if (this.policy=="Similarity") {
			List<Case> cases = this.database.retriveClosestCases(newCase);
			
		}
		else if (this.policy=="NegativeCases") {
			
		}
		else if (this.policy=="AllPolicies"){
			
		}
		// Removing?? Maybe??
	}
	
}

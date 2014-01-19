package CBR;

public class DistanceCase implements Comparable<DistanceCase> {
	
	private Case pastCase;
	private double distance;
	
	public DistanceCase(Case myCase, double myDistance){
		this.pastCase = myCase;
		this.distance = myDistance;
	}
	
	public Case getCase(){
		return this.pastCase;
	}
	
	@Override
	public int compareTo(DistanceCase a) {
	     //return either 1, 0, or -1
	     //that you compare between this object and object a
		 int out=0;
		 
		 if (this.distance < a.distance){
			 out=-1;
		 }
		 else if(this.distance > a.distance){
			 out=1;
		 }
		 
		 return out;
	}
	
	@Override
	public String toString() { 
		
		return String.valueOf(this.distance);
		
	}

}

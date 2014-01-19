package CBR;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class My_tests {

	
		public static void main(String[] args) throws IOException {
			
			Vector<DistanceCase> test = new Vector<DistanceCase>();
			double c[] = {3,5,6,2,1,78,7,9,10,4};
			Case myCase = new Case(null);
			
			for (int i=0;i<10;i++){
				test.add(new DistanceCase(myCase,c[i]));
				System.out.println(test.get(i));
			}
			
			Collections.sort(test);
			
			System.out.println(test);
			
		}
	
}

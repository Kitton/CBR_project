package CBR;

import java.util.List;
import java.util.Vector;

public class CBR_Reuse {
	
	
	/*
	public List<Case> Neighbors;
	
	public CBR_Reuse(List<Case> Neighbors)
	{
		this.Neighbors = Neighbors;
	}*/
	
	public int getNewCaseClassLabel(List<Case> Neighbors,Case NewCase)
	{
		int ClassLabel = -1;
		int nuberofNeighbors;
		int tempClass;
		int []classShows;
		
		//allocate memory for 10 integers
		classShows = new int[10];
		
		//initialize the values of the matrix classShows
		for (int i=0; i<=9;i++)
		{
			classShows[i] = 0;
		}
		
		nuberofNeighbors = Neighbors.size();
		
		//Find the majority class of the neighbors
		for (int i=0; i<nuberofNeighbors;i++)
		{
			tempClass = Neighbors.get(i).getClassLabel();
			for (int j=0; j<=9;j++)
			{
				if (tempClass == j )
				{
					classShows[j] = classShows[j] +1;
				}
					
			}
		}
		
		int max = -1;
		for (int i=0; i<=9;i++)
		{
			if (classShows[i] > max)
			{
				max = classShows[i];
				ClassLabel = i;
				
			}
		}
		
		
		
		
		return ClassLabel;
	}
}

package CBR;

import java.util.ArrayList;
import java.util.List;

public class CaseTree {
	private int[] attrRating;
	private int[] attrSplits;
	private Node rootNode;
	private List<Case> wholeList;
	private List<Node> leaves;
	
	public CaseTree(List<Case> trainingSet) {
		leaves = new ArrayList<Node>();

//		static array from InfoGain results.
		attrRating = new int[]{13, 15, 7, 5, 9, 4, 14, 11, 0, 10, 1, 3, 8, 6, 2, 12};
//		CAIM results.
		attrSplits = new int[]{36, 28, 36, 61, 53, 39, 47, 39, 52, 48, 66, 69, 46, 49, 37, 51};
		
		rootNode = new Node(null, null, -1, -1, -1);
		buildSubTree(0, rootNode);
		createTree(trainingSet);
		
		wholeList = new ArrayList<Case>();
		wholeList.addAll(trainingSet);
		
		//System.out.println("leaves.size : " + leaves.size());
		for (int i = 0; i < leaves.size(); i++){
			//System.out.println(i + " : " + leaves.get(i).getCases().size());
		}
	}
	
	public List<Case> getSimilarCases(Case searchCase){
//		System.out.println("Getting similar cases...");
		Node currentNode = searchLeaf(searchCase);
//		System.out.println("Getting similar cases finished");
		return currentNode.getCases();
	}
	
	public void addCase(Case newCase) {
		getSimilarCases(newCase).add(newCase);
		wholeList.add(newCase);
	}
	
	public List<Case> getWholeList() { return this.wholeList; }
	
	public void deleteCase(Case deletedCase){
		getSimilarCases(deletedCase).remove(deletedCase);
		wholeList.remove(deletedCase);
	}
	
	private void createTree(List<Case> trainingSet) {	
		//System.out.println("Creating caseTree...");
		for (Case currentCase : trainingSet){
			Node currentNode = searchLeaf(currentCase);
			currentNode.getCases().add(currentCase);
//			System.out.println(currentNode.getCases().size() + " : " + currentNode);
		}
		//System.out.println("Creating caseTree finished");
	}
	
	private Node searchLeaf(Case currentCase){
		List<Integer> currentAttr = currentCase.getAttributes();
		Node currentNode = rootNode;
		while (currentNode.getCases() == null){
			List<Node> children = currentNode.getChildren();
			for (Node child : children){
				int attrValue = currentAttr.get(child.getSplittingAttr());
				if ( attrValue <= child.getMaxValue() && attrValue >= child.getMinValue() ){
					currentNode = child;
					break;
				}
			}
//			if none of the children fits to our attributes, itsn't ok!
			if (!children.contains(currentNode)){
				System.out.println("Comething's wrong in searchLeaf: !children.contains(currentNode)");
				System.exit(0);
			}
		}
//		check if we in any lead now.
		if (currentNode.getCases() == null) {
			System.out.println("Comething's wrong in searchLeaf: currentNode.getCases() == null");
			System.exit(0);
		}
		return currentNode;
	}
	
	private void buildSubTree(int currentLevel, Node currentParent){
		Node child1 = new Node(null, currentParent, attrRating[currentLevel], 0, attrSplits[currentLevel]);
		currentParent.addChild(child1);

		Node child2 = new Node(null, currentParent, attrRating[currentLevel], attrSplits[currentLevel] + 1, 100);
		currentParent.addChild(child2);
		
		if (currentLevel + 1 <  attrRating.length/4) {
//		if (currentLevel + 1 <  attrRating.length) {
			buildSubTree(currentLevel + 1, child1);
			buildSubTree(currentLevel + 1, child2);
		}
//		intitialize leaves with empty lists
		else {
			child1.setCases(new ArrayList<Case>());
			child2.setCases(new ArrayList<Case>());
			leaves.add(child1);
			leaves.add(child2);
		}
	}
	
	private static class Node {
        private List<Case> cases;
        private Node parent;
        private List<Node> children;
        
        private int splittingAttr;
        private int maxValue;
        private int minValue;
        
        public Node() {
        	cases = null;
        }
        
        public Node(List<Case> cases, Node parent, int splittingAttr, int minValue, int maxValue) {
        	this.cases = cases;
        	this.parent = parent;
        	this.splittingAttr = splittingAttr;
        	this.maxValue = maxValue;
        	this.minValue = minValue;
        	this.children = new ArrayList<Node>();
        }
                
        public int getSplittingAttr() { return splittingAttr;}
        public int getMaxValue() {return maxValue;}
        public int getMinValue() {return minValue;}
        public List<Case> getCases() {return cases;}
        public List<Node> getChildren() {return children;}
        
        public void setParent(Node value) {this.parent = value;}
        public void setCases(List<Case> cases) { this.cases = cases;}
      
        public void addChild(Node child) {
        	children.add(child);
        }
    }
}
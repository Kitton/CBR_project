package CBR;

import java.util.ArrayList;
import java.util.List;

public class CaseTree {
	private int[] attrRating;
	private Node rootNode;
	
	public CaseTree() {
		attrRating = new int[16];
		for (int i=0; i < 16; i++){
			attrRating[i] = i;
		}
		
		rootNode = new Node(null, null, -1, -1, -1);
		buildSubTree(0, rootNode);
	}
	
	public void createTree(List<Case> trainingSet) {	
		for (Case currentCase : trainingSet){
			Node currentNode = searchLeaf(currentCase);
			currentNode.getCases().add(currentCase);
		}
	}
	
	public List<Case> getSimilarCases(Case searchCase){
		Node currentNode = searchLeaf(searchCase);
		return currentNode.getCases();
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
		Node child1 = new Node(null, currentParent, attrRating[currentLevel], 0, 50);
		currentParent.addChild(child1);

		Node child2 = new Node(null, currentParent, attrRating[currentLevel], 51, 100);
		currentParent.addChild(child2);
		
		if (currentLevel + 1 <  attrRating.length) {
			buildSubTree(currentLevel + 1, child1);
			buildSubTree(currentLevel + 1, child2);
		}
//		intitialize leaves with empty lists
		else {
			child1.setCases(new ArrayList<Case>());
			child2.setCases(new ArrayList<Case>());
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
        
        public Node(List<Case> cases, Node parent, int splittingAttr, int maxValue, int minValue) {
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
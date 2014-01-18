package CBR;

import java.util.List;

public class CaseTree {
	
	public static class Node {
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
        }
                
        public int getSplittingAttr() { return splittingAttr;}
        public int getMaxValue() {return maxValue;}
        public int getMinValue() {return minValue;}
        public List<Case> getCases() {return cases;}
        
        public void setParent(Node value) {this.parent = value;}
      
        public void addChild(Node child) {
        	children.add(child);
        }
    }
}
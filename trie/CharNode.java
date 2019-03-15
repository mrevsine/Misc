package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharNode {
	
	private String letter;
	private long count;
	private long usage;
	private boolean inOrder;
	List<CharNode> children;
	
	public CharNode(String letter) {
		this.letter = letter;
		count = 1;
		usage = 0;
		children = new ArrayList<CharNode>();
		inOrder = false;
	}
	
	public String getLetter() { return letter; }
	public long getCount() { return count; }
	public List<CharNode> getChildren() { return children; }
	public void incrementCount() { count++; }
	public boolean getIsInOrder() { return inOrder; }
	public void setIsInOrder(boolean b) { inOrder = b; }
	public long getUsage() { return usage; }
	public void setUsage(long n) { usage = n; }
	
	public void addWord(String word) {
		
		String letter = word.substring(0, 1).toLowerCase();
		CharNode child = getNodeByLetter(letter);
		if (child == null) {
			child = new CharNode(letter);
			children.add(child);
		} else {
			child.incrementCount();
		}
		if (word.length() > 1) {
			child.addWord(word.substring(1, word.length()));
			child.setIsInOrder(false);
		} 
		
	}
	
	public String getMostCommonChildren() {
		
		orderChildren();
		String common = "";
		for (CharNode CN : children) {
			common += CN.getLetter();
		}
		return common;
		
	}
	
	public void orderChildren() {
		
		if (!inOrder) {
			CharNode[] ordered = new CharNode[children.size()];
			int index = 0;
			for (CharNode c: children) { ordered[index++] = c; }
			
			Arrays.sort(ordered, new CharNodeComparator());
			
			children.clear();
			for (int i = 0; i < ordered.length; i++) {
				children.add(ordered[i]);
			}
			inOrder = true;
		}
		
	}
	
	public CharNode getNodeByLetter(String letter) {
		for (CharNode c : children) {
			if (c.getLetter().equalsIgnoreCase(letter)) return c;
		} 
		return null;
	}
	
	public String getMostLikelyContinuation() {
		
		for (CharNode CN : children) {
			
			if (CN.getUsage() <= usage) {
				
				return letter + CN.getMostLikelyContinuation();
				
			}
			
		}
		
		return letter;
		
	}

}

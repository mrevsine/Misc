package trie;

import java.util.Comparator;

public class CharNodeComparator implements Comparator<CharNode> {
	
	public int compare(CharNode a, CharNode b) {
		return (int) (b.getUsage() - a.getUsage());
	}

}

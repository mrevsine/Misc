package trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Trie {
	
	private CharNode sentinel;
	private long size;
	
	public Trie() {
		sentinel = new CharNode(" ");
		size = 0;
	}
	
	public CharNode getSentinel() { return sentinel; }
	public long getSize() { return size; }
	
	public void includeFrequencies() {
		
		File file = new File("/Users/MahlerRevsine/Documents/Word_Frequencies.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file");
		}
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		String line;
		try {
			line = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file");
		}
		
		while (line != null) {
			
			String[] words = line.split("\\s+");
			if (!contains(words[1])) addWord(words[1]);
			assignFrequency(words[1], Long.parseLong(words[3]));
			
			addWord(line);
			try {
				line = br.readLine();
			} catch (IOException e) {
				break;
			}
		}
		
	}
	
	public void buildBig() {
		
		File file = new File("/Users/MahlerRevsine/Documents/Big_English_Dictionary.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file");
		}
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		String line;
		try {
			line = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file");
		}
		
		while (line != null) {
			addWord(line);
			try {
				line = br.readLine();
			} catch (IOException e) {
				break;
			}
		}
		
	}
	
	public void buildSmall() {
		
		File file = new File("/Users/MahlerRevsine/Documents/English_Dictionary.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file");
		}
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		String line;
		try {
			line = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file");
		}
		
		while (line != null) {
			addWord(line);
			try {
				line = br.readLine();
			} catch (IOException e) {
				break;
			}
		}
		
	}
	
	public void readInTextFile(String fileName) {
		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(file));
//		    StringBuilder sb = new StringBuilder();
//		    String line = br.readLine();
//		    String[] temp = line.split(" ");
//	        for (String s : temp) {
//	        		if (!contains(s)) addWord(s);
//	        }
//
//		    while (line != null) {
//		        sb.append(line);
//		        sb.append(System.lineSeparator());
//		        line = br.readLine();
//		        String[] words = line.split(" ");
//		        for (String s : words) {
//		        		if (!contains(s)) addWord(s);
//		        }
//		    }
//		    br.close();
////		    String everything = sb.toString();
////		    System.out.print(everything);
//		} catch (IOException e) {
//			System.out.println("Unable to process file");
//		} 
		
		File file = new File(fileName);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file");
		}
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		String line;
		try {
			line = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file");
		}
		
		while (line != null) {
			addWord(line);
			try {
				line = br.readLine();
			} catch (IOException e) {
				break;
			}
		}
		
	}
	
	public void addWord(String word) {
		
		size++;
		sentinel.addWord(word);
		
	}
	
	public boolean contains(String s) {
		
		CharNode parent = sentinel;
		
		for (int i = 0; i < s.length(); i++) {
			
			String letter = s.substring(i, i+1);
			parent = parent.getNodeByLetter(letter);
			if (parent == null) return false;
			
		}
		
		return true;
		
	}
	
	public String getContinuation(String s) {
		
		String continuation = s;
		CharNode parent = sentinel;
		
		//by the end of this loop, parent should be the node correlating with the last letter of the input string
		for (int i = 0; i < s.length(); i++) {
			
			String letter = s.substring(i, i+1);
			parent = parent.getNodeByLetter(letter);
			if (parent == null) {
				return continuation;
			}
			
		}
	
		while (parent.getChildren().size() > 0) {
			
			parent.orderChildren();
			if (parent.getUsage() > parent.getChildren().get(0).getUsage()) {
				break;
			} else {
				continuation += parent.getChildren().get(0).getLetter();
				parent = parent.getChildren().get(0);
			}
			
		}
		
		return continuation;
		
	}
	
	public void assignFrequency(String s, long n) {
		
		CharNode node = sentinel;
		
		//by the end of this loop, parent should be the node correlating with the last letter of the input string
		for (int i = 0; i < s.length(); i++) {
			
			String letter = s.substring(i, i+1);
			node = node.getNodeByLetter(letter);
			if (node == null) {
				System.out.println(s);
				throw new IllegalArgumentException("\"" + s + "\"" + " is not in the trie");
			} else {
				if (node.getUsage() < n) node.setUsage(n);
			}
			
		}
		
	}

}

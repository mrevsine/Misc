package trie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import trie.Trie;

public class Display extends JPanel {
	
	private JTextField textField;
	private JLabel label;
	private Trie trie;
	
	public Display() {
		
		setLayout(new GridLayout(2,1));
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLabel();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLabel();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLabel();
			}
			
		});
		label = new JLabel();
		add(textField);
		add(label);
		
		trie = new Trie();
		trie.buildBig();
		trie.includeFrequencies();
		
	}
	

	public void updateLabel() {
		
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				
				if (textField.getText().equals("")) label.setText("");
				else label.setText(trie.getContinuation(textField.getText()));
				
			}
			
		});
		
		t.start();
		
	}
	
	/**
	 * Works for single words
	 * Suggests most likely continuation for the given word
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("Text-Suggestion");
		frame.setDefaultCloseOperation(3);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		Display d = new Display();
		
		panel.add(d);
		
		frame.setContentPane(panel);
		frame.setMinimumSize(new Dimension(300,100));
		
		frame.setVisible(true);
		frame.pack();
		
	}
	

}

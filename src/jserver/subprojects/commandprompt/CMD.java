package jserver.subprojects.commandprompt;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import console.TextAreaOutputStream;

public class CMD {
	static String command = "";
	static boolean get = false;
	public CMD() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		
		
	    JFrame frame = new JFrame("JTextArea Test");
	    frame.setLayout(new FlowLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    String text = "";
	    
	    JTextArea textArea = new JTextArea(text, 10, 20);
	    textArea.setPreferredSize(new Dimension(100, 100));
	    JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    textArea.setLineWrap(true);
	    frame.add(scrollPane);
	    JTextArea textArea2 = new JTextArea(text, 5, 10);
	    textArea.setPreferredSize(new Dimension(100, 100));
	    JScrollPane scrollPane2 = new JScrollPane(textArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    textArea.setLineWrap(true);
	    frame.add(scrollPane);
	    frame.add(scrollPane2);
	    JButton submit = new JButton("Execute");
	    frame.add(submit);
	    JButton clear = new JButton("Clear");
	    frame.add(clear);
	    frame.pack();
	    frame.setVisible(true);
	    PrintStream temp = new PrintStream(new TextAreaOutputStream(textArea, 20));
	    System.setOut(temp);
		System.setErr(temp);
		String cd = "";
		submit.addActionListener((e) -> {
			if (e.getActionCommand().equals("Execute")) {
				get = true;
			}
		});
		clear.addActionListener((e) -> {
			if (e.getActionCommand().equals("Clear")) {
				textArea2.setText("");
			}
		});
		while (true) {
			while (!get) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			command = textArea2.getText();
			textArea.setText("");
			if (!command.startsWith("cd ")) {
				try {
					Process process;
					if (!(cd.equals(""))) process = Runtime.getRuntime().exec(command, null, new File(cd));
					else process = Runtime.getRuntime().exec(command);
					BufferedReader reader = new BufferedReader(
						new InputStreamReader(process.getInputStream()));
					String line;
					while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				
				reader.close();
		    	
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				cd = command.substring(2);
			}
		}
	}

}

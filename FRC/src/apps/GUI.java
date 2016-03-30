package apps;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

public class GUI extends JFrame {
	private static String path = "C:\\Users\\lbeal3008\\Desktop\\test.txt";
	// private static String path = "";
	private JTextField Conf;
	private static String[] var;
	private static String[] val;
	private static int length;
	private JTextField[] textField;
	private JTextField UpathT;
	
	
	
	public static void rewrite(String[] m, String[] y){
		   Path file = Paths.get(path);

			  if(Files.exists(file) && Files.isWritable(file)) {
				  
			      try {
			          // File writer
			          BufferedWriter Writer = Files.newBufferedWriter(file, Charset.defaultCharset());
			          Writer.write("package org.usfirst.frc.team5572.robot;");
			          Writer.newLine();
			          Writer.write("public class Configuration {");
			          Writer.newLine();
			         for (int l = 0; l < length; l++){
			          Writer.write("    public static void " + m[l] + " = " + y[l] +";");
			          Writer.newLine();
			         }
			         Writer.write("}");
			          Writer.close();
			      } catch (Exception e) {
			          e.printStackTrace();
			      }
			  }
	}

	public static void getLength() {
		Path file = Paths.get(path);

		if (Files.exists(file) && Files.isReadable(file)) {

			try {
				BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());
				String line;
				String sub;
				length = 0;
				while ((line = reader.readLine()) != null) {
					{
						if (!line.isEmpty() && line.length() > 8 && (line.indexOf("public sta")) > -1) {
							length++;
						}
					}
				}
				reader.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void varArray() {
		Path file = Paths.get(path);
		var = new String[length+1];
		
		val = new String[length+1];
	

		if (Files.exists(file) && Files.isReadable(file)) {

			try {
				BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());
			
				String line;
				String sub;
				int q = 0;
				while ((line = reader.readLine()) != null) {
					if (!line.isEmpty() && line.length() > 8 && (line.indexOf("public sta")) > -1) {
						sub = StringU.var(line);
						var[q] = sub;
						sub = StringU.val(line);
						val[q] = sub;
						q++;
					}
				}
				reader.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public GUI(int a) {

		// Retrieve the content-pane of the top-level container JFrame
		// All operations done on the content-pane
		if (a == 1){
			Container cp = getContentPane();
			cp.setLayout(new FlowLayout());
			cp.add(new JLabel("name"));
			Conf = new JTextField("count", 10);
			Conf.setEditable(true);
			cp.add(Conf);
			
			Panel text = new Panel(new FlowLayout());
			UpathT = new JTextField("", 10);
			text.add(new JLabel("path"));
			text.add(UpathT);
		}
		
		if (a == 2){
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new JLabel("name"));
		Conf = new JTextField("count", 10);
		Conf.setEditable(true);
		cp.add(Conf);

		Panel panelButtons = new Panel(new GridLayout(0, 6));
		textField = new JTextField[length];
		for (int n = 0; n < length; n++) {
			panelButtons.add(new JLabel(var[n]));
			textField[n] = new JTextField(val[n]);
			panelButtons.add(textField[n]);

		}
		JButton save = new JButton("save");
		panelButtons.add(save);
		
		save.addActionListener(new ActionListener(){
	    	 
	    	  @Override
	    	  public void actionPerformed(ActionEvent e){
	    		  String[] val1 = new String[length];
	    		  for (int k = 0; k < length; k++){
		    			 val1[k] = textField[k].getText();
		    			 
		    		  }
		    		  rewrite(var, val1);
	    		  
	    	  }
	      });
		
	
		

		setLayout(new BorderLayout()); // "this" Frame sets to BorderLayout
		// add(panelDisplay, BorderLayout.NORTH);
		add(panelButtons, BorderLayout.CENTER);
		// Allocate an anonymous instance of an anonymous inner class that
		// implements ActionListener as ActionEvent listener

		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program if
														// close-window button
														// clicked
		setTitle("Configuration"); // "this" JFrame sets title
		setSize(1100, 500); // "this" JFrame sets initial size
		setVisible(true); // "this" JFrame shows

		}
	}

	public static void main(String[] args) {
		new GUI(1);
		System.out.println(length);
		getLength();
		varArray();
		System.out.println(length);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			//	new GUI(1);
				new GUI(2); // Let the constructor do the job
			}
		});

	}
}

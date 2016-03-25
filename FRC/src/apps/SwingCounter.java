package apps;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\ 
 * http://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html										 *
 * http://www3.ntu.edu.sg/home/ehchua/programming/howto/EclipseJava_HowTo.html#GUIBuider				 *
 * http://stackoverflow.com/questions/5752307/how-to-retrieve-value-from-jtextfield-in-java-swing		 *
 * http://stackoverflow.com/questions/5585779/converting-string-to-int-in-java							 *
 * https://docs.oracle.com/javase/tutorial/uiswing/components/label.html								 *
 * http://stackoverflow.com/questions/2788080/java-how-to-read-a-text-file								 *
 *
\* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class SwingCounter extends JFrame{
		private JTextField tfCount;  // Use Swing's JTextField instead of AWT's TextField
			private static String path = "C:\\Users\\lbeal3008\\Desktop\\test.txt";
//			private static String path = "";
		   private int count = 4;
		   private String Coun = count + "";
		   private String name = "Counter";
		   private JTextField[] textField = new JTextField[10];
		   private String[] toppings = new String[10];
		   private static String[] read = new String[10];
		   
		   //private ;
		 
		   public static void clearFile(String[] m){
			   Path file = Paths.get(path);

				  if(Files.exists(file) && Files.isWritable(file)) {
					  
				      try {
				          // File reader
				          BufferedWriter Writer = Files.newBufferedWriter(file, Charset.defaultCharset());
				          
				         for (int l = 1; l < 10; l++){
				          Writer.write(m[l]);
				          Writer.newLine();
				         }
				          // read each line
				          
				          Writer.close();
				      } catch (Exception e) {
				          e.printStackTrace();
				      }
				  }
		   }
		   public static void readFile(){
			   Path file = Paths.get(path);

				  if(Files.exists(file) && Files.isReadable(file)) {
					  
					  try {
					        // File reader
					        BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());

					        String line;
					        // read each line
					        int q = 1;
					        while(q < 10) {
					        	
					          //  System.out.println(line);
					            line = reader.readLine();
					            read[q] = line;
					            q++;
					            // tokenize each number
					   /*         StringTokenizer tokenizer = new StringTokenizer(line, " ");
					            while (tokenizer.hasMoreElements()) {
					                // parse each integer in file
					                int element = Integer.parseInt(tokenizer.nextToken());
					            }
					        } */
					        
					        }
					        reader.close();
					        
					    }
					     catch (Exception e) {
					        e.printStackTrace();
					    }
				  }
		   }
		   
		   /** Constructor to setup the GUI */
		  public static void filetest(String in){
			  Path file = Paths.get(path);

			  if(Files.exists(file) && Files.isWritable(file)) {

			      try {
			          // File reader
			    	  PrintStream ps = new PrintStream(file.toFile());
			    	  
			    	  ps.println(in);
			    	  
			    	  ps.close();
			      } catch (Exception e) {
			          e.printStackTrace();
			      }
			  }
			  
		  }
		   public SwingCounter () {
		      // Retrieve the content-pane of the top-level container JFrame
		      // All operations done on the content-pane
		      Container cp = getContentPane();
		      cp.setLayout(new FlowLayout());
		      cp.add(new JLabel(name));
		      tfCount = new JTextField(Coun, 10);
		      tfCount.setEditable(true);
		      cp.add(tfCount);
		 
		      JButton btnCount = new JButton("Count");
		      cp.add(btnCount);
		      JButton btnUncount = new JButton("Uncount");
		      cp.add(btnUncount);
		      
		      
		      Panel panelButtons = new Panel(new GridLayout(10,1));
		      
		      for (int n = 1; n < 10; n++){
		    panelButtons.add(new JLabel(n + ""));
		      textField[n] = new JTextField(read[n]);
		      panelButtons.add(textField[n]);
		      
		      }
		      JButton save = new JButton("save");
		      panelButtons.add(save);
		     
		      
		      save.addActionListener(new ActionListener(){
		    	 
		    	  @Override
		    	  public void actionPerformed(ActionEvent e){
		    		  String add = "";
		    		  for (int k = 1; k < 10; k++){
		    			 toppings[k] = textField[k].getText();
		    			 
		    		  }
		    		  clearFile(toppings);
		    		  
		    		  
		    	  }
		      });
		      
		   
		      setLayout(new BorderLayout());  // "this" Frame sets to BorderLayout
		     // add(panelDisplay, BorderLayout.NORTH);
		      add(panelButtons, BorderLayout.CENTER);
		      
		      
		      
		      
		      
		      btnUncount.addActionListener(new ActionListener() {
		    	  @Override
		    	  public void actionPerformed(ActionEvent e){
		    		  --count;
		    		  tfCount.setText(count + "");
		    		  
		    	  }
		      });
		 
		      // Allocate an anonymous instance of an anonymous inner class that
		      //  implements ActionListener as ActionEvent listener
		      btnCount.addActionListener(new ActionListener() {
		         @Override
		         public void actionPerformed(ActionEvent e) {
		            ++count;
		            tfCount.setText(count + "");
		            if (count == 30 ){
				    	  name = "Slow down there pal";
				      }
				      else{
				    	  name = "Counter";
				      }
		         }
		      });
		      
		      tfCount.addActionListener(new ActionListener(){
		    	   public void actionPerformed(ActionEvent e){
		    	      String textFieldValue = tfCount.getText();
		    	      // .... do some operation on value ...
		    	      count = Integer.parseInt(textFieldValue);
		    	      tfCount.setText(count + "");
		    	   }
		    	});
		 
		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
		      setTitle("test stuff"); // "this" JFrame sets title
		      setSize(500, 500);         // "this" JFrame sets initial size
		      setVisible(true);          // "this" JFrame shows
		     
		   }

		   /** The entry main() method 
		    
		    */
		   
		   public static void main(String[] args) {
		//	   filetest("G");
		      // Run the GUI construction in the Event-Dispatching thread for thread-safety
			   readFile();
		      SwingUtilities.invokeLater(new Runnable() {
		         @Override
		         public void run() {
		            new SwingCounter(); // Let the constructor do the job
		         }
		});

	}
}

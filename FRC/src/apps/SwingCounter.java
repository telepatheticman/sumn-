package apps;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\ 
 * http://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html										 *
 * http://www3.ntu.edu.sg/home/ehchua/programming/howto/EclipseJava_HowTo.html#GUIBuider				 *
 * http://stackoverflow.com/questions/5752307/how-to-retrieve-value-from-jtextfield-in-java-swing		 *
 * http://stackoverflow.com/questions/5585779/converting-string-to-int-in-java							 *
 * https://docs.oracle.com/javase/tutorial/uiswing/components/label.html								 *
 * 
\* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class SwingCounter extends JFrame{
		private JTextField tfCount;  // Use Swing's JTextField instead of AWT's TextField
		   private int count = 4;
		   private String Coun = count + "";
		   private String name = "Counter";
		   private JTextField[] btnNumbers = new JTextField[10];
		 
		   /** Constructor to setup the GUI */
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
		      
		      Panel panelButtons = new Panel(new GridLayout(9,2));
		      
		      for (int n = 1; n < 10; n++){
		    panelButtons.add(new JLabel(n + ""));
		      btnNumbers[n] = new JTextField("test");
		      panelButtons.add(btnNumbers[n]);
		      
		      }
		      
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
		      setTitle("Swing Counter"); // "this" JFrame sets title
		      setSize(400, 100);         // "this" JFrame sets initial size
		      setVisible(true);          // "this" JFrame shows
		     
		   }
		 
		   /** The entry main() method */
		   public static void main(String[] args) {
		      // Run the GUI construction in the Event-Dispatching thread for thread-safety
		      SwingUtilities.invokeLater(new Runnable() {
		         @Override
		         public void run() {
		            new SwingCounter(); // Let the constructor do the job
		         }
		      });
		   }
}

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
	private static String path = "C:\\Dev\\Configuration.java";
	// private static String path = "";
	private JTextField Conf;
	private static String[] var;
	private static String[] val;
	private static int length;
	private JTextField[] textField;

	public static void getLength() {
		Path file = Paths.get(path);

		if (Files.exists(file) && Files.isReadable(file)) {
			System.out.println("yo");

			try {
				BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());
				String pub = "public sta";
				String line;
				String sub;
				length = 0;
				while ((line = reader.readLine()) != null) {
					{
						System.out.println("sup");
						if (!line.isEmpty() && line.length() > 8 && (line.indexOf("public sta")) > -1) {
							System.out.println("hi");
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
		var = new String[length];
		Path file = Paths.get(path);

		if (Files.exists(file) && Files.isReadable(file)) {

			try {
				BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());
				String pub = "public sta";
				String line;
				String sub;
				int q = 0;
				while ((line = reader.readLine()) != null) {
					if (!line.isEmpty() && line.length() > 8 && (line.substring(0, 9)).equals(pub)) {
						sub = StringU.var(line);
						var[q] = sub;
						q++;
					}
				}
				reader.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void valArray() {
		val = new String[length];
		Path file = Paths.get(path);

		if (Files.exists(file) && Files.isReadable(file)) {

			try {
				BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());
				String pub = "public sta";
				String line;
				String sub;
				int q = 0;
				while ((line = reader.readLine()) != null) {
					if (!line.isEmpty() && line.length() > 8 && (line.substring(0, 9)).equals(pub)) {
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

	public GUI() {

		// Retrieve the content-pane of the top-level container JFrame
		// All operations done on the content-pane
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new JLabel("name"));
		Conf = new JTextField("count", 10);
		Conf.setEditable(true);
		cp.add(Conf);

		JButton btnCount = new JButton("Count");
		cp.add(btnCount);
		JButton btnUncount = new JButton("Uncount");
		cp.add(btnUncount);

		Panel panelButtons = new Panel(new GridLayout(length, 1));
		textField = new JTextField[length];
		for (int n = 0; n < length; n++) {
			panelButtons.add(new JLabel(var[n]));
			textField[n] = new JTextField(val[n]);
			panelButtons.add(textField[n]);

		}
		JButton save = new JButton("save");
		panelButtons.add(save);

		/*
		 * save.addActionListener(new ActionListener(){
		 * 
		 * @Override public void actionPerformed(ActionEvent e){ String add =
		 * ""; for (int k = 1; k < 10; k++){ toppings[k] =
		 * textField[k].getText();
		 * 
		 * } clearFile(toppings);
		 * 
		 * 
		 * } });
		 */

		setLayout(new BorderLayout()); // "this" Frame sets to BorderLayout
		// add(panelDisplay, BorderLayout.NORTH);
		add(panelButtons, BorderLayout.CENTER);
		// Allocate an anonymous instance of an anonymous inner class that
		// implements ActionListener as ActionEvent listener

		/*
		 * tfCount.addActionListener(new ActionListener(){ public void
		 * actionPerformed(ActionEvent e){ String textFieldValue =
		 * tfCount.getText(); // .... do some operation on value ... count =
		 * Integer.parseInt(textFieldValue); tfCount.setText(count + ""); } });
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program if
														// close-window button
														// clicked
		setTitle("test stuff"); // "this" JFrame sets title
		setSize(500, 500); // "this" JFrame sets initial size
		setVisible(true); // "this" JFrame shows

	}

	public static void main(String[] args) {
		System.out.println(length);
		getLength();
		valArray();
		varArray();
		System.out.println(length);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI(); // Let the constructor do the job
			}
		});

	}
}

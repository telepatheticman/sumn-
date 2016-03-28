package apps;

import java.util.regex.Pattern;

public class StringU {
	private static String[] subString;
	public static String var(String m){
		subString = new String[10];
		String [] subString = m.split(Pattern.quote(" "));
		String g = subString[4] + " " + subString[5];
		return g;
	}
	public static String val(String m){
		subString = new String[10];
		String [] subString = m.split(Pattern.quote(" "));
		String g = subString[7];
		return g;
	}
}

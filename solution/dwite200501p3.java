import java.io.*;


// DWITE - January 2005 - Problem 3: Harshad Numbers
public class dwite200501p3 {
	
	public static void main(BufferedReader in, PrintWriter out) throws IOException {
		for (int i = 0; i < 5; i++)
			mainOnce(in, out);
	}
	
	
	private static void mainOnce(BufferedReader in, PrintWriter out) throws IOException {
		// Read input
		int start = Integer.parseInt(in.readLine());
		int end = Integer.parseInt(in.readLine());
		
		// Compute longest run
		int longestrun = 0;
		int currentrun = 0;
		for (int i = start; i <= end; i++) {
			if (i % getDigitSum(i) == 0)
				currentrun++;
			else {
				longestrun = Math.max(currentrun, longestrun);
				currentrun = 0;
			}
		}
		longestrun = Math.max(currentrun, longestrun);
		
		// Write output
		out.println(longestrun);
	}
	
	
	private static int getDigitSum(int n) {  // Valid for 0 <= n < 10 000 000
		return n /       1 % 10
		     + n /      10 % 10
		     + n /     100 % 10
		     + n /    1000 % 10
		     + n /   10000 % 10
		     + n /  100000 % 10
		     + n / 1000000 % 10;
	}
	
	
	
	private static String infile = "DATA31.txt";  // Specify null to use System.in
	private static String outfile = "OUT31.txt";  // Specify null to use System.out
	
	
	public static void main(String[] args) throws IOException {
		InputStream in0;
		if (infile != null) in0 = new FileInputStream(infile);
		else in0 = System.in;
		Reader in1 = new InputStreamReader(in0, "US-ASCII");
		BufferedReader in = new BufferedReader(in1);
		
		OutputStream out0;
		if (outfile != null) out0 = new FileOutputStream(outfile);
		else out0 = System.out;
		Writer out1 = new OutputStreamWriter(out0, "US-ASCII");
		PrintWriter out = new PrintWriter(out1, true);
		
		main(in, out);
		
		in.close();
		in1.close();
		in0.close();
		out.close();
		out1.close();
		out0.close();
	}
	
}
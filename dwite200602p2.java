import java.io.*;


// DWITE - February 2006 - Problem 2: Floppy Disk 3 1/2-inch High Density
public class dwite200602p2 {
	
	private static final int CAPACITY = 1440;
	
	
	public static void main(BufferedReader in, PrintWriter out) throws IOException {
		for (int i = 0; i < 5; i++)
			mainOnce(in, out);
	}
	
	
	private static void mainOnce(BufferedReader in, PrintWriter out) throws IOException {
		// Solve subset sum problem using dynamic programming
		boolean[] possible = new boolean[CAPACITY + 1];
		possible[0] = true;
		int n = Integer.parseInt(in.readLine());
		for (int i = 0; i < n; i++) {
			int filesize = Integer.parseInt(in.readLine());
			for (int j = CAPACITY - filesize; j >= 0; j--)
				possible[j + filesize] |= possible[j];
		}
		
		// Write the largest possible total size
		for (int i = CAPACITY; i >= 0; i--) {
			if (possible[i]) {  // Guaranteed to execute before the loop ends
				out.println(CAPACITY - i);
				break;
			}
		}
	}
	
	
	
	private static String infile = "DATA21.txt";  // Specify null to use System.in
	private static String outfile = "OUT21.txt";  // Specify null to use System.out
	
	
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
import java.io.*;
import java.util.StringTokenizer;


// DWITE - January 2007 - Problem 4: Number Theory
public class dwite200701p4 {
	
	public static void main(BufferedReader in, PrintWriter out) throws IOException {
		for (int i = 0; i < 5; i++)
			mainOnce(in, out);
	}
	
	
	private static void mainOnce(BufferedReader in, PrintWriter out) throws IOException {
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		out.println(partition(n, r));
	}
	
	
	// Returns the number of ways n can be written as a sum of k positive integers.
	private static int partition(int n, int k) {
		if (k > n)
			return 0;
		else if (k == n)
			return 1;
		else if (k == 0)
			return 0;
		else
			return partition(n - 1, k - 1) + partition(n - k, k);
	}
	
	
	
	private static String infile = "DATA41.txt";  // Specify null to use System.in
	private static String outfile = "OUT41.txt";  // Specify null to use System.out
	
	
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
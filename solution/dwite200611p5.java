import java.io.*;


// DWITE - November 2006 - Problem 5: Goldbach's Weak Conjecture
public class dwite200611p5 {
	
	private static boolean[] isPrime;
	
	static {
		isPrime = sievePrimes(999999);
		isPrime[2] = false;  // For the purposes of this problem
	}
	
	
	
	public static void main(BufferedReader in, PrintWriter out) throws IOException {
		for (int i = 0; i < 5; i++)
			mainOnce(in, out);
	}
	
	
	private static void mainOnce(BufferedReader in, PrintWriter out) throws IOException {
		int n = Integer.parseInt(in.readLine());
		out.printf("%d=%s%n", n, solve(n));
	}
	
	
	private static String solve(int n) {
		if (n % 2 == 0)
			throw new IllegalArgumentException("Not applicable to even numbers");
		if (n <= 7)
			throw new IllegalArgumentException("Not applicable for numbers 7 or less");
		return solve(n, 3, Integer.MAX_VALUE);
	}
	
	
	private static String solve(int n, int terms, int max) {
		if (n > max)
			return null;
		else if (terms == 1) {
			if (isPrime[n])
				return Integer.toString(n);
			else
				return null;
		} else {
			for (int i = Math.min(n,max), end = (n+terms-1)/terms; i >= end; i--) {
				if (!isPrime[i])
					continue;
				String temp = solve(n - i, terms - 1, i);
				if (temp != null)
					return String.format("%d+%s", i, temp);
			}
			return null;
		}
	}
	
	
	
	private static boolean[] sievePrimes(int n) {
		boolean[] isPrime = new boolean[n + 1];
		if (n >= 2)
			isPrime[2] = true;
		for (int i = 3; i <= n; i += 2)
			isPrime[i] = true;
		for (int i = 3, end = sqrt(n); i <= end; i += 2) {
			if (isPrime[i]) {
				for (int j = i * 3; j <= n; j += i << 1)
					isPrime[j] = false;
			}
		}
		return isPrime;
	}
	
	
	private static int sqrt(int x) {
		int y = 0;
		for (int i = 15; i >= 0; i--) {
			y |= 1 << i;
			if (y > 46340 || y * y > x)
				y ^= 1 << i;
		}
		return y;
	}
	
	
	
	private static String infile = "DATA51.txt";  // Specify null to use System.in
	private static String outfile = "OUT51.txt";  // Specify null to use System.out
	
	
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
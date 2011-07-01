import java.io.*;
import java.util.*;


// DWITE - November 2004 - Problem 3: Factoring
public class dwite200411p3 {
	
	public static void main(BufferedReader in, PrintWriter out) throws IOException {
		for (int i = 0; i < 5; i++)
			mainOnce(in, out);
	}
	
	
	private static void mainOnce(BufferedReader in, PrintWriter out) throws IOException {
		// Read input
		Polynomial poly = parsePolynomial(in.readLine()).reduce();
		
		// Find factors
		ArrayList<Integer> output = new ArrayList<Integer>();
		int a0 = poly.getCoefficient(poly.getDegree());
		while (true) {
			Fraction root = findRoot(poly);
			if (root == null)
				break;
			output.add(a0 / root.denom * root.num);
			poly = poly.divide(root.denom, -root.num);
		}
		
		// Sort ascending and write output
		Collections.sort(output);
		boolean initial = true;
		for (int i : output) {
			if (initial) initial = false;
			else out.print(" ");
			out.print(i);
		}
		out.println();
	}
	
	
	private static Polynomial parsePolynomial(String line) {
		StringTokenizer st = new StringTokenizer(line, " ");
		int degree = Integer.parseInt(st.nextToken());
		List<Integer> coef = new ArrayList<Integer>();
		for (int i = 0; i < degree+1; i++)
			coef.add(Integer.parseInt(st.nextToken()));
		return new Polynomial(coef);
	}
	
	
	private static Fraction findRoot(Polynomial poly) {
		int p = Math.abs(poly.getCoefficient(0));
		int q = Math.abs(poly.getCoefficient(poly.getDegree()));
		for (int i = 1; i <= p; i++) {
			if (p % i != 0)
				continue;
			for (int j = 1; j <= q; j++) {
				if (q % j != 0)
					continue;
				if (poly.hasRootAt(new Fraction( i, j)))
					return new Fraction( i, j);
				if (poly.hasRootAt(new Fraction(-i, j)))
					return new Fraction(-i, j);
			}
		}
		return null;
	}
	
	
	
	private static int gcd(int x, int y) {
		while (y != 0) {
			int z = x % y;
			x = y;
			y = z;
		}
		return Math.abs(x);
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
	
	
	
	private static class Polynomial {
		
		private final List<Integer> coefficients;  // From highest power downward
		
		
		
		public Polynomial(List<Integer> coef) {
			coefficients = new ArrayList<Integer>(coef);
		}
		
		
		
		public int getDegree() {
			return coefficients.size() - 1;
		}
		
		
		// Returns the coefficient of the monomial with the specified power.
		public int getCoefficient(int i) {
			return coefficients.get(coefficients.size() - 1 - i);
		}
		
		
		// Returns this polynomial divided by the GCD of all coefficients.
		public Polynomial reduce() {
			int gcd = coefficients.get(0);
			for (int a : coefficients) {
				gcd = gcd(a, gcd);
			}
			
			if (gcd == 1)
				return this;
			else {
				List<Integer> coef = new ArrayList<Integer>();
				for (int a : coefficients)
					coef.add(a / gcd);
				return new Polynomial(coef);
			}
			
		}
		
		
		public Fraction evaluateAt(Fraction x) {
			Fraction result = new Fraction(0);
			for (int i = 0; i < coefficients.size(); i++) {
				result = result.multiply(x);
				result = result.add(new Fraction(coefficients.get(i)));
			}
			return result;
		}
		
		
		public boolean hasRootAt(Fraction x) {
			return evaluateAt(x).isZero();
		}
		
		
		// Returns a new polynomial representing this polynomial divided by (ax + b).
		public Polynomial divide(int a, int b) {
			if (!hasRootAt(new Fraction(-b, a)))
				throw new IllegalArgumentException();
			List<Integer> coef = new ArrayList<Integer>();
			int remainder = 0;
			for (int i = getDegree(); i >= 1; i--) {
				int quotient = (getCoefficient(i) - remainder) / a;
				coef.add(quotient);
				remainder = quotient * b;
			}
			return new Polynomial(coef);
		}
		
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			boolean initial = true;
			for (int i = getDegree(); i >= 0; i--) {
				if (initial) {
					if (getCoefficient(i) < 0)
						sb.append("-");
					initial = false;
				} else {
					if (getCoefficient(i) >= 0)
						sb.append(" + ");
					else
						sb.append(" - ");
				}
				sb.append(Math.abs(getCoefficient(i))).append(" x^").append(i);
			}
			return sb.toString();
		}
		
	}
	
	
	
	private static class Fraction {
		
		private int num;
		private int denom;
		
		
		
		public Fraction(int num) {
			this(num, 1);
		}
		
		
		public Fraction(int num, int denom) {
			if (denom == 0)
				throw new IllegalArgumentException("Zero denominator");
			int gcd = gcd(num, denom);
			num /= gcd;
			denom /= gcd;
			this.num = num;
			this.denom = denom;
		}
		
		
		
		public Fraction add(Fraction x) {
			return new Fraction(num * x.denom + x.num * denom, denom * x.denom);
		}
		
		
		public Fraction multiply(Fraction x) {
			return new Fraction(num * x.num, denom * x.denom);
		}
		
		
		public boolean isZero() {
			return num == 0;
		}
		
		
		public String toString() {
			return String.format("%d/%d", num, denom);
		}
		
	}
	
}
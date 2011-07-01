import java.io.*;
import java.util.*;


// DWITE - October 2006 - Problem 3: Basketball Statistics II
public class dwite200610p3 {
	
	public static void main(BufferedReader in, PrintWriter out) throws IOException {
		// Read input
		int n = Integer.parseInt(in.readLine());
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < n; i++) {
			String name = in.readLine();
			int foulshots = Integer.parseInt(in.readLine());
			int fieldgoals = Integer.parseInt(in.readLine());
			int threepointbaskets = Integer.parseInt(in.readLine());
			int time = Integer.parseInt(in.readLine());
			players.add(new Player(name, foulshots, fieldgoals, threepointbaskets, time));
		}
		
		// Sort descending by points per minute and write output
		Collections.sort(players, Collections.reverseOrder());
		for (int i = 0; i < Math.min(5,players.size()); i++) {
			Player player = players.get(i);
			out.printf("%s-%.3f%n", player.name, player.getPpm());
		}
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
	
	
	
	private static class Player implements Comparable<Player> {
		
		public final String name;
		public final int score;
		public final int time;  // In minutes
		
		
		
		public Player(String name, int foulShots, int fieldGoals, int threePointBaskets, int time) {
			this.name = name;
			score = foulShots*1 + fieldGoals*2 + threePointBaskets*3;
			this.time = time;
		}
		
		
		
		public double getPpm() {
			return (double)score / time;
		}
		
		
		public int compareTo(Player p) {
			return Double.compare(getPpm(), p.getPpm());
		}
		
		
		public String toString() {
			return String.format("%s (score %d, time %d, PPM %.2f)", name, score, time, getPpm());
		}
		
	}
	
}
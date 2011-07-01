import java.io.*;
import java.util.StringTokenizer;


// DWITE - October 2005 - Problem 2: The Game of Life
public class dwite200510p2 {
	
	public static void main(BufferedReader in, PrintWriter out) throws IOException {
		// Read input
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		char[][] grid = readGridAndPad(in, width, height, '.');
		
		// Compute and write output
		for (int i = 0; i <= 100; i++) {
			if (isBreakpoint(i))
				out.println(countTotalAlive(grid));
			nextGeneration(grid);
		}
	}
	
	
	private static void nextGeneration(char[][] grid) {
		char[][] gridnew = new char[grid.length][grid[0].length];
		for (int y = 1; y < grid.length - 1; y++) {
			for (int x = 1; x < grid[0].length - 1; x++) {
				int liveneigh = countLiveNeighbours(grid, x, y);
				if (grid[y][x] == '.' && liveneigh == 3)  // Birth
					gridnew[y][x] = 'X';
				else if (grid[y][x] == 'X' && (liveneigh < 2 || liveneigh > 3))  // Death
					gridnew[y][x] = '.';
				else  // Unchanged
					gridnew[y][x] = grid[y][x];
			}
		}
		for (int y = 1; y < grid.length - 1; y++) {
			for (int x = 1; x < grid[0].length - 1; x++)
				grid[y][x] = gridnew[y][x];
		}
	}
	
	
	private static int countLiveNeighbours(char[][] grid, int x, int y) {
		int count = 0;
		if (grid[y - 1][x - 1] == 'X') count++;
		if (grid[y - 1][x + 0] == 'X') count++;
		if (grid[y - 1][x + 1] == 'X') count++;
		if (grid[y + 0][x - 1] == 'X') count++;
		if (grid[y + 0][x + 1] == 'X') count++;
		if (grid[y + 1][x - 1] == 'X') count++;
		if (grid[y + 1][x + 0] == 'X') count++;
		if (grid[y + 1][x + 1] == 'X') count++;
		return count;
	}
	
	
	private static int countTotalAlive(char[][] grid) {
		int count = 0;
		for (int y = 1; y < grid.length - 1; y++) {
			for (int x = 1; x < grid[0].length - 1; x++) {
				if (grid[y][x] == 'X')
					count++;
			}
		}
		return count;
	}
	
	
	private static boolean isBreakpoint(int i) {
		return i ==   1
		    || i ==   5
		    || i ==  10
		    || i ==  50
		    || i == 100;
	}
	
	
	
	private static char[][] readGridAndPad(BufferedReader in, int width, int height, char border) throws IOException {
		char[][] grid = new char[height + 2][width + 2];
		for (int y = 1; y <= height; y++) {
			String line = in.readLine();
			for (int x = 1; x <= width; x++)
				grid[y][x] = line.charAt(x - 1);
			grid[y][0] = border;
			grid[y][width + 1] = border;
		}
		for (int x = 0; x < width + 2; x++) {
			grid[0][x] = border;
			grid[height + 1][x] = border;
		}
		return grid;
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
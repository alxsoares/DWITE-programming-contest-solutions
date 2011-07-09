// DWITE - January 2005 - Problem 3: Harshad Numbers

import dwite.*;


public final class dwite200501p3 extends Solution {
	
	public static void main(String[] args) {
		Runner.run("DATA31.txt", "OUT31.txt", new dwite200501p3());
	}
	
	
	protected void runOnce(Io io) {
		// Read input
		int start = io.readIntLine();
		int end = io.readIntLine();
		
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
		io.println(longestrun);
	}
	
	
	private static int getDigitSum(int n) {
		int sum = 0;
		while (n != 0) {
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}
	
}

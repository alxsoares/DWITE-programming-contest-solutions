// DWITE - December 2004 - Problem 4: Waring's Prime Number Conjecture

import dwite.*;


public final class dwite200412p4 extends Solution {
	
	public static void main(String[] args) {
		Runner.run("DATA41.txt", "OUT41.txt", new dwite200412p4());
	}
	
	
	private static boolean[] isPrime;  // [0] = false, [1] = false, [2] = true, [3] = true, [4] = false, [5] = true, ...
	
	private static int[] primes;  // 2, 3, 5, 7, 11, 13, 17, 19, ...
	private static int primesLength;
	
	
	static {
		isPrime = sievePrimes(99999);
		primes = new int[isPrime.length];
		primesLength = 0;
		for (int i = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				primes[primesLength] = i;
				primesLength++;
			}
		}
	}
	
	
	protected void runOnce(Io io) {
		int n = io.readIntLine();
		if (isPrime[n])
			io.println("PRIME");
		else
			// out.println(countSums(n, 3, 0));
			// out.println(countSumsSemifast(n, 3, 0, 0));
			io.println(countSumsFast(n));
	}
	
	
	// Returns the number of unordered sums that add up to 'sum' with exactly 'terms' prime terms, each of which is at least 'minimum'.
	@SuppressWarnings("unused")
	private static int countSums(int sum, int terms, int minimum) {
		if (terms == 1) {
			if (isPrime[sum] && sum >= minimum)
				return 1;
			else
				return 0;
		} else {
			int count = 0;
			for (int i = minimum; i <= sum; i++) {
				if (isPrime[i])
					count += countSums(sum - i, terms - 1, i);
			}
			return count;
		}
	}
	
	
	// Assumes that primes[minimumIndex] >= minimum.
	@SuppressWarnings("unused")
	private static int countSumsSemifast(int sum, int terms, int minimum, int minimumIndex) {
		if (terms == 1) {
			if (isPrime[sum] && sum >= minimum)
				return 1;
			else
				return 0;
		} else {
			int count = 0;
			for (int i = minimumIndex, end = sum / terms; i < primesLength && primes[i] <= end; i++)
				count += countSumsSemifast(sum - primes[i], terms - 1, primes[i], i);
			return count;
		}
	}
	
	
	// Hard-coded for 3-term sums.
	private static int countSumsFast(int sum) {
		int count = 0;
		for (int i = 0, iend = sum / 3; i < primesLength && primes[i] <= iend; i++) {
			int temp = sum - primes[i];
			for (int j = i, jend = temp / 2; j < primesLength && primes[j] <= jend; j++) {
				// temp-primes[j] >= primes[j] because of jend
				if (isPrime[temp - primes[j]])
					count++;
			}
		}
		return count;
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
	
}
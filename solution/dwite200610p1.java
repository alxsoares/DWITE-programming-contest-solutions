// DWITE - October 2006 - Problem 1: Pete's Printing Press

import dwite.*;

import java.util.HashMap;
import java.util.Map;


public final class dwite200610p1 extends Solution {
	
	public static void main(String[] args) {
		Runner.run("DATA11.txt", "OUT11.txt", new dwite200610p1());
	}
	
	
	private static final Map<PrintingConfiguration,Double> unitCostByConfig;
	
	static {
		unitCostByConfig = new HashMap<PrintingConfiguration,Double>();
		
		unitCostByConfig.put(new PrintingConfiguration(    1, "8.5\"x11\"", "B&W"), 0.08);
		unitCostByConfig.put(new PrintingConfiguration(  100, "8.5\"x11\"", "B&W"), 0.06);
		unitCostByConfig.put(new PrintingConfiguration(  500, "8.5\"x11\"", "B&W"), 0.05);
		unitCostByConfig.put(new PrintingConfiguration( 1000, "8.5\"x11\"", "B&W"), 0.04);
		unitCostByConfig.put(new PrintingConfiguration(10000, "8.5\"x11\"", "B&W"), 0.03);
		
		unitCostByConfig.put(new PrintingConfiguration(    1, "8.5\"x14\"", "B&W"), 0.09);
		unitCostByConfig.put(new PrintingConfiguration(  100, "8.5\"x14\"", "B&W"), 0.07);
		unitCostByConfig.put(new PrintingConfiguration(  500, "8.5\"x14\"", "B&W"), 0.06);
		unitCostByConfig.put(new PrintingConfiguration( 1000, "8.5\"x14\"", "B&W"), 0.05);
		unitCostByConfig.put(new PrintingConfiguration(10000, "8.5\"x14\"", "B&W"), 0.03);
		
		unitCostByConfig.put(new PrintingConfiguration(    1, "11\"x17\"", "B&W"), 0.15);
		unitCostByConfig.put(new PrintingConfiguration(  100, "11\"x17\"", "B&W"), 0.12);
		unitCostByConfig.put(new PrintingConfiguration(  500, "11\"x17\"", "B&W"), 0.10);
		unitCostByConfig.put(new PrintingConfiguration( 1000, "11\"x17\"", "B&W"), 0.08);
		unitCostByConfig.put(new PrintingConfiguration(10000, "11\"x17\"", "B&W"), 0.05);
		
		unitCostByConfig.put(new PrintingConfiguration(    1, "8.5\"x11\"", "COLOUR"), 0.75);
		unitCostByConfig.put(new PrintingConfiguration(  100, "8.5\"x11\"", "COLOUR"), 0.65);
		unitCostByConfig.put(new PrintingConfiguration(  500, "8.5\"x11\"", "COLOUR"), 0.55);
		unitCostByConfig.put(new PrintingConfiguration( 1000, "8.5\"x11\"", "COLOUR"), 0.45);
		unitCostByConfig.put(new PrintingConfiguration(10000, "8.5\"x11\"", "COLOUR"), 0.30);
		
		unitCostByConfig.put(new PrintingConfiguration(    1, "8.5\"x14\"", "COLOUR"), 0.90);
		unitCostByConfig.put(new PrintingConfiguration(  100, "8.5\"x14\"", "COLOUR"), 0.85);
		unitCostByConfig.put(new PrintingConfiguration(  500, "8.5\"x14\"", "COLOUR"), 0.65);
		unitCostByConfig.put(new PrintingConfiguration( 1000, "8.5\"x14\"", "COLOUR"), 0.50);
		unitCostByConfig.put(new PrintingConfiguration(10000, "8.5\"x14\"", "COLOUR"), 0.30);
		
		unitCostByConfig.put(new PrintingConfiguration(    1, "11\"x17\"", "COLOUR"), 1.40);
		unitCostByConfig.put(new PrintingConfiguration(  100, "11\"x17\"", "COLOUR"), 1.20);
		unitCostByConfig.put(new PrintingConfiguration(  500, "11\"x17\"", "COLOUR"), 1.10);
		unitCostByConfig.put(new PrintingConfiguration( 1000, "11\"x17\"", "COLOUR"), 0.90);
		unitCostByConfig.put(new PrintingConfiguration(10000, "11\"x17\"", "COLOUR"), 0.60);
	}
	
	
	protected void runOnce(Io io) {
		// Read input
		int n = io.readIntLine();
		String papersize = io.readLine();
		String colour = io.readLine();
		
		// Compute and write output
		PrintingConfiguration config = new PrintingConfiguration(n, papersize, colour);
		double cost = n * unitCostByConfig.get(config);
		io.printf("$%.2f%n", cost);
	}
	
	
	
	private static class PrintingConfiguration {
		
		/*
		 * 0 for [1, 99]
		 * 1 for [100, 499]
		 * 2 for [500, 999]
		 * 3 for [1000, 9999]
		 * 4 for [10000, infinity)
		 */
		private int quantityClass;
		
		/*
		 * 0 for 8.5 in. x 11 in.
		 * 1 for 8.5 in. x 14 in.
		 * 2 for 11 in. x 17 in.
		 */
		private int paperClass;
		
		/*
		 * true for colour copies
		 * false for black and white copies
		 */
		private boolean colour;
		
		
		
		public PrintingConfiguration(int quantity, String paperSize, String colour) {
			if      (quantity <     1) throw new AssertionError("Invalid quantity");
			else if (quantity <   100) quantityClass = 0;
			else if (quantity <   500) quantityClass = 1;
			else if (quantity <  1000) quantityClass = 2;
			else if (quantity < 10000) quantityClass = 3;
			else                       quantityClass = 4;
			
			if      (paperSize.equals("8.5\"x11\"")) paperClass = 0;
			else if (paperSize.equals("8.5\"x14\"")) paperClass = 1;
			else if (paperSize.equals("11\"x17\"" )) paperClass = 2;
			else throw new AssertionError("Invalid paper size");
			
			if      (colour.equals("B&W"   )) this.colour = false;
			else if (colour.equals("COLOUR")) this.colour = true;
			else throw new AssertionError("Invalid colour configuration");
		}
		
		
		
		public boolean equals(Object other) {
			if (this == other)
				return true;
			else if (!(other instanceof PrintingConfiguration))
				return false;
			else {
				PrintingConfiguration pc = (PrintingConfiguration)other;
				return quantityClass == pc.quantityClass
				    && paperClass    == pc.paperClass
				    && colour        == pc.colour;
			}
		}
		
		
		public int hashCode() {
			return (quantityClass << 3) ^ (paperClass << 1) ^ (colour ? 1 : 0);
		}
		
		
		public String toString() {
			return String.format("Printing configuration (%d, %d, %b)", quantityClass, paperClass, colour);
		}
		
	}
	
}

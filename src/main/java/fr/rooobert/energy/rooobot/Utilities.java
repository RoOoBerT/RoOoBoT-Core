package fr.rooobert.energy.rooobot;

/** Utility class with useful methods */
public class Utilities {
	// --- Methodes
	/** @param str String
	 * @param defaultValue Default value
	 * @return Integer value of <code>str</code> or <code>defaultValue</code> if 
	 * it does not represent a number */
	public static int getInt(String str, int defaultValue) {
		return getInt(str, defaultValue, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/** @param str String
	 * @param defaultValue Default value
	 * @param minimum
	 * @param maximum
	 * @return Integer value of <code>str</code> or <code>defaultValue</code> if 
	 * it does not represent a number or outside the min-max range */
	public static int getInt(String str, int defaultValue, int minimum, int maximum) {
		int l = defaultValue;
		if (str != null) {
			l = Integer.parseInt(str);
		}
		if (l < minimum || l > maximum) {
			l = defaultValue;
		}
		return l;
	}
	
	/** @param str String
	 * @param defaultValue Default value
	 * @return Long value of <code>str</code> or <code>defaultValue</code> if 
	 * it does not represent a number */
	public static long getLong(String str, long defaultValue) {
		long l = defaultValue;
		if (str != null) {
			l = Long.parseLong(str);
		}
		return l;
	}
}

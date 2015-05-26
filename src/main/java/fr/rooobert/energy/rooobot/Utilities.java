package fr.rooobert.energy.rooobot;

/** Utility class with useful methods */
public class Utilities {
	// --- Methodes
	/** @param str String
	 * @param defaultValue Default value
	 * @return Integer value of <code>str</code> or <code>defaultValue</code> if 
	 * it does not represent a number */
	public static int getInt(String str, int defaultValue) {
		int l = defaultValue;
		if (str != null) {
			l = Integer.parseInt(str);
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

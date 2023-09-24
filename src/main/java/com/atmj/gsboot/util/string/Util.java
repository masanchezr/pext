package com.atmj.gsboot.util.string;

/**
 * The Class Util.
 */
public class Util {

	private Util() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Checks if is empty.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String string) {
		boolean isEmpty = false;
		if (string == null || string.isEmpty() || string.trim().isEmpty()) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public static boolean isNumeric(String str) {
		return (str.matches("[+-]?\\d*(\\.\\d+)?") && !str.equals(""));
	}
}

package com.scg.util;

public class NullObjectCompare {
	
	private static final int POSITIVE = 1;
	private static final int ZERO = 0;
	private static final int NEGATIVE = -1;

	/**
	 * @param object1
	 * @param object2
	 */
	public static  	int  nullObjectsComparator(Object object1, Object object2) {
		
		if ((object1 == null) && (object2 == null ) ) return ZERO;
		else if ((object1 == null) && (object2 != null)) return NEGATIVE;
		//((object1 != null ) && (object2 == null ) )
		else return POSITIVE;
		
	}
	
	
	
	/**
	 * helper method that checks if any of the two strings is null
	 * and returns the right value accordingly
	 * @param first
	 * @param second
	 * @return > 0 , 0, < 0
	 */
	public static  int stringCompareHelper(String first, String second){
		if (first == null && second == null) {
			return 0;
		} else if (first == null) {
			return -1;
			
		} else if (second == null) {
			return 1;
		} else {
			return first.compareTo(second);
		}
	}

}
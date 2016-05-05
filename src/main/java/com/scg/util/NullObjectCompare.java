package com.scg.util;

class NullObjectCompare {
	
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

}

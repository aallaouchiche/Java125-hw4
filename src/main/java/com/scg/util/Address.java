package com.scg.util;
import static com.scg.util.NullObjectCompare.*;

/**
 * Encapsulates an address in the United States.
 * 
 * @author jack Modified by Amine Allaouchiche
 */
public class Address implements Comparable<Address> {
	private final String streetNumber;
	private final String city;
	private final StateCode state;
	private final String postalCode;

	/**
	 * Constructs an instance of an address.
	 * 
	 * @param streetNumber
	 *            Street portion of address.
	 * @param city
	 *            City portion of address.
	 * @param state
	 *            State portion of address.
	 * @param postalCode
	 *            Postal code portion of address.
	 */
	public Address(String streetNumber, String city, StateCode state, String postalCode) {
		this.streetNumber = streetNumber;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	/**
	 * Gets the street portion of the address.
	 * 
	 * @return The street portion of the address;
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * Gets the city portion of the address.
	 * 
	 * @return The city portion of the address;
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Gets the state portion of the address.
	 * 
	 * @return The state portion of the address;
	 */
	public StateCode getState() {
		return state;
	}

	/**
	 * Gets the postal code portion of the address.
	 * 
	 * @return The postal code portion of the address;
	 */
	public String getPostalCode() {
		return postalCode;
	}

	@Override
	/**
	 * Indicates whether this Address is equal to another Object. The two
	 * objects are equal if <i>obj</i> is type Address, and their address fields
	 * are equal.
	 */
	public boolean equals(Object obj) {
		boolean rcode = false;
		if (obj instanceof Address) {
			Address other = (Address) obj;
			rcode = state == other.state && areEqual(streetNumber, other.streetNumber) && areEqual(city, other.city)
					&& areEqual(postalCode, other.postalCode);
		}

		return rcode;
	}

	@Override
	/**
	 * Returns a hash code for this object. Overriding this method is necessary
	 * because we override <i>equals</i>.
	 * 
	 * @return hash code
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + state.hashCode();
		result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
		return result;
	}

	@Override
	/**
	 * Returns a readable string representing this object.
	 * 
	 * @return A readable string representing this object.
	 */
	public String toString() {
		StringBuilder bldr = new StringBuilder(streetNumber);
		bldr.append("\n").append(city).append(", ").append(state);
		bldr.append(" ").append(postalCode);

		return bldr.toString();
	}

	private boolean areEqual(String str1, String str2) {
		boolean rcode = str1 == str2;
		if (!rcode && str1 != null)
			rcode = str1.equals(str2);
		return rcode;
	}
	//
	// private String echo( String str )
	// {
	// String result = str != null ? str: null;
	// return result;
	// }

	/**
	 * Compares this Address to another for magnitude. The order of comparison is: 
	 * <ol> 
	 * <li>state </li>
		<li>city</li>
		<li>postalCode</li>
		<li>streetAddress</li>
		</ol> 
		If one property is null and the other is not, the null property is deemed to be less than the other.
		 The state property is tested by converting the two state values to strings and then 
		 comparing the strings
		 @param that  The other Address to compare against
		 @return <0 if this object is less than the other; 0 if the two objects are equal; and >0 
		 if this object is greater than the other.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Address that) {

		//		if (stringCompareHelper(this.getState().toString(),(that.getState().toString())) == 0  
		//				&& stringCompareHelper(this.getCity(),that.getCity()) == 0
		//			&&  stringCompareHelper (this.getPostalCode(),that.getPostalCode()) ==0) {
		//				return stringCompareHelper (this.streetNumber,that.streetNumber);
		//			} else if (stringCompareHelper(this.getState().toString(),(that.getState().toString())) == 0  
		//					&& stringCompareHelper(this.getCity(),that.getCity()) == 0) {
		//				return stringCompareHelper (this.getPostalCode(),that.getPostalCode());
		//			} else if (stringCompareHelper(this.getState().toString(),(that.getState().toString())) == 0 ) {
		//				return stringCompareHelper(this.getCity(),that.getCity());
		//			} else {
		//				return stringCompareHelper(this.getState().toString(),(that.getState().toString()));
		//			}
		//	}
		
		// if states are not equal, return the result of their comparison
		if (stringCompareHelper(this.getState().toString(),(that.getState().toString())) != 0)  
		{
			return stringCompareHelper (this.getState().toString(),that.getState().toString());
			// if states are equal, and cities are not, return the result of their comparison
		} else if (stringCompareHelper(this.getCity(),that.getCity()) != 0) {
			return stringCompareHelper (this.getCity(),that.getCity());
			// if states are and cities  equal, and postalcodes are not, return the result of their comparison
		} else if (stringCompareHelper(this.getPostalCode(),that.getPostalCode()) != 0 ) {
			return stringCompareHelper(this.getPostalCode(),that.getPostalCode());
		} else {
			// if states  and cities and postal codes areequal, return the result of  street numbers comparison
			return stringCompareHelper(this.streetNumber,that.getStreetNumber());
		}
	}



}
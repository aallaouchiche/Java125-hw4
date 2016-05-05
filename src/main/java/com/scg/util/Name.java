package com.scg.util;

import static com.scg.util.NullObjectCompare.*;

/**
 * Encapsulates the first, middle and last names of a person.
 * 
 * @author jack
 */
public class Name implements Comparable<Name>
{
    private String  firstName;
    private String  lastName;
    private String  middleName;
    
    /**
     * Default constructor; sets first, last and middle names to "";
     */
    public Name()
    {
        this( "", "", "" );
    }
    
    /**
     * Constructs a Name instance using last, first and middle names.
     * 
     * @param lastName      The first name of the instance.
     * @param firstName     The last name of the instance.
     * @param middleName    The middle name of the instance.
     */
    public Name( String lastName, String firstName, String middleName )
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    /**
     * Constructs a Name instance using last and first names; the middle
     * name defaults to the empty string ("").
     * 
     * @param lastName      The first name of the instance.
     * @param firstName     The last name of the instance.
     */
    public Name(String lastName, String firstName)
    {
        this( lastName, firstName, "" );
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the instance's first name.
     * 
     * @return first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the instance's first name.
     * 
     * @param firstName The name to use.
     * 
     */
    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    /**
     * Gets the instance's last name.
     * 
     * @return last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the instance's last name.
     * 
     * @param lastName The name to use.
     * 
     */
    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    /**
     * Gets the instance's middle name.
     * 
     * @return middle name
     */
    public String getMiddleName()
    {
        return middleName;
    }

    /**
     * Sets the instance's middle name.
     * 
     * @param middleName The name to use.
     * 
     */
    public void setMiddleName( String middleName )
    {
        this.middleName = middleName;
    }

    @Override
    /**
     * Obtains a hash code for this object. Overriding this method is 
     * required because we also override <i>equals.</i>
     * 
     * @return hash code
     */
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
        return result;
    }

    @Override
    /**
     * Indicates whether an object is equal to this one.
     * The objects are equal if the test object is the correct type,
     * and the content matches the content of this object (first,
     * middle and last names must be equal).
     * 
     * @param   obj The object to test for equality.
     * 
     * @return true if the test object is equal to this object.
     */
    public boolean equals( Object obj )
    {
        boolean rcode   = false;
        if ( obj != null && obj instanceof Name )
        {
            Name    other   = (Name)obj;
            rcode =
                areEqual( firstName, other.firstName )   &&
                areEqual( middleName, other.middleName ) &&
                areEqual( lastName, other.lastName );
        }
        return rcode;
    }
    
    @Override
    /**
     * Returns a readable string representing this object.
     * 
     * @return A readable string representing this object
     */
    public String toString()
    {
        String  str = lastName + ", " + firstName + " " + middleName;
        return str;
    }
    
    private boolean areEqual( String name1, String name2 )
    {
        boolean rcode;
        if ( name1 == name2 )
            rcode = true;
        else if ( name1 != null )
            rcode = name1.equals( name2 );
        else
            rcode = false;
        
        return rcode;
    }

	@Override
	public int compareTo(Name other) {
		
		int codeReturn = 0;
		codeReturn = NullObjectCompare.nullObjectsComparator(this, other);
		
		if (codeReturn != 0 ){
			// one of the params is null
			return codeReturn;
			// none of the names is null
			}else {
				
			// checking if any of the last names is null
				codeReturn = stringCompareHelper(this.lastName, other.lastName);
				if (codeReturn != 0){
					return codeReturn;					
				} else {
					
						codeReturn = stringCompareHelper(this.firstName, other.firstName);
						if (codeReturn != 0){
							return codeReturn;
						} else {
							codeReturn = stringCompareHelper(this.middleName, other.middleName);
							return codeReturn;
							
					}
				}
					
				}
			 
	}
	
	
	
	
	
	
}

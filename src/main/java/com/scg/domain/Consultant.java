package com.scg.domain;

import com.scg.util.Name;

/**
 * Encapsulates data associated with a consultant.
 * 
 * @author jack
 */
public class Consultant implements Comparable<Consultant>
{
    private final Name  name;
    
    /**
     * Constructs a Consultant object with the given name.
     * 
     * @param name The name of the consultant.
     */
    public Consultant( Name name )
    {
        this.name = name;
    }
    
    /**
     * Gets the consultant's name.
     * @return The consultant's name.
     */
    public Name getName()
    {
        return name;
    }
    
    @Override
    /**
     * Returns the contents of this object as an easily
     * readable string.
     * 
     * @return The contents of this object as a string.
     */
    public String toString()
    {
        String  str = name.getLastName() + ", " +
                      name.getFirstName() + " " + 
                      name.getMiddleName();
        return str;
    }

	@Override
	public int compareTo(Consultant other) {
		
		return this.getName().compareTo(other.getName());
	}
}

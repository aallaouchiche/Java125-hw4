package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.Name;
import static com.scg.util.NullObjectCompare.*;

/**
 * Encapsulates a billable account.
 * 
 * @author jack
 *
 */
public final class ClientAccount implements Account, Comparable<ClientAccount>
{
    private       Name      contact;
    private final String    name;
    private       Address   address;
    
    /**
     * Creates an account with the given account and contact names.
     * @param name      Account name
     * @param contact   Contact name
     * @param address   Account address
     */
    public ClientAccount( String name, Name contact, Address address )
    {
        this.name = name;
        this.contact = contact;
        this.address = address;
    }
    
    /**
     * Returns the name of this account.
     * 
     * @return The name of this account.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns true if this is a billable account.
     * 
     * @return true if this account is billable, false otherwise.
     */
    public boolean isBillable()
    {
        return true;
    }

    /**
     * Gets the account's contact name.
     * @return the contact name.
     */
    public Name getContact()
    {
        return contact;
    }

    /**
     * Sets the account's contact name.
     * @param contact The contact name.
     */
    public void setContact( Name contact )
    {
        this.contact = contact;
    }
    
    /**
     * Gets the address for this account.
     * @return The address for this account.
     */
    public Address getAddress()
    {
        return address;
    }
    
    /**
     * Sets the address for this account.
     * @param address The new address for this account.
     */
    public void setAddress( Address address )
    {
        this.address = address;
    }

	@Override
	public int compareTo(ClientAccount other) {
		if (other == null ) {
			return 1;
		} else {
			// if both contact names and addresses are equal, then the result is the comparison of the names
			if (this.contact.compareTo(other.contact) == 0 && (this.address.compareTo(other.address) == 0)){
				return stringCompareHelper(this.name, other.name);
				// if the addresses are different, then 
			} else if (this.contact.compareTo(other.contact) == 0){
				return this.address.compareTo(other.address);
				// the contact names are different, then it has more weight on the comparison
				// return the result of the comparison
			} else {
				return this.contact.compareTo(other.contact);
			}
		}
		
	}
}

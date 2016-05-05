package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.Name;

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
	public int compareTo(ClientAccount o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

package com.scg.domain;

/**
 * Interface all accounts must implement.
 * @author jack
 *
 */
public interface Account
{
    /**
     * Gets the name of this account.
     * @return Account name
     */
    public String getName();
    
    /**
     * Determines if this account is billable.
     * @return true if the account is billable.
     */
    public boolean isBillable();
}

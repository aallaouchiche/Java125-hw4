package com.scg.domain;

/**
 * Encapsulates designations for non-billable account activity. 
 * @author jack
 */
public enum NonBillableAccount implements Account
{
    /**
     * Designates business development activity.
     */
    BUSINESS_DEVELOPMENT,
    
    /**
     * Designates sick leave.
     */
    SICK_LEAVE,
    
    /**
     * Designates vacation time.
     */
    VACATION;
    
    /**
     * Gets the name of this constant as a string.
     * @return The name of this constant as a string.
     */
    public String getName()
    {
        String  name    = toString();
        return name;
    }
    
    /**
     * Indicates whether this account is billable.
     * @return true if the account is billable, false otherwise.
     */
    public boolean isBillable()
    {
        return false;
    }
    
    /**
     * Gets an array containing the enum values in this class in the order 
     * they were declared.
     * 
     * @return Array of enum values in this class.
     */
    public static NonBillableAccount[] getValues()
    {
        NonBillableAccount[]    values  =
        { BUSINESS_DEVELOPMENT, SICK_LEAVE, VACATION };
        
        return values;
    }

// These are already part of the Java enum API
//    public static NonBillableAccount valueOf( String name )
//    {
//        return VACATION;
//    }
    
//    @Override
//    public String toString()
//    {
//        String  str = this.name();
//        return str;
//    }
}

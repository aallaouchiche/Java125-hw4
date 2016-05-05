package com.scg.domain;

import java.util.Date;

/**
 * Encapsulates a consultant's time, skill and account data associated 
 * with a billable item.
 * 
 * @author jack
 *
 */
public class ConsultantTime
{
    private static final String HOURS_ERROR = 
        "ConsultantTime hours must be greater than 0";
    
    private final Skill     skillType;
    private       Account   account;
    private       Date      date;
    private       int       hours;
    
    /**
     * Constructs an instance of a ConsultantTime record.
     * The specified hours must be greater than 0.
     * 
     * @param date          Activity date.
     * @param account       Account to which the activity is assigned.
     * @param skillType     Denotes the skill type associated with the
     *                      activity.
     * @param hours         Number of hours associated with the activity;
     *                      must be &gt; 0.
     */
    public
    ConsultantTime( Date date, Account account, Skill skillType, int hours )
    {
        if ( hours <= 0 )
            throw new IllegalArgumentException( HOURS_ERROR );
        this.date = date;
        this.account = account;
        this.skillType = skillType;
        this.hours = hours;
    }

    /**
     * Gets the account associated with this record.
     * @return Associated account.
     */
    public Account getAccount()
    {
        return account;
    }

    /**
     * Sets the account associated with this record.
     * @param account The designated account.
     */
    public void setAccount( Account account )
    {
        this.account = account;
    }

    /**
     * Gets the date associated with this record.
     * @return Associated date.
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Sets the date associated with this record.
     * @param date Designated date.
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * Gets the hours associated with this record.
     * @return Associated hours.
     */
    public int getHours()
    {
        return hours;
    }

    /**
     * Sets the hours associated with this record.
     * Hours must be greater than 0.
     * @param hours Designated hours; must be &gt; 0.
     */
    public void setHours(int hours)
    {
        if ( hours <= 0 )
            throw new IllegalArgumentException( HOURS_ERROR );
        this.hours = hours;
    }

    /**
     * Gets the skill type associated with this record.
     * @return Associated skill type.
     */
    public Skill getSkillType()
    {
        return skillType;
    }
    
    /**
     * Determines whether the time is billable.
     * 
     * @return true if time is billable, false otherwise.
     */
    public boolean isBillable()
    {
        return account.isBillable();
    }
    
    @Override
    /**
     * Indicates whether an object is equal to this one.
     * The objects are equal if the test object is the correct type,
     * and the content matches the content of this object.
     * 
     * @param   obj The object to test for equality.
     * 
     * @return true if the test object is equal to this object.
     */
    public boolean equals( Object obj )
    {
        boolean result  = false;
        if ( obj instanceof ConsultantTime )
        {
            ConsultantTime  that        = (ConsultantTime)obj;
            String          thisAccount =
                account == null ? null : account.getName();
            String          thatAccount = 
                that.account == null ? null : that.account.getName();
            result = skillType == that.skillType && hours == that.hours;
            
            if ( result )
                if ( date == that.date )
                    ; // still true
                else if ( date == null )
                    result = false;
                else
                    result = date.equals( that.date );
            
            if ( result )
                if ( thisAccount == thatAccount )
                    ; // still true
                else if ( thisAccount == null )
                    result = false;
                else
                    result = thisAccount.equals( thatAccount );
        }
        
        return result;
    }
    
    @Override
    /**
     * Calculate a hash code for this object.
     * This is required because we overloaded "equals".
     * @return hash code
     */
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + hours;
        result = prime * result + skillType.hashCode();
        return result;
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
        StringBuilder   bldr    = new StringBuilder( account.getName() );
        bldr.append( " ").append( date ).append( " " ).append( skillType )
        .append( " " ) .append( hours );
        
        return bldr.toString();
    }
}

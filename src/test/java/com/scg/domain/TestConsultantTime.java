package com.scg.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class TestConsultantTime
{
    private final Date      date        =
        Calendar.getInstance().getTime();
    private final Account   account     =
        NonBillableAccount.BUSINESS_DEVELOPMENT;
    private final Skill     skillType   = Skill.PROJECT_MANAGER;
    private final int       hours       = 22;

    @Test
    public void testConstructor()
    {
        ConsultantTime  time    = 
            new ConsultantTime( date, account, skillType, hours );
        assertEquals( date, time.getDate() );
        assertEquals( account, time.getAccount() );
        assertEquals( skillType, time.getSkillType() );
        assertEquals( hours, time.getHours() );
    }
    
    @Test
    public void testConstructor2()
    {
        for ( int inx = 0 ; inx > -2 ; --inx )
        {
            try
            {
                ConsultantTime  time    = 
                    new ConsultantTime( date, account, skillType, inx );
                fail( "new ConsultantTime() failed to throw exception" );
            }
            catch ( IllegalArgumentException exc )
            {
            }
        }
    }
    
    @Test
    public void testAccessors1()
    {
        ConsultantTime  time        = 
            new ConsultantTime( date, account, skillType, hours );
        assertEquals( account, time.getAccount() );
        assertEquals( account.isBillable(), time.isBillable() );
        assertEquals( skillType, time.getSkillType() );
        assertEquals( hours, time.getHours() );
    }
    
    @Test
    public void testAccessors2()
    {
        ConsultantTime  time        = 
            new ConsultantTime( date, account, skillType, hours );
        Date            newDate         = new Date( date.getTime() + 5 );
        Account         newAccount      = NonBillableAccount.VACATION;
        //Skill           newSkillType    = Skill.SYSTEM_ARCHITECT;
        int             newHours        = hours + 5;
        
        time.setDate( newDate );
        time.setAccount( newAccount );
        time.setHours( newHours );
        
        assertNotEquals( date, time.getDate() );
        assertNotEquals( account, time.getAccount() );
        assertEquals( skillType, time.getSkillType() ); // !!!
        assertNotEquals( hours, time.getHours() );
        
        assertEquals( newDate, time.getDate() );
        assertEquals( newAccount, time.getAccount() );
        // assertEquals( skillType, time.getSkillType() );
        assertEquals( newHours, time.getHours() );
    }

    @Test
    public void testAccessors3()
    {
        ConsultantTime  time    = 
            new ConsultantTime( date, account, skillType, 5 );
        for ( int inx = 0 ; inx > -2 ; --inx )
        {
            try
            {
                time.setHours( inx );
                fail( "setHours() failed to throw exception" );
            }
            catch ( IllegalArgumentException exc )
            {
            }
        }
    }
    
    @Test
    public void testEquals()
    {
        Date            newDate         = new Date( date.getTime() + 5 );
        Account         newAccount      = NonBillableAccount.VACATION;
        Skill           newSkillType    = Skill.SYSTEM_ARCHITECT;
        int             newHours        = hours + 5;

        ConsultantTime  time        = 
            new ConsultantTime( date, account, skillType, hours );
        ConsultantTime  test        = 
            new ConsultantTime( date, account, skillType, hours );
        assertTrue( time.equals( test ) );
        assertEquals( time.hashCode(), test.hashCode() );
        
        test = new ConsultantTime( newDate, account, skillType, hours );
        Assert.assertFalse( time.equals( test ) );
        
        test = new ConsultantTime( date, newAccount, skillType, hours );
        Assert.assertFalse( time.equals( test ) );
        
        test = new ConsultantTime( date, account, newSkillType, hours );
        Assert.assertFalse( time.equals( test ) );
        
        test = new ConsultantTime( date, account, skillType, newHours );
        Assert.assertFalse( time.equals( test ) );
    }
    
    @Test
    public void testEquals2()
    {
        ConsultantTime  time        = 
            new ConsultantTime( date, account, skillType, hours );
        ConsultantTime  test        = 
            new ConsultantTime( date, account, skillType, hours );
        assertTrue( time.equals( test ) );
        assertEquals( time.hashCode(), test.hashCode() );
        
        time.setDate( null );
        assertFalse( time.equals( test ) );
        test.setDate( null );
        assertTrue( time.equals( test ) );
        assertEquals( time.hashCode(), test.hashCode() );
        
        time.setAccount( null );
        assertFalse( time.equals( test ) );
        test.setAccount( null );
        assertTrue( time.equals( test ) );
        assertEquals( time.hashCode(), test.hashCode() );
        
        assertFalse( time.equals( new Object() ) );
    }

    @Test
    public void testToString()
    {
        ConsultantTime  time        = 
            new ConsultantTime( date, account, skillType, hours );
        assertNotNull( time.toString() );
    }
}

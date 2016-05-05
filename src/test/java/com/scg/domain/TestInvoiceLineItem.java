package com.scg.domain;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.scg.util.Name;

public class TestInvoiceLineItem
{

    private final String        lastName    = "Smith";
    private final String        firstName   = "Anna";
    private final String        middleName  = "Vera";
    private final Name          name;
    private final Date          date;
    private final Consultant    consultant;
    private final Skill         skill;
    private final int           hours;
    
    public TestInvoiceLineItem()
    {
        name = new Name( lastName, firstName, middleName );
        consultant = new Consultant( name );
        date = Calendar.getInstance().getTime();
        skill = Skill.PROJECT_MANAGER;
        hours = 10;
    }
    
    @Test
    public void testConstructor()
    {
        InvoiceLineItem item    =
            new InvoiceLineItem( date, consultant, skill, hours );
        assertEquals( date, item.getDate() );
        assertEquals( consultant, item.getConsultant() );
        assertEquals( skill, item.getSkill() );
        assertEquals( hours, item.getHours() );
    }
    
    @Test
    public void testGetCharge()
    {
        testGetCharge( Skill.PROJECT_MANAGER, 250 );
        testGetCharge( Skill.SYSTEM_ARCHITECT, 200 );
        testGetCharge( Skill.SOFTWARE_ENGINEER, 150 );
        testGetCharge( Skill.SOFTWARE_TESTER, 100 );
    }

    private void testGetCharge( Skill testSkill, int rate )
    {
        InvoiceLineItem item        =
            new InvoiceLineItem( date, consultant, testSkill, hours );
        int             expCharge   = hours * rate;
        int             actCharge   = item.getCharge();
        assertEquals( expCharge, actCharge );
        System.out.println( item );
    }
}

package com.scg.domain;

import static com.scg.domain.NonBillableAccount.BUSINESS_DEVELOPMENT;
import static com.scg.domain.NonBillableAccount.SICK_LEAVE;
import static com.scg.domain.NonBillableAccount.VACATION;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.scg.domain.NonBillableAccount;

public class TestNonBillableAccount
{
    @Test
    public void testValues()
    {
        String[]    strings = 
            { "BUSINESS_DEVELOPMENT", "SICK_LEAVE", "VACATION" };
        try
        {
            for ( String str : strings )
            {
                NonBillableAccount  account =
                    NonBillableAccount.valueOf( str );
                System.out.println( account );
            }
        }
        catch ( IllegalArgumentException exc )
        {
            fail( "Couldn't find existing enum value" );
        }
    }

    @Test
    public void testGetValues()
    {
        NonBillableAccount[]    expected    =
            { BUSINESS_DEVELOPMENT, SICK_LEAVE, VACATION };
        NonBillableAccount[]    actual      =
            NonBillableAccount.getValues();
        assertArrayEquals( expected, actual );
    }
    
    @Test
    public void testAccessors()
    {
        NonBillableAccount  account     = SICK_LEAVE;
        String              expected    = account.getName();
        assertEquals( expected, account.getName() );
        assertFalse( account.isBillable() );
    }
}

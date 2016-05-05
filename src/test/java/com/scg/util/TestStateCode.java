package com.scg.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestStateCode
{

    @Test
    public void testValues()
    {
        StateCode[] allCodes    = StateCode.values();
        assertEquals( StateCode.CA, allCodes[0] );
        assertEquals( StateCode.WA, allCodes[1] );
    }

    @Test
    public void testValueOf()
    {
        StateCode   testCA  = StateCode.valueOf( "CA" );
        StateCode   testWA  = StateCode.valueOf( "WA" );
        
        assertEquals( StateCode.CA, testCA );
        assertEquals( StateCode.WA, testWA );
    }
}

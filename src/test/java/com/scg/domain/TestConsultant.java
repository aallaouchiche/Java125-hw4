package com.scg.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.scg.util.Name;

public class TestConsultant
{

    @Test
    public void test()
    {
        String  first   = "Peter";
        String  last    = "Jones";
        String  middle  = "Roger";
        
        Name        name        = new Name( last, first, middle );
        Consultant  consultant  = new Consultant( name );
        assertTrue( name.equals( consultant.getName() ) );
        
        String      exp = last + ", " + first + " " + middle;
        assertEquals( exp, name.toString() );
        assertEquals( exp, consultant.toString() );
    }

}

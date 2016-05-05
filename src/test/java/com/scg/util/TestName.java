package com.scg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestName
{
    @Test
    public void test3ArgConstructor()
    {
        String  first   = "Peter";
        String  last    = "Jones";
        String  middle  = "Roger";
        
        Name    name    = new Name( last, first, middle );
        assertTrue( testEqual( last, name.getLastName() ) );
        assertTrue( testEqual( first, name.getFirstName() ) );
        assertTrue( testEqual( middle, name.getMiddleName() ) );
    }
    
    @Test
    public void testDefaultConstructor()
    {
        Name    name    = new Name();
        assertTrue( testEqual( "", name.getLastName() ) );
        assertTrue( testEqual( "", name.getFirstName() ) );
        assertTrue( testEqual( "", name.getMiddleName() ) );
    }

    @Test
    public void test2ArgConstructor()
    {
        String  first   = "Peter";
        String  last    = "Jones";
        String  middle  = "";
        
        Name    name    = new Name( last, first );
        assertTrue( testEqual( last, name.getLastName() ) );
        assertTrue( testEqual( first, name.getFirstName() ) );
        assertTrue( testEqual( middle, name.getMiddleName() ) );
    }
    
    @Test
    public void testToString()
    {
        String  first   = "Peter";
        String  last    = "Jones";
        String  middle  = "Roger";
        
        Name    name    = new Name( last, first, middle );
        String  test    = last + ", " + first + " " + middle;
        assertTrue( testEqual( test, name.toString() ) );
        System.out.println( name );
    }

    @Test
    public void testAccessors()
    {
        String  first   = "Peter";
        String  last    = "Jones";
        String  middle  = "Roger";
        
        String  first2  = "Peter";
        String  last2   = "Jones";
        String  middle2 = "Roger";
        
        Name    name    = new Name( last, first, middle );
        name.setFirstName( first2 );
        name.setLastName( last2 );
        name.setMiddleName( middle2 );
        
        assertTrue( testEqual( first2, name.getFirstName() ) );
        assertTrue( testEqual( last2, name.getLastName() ) );
        assertTrue( testEqual( middle2, name.getMiddleName() ) );
    }
    
    @Test
    public void testEquals()
    {
        String  first   = "Peter";
        String  last    = "Jones";
        String  middle  = "Roger";
        
        String  first2  = getNewString( first );
        String  last2   = getNewString( last );
        String  middle2 = getNewString( middle );
        
        String  test    = "wrong";
        
        Name    name    = new Name( last, first, middle );
        Name    dupe    = new Name( last2, first2, middle2 );
        
        assertTrue( name.equals( dupe ) );
        assertFalse( name.equals( new Name( test, first, middle ) ) );
        assertFalse( name.equals( new Name( last, test, middle ) ) );
        assertFalse( name.equals( new Name( last, first, test ) ) );
        assertFalse( name.equals( "wrong" ) );
        assertFalse( name.equals( null ) );
        
        dupe.setMiddleName( null );
        assertFalse( name.equals( dupe ) );
        
        dupe.setLastName( null );
        assertFalse( name.equals( dupe ) );
        
        dupe.setFirstName( null );
        assertFalse( name.equals( dupe ) );
    }
    
    @Test
    public void testHashCode()
    {
        String  first   = "Peter";
        String  last    = "Jones";
        String  middle  = "Roger";
        
        String  first2  = getNewString( first );
        String  last2   = getNewString( last );
        String  middle2 = getNewString( middle );
        
        String  test    = "wrong";
        
        Name    name    = new Name( last, first, middle );
        Name    dupe    = new Name( last2, first2, middle2 );        
        assertTrue( name.equals( dupe ) );
        assertEquals( (long)name.hashCode(), (long)dupe.hashCode() );
        
        Name    wrong   = new Name( test, first, middle );
        assertFalse( (long)name.hashCode() == (long)wrong.hashCode() );
        
        wrong = new Name( last, test, middle );
        assertFalse( (long)name.hashCode() == (long)wrong.hashCode() );
        
        wrong = new Name( last, first, test );
        assertFalse( (long)name.hashCode() == (long)wrong.hashCode() );
        
        // Play with null for code coverage
        dupe.setMiddleName( null );
        assertFalse( name.equals( dupe ) );
        assertFalse( dupe.equals( name ) );
        name.setMiddleName( null );
        assertEquals( (long)name.hashCode(), (long)dupe.hashCode() );
        assertEquals( name, dupe );
        
        dupe.setFirstName( null );
        assertFalse( name.equals( dupe ) );
        assertFalse( dupe.equals( name ) );
        name.setFirstName( null );
        assertEquals( (long)name.hashCode(), (long)dupe.hashCode() );
        assertEquals( name, dupe );
        
        dupe.setLastName( null );
        assertFalse( name.equals( dupe ) );
        assertFalse( dupe.equals( name ) );
        name.setLastName( null );
        assertEquals( (long)name.hashCode(), (long)dupe.hashCode() );
        assertEquals( name, dupe );
    }
    
    /*
     * Create a duplicate String object, guaranteeing that the new object
     * reference is different from the original object reference.
     */
    private String getNewString( String from )
    {
        char[]  cArr    = from.toCharArray();
        String  dupe    = new String( cArr );
        assertTrue( from != dupe );
        
        return dupe;
    }

    private boolean testEqual( String exp, String act )
    {
        boolean test    = exp.equals( act );
        if ( !test )
        {
            System.err.print( "String compare failed; " );
            System.err.println( "expected: " + exp + " actual: " + act );
        }
        
        return test;
    }
}

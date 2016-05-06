package com.scg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestAddress
{
    private final String    street      = "118 Washington Ave.";
    private final String    city        = "South Bend";
    private final StateCode state       = StateCode.CA;
    private final String    postal      = "98111";
    
    private final StateCode dummySC     = StateCode.WA;

    @Test
    public void testConstructor()
    {
        Address addr    = new Address( street, city, state, postal );
        assertEquals( street, addr.getStreetNumber() );
        assertEquals( city, addr.getCity() );
        assertEquals( state, addr.getState() );
        assertEquals( postal, addr.getPostalCode() );
    }
    
    @Test
    public void testEquals()
    {
        Address addr    = new Address( street, city, state, postal );
        Address addrEQ  = new Address( street, city, state, postal );
        Address addrNE1 = new Address( "dummy", city, state, postal );
        Address addrNE2 = new Address( street, "dummy", state, postal );
        Address addrNE3 = new Address( street, city, dummySC, postal );
        Address addrNE4 = new Address( street, city, state, "dummy" );
        Object  addrNE5 = new Object();
        Address addrNE6 = null; 
        
        assertEquals( addr, addr );
        assertEquals( addr.hashCode(), addrEQ.hashCode() );
        assertEquals( addr, addrEQ );
        assertFalse( addr.equals( addrNE1 ) );
        assertFalse( addr.equals( addrNE2 ) );
        assertFalse( addr.equals( addrNE3 ) );
        assertFalse( addr.equals( addrNE4 ) );
        assertFalse( addr.equals( addrNE5 ) );
        assertFalse( addr.equals( addrNE6 ) );
    }
    
    @Test
    public void testToString()
    {
        StringBuilder   exp = new StringBuilder( street );
        exp.append( "\n" ).append( city ).append( ", " );
        exp.append( state ).append( " " ).append( postal );
        
        Address addr    = new Address( street, city, state, postal );
        System.out.println( exp );
        System.out.println( addr.toString() );
        assertEquals( exp.toString(), addr.toString() );
    }
    
    @Test
    public void testHashCode()
    {
        Address addr1   = new Address( street, city, state, postal );
        Address addr2   = new Address( street, city, state, postal );
        long    hash1   = addr1.hashCode();
        long    hash2   = addr1.hashCode();
        assertTrue( addr1.equals( addr2 ) );
        assertEquals( hash1, hash2 );
        
        addr2 = new Address( null, city, state, postal );
        assertFalse( addr1.equals( addr2 ) );
        addr1 = new Address( null, city, state, postal );
        hash1 = addr1.hashCode();
        hash2 = addr2.hashCode();
        assertTrue( addr1.equals( addr2 ) );
        assertEquals( hash1, hash2 );
        
        addr2 = new Address( null, null, state, postal );
        assertFalse( addr1.equals( addr2 ) );
        addr1 = new Address( null, null, state, postal );
        hash1 = addr1.hashCode();
        hash2 = addr2.hashCode();
        assertTrue( addr1.equals( addr2 ) );
        assertEquals( hash1, hash2 );
        
        addr2 = new Address( null, null, state, null );
        assertFalse( addr1.equals( addr2 ) );
        addr1 = new Address( null, null, state, null );
        hash1 = addr1.hashCode();
        hash2 = addr2.hashCode();
        assertTrue( addr1.equals( addr2 ) );
        assertEquals( hash1, hash2 );
    }
    
    @Test
    public void testCompareTo()
    {
        Address addr1   = new Address( street, city, state, postal );
        Address addr2   = new Address( street, city, state, postal );
        long    hash1   = addr1.hashCode();
        long    hash2   = addr1.hashCode();
        assertTrue( addr1.compareTo( addr2 ) == 0);
       
        
        addr2 = new Address( null, city, state, postal );
        assertTrue( addr1.compareTo( addr2 ) > 0 );
//        addr1 = new Address( null, city, state, postal );
//        hash1 = addr1.hashCode();
//        hash2 = addr2.hashCode();
//        assertTrue( addr1.equals( addr2 ) );
//        assertEquals( hash1, hash2 );
//        
//        addr2 = new Address( null, null, state, postal );
//        assertFalse( addr1.equals( addr2 ) );
//        addr1 = new Address( null, null, state, postal );
//        hash1 = addr1.hashCode();
//        hash2 = addr2.hashCode();
//        assertTrue( addr1.equals( addr2 ) );
//        assertEquals( hash1, hash2 );
//        
//        addr2 = new Address( null, null, state, null );
//        assertFalse( addr1.equals( addr2 ) );
//        addr1 = new Address( null, null, state, null );
//        hash1 = addr1.hashCode();
//        hash2 = addr2.hashCode();
//        assertTrue( addr1.equals( addr2 ) );
//        assertEquals( hash1, hash2 );
    }
}

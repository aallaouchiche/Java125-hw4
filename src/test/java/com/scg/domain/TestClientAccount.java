package com.scg.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class TestClientAccount
{
    @Test
    public void testConstructor()
    {
        String          name    = "Martha";
        Name            contact = new Name( "washington", "george", "q." );
        Address         addr    = 
            new Address( "street", "city", StateCode.CA, "98111" );
        ClientAccount   account = new ClientAccount( name, contact, addr );
        
        assertEquals( name, account.getName() );
        assertEquals( addr, account.getAddress() );
        assertTrue( contact.equals( account.getContact() ) );
        assertEquals( addr, account.getAddress() );
        assertTrue( account.isBillable() );
    }

    @Test
    public void testAccessors()
    {
        String          name        = "Martha";
        Name            contact     = new Name( "washington", "george", "q." );
        Name            contact2    = new Name( "smit", "john", "q." );
        Address         addr1      = 
            new Address( "street", "city", StateCode.CA, "98111" );
        Address         addr2       = 
            new Address( "street", "city", StateCode.WA, "98111" );
        ClientAccount   account = new ClientAccount( name, contact, addr1 );
        
        assertTrue( contact.equals( account.getContact() ) );
        assertTrue( addr1.equals( account.getAddress() ) );
        
        account.setContact( contact2 );
        assertFalse( contact.equals( account.getContact() ) );
        assertTrue( contact2.equals( account.getContact() ) );
        
        account.setAddress( addr2 );
        assertFalse( addr1.equals( account.getAddress() ) );
        assertTrue( addr2.equals( account.getAddress() ) );
    }
}

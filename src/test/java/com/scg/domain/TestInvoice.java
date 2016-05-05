package com.scg.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class TestInvoice
{
    private static final int[]  UNUSED_CAL_FIELDS   =
    {
        Calendar.DAY_OF_MONTH,
        Calendar.HOUR,
        Calendar.MINUTE,
        Calendar.SECOND,
        Calendar.MILLISECOND
    };
    
    private final Random            randy           = new Random( 0 );
    private final int               invoiceMonth    = 3;
    private final int               invoiceYear     = 2016;
    private final Calendar          goodDate;
    private final Calendar          badDatePast;
    private final Calendar          badDateFuture;
    private final Calendar          weekStartingDay;
    private final ClientAccount     goodClient;
    private final Account           badClient;
    private final Account           nonBillable;
    
    public TestInvoice()
    {
        goodDate = getGoodDate();
        badDatePast = getBadDatePast();
        badDateFuture = getBadDateFuture();
        goodClient = getGoodClient();
        badClient = getBadClient();
        weekStartingDay = getWeekStartingDay();
        nonBillable = NonBillableAccount.BUSINESS_DEVELOPMENT;
    }
    
    @Test
    public void test()
    {
        testToString();
        testItems( 3, Skill.PROJECT_MANAGER, "Banks" );
        testItems( 1, Skill.SOFTWARE_ENGINEER, "Blubber" );
        testItems( 0, Skill.SOFTWARE_TESTER, "Robber" );
        testItems( 5, Skill.PROJECT_MANAGER, "Banks" );
        testItems( 7, Skill.SOFTWARE_ENGINEER, "Blubber" );
        testItems( 9, Skill.SOFTWARE_TESTER, "Robber" );
        testItems( 10, Skill.SOFTWARE_TESTER, "Robber" );
        testNoFile();
        testBadFile();
    }
   
    @Test
    public void testAccessors()
    {
        ClientAccount   account     = getGoodClient();
        int             month       = 6;
        int             year        = 2014;
        Invoice         invoice     = new Invoice( account, month, year );
        
        ClientAccount   actAccount  = invoice.getClientAccount();
        int             actMonth    = invoice.getInvoiceMonth();
        int             actYear     = invoice.getInvoiceYear();
        
        assertEquals( account, actAccount );
        assertEquals( month, actMonth );
        assertEquals( year, actYear );
    }
    
    private void testToString()
    {
        Invoice invoice = new Invoice( goodClient, invoiceMonth, invoiceYear );
        System.out.println( invoice );
    }
    
    private void testNoFile()
    {
        Invoice invoice = 
            new Invoice( goodClient, invoiceMonth, invoiceYear, "badfile.txt" );
    }
    
    private void testBadFile()
    {
        String      fileName    = "baddata";
        File        file        = new File( fileName );
        String      badVal      = "\\uggggname = value";
        
        try ( PrintWriter writer  = new PrintWriter( file ); )
        {            
            writer.println( badVal );
        }
        catch ( IOException exc )
        {
            fail( "IO Exception" );
        }
        
        if ( file.exists() )
        {
            Invoice invoice = 
                new Invoice( goodClient, invoiceMonth, invoiceYear, fileName );
            file.delete();
        }
    }
    
    private void testItems( 
        int    goodItems,
        Skill  skill,
        String contractor
    )
    {
        Name        contractorName  = new Name( contractor, "First", "MI" );
        Consultant  consultant      = new Consultant( contractorName );
        Date        startDate       = weekStartingDay.getTime();
        TimeCard    card            = new TimeCard( consultant, startDate );
        for ( int inx = 0 ; inx < goodItems ; ++inx )
        {
            // billable item
            Date            date    = goodDate.getTime();
            int             hours   = randy.nextInt( 8 ) + 1;
            ConsultantTime  time    =
                new ConsultantTime( date, goodClient, skill, hours );
            card.addConsultantTime( time );
            
            // non-billable item
            time = new ConsultantTime( date, nonBillable, skill, hours );
            card.addConsultantTime( time );
            
            // not the target client item
            time = new ConsultantTime( date, badClient, skill, hours );
            card.addConsultantTime( time );
            
            // line item from the past
            date = badDatePast.getTime();
            time = new ConsultantTime( date, goodClient, skill, hours );
            card.addConsultantTime( time );
            
            // line item from the future
            date = badDateFuture.getTime();
            time = new ConsultantTime( date, goodClient, skill, hours );
            card.addConsultantTime( time );
        }
        
        Invoice invoice = new Invoice( goodClient, invoiceMonth, invoiceYear );
        invoice.extractLineItems( card );
        System.out.println( invoice.toReportString() );
    }
    
    private Calendar getGoodDate()
    {
        Calendar    cal = Calendar.getInstance();

        cal.set( Calendar.YEAR, invoiceYear );
        cal.set( Calendar.MONTH, invoiceMonth );
        
        for ( int field : UNUSED_CAL_FIELDS )
            cal.set( field, cal.getMinimum( field ) );
        
        return cal;
    }
    
    private Calendar getWeekStartingDay()
    {
        Calendar    cal = Calendar.getInstance();

        cal.set( Calendar.YEAR, invoiceYear );
        cal.set( Calendar.MONTH, invoiceMonth );
        
        for ( int field : UNUSED_CAL_FIELDS )
            cal.set( field, cal.getMaximum( field ) );
        
        cal.add( Calendar.WEEK_OF_MONTH, 1 );
        
        return cal;
    }

    private Calendar getBadDatePast()
    {
        Calendar    cal = Calendar.getInstance();
        cal.set( Calendar.YEAR, goodDate.get( Calendar.YEAR ) );
        cal.set( Calendar.MONTH, goodDate.get( Calendar.MONTH ) );
        for ( int field : UNUSED_CAL_FIELDS )
            cal.set( field, cal.getMinimum( field ) );
        
        cal.add( Calendar.DAY_OF_MONTH, -1 );
        System.out.println( "***** past: " + cal.getTime() );
        return cal;
    }

    private Calendar getBadDateFuture()
    {
        Calendar    cal = Calendar.getInstance();
        cal.set( Calendar.YEAR, goodDate.get( Calendar.YEAR ) );
        cal.set( Calendar.MONTH, goodDate.get( Calendar.MONTH ) );
        for ( int field : UNUSED_CAL_FIELDS )
            cal.set( field, cal.getMaximum( field ) );
        
        System.out.println( "***** good: " + goodDate.getTime() );
        System.out.println( "***** future1: " + cal.getTime() );
        cal.add( Calendar.DAY_OF_MONTH, 1 );
        System.out.println( "***** future2: " + cal.getTime() );
        return cal;
    }
    
    private ClientAccount getGoodClient()
    {
        String  name            = "GoodWeather Galoshes";
        Name    contact         = new Name( "Twoshoes", "Goody", "Tony" );
        Address address         = new Address(
            "13 Friday Ave",
            "Good Times",
            StateCode.WA,
            "2222" 
        );
        ClientAccount   client  = new ClientAccount( name, contact, address );
        return client;
    }
    
    private ClientAccount getBadClient()
    {
        String  name            = "Bad Tunes Radio";
        Name    contact         = new Name( "Boy", "Bad", "Timmy" );
        Address address         = new Address(
            "Bad Street",
            "Tough City",
            StateCode.WA,
            "2222" 
        );
        ClientAccount   client  = new ClientAccount( name, contact, address );
        return client;
    }
}

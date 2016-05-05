package com.scg.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class TestTimeCard
{
    private final Date      weekStartingDay; 
    private final Skill     skillType           = Skill.PROJECT_MANAGER;
    
    private final Name          consultantName  =
        new Name( "ConLast", "ConFirst", "ConMiddle" );
    private final Consultant    consultant      = 
        new Consultant( consultantName );
    private final Name      contact1            =
        new Name( "last1", "first1", "middle1" );
    private final Name      contact2            =
        new Name( "last2", "first2", "middle2" );
    private final Name      contact3            =
        new Name( "last3", "first3", "middle3" );
    private final Name      contact4            =
        new Name( "last4", "first4", "middle4" );
    
    private final String    clientName1         = "client one.";
    private final String    clientName2         = "client two.";
    private final String    clientName3         = "client three.";
    private final String    clientName4         = "client four.";
    private final String[]  allClientNames      =
        { clientName1, clientName2, clientName3, clientName4 };
    
    private final Address   addr1               =
        new Address( "street1", "city1", StateCode.CA, "11111" );
    private final Address   addr2               =
        new Address( "street2", "city2", StateCode.CA, "22222" );
    private final Address   addr3               =
        new Address( "street3", "city3", StateCode.CA, "33333" );
    private final Address   addr4               =
        new Address( "street2", "city2", StateCode.CA, "22222" );
    
    private final Account   billableAccount1    =
        new ClientAccount( clientName1, contact1, addr1 );
    private final Account   billableAccount2    =
        new ClientAccount( clientName2, contact2, addr2 );
    private final Account   billableAccount3    =
        new ClientAccount( clientName3, contact3, addr3 );
    private final Account   billableAccount4    =
        new ClientAccount( clientName4, contact4, addr4 );
    private final Account[] allBillableAccounts =
    {
        billableAccount1,
        billableAccount2,
        billableAccount3,
        billableAccount4
    };
    
    private final Account   nonBillableAccount1 = 
        NonBillableAccount.BUSINESS_DEVELOPMENT;
    private final Account   nonBillableAccount2 =
        NonBillableAccount.SICK_LEAVE;
    private final Account   nonBillableAccount3 =
        NonBillableAccount.VACATION;
    private final Account   nonBillableAccount4 =
        NonBillableAccount.BUSINESS_DEVELOPMENT;
    private final Account[] allNonBillableAccounts  =
    {
        nonBillableAccount1,
        nonBillableAccount2,
        nonBillableAccount3,
        nonBillableAccount4
    };
    
    private int     totalBillableHours;
    private int     totalNonBillableHours;
    
    public TestTimeCard()
    {
        Calendar    cal     = Calendar.getInstance();
        cal.set( 2016, 03, 10, 5, 10, 15 );
        weekStartingDay = cal.getTime();
    }

    @Test
    public void testConstructor()
    {
        TimeCard    card    = new TimeCard( consultant, weekStartingDay );
        assertEquals( consultant, card.getConsultant() );
        assertEquals( weekStartingDay, card.getWeekStartingDay() );
    }

    @Test
    public void testAccessors1()
    {
        TimeCard    card    = new TimeCard( consultant, weekStartingDay );
        assertEquals( consultant, card.getConsultant() );
        assertEquals( weekStartingDay, card.getWeekStartingDay() );
        
        List<ConsultantTime>    allTimes    = getList( 10 );
        for ( ConsultantTime time : allTimes )
            card.addConsultantTime( time );
        
        int totalHours  = totalBillableHours + totalNonBillableHours;
        assertEquals( totalBillableHours, card.getTotalBillableHours() );
        assertEquals( totalNonBillableHours, card.getTotalNonBillableHours() );
        assertEquals( totalHours, card.getTotalHours() );
        
        List<ConsultantTime>    testTimes   = card.getConsultingHours();
        for ( ConsultantTime time : allTimes )
            assertNotNull( testTimes.remove( time ) );
        assertEquals( 0, testTimes.size() );
    }

    @Test
    public void testAccessors2()
    {
        TimeCard    card    = new TimeCard( consultant, weekStartingDay );
        assertEquals( consultant, card.getConsultant() );
        assertEquals( weekStartingDay, card.getWeekStartingDay() );
        
        int                     num         = 10;
        List<ConsultantTime>    allTimes    = getList( 0, 1, num );
        for ( ConsultantTime time : allTimes )
            card.addConsultantTime( time );
        
        int billableAccum   = 0;
        List<ConsultantTime>    testTimes   =
            card.getBillableHoursForClient( allClientNames[0] );
        assertEquals( num, testTimes.size() );
        for ( ConsultantTime time : testTimes )
        {
            assertEquals( time.getAccount(), allBillableAccounts[0] );
            assertTrue( time.isBillable() );
            billableAccum += time.getHours();
        }
        assertEquals( totalBillableHours, billableAccum );
    }
    
    @Test
    public void testToString()
    {
        TimeCard                card        = new 
            TimeCard( consultant, weekStartingDay );
        List<ConsultantTime>    allTimes    = getList( 10 );
        for ( ConsultantTime time : allTimes )
            card.addConsultantTime( time );
        System.out.println( card );
        System.out.println( card.toReportString() );
    }
    
    private List<ConsultantTime> getList( int num )
    {
        Random                  randy   = new Random( 1 );  
        List<ConsultantTime>    list    = new ArrayList<>();
        totalBillableHours = totalNonBillableHours = 0;
        
        for ( int inx = 0 ; inx < num ; ++inx )
        {
            int bill    = inx % allBillableAccounts.length;
            int nonBill = inx % allNonBillableAccounts.length;
            int hoursB  = randy.nextInt( 7 ) + 1;
            int hoursN  = randy.nextInt( 7 ) + 1;
            
            ConsultantTime  consultantTimeB = new ConsultantTime(
                weekStartingDay,
                allBillableAccounts[bill],
                skillType,
                hoursB
            );
            
            ConsultantTime  consultantTimeN = new ConsultantTime(
                weekStartingDay,
                allNonBillableAccounts[nonBill],
                skillType,
                hoursN
            );
            
            list.add( consultantTimeB );
            list.add( consultantTimeN );
            
            totalBillableHours += hoursB;
            totalNonBillableHours += hoursN;
        }
        
        return list;
    }
    
    private List<ConsultantTime>
    getList( int testAccount, int dummyAccount, int num )
    {
        Random      randy   = new Random( 1 );
        Account     billable    = allBillableAccounts[testAccount];
        Account     bill1       = allBillableAccounts[dummyAccount];
        Account     nBill1      = allNonBillableAccounts[dummyAccount];
        
        List<ConsultantTime>    list    = new ArrayList<>();
        totalBillableHours = totalNonBillableHours = 0;
        
        for ( int inx = 0 ; inx < num ; ++inx )
        {
            int hoursB  = randy.nextInt( 8 ) + 1;
            int hoursN  = randy.nextInt( 8 ) + 1;
            int hoursB1 = randy.nextInt( 8 ) + 1;
            int hoursN1 = randy.nextInt( 8 ) + 1;
            
            ConsultantTime  timeB   = new ConsultantTime(
                weekStartingDay,
                billable,
                skillType,
                hoursB
            );
            
            list.add( timeB );
            
            totalBillableHours += hoursB;
            totalNonBillableHours += hoursN;
            
            timeB = new ConsultantTime(
                weekStartingDay,
                bill1,
                skillType,
                hoursB1
            );
            
            ConsultantTime  timeN = new ConsultantTime(
                weekStartingDay,
                nBill1,
                skillType,
                hoursN1
            );
            
            list.add( timeB );
            list.add( timeN );
        }
        
        return list;
    }
}

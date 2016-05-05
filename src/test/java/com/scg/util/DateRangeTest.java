package com.scg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for the DateRange class.
 *
 * @author Russ Moul
 */
public final class DateRangeTest {
    /** Test month. */
    private static final int TEST_MONTH = Calendar.MARCH;

    /** Test year. */
    private static final int TEST_YEAR = 2006;

    /** Test start date. */
    private Date startDate;

    /** Test end date. */
    private Date endDate;
    
    /** Added by jack */
    private Date                startLess;
    private Date                startGreater;
    private Date                endLess;
    private Date                endGreater;
    private DateRange           targetRange;
    private List<DateTester>    dateTesters;
    /* */

    /**
     * Test the various constructors.
     */
    @Test
    public void testConstructors() {
        DateRange dateRange = new DateRange(startDate, endDate);
        assertNotNull("DateRange(Date, Date) failed.", dateRange);
        assertEquals(startDate, dateRange.getStartDate());
        assertEquals(endDate, dateRange.getEndDate());

        dateRange = new DateRange(TEST_MONTH, TEST_YEAR);
        assertEquals(startDate, dateRange.getStartDate());
        assertEquals(endDate, dateRange.getEndDate());
    }

    /**
     * Initialize start and end month dates.
     */
    @Before
    public void setUp() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(TEST_YEAR, TEST_MONTH, calendar
                .getMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        Calendar startCal = Calendar.getInstance();
        startCal.setTime( calendar.getTime() );
        
        calendar.set(TEST_YEAR, TEST_MONTH,
                     calendar.getMaximum(Calendar.DAY_OF_MONTH),
                     calendar.getMaximum(Calendar.HOUR_OF_DAY),
                     calendar.getMaximum(Calendar.MINUTE),
                     calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND,
                     calendar.getMaximum(Calendar.MILLISECOND));
        
        Calendar    endCal  = Calendar.getInstance();
        endCal.setTime( calendar.getTime());       
        
        startDate = startCal.getTime();
        endDate = endCal.getTime();
        startLess = tweakDay( startCal, -1 );
        endLess = tweakDay( endCal, -1 );
        startGreater = tweakDay( startCal, 1 );
        endGreater = tweakDay( endCal, 1 );
        targetRange = new DateRange( startDate, endDate );
    }

    /**
     * Test the isInDange method.
     */
    @Test
    public void testIsInRange()
    {
        DateTester[]   testers = 
        {
            new DateTester( targetRange, startDate, true ),
            new DateTester( targetRange, endDate, true ),
            new DateTester( targetRange, startGreater, true ),
            new DateTester( targetRange, endLess, true ),
            new DateTester( targetRange, startLess, false ),
            new DateTester( targetRange, endGreater, false ),
        };
        
        for ( DateTester tester : testers )
            tester.test();
    }
    
//    @Test
//    public void testIsInRange() {
//        final DateRange dateRange = new DateRange(startDate, endDate);
//        assertTrue(dateRange.isInRange(startDate));
//
//        DateRange testDateRange = new DateRange(Calendar.FEBRUARY, TEST_YEAR);
//        System.out.println( testDateRange );
//        System.out.println( startDate + ", " + endDate );
//        assertFalse(testDateRange.isInRange(startDate));
//
//        testDateRange = new DateRange("2/1/2006", "2/28/2006");
//        assertFalse(testDateRange.isInRange(startDate));
//
//        testDateRange = new DateRange("2/26/2006", "3/04/2006");
//        assertTrue(testDateRange.isInRange(startDate));
//    }
    
    private Date tweakDay( Calendar cal, int tweakFactor )
    {
        Calendar    temp    = Calendar.getInstance();
        temp.setTime( cal.getTime() );
        temp.add( Calendar.DAY_OF_YEAR, tweakFactor );
        Date    date    = temp.getTime();
        return date;
    }
    
    private String getDateStr( Date date )
    {
        String              fmt         = "M/d/yyyy";
        SimpleDateFormat    formatter   = new SimpleDateFormat( fmt );
        String              formatted   = formatter.format( date );
        
        return formatted;
    }
    
    private class DateTester
    {
        private final DateRange range;
        private final Date      date;
        private final boolean   expected;
        
        public DateTester( DateRange range, Date date, boolean expected )
        {
            this.range = range;
            this.date = date;
            this.expected = expected;
        }
        
        public void test()
        {
            boolean actual  = range.isInRange( date );
            System.out.println( range );
            System.out.println( date );
            assertEquals( expected, actual );
        }
    }
}

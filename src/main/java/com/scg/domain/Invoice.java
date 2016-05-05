package com.scg.domain;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.scg.util.Address;
import com.scg.util.StateCode;

/**
 * Encapsulates the attributes and behavior for creating
 * client invoices for a given time period from time cards.
 * @author jack
 */
public class Invoice
{
//    private static final Logger logger = 
//        LoggerFactory.getLogger(Invoice.class);
    
    /**
     * Name of the file containing invoice property.
     */
    public static final String  PROP_FILE_NAME          = "invoice.properties";
    
    /** 
     * Name of the "business name" property
     */
    public static final String  BUSINESS_NAME_PROP      = "business.name";
    
    /** 
     * Name of the "business street" property
     */
    public static final String  BUSINESS_STREET_PROP    = "business.street";
    
    /** 
     * Name of the "business city" property
     */
    public static final String  BUSINESS_CITY_PROP      = "business.city";
    
    /** 
     * Name of the "business state" property
     */
    public static final String  BUSINESS_STATE_PROP     = "business.state";
    
    /** 
     * Name of the "business zip" property
     */
    public static final String  BUSINESS_ZIP_PROP       = "business.zip";
    
    /** 
     * Default value for every property
     */
    public static final String  NA                      = "N/A";
    
    private final String                propFileName;
    private final Properties            businessProps;
    private final String                businessName;
    private final Address               businessAddress;
    private final List<InvoiceLineItem> lineItems;
    private final ClientAccount         client;
    private final int                   invoiceMonth;
    private final int                   invoiceYear;
    private final int                   maxLineItems;
    
    private InvoiceHeader   rptHeader;
    private InvoiceFooter   rptFooter;
    private int             rptNextItem;
    private int             rptPageItems;
    
    private static final int[]  UNUSED_CAL_FIELDS   =
    {
        Calendar.DAY_OF_MONTH,
        Calendar.HOUR_OF_DAY,
        Calendar.MINUTE,
        Calendar.SECOND,
        Calendar.MILLISECOND
    };

    /**
     * Instantiates a new invoice for a given client and time period.
     * 
     * @param client        The associated client.
     * @param invoiceMonth  The month of the target time period.
     * @param invoiceYear   The year of the target time period.
     */
    public Invoice( ClientAccount client, int invoiceMonth, int invoiceYear )
    {
        this( client, invoiceMonth, invoiceYear, PROP_FILE_NAME );
    }
    
    /**
     * Instantiates a new invoice for a given client and time period.
     * This constructor is to facilitate testing; programmers would normally
     * use Invoice( ClientAccount, int, int ).
     * 
     * @param client        The associated client.
     * @param invoiceMonth  The month of the target time period.
     * @param invoiceYear   The year of the target time period.
     * @param propFileName  The name of the properties file for printing
     *                      invoices
     *                      
     * @see #Invoice(ClientAccount, int, int)
     */
    public Invoice(
        ClientAccount client,
        int           invoiceMonth,
        int           invoiceYear,
        String        propFileName
    )
    {
        lineItems = new ArrayList<>();
        this.propFileName = propFileName;
        this.client = client;
        this.invoiceMonth = invoiceMonth;
        this.invoiceYear = invoiceYear;
        this.maxLineItems = 5;
        
        businessProps = new Properties();
        getProperties();
        this.businessAddress = getBusinessAddress();
        this.businessName = businessProps.getProperty( BUSINESS_NAME_PROP, NA );       
    }

    /**
     * Gets the client associated with this account.
     * @return the client
     */
    public ClientAccount getClientAccount()
    {
        return client;
    }

    /**
     * Gets the month of the invoice time period.
     * @return the invoiceMonth
     */
    public int getInvoiceMonth()
    {
        return invoiceMonth;
    }

    /**
     * Gets the year of the invoice time period.
     * @return the invoiceYear
     */
    public int getInvoiceYear()
    {
        return invoiceYear;
    }
    
    /**
     * Extrapolates line items from a given TimeCard.
     * Only line items for billable hours charged during this invoice's 
     * time period (as determined by invoiceMonth and invoiceYear) are
     * accumulated.
     * 
     * @param timeCard The given TimeCard.
     */
    public void extractLineItems( TimeCard timeCard )
    {
        String                  name    = client.getName(); 
        List<ConsultantTime>    times   =
            timeCard.getBillableHoursForClient( name );
        for ( ConsultantTime time : times )
        {
            Date        date    = time.getDate();
            if ( isValidDate( date ) )
            {
                Consultant  consultant  = timeCard.getConsultant();
                Skill       skill       = time.getSkillType();
                int         hours       = time.getHours();
                
                InvoiceLineItem item    =
                    new InvoiceLineItem( date, consultant, skill, hours );
                lineItems.add( item );
            }
        }
    }
    
    /**
     * Get the total charges for all line items in this invoice.
     * @return Total charges for all line items in this invoice.
     */
    public int getTotalCharges()
    {
        int charges = 0;
        for ( InvoiceLineItem item : lineItems )
            charges += item.getCharge();
        return charges;
    }
    
    /**
     * Get the total hours for all line items in this invoice.
     * @return Total hours for all line items in this invoice.
     */
    public int getTotalHours()
    {
        int hours   = 0;
        for ( InvoiceLineItem item : lineItems )
            hours += item.getHours();
        return hours;
    }
    
    /**
     * Gets the end date associated with this invoice's billing period.
     * @return The end date associated with this invoice's billing period.
     */
    public Date getEndDate()
    {
        Calendar    cal     = Calendar.getInstance();
        cal.set( Calendar.MONTH, invoiceMonth );
        cal.set( Calendar.YEAR, invoiceYear );
        for ( int field : UNUSED_CAL_FIELDS )
            cal.set( field, cal.getMaximum( field ) );
        
        Date    date    = cal.getTime();
        return date;
    }
    
    /**
     * Gets the start date associated with this invoice's billing period.
     * @return The start date associated with this invoice's billing period.
     */
    public Date getStartDate()
    {
        Calendar    cal     = Calendar.getInstance();
        cal.set( Calendar.MONTH, invoiceMonth );
        cal.set( Calendar.YEAR, invoiceYear );
        for ( int field : UNUSED_CAL_FIELDS )
            cal.set( field, cal.getMinimum( field ) );
        
        Date    date    = cal.getTime();
        return date;
    }
    
    /**
     * Returns a string containing representing the entire invoice
     * suitable for printing. May contain multiple pages; each page
     * begins with an InvoiceHeader, and ends with an InvoiceFooter.
     * 
     * @return String, suitable for printing, representing an invoice
     */
    public String toReportString()
    {
        Date    forMonth    = getStartDate();
        Date    invDate     = Calendar.getInstance().getTime();
        rptHeader = 
            new InvoiceHeader(
                businessName,
                businessAddress,
                client,
                forMonth,
                invDate
            );
        rptFooter = new InvoiceFooter( "The Small Consulting Group" );
        
        StringBuilder   bldr        = new StringBuilder();
        int             itemCount   = lineItems.size();
        do
        {
            bldr.append( rptHeader );
            nextPage( bldr );
            bldr.append( '\n' );
            if ( rptNextItem < itemCount )
                bldr.append( '\n' );
            else
                getTotalLine( bldr );
            String  footer  = rptFooter.toString();
            bldr.append( "\n\n\n" );
            bldr.append( footer );
            rptFooter.incrementPageNumber();
        } while ( rptNextItem < itemCount );
        
        return bldr.toString();
    }
    
    /**
     * Returns a string representation of this invoice's client
     * name, and billing start date.
     * 
     * @return A string representation of this invoice.
     */
    public String toString()
    {
        String  fmt     = "Invoice for: %s, Date: %2$tb %2$td,%2$tY\n";
        String  name    = client.getName();
        Date    date    = getStartDate();
        String  rval    = String.format( fmt, name, date );
        
        return rval;
    }
    
    private void getProperties()
    {
        File    file    = new File( propFileName );
        try ( FileReader reader = new FileReader( file ) )
        {
            businessProps.load( reader );
        }
        catch ( IOException exc )
        {
            //  logger.error( "properties file error", exc );
        }
        catch ( IllegalArgumentException exc )
        {
            //  logger.error( "properties file data error", exc );
        }
    }
    
    private Address getBusinessAddress()
    {
        String  busiStreet  = businessProps.getProperty( BUSINESS_STREET_PROP, NA );
        String  busiCity    = businessProps.getProperty( BUSINESS_CITY_PROP, NA );
        String  busiState   = businessProps.getProperty( BUSINESS_STATE_PROP, NA );
        String  busiZip     = businessProps.getProperty( BUSINESS_ZIP_PROP, NA );
        
        StateCode   stateCode;
        try
        {
            stateCode = StateCode.valueOf( busiState );
        }
        catch ( IllegalArgumentException exc)
        {
            stateCode = StateCode.WA;
        }
        Address     addr        = new 
            Address( busiStreet, busiCity, stateCode, busiZip );
        
        return addr;
    }
    
    private boolean isValidDate( Date date )
    {
        Date    startDate   = getStartDate();
        Date    endDate     = getEndDate();
        
        boolean rcode;
        if ( date.before( startDate ) )
            rcode = false;
        else if ( date.after( endDate ) )
            rcode = false;
        else
            rcode = true;
        
        return rcode;
    }
    
    private void nextPage( StringBuilder bldr )
    {
        rptPageItems = 0;
        for ( int inx = 0, end = lineItems.size() ; 
              inx < maxLineItems && rptNextItem < end ;
              ++inx, ++rptNextItem, ++rptPageItems
            )
        {
            String  nextItem    = lineItems.get( rptNextItem ).toString();
            bldr.append( nextItem ).append( '\n' );
        }
    }
    
    private void getTotalLine( StringBuilder bldr )
    {
        int blankLines  = maxLineItems - rptPageItems;
        for ( int inx = 0 ; inx < blankLines ; ++inx )
            bldr.append( '\n' );
        
        int             hours   = getTotalHours();
        int             charges = getTotalCharges();
        NumberFormat    numFmt  = NumberFormat.getInstance();
        numFmt.setMinimumFractionDigits( 2 );
        String          numStr  = numFmt.format( charges );
        String          strFmt  = "%-62s%5d%12s";
        String  line    = 
            String.format( strFmt, "Total:", hours, numStr );
        bldr.append( line );
    }
}

package com.scg.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.scg.util.Address;
import com.scg.util.StateCode;

/**
 * Represents a header for an invoice. It is printed at the top of every
 * page in an invoice.
 * 
 * @author jack
 */
public class InvoiceHeader
{
    private static final String     SCG_NAME    = "The Small Consulting Group";
    private static final Address    SCG_ADDRESS =
        new Address( "1313 Mockingbird Ln" , "Redmond", StateCode.WA, "11111" );
    
    private static final String HEADER_STATIC   =
          "Date        Consultant                   Skill                Hours  Charge\n"
        + "----------  ---------------------------  ------------------   -----  ----------\n";
    
    private final String        businessName;
    private final Address       businessAddress;
    private final ClientAccount client;
    private final Date          invoiceDate;
    private final Date          invoiceForMonth;
    
    /**
     * Instantiates an invoice header for a given business and date.
     * @param businessName      The business's name.
     * @param businessAddress   The business's address.
     * @param client            The client name associated with the business.
     * @param invoiceForMonth   The month for which the invoice is generated.
     * @param invoiceDate       The date the invoice is printed.
     */
    public InvoiceHeader(
        String          businessName,
        Address         businessAddress,
        ClientAccount   client,
        Date            invoiceForMonth,
        Date            invoiceDate
        )
    {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.client = client;
        this.invoiceDate = invoiceDate;
        this.invoiceForMonth = invoiceForMonth;
    }
    
    /**
     * Returns the invoice header as a string suitable for printing.
     * @return A printable string representing this InvoiceHeader.
     */
    @Override
    public String toString()
    {
        SimpleDateFormat    forMonth  = new SimpleDateFormat( "MMMM, yyyy" );
        SimpleDateFormat    invDate   = new SimpleDateFormat( "MMM dd, yyyy" );
        
        StringBuilder   bldr        = new StringBuilder( SCG_NAME );
        bldr.append( "\n" ).append( SCG_ADDRESS ).append( "\n\n" );
        bldr.append( "Invoice For: " ).append( "\n" );
        bldr.append( businessName ).append( "\n" );
        bldr.append( businessAddress ).append( "\n" );
        bldr.append( client.getContact() );
        bldr.append( "\n\n" ).append( "Invoice For Month Of: " );
        bldr.append( forMonth.format( invoiceForMonth ) ).append( "\n" );
        bldr.append( "Invoice Date: " );
        bldr.append( invDate.format( invoiceDate ) ).append( "\n\n" );
        bldr.append( HEADER_STATIC );
        return bldr.toString();
    }
}

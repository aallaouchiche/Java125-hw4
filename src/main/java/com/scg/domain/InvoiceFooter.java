package com.scg.domain;

/**
 * Encapsulates a footer to be printed at the bottom of an invoice.
 * @author jack
 */
public class InvoiceFooter
{
    private static final String FOOTER_LINE2    =
        "================================" +
        "===============================================";
    
    private final String    businessName;
    private int             pageNumber      = 1;
    
    /**
     * Instantiates an invoice footer with the given business name. 
     * @param businessName  The given business name.
     */
    public InvoiceFooter( String businessName )
    {
        this.businessName = businessName;
    }
    
    /**
     * Increments the page number printed in a footer. The first page number
     * is 1.
     */
    public void incrementPageNumber()
    {
        ++pageNumber;
    }
    
    /**
     * Formats the invoice footer as a printable string.
     * 
     * @return The invoice footer as a printable string.
     */
    @Override
    public String toString()
    {
        String  fmt     = "%-69s Page: %3d%n";
        String  str   = String.format( fmt, businessName, pageNumber );
        str += FOOTER_LINE2 + "\n";
        return str;
    }
}

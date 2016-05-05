package com.scg.domain;

import org.junit.Test;

public class TestInvoiceFooter
{
    private final String    businessName    = "Colonial Widget Co.";
    
    @Test
    public void test()
    {
        InvoiceFooter   footer  = new InvoiceFooter( businessName );
        System.out.println( footer );
        for ( int inx = 0 ; inx < 20 ; ++inx )
            footer.incrementPageNumber();
        System.out.println( footer );
    }
}

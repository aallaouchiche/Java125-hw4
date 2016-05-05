package com.scg.domain;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class TestInvoiceHeader
{
    private final String        businessName;
    private final Address       businessAddress;
    private final ClientAccount client;
    private final Date          invoiceDate;
    private final Date          invoiceForMonth;
    
    public TestInvoiceHeader()
    {
        businessName = "Marquis Enterprises";
        businessAddress =
            new Address( "street", "city", StateCode.CA, "11111" );
        
        Name    name    = new Name( "Last", "First", "M." );
        client = new ClientAccount( "Greg Twomey", name, businessAddress );
        this.invoiceDate = Calendar.getInstance().getTime();
        this.invoiceForMonth = invoiceDate;
    }

    @Test
    public void test()
    {
        InvoiceHeader   header  = new InvoiceHeader(
            businessName,
            businessAddress,
            client,
            invoiceDate,
            invoiceForMonth
            );
        
        System.out.println( header );
    }

}

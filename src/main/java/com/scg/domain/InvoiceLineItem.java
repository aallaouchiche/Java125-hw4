/**
 * 
 */
package com.scg.domain;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates a billable item for an invoice;
 * 
 * @author jack
 */
public final class InvoiceLineItem
{
    private static final Map<Skill, Integer>    rateMap = new HashMap<>();
    static
    {
        rateMap.put( Skill.PROJECT_MANAGER, 250 );
        rateMap.put( Skill.SYSTEM_ARCHITECT, 200 );
        rateMap.put( Skill.SOFTWARE_ENGINEER, 150 );
        rateMap.put( Skill.SOFTWARE_TESTER, 100 );
    }
    
    private final Date          date;
    private final Consultant    consultant;
    private final Skill         skill;
    private final int           hours;

    /**
     * Creates an InvoiceLineItem;
     * 
     * @param date          line item date;
     * @param consultant    line item consultant;
     * @param skill         line item skill;
     * @param hours         line item hours;
     */
    public 
    InvoiceLineItem( Date date, Consultant consultant, Skill skill, int hours )
    {
        this.date = date;
        this.consultant = consultant;
        this.skill = skill;
        this.hours = hours;
    }
    
    /**
     * Gets the line item date.
     * @return The line item date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Gets the line item consultant.
     * @return The line item consultant
     */
    public Consultant getConsultant()
    {
        return consultant;
    }

    /**
     * Gets the line item skill.
     * @return The line item skill
     */
    public Skill getSkill()
    {
        return skill;
    }

    /**
     * Gets the line item hours.
     * @return The line item hours
     */
    public int getHours()
    {
        return hours;
    }
    
    /**
     * Calculates the charge for this line item.
     * 
     * @return charge for this line item.
     */
    public int getCharge()
    {
        int     rate        = rateMap.get( skill );
        int     charge      = hours * rate;
        return charge;
    }
    
    @Override
    /**
     * Returns this InvoiceLineItem as a string suitable for printing
     * in a report.
     * 
     * @return This InvoiceLineItem as a string suitable for printing
     * in a report
     */
    public String toString()
    {
        SimpleDateFormat    dateFmt     = new SimpleDateFormat( "MM/dd/YYYY" );
        String              dateStr     = dateFmt.format( date );
        String              consStr     = consultant.getName().toString();
        String              skillStr    = skill.toString();
        int                 charge      = getCharge();
        NumberFormat        numFmt      = NumberFormat.getInstance();
        numFmt.setMinimumFractionDigits( 2 );
        String              numStr      = numFmt.format( charge );
        String              strFmt      = "%-12s%-29s%-23s%3d    %8s";
        String              result      =
            String.format( strFmt, dateStr, consStr, skillStr, hours, numStr );
        return result;
    }
}

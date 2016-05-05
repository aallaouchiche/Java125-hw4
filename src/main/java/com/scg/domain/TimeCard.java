package com.scg.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Encapsulates the billable and non-billable hours worked by a consultant.
 * @author jack
 *
 */
public final class TimeCard implements Comparable<TimeCard>, Comparator<TimeCard>
{
    private final Consultant            consultant;
    private final Date                  weekStartingDay;
    private final List<ConsultantTime>  consultingHours;
    
    /**
     * Instantiates a time card for the given consultant, with the given
     * week-starting-day.
     * 
     * @param consultant        The given consultant.
     * @param weekStartingDay   The given week-starting-day.
     */
    public TimeCard( Consultant consultant, Date weekStartingDay )
    {
        this.consultant = consultant;
        this.weekStartingDay = weekStartingDay;
        this.consultingHours = new ArrayList<>();
    }

    /**
     * Gets the Consultant object associated with this time card.
     * 
     * @return Associated Consultant object.
     */
    public Consultant getConsultant()
    {
        return consultant;
    }

    /**
     * Gets the week-starting-day for this time card.
     * 
     * @return The week-starting-day for this time card.
     */
    public Date getWeekStartingDay()
    {
        return weekStartingDay;
    }

    /**
     * Gets the collection on ConsultantTime records associated with
     * this time card.
     * 
     * @return The collection on ConsultantTime records associated with
     *         this time card.
     */
    public List<ConsultantTime> getConsultingHours()
    {
        return consultingHours;
    }
    
    /**
     * Adds a ConsultantTime object to this time card.
     * @param consultantTime The consultant time to add.
     */
    public void addConsultantTime( ConsultantTime consultantTime )
    {
        consultingHours.add( consultantTime );
    }
    
    /**
     * Returns a list of ConsultTime objects representing billable hours
     * for a given client.
     * 
     * @param clientName The name of the given client.
     * 
     * @return List of billable hours for the given client.
     */
    public List<ConsultantTime> getBillableHoursForClient( String clientName ) 
    {
        List<ConsultantTime>    times   = new ArrayList<>();
        
        for ( ConsultantTime time : consultingHours )
        {
            Account account = time.getAccount();
            String  name    = account.getName();
            if ( account.isBillable() && name.equals( clientName ) )
                times.add( time );
        }
        
        return times;
    }
    
    /**
     * Gets the total billable hours for this time card.
     * 
     * @return The total billable hours for this time card.
     */
    public int getTotalBillableHours()
    {
        int     totalHours  = 0;
        for ( ConsultantTime time : consultingHours )
        {
            if ( time.isBillable() )
                totalHours += time.getHours();
        }
        
        return totalHours;
    }
    
    /**
     * Gets the total hours for this time card.
     * 
     * @return The total hours for this time card.
     */
    public int getTotalHours()
    {
        int     totalHours  = 0;
        for ( ConsultantTime time : consultingHours )
            totalHours += time.getHours();
        
        return totalHours;
    }
    
    /**
     * Gets the total non-billable hours for this time card.
     * 
     * @return The total non-billable hours for this time card.
     */
    public int getTotalNonBillableHours()
    {
        int     totalHours  = 0;
        for ( ConsultantTime time : consultingHours )
        {
            if ( !time.isBillable() )
                totalHours += time.getHours();
        }
        
        return totalHours;
    }
    
    /**
     * Returns a string representation of this object suitable for
     * printing in a report.
     * 
     * @return A string suitable for printing in a report.
     */
    public String toReportString()
    {
        StringBuilder   bldr    = new StringBuilder( consultant.toString() );
        bldr.append( " for week starting " )
            .append( weekStartingDay ).append( "\n" );
        for ( ConsultantTime time : consultingHours )
            bldr.append( time.toString() ).append( "\n" );
        return bldr.toString();
    }
    
    @Override
    /**
     * Returns the contents of this object as an easily
     * readable string.
     * 
     * @return The contents of this object as a string.
     */
    public String toString()
    {
        String  str = this.consultant.getName() + "; " + weekStartingDay;
        return str;
    }

	@Override
	public int compareTo(TimeCard arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compare(TimeCard arg0, TimeCard arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
}

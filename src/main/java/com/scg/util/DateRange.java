package com.scg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Encapsulates a range of dates, with a given start and end.
 * @author Amine Allaouchiche
 *
 */
public class DateRange {
	
	
	private Date startDate;
	private Date endDate;

	 private static final int[]  UNUSED_CAL_FIELDS   =
		    {
		        Calendar.DAY_OF_MONTH,
		        Calendar.HOUR_OF_DAY,
		        Calendar.MINUTE,
		        Calendar.SECOND,
		        Calendar.MILLISECOND
		    };
	 
	 // formatter for the string date parameters
	//private static final String DATE_FORMATTER = "EEEE, dd/MM/yyyy/hh:mm:ss";
	private static final String DATE_FORMATTER = "MM/dd/yyyy";
	
	/**
	 * Constructs a range instance with the given start and end end dates.
	 * @param start The given start date
	 * @param end The given end date
	 */
	public DateRange(Date start, Date end) {
		//checking if start is actually before the end
		//if so, accept the values otherwise invert the 
		// order
		if (start.after(end)) { 
		this.startDate = end;
		this.endDate = start;
		} else {
			this.startDate = start;
			this.endDate = end;
		}
	}
	
	 
	/**
	 * Constructs a range instance that covers a single month.
	 * @param month Month of the DateRange
	 * @param year Year of the DateRange
	 */
	public DateRange(int month, int year) {
		
		Calendar    cal     = Calendar.getInstance();
        cal.set( Calendar.MONTH, month );
        cal.set( Calendar.YEAR, year );
        
        //setting up the date to the beginning of the month
        for ( int field : UNUSED_CAL_FIELDS ) {
            cal.set( field, cal.getMinimum( field ) );
        }
        this.startDate = cal.getTime();
        
        //setting up the date to the end of the month
        for ( int field : UNUSED_CAL_FIELDS ) {
            cal.set( field, cal.getMaximum( field ) );
        }
        this.endDate = cal.getTime();
		
	}
	
	 
	/**
	 * Constructs a range instance with the given start and end dates. 
	 * @param start Given start date
	 * @param end Given end date
	 */
	public DateRange(String start, String end) {
		
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMATTER);
		try {
			startDate = formatter.parse(start);
			endDate = formatter.parse(end);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Gets the end date of the DateRange.
	 * @return The end date of the DateRange
	 */
	public Date getEndDate(){
		return this.endDate;
		
	}
	
	/**
	 * Gets the start date of the DateRange.
	 * @return The start date of the DateRange
	 */
	public Date getStartDate(){
		return this.startDate;
		
	}
	
	
	/**
	 * Determines if a given date falls inclusively before the start and end dates of this DateRange.
	 * @param date  The given date
	 * @return true if startDate <= date <= endDate
	 */
	public boolean isInRange(Date date) {
		return (date.after(startDate) && date.before(endDate))
				// including the start and end dates in the acceptance
				|| date.equals(startDate) || date.equals(endDate);
		
	}
	
	/**
	 * A readable representation of this object
	 * @return a readable representation of this object
	 */
	@Override
	public String toString() {
		return "Date range from : " + this.startDate.toString() + " to:  " + this.endDate.toString();
		
	}

}

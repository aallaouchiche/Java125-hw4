package com.scg.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * Utility class for processing timecard lists.
 * @author Amine Allaouchiche
 *
 */
public class TimeCardListUtil {

	
			
		public TimeCardListUtil(){
				
		}
			
		/**
		 * Given a list of TimeCards, extract those matching a given date range.
		 * @param timeCards The given TimeCardList
		 * @param dateRange The given date range
		 * @return A list containing only those time cards that match the date range.
		 */
		public static List<TimeCard> getTimeCardsForDateRange(List<TimeCard> timeCards,
                    DateRange dateRange) {
			
			List<TimeCard>  list    = new ArrayList<>();
		    for ( TimeCard card : timeCards )
		    {

		    	Date cardDate = card.getWeekStartingDay();
		        if ( dateRange.isInRange(cardDate))
		            list.add( card );
		    }
		    
		    return list;
				
		}
		
		/**
		 * Sorts a list of TimeCards by consultant name.
		 * @param timeCards The list to sort.
		 */
		public static void sortByConsultantName(List<TimeCard> timeCards){
			
		}
		
		/**
		 * Sorts a list of TimeCards by start date.
		 * @param timeCards The list to sort
		 */
		public static void sortByStartDate(List<TimeCard> timeCards) {
			
		}


		
		
		public static List<TimeCard> getTimeCardsForConsultant( List<TimeCard> timeCards, Consultant consultant )
		{
		    List<TimeCard>  list    = new ArrayList<>();
		    for ( TimeCard card : timeCards )
		    {
		        Consultant  cons    = card.getConsultant();
		        if ( cons.equals( consultant ) )
		            list.add( card );
		    }
		    
		    return list;
		}

}

/**
 * 
 */
package com.scg.util;

import java.util.Comparator;
import java.util.Date;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import static com.scg.util.NullObjectCompare.*;

/**
 * Implementation of a comparator for TimeCards.
 * @author Amine Allaouchiche
 *
 */
public class TimeCardConsultantComparator implements Comparator<TimeCard> {


	public TimeCardConsultantComparator() {

	}

	/**
	 * 
	 * Compares two TimeCards for magnitude.
	 * Compares two TimeCards for magnitude. If card1 is less than card2, a value less than zero is returned; if the cards are equal, 0 is returned; if card1 is greater than card2, a value greater than 0 is returned. 
	 * The magnitude is determined by testing the TimeCard properties in the following order:
	 * <ul> 
	 * 		<li> 1.Consultant</li>
	 * 		<li>2.Week</li>
	 * 		<li>3.Hours</li>
	 * 		<li>4.Total hours</li>
	 * 		<li>5.Billable hours</li>
	 * 		<li>6.Non-billable hours</li>
	 * </ul>
	 * 
	 * Specified by:compare in interface java.util.Comparator<TimeCard>
	 * @param   card1  The first TimeCard to compare
	 * 	@param	card2  The second TimeCard to compare
	 * @return An integer value less than, 
	 * equal to or greater than 0 if card1 is less than, equal to or greater than card2, respectively
	 */

	//	@Override
	//	public int compare(TimeCard card1, TimeCard card2) {
	//		int returnValue = 0;
	//		if (card1 == null || card2 == null) {
	//			return nullObjectsComparator(card1, card2);
	//		// none of the cards is null	
	//		} else {
	//			// using variables to avoid repeating same 
	//			// calls over and over
	//			Consultant consultant1 = card1.getConsultant();
	//			Consultant consultant2 = card2.getConsultant();
	//			Date week1 = card1.getWeekStartingDay();
	//			Date week2 = card2.getWeekStartingDay();
	//			int hours1 = card1.getTotalBillableHours();
	//			int hours2 = card2.getTotalBillableHours();
	//			int totalHours1 = card1.getTotalHours();
	//			int totalHours2 = card2.getTotalHours();
	//			int billableHours1 = card1.getTotalBillableHours();
	//			int billableHours2 = card2.getTotalBillableHours();
	//			int nonBillableHours1 = card1.getTotalNonBillableHours();
	//			int nonBillableHours2 = card2.getTotalNonBillableHours();
	//			
	//			// check if any of the consultant is null
	//			if (consultant1 == null  || consultant2 == null) {
	//				returnValue = nullObjectsComparator(consultant1, consultant2);
	//				if (  returnValue != 0 ) {
	//					return returnValue;
	//				};
	//			// none of the consultant is null
	//			} else {
	//				returnValue = consultant1.compareTo(consultant2);
	//				if (returnValue == 0 ) {
	//					if (week1 == null || week2 == null) {
	//						returnValue = nullObjectsComparator(week1, week2);
	//					}
	//					if (  returnValue != 0 ) {
	//						return returnValue;
	//					}else {
	//						returnValue = week1.compareTo(week2);
	//					};
	//					
	//					}
	//				}
	//			}
	//			
	//			
	//		
	//		
	//		return returnValue;
	//	}



	@Override
	public int compare(TimeCard card1, TimeCard card2) {
		int returnValue = 0;
		if (card1 == null || card2 == null) {
			return nullObjectsComparator(card1, card2);
			// none of the cards is null	
		} else {
			// using variables to avoid repeating same 
			// calls over and over
			Consultant consultant1 = card1.getConsultant();
			Consultant consultant2 = card2.getConsultant();
			Date week1 = card1.getWeekStartingDay();
			Date week2 = card2.getWeekStartingDay();
			int totalHours1 = card1.getTotalHours();
			int totalHours2 = card2.getTotalHours();
			int billableHours1 = card1.getTotalBillableHours();
			int billableHours2 = card2.getTotalBillableHours();
			int nonBillableHours1 = card1.getTotalNonBillableHours();
			int nonBillableHours2 = card2.getTotalNonBillableHours();

			// check if any of the consultant is null
			if (consultant1 == null  || consultant2 == null) {
				returnValue = nullObjectsComparator(consultant1, consultant2);
				if (  returnValue != 0 ) {
					return returnValue;
				};
				// none of the consultant is null
			} else {
				returnValue = consultant1.compareTo(consultant2);
				if (returnValue != 0 ) {
					return returnValue;
					// consultant1 == consultant2	
				} else {
					if (week1 == null || week2 == null) {
						returnValue = nullObjectsComparator(week1, week2);
					}
					if (  returnValue != 0 ) {
						return returnValue;
					}else {
						returnValue = week1.compareTo(week2);
						if (returnValue != 0) {
							return returnValue;
							// week1 = week2	
						} else {
							if (totalHours1 == totalHours2 && billableHours1 == billableHours2){
								return nonBillableHours1 - nonBillableHours2;
							} else if (totalHours1 == totalHours2) {
								return billableHours1 - billableHours2;
							}else {
								return totalHours1 - totalHours2;
							}

						}
					}

				}
			}
		}
		return returnValue;
	}



}

/*    
 *     RecruitSystem - a distributed application to handle job applications.
 *     Copyright (C) 2014  Federico Klappenbach, Fredrik Johansson, Mikael Tenhunen
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *      along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.kth.ict.ffm.recruitsystem.util.dto;

import java.io.Serializable;
import java.util.Date;
import se.kth.ict.ffm.recruitsystem.util.validation.ValidDate;

/**
 * AvailabilityFromView instances represent single availability periods created
 * in view.
 */
public class AvailabilityFromView implements Serializable {
    @ValidDate
    private Date fromDate;
    @ValidDate
    private Date toDate;

    public AvailabilityFromView() {
    }   
    
    /**
     * Creates a new AvailabilityFromView as the period from fromDate to toDate
     * @param fromDate
     * @param toDate 
     */
    public AvailabilityFromView(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * @return end date of this availability period
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate 
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return start date of this availability period
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate 
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}

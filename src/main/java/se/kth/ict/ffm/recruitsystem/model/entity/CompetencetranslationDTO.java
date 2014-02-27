/*
* RecruitSystem - a distributed application to handle job applications.
* Copyright (C) 2014 Federico Klappenbach, Fredrik Johansson, Mikael Tenhunen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package se.kth.ict.ffm.recruitsystem.model.entity;

/**
 * Used to expose some accessor methods of Competencetranslation 
 * needed outside the Model layer
 */
public interface CompetencetranslationDTO {
    public String getName();
    public String getLocale();
    public int getCompetenceId();
    public String toString();
}
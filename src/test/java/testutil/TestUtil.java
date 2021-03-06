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

package testutil;

import java.lang.reflect.Field;

/**
 * TestUtil is a utility used by test classes for things like setting private fields
 * by reflection.
 */
public class TestUtil {
    
    public static void setPrivateField(Class<? extends Object> instanceFieldClass, Object instance, String fieldName, Object fieldValue) throws Exception {
        Field setId = instanceFieldClass.getDeclaredField(fieldName);
        setId.setAccessible(true);
        setId.set(instance, fieldValue);
    }
}

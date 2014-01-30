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

package se.kth.ict.ffm.recruitsystem.view;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Stateless
@Named
public class ValidationManager {
    @NotNull
    @Size(min = 1, max = 45)
    @Pattern(regexp="[a-zA-Z_0-9_@]")
    private String username;
    
    @NotNull
    @Size(min = 1, max = 45)
    @Pattern(regexp="[a-zA-Z_0-9_@]")
    private String password;
    
      @Validate
      public String setUsername(String username){
          try {
              this.username=username;
          } catch (Exception e) {
              return "fail";
          }
          
        return "success";
    }
      
      
    public String setPassword(String password){
          try {
              this.password=password;
          } catch (Exception e) {
               return "fail";
          }
        return "success";
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}

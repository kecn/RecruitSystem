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


import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import se.kth.ict.ffm.recruitsystem.util.validation.ValidUsername;
import se.kth.ict.ffm.recruitsystem.util.validation.ValidPassword;


@Named("loginManager")
@RequestScoped
public class LoginManager implements Serializable   {

    @ValidUsername
    private String username;
    
    @ValidPassword
    private String password;

    public void LoginManager(){}
    
    public String getUsername() {
        return this.username;
    }
    
    /**
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password field
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Called to try a login with the username and password that
     * have already been set.
     * @return indication of success or failure, used by JSF navigation
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(this.username, this.password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed"));
            return "failure";
        }
        return "success";
    }

    /**
     * Called to logout a logged in user.
     */
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {

            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }
}

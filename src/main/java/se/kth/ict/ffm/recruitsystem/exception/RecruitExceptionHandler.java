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
package se.kth.ict.ffm.recruitsystem.exception;

import java.util.Iterator;
import java.util.ResourceBundle;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 *
 * 
 */

public class RecruitExceptionHandler extends ExceptionHandlerWrapper {

    private final ExceptionHandler wrapped;  
   
    public RecruitExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();

        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable throwable = context.getException();

            FacesContext fc = FacesContext.getCurrentInstance();            

            try {
                Flash flash = fc.getExternalContext().getFlash();
                
                ResourceBundle bundle = ResourceBundle.getBundle(
                        "se.kth.ict.ffm.recruitsystem.properties.language",
                        fc.getViewRoot().getLocale());                
                
                // Put the exception in the flash scope to be displayed in the error 
                // page if necessary ...
                String message = throwable.getMessage().substring(throwable.getMessage().indexOf("%")+1);
                flash.put("errorDetails", bundle.getString(message));
                flash.put("errorTitle", bundle.getString("error"));

                NavigationHandler navigationHandler = fc.getApplication().getNavigationHandler();
                navigationHandler.handleNavigation(fc, null, "error?faces-redirect=true");
                fc.renderResponse();
            } finally {
                iterator.remove();
            }
        }
    }
}

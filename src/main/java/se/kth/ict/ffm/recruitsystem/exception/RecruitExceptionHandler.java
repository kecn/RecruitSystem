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
 * When an ecxeption is shown it is intercepted by this class and redirected to the error.xhtml page.
 * The messages are localized.
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

    /**
     * 
     * @throws FacesException 
     */
    @Override
    public void handle() throws FacesException {
        //Get exception iterator
        Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();

        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable throwable = context.getException();
            FacesContext fc = FacesContext.getCurrentInstance();

            try {
                Flash flash = fc.getExternalContext().getFlash();
                
                //Get localisation
                ResourceBundle bundle = ResourceBundle.getBundle(
                        "se.kth.ict.ffm.recruitsystem.properties.language",
                        fc.getViewRoot().getLocale());

                //Concatinate string, show only relevant info
                String message = throwable.getMessage().substring(throwable.getMessage().indexOf("%") + 1);
                flash.put("errorTitle", bundle.getString("error"));
                flash.put("errorDetails", bundle.getString(message));

                NavigationHandler navigationHandler = fc.getApplication().getNavigationHandler();
                navigationHandler.handleNavigation(fc, null, "error?faces-redirect=true");
                fc.renderResponse();
            } finally {
                iterator.remove();
            }
        }
    }
}

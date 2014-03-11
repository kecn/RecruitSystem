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

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * LanguageBean is responsible for localization.
 */
@Stateless
public class LanguageBean {
    private final Map<String,Object> countries;
    private String currentLanguage;
    private Locale currentLocale;
    @Inject
    private Event<String> localeChangedEvent;
    
    public LanguageBean() {
            Locale seLocale = new Locale("se");
            Locale enLocale = new Locale("en");
            countries = new LinkedHashMap<>();
            countries.put("English", enLocale); //label, value
            countries.put("Swedish", seLocale);
            this.currentLanguage = "se";
            this.currentLocale = seLocale;            
    }
    
    /**
     * Called whenever a user has changed locale. 
     * Fires localeChanged event
     * @param e String representing locale
     */
    public void countryLocaleCodeChanged(String e) {
        //loop a map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            if (entry.getKey().equals(e)) {
                FacesContext.getCurrentInstance()
                     .getViewRoot().setLocale((Locale) entry.getValue());
                
                if(entry.getKey().equals("English"))
                    setCurrentLanguage("en");
                if(entry.getKey().equals("Swedish"))
                    setCurrentLanguage("se");
            }
        }
        //fire an event 
        localeChangedEvent.fire(currentLanguage);
    }
    
    /**
     * @return String representing current locale
     */
    public String getCurrentLanguage() {
        return this.currentLanguage;
    }
    
    /**
     * @param newLang 
     */
    public void setCurrentLanguage(String newLang){
        this.currentLanguage = newLang;
    }

    /**
     * @return current Locale
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * @param currentLocale 
     */
    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }
}

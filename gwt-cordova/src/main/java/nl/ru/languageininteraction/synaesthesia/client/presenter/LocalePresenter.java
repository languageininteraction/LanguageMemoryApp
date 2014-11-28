/*
 * Copyright (C) 2014 Language In Interaction
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nl.ru.languageininteraction.synaesthesia.client.presenter;

import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.view.MenuView;

/**
 * @since Oct 31, 2014 10:58:32 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class LocalePresenter extends AbstractPresenter implements Presenter {

    public LocalePresenter(RootPanel widgetTag) {
        super(widgetTag, new MenuView());
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.localeScreenTitle(), titleBarListner);
    }

    @Override
    protected void setContent(final AppEventListner appEventListner) {
        setUpLocaleOptions();
    }

    @Override
    protected void pageClosing() {
    }

    private void setUpLocaleOptions() {
        for (final String localeName : LocaleInfo.getAvailableLocaleNames()) {
            final String displayName = LocaleInfo.getLocaleNativeDisplayName(localeName);
            if (displayName != null && !displayName.isEmpty()) {
                ((MenuView) simpleView).addMenuItem(new PresenterEventListner() {

                    @Override
                    public void eventFired(Button button) {
                        final String queryString = Window.Location.getQueryString();
                        final String localeGet = "locale=";
                        final String updatedPathValue;
                        if (queryString.contains(localeGet)) {
                            // if a locale vale already exists then update it
                            updatedPathValue = queryString.replaceFirst(localeGet + "[^&]*", localeGet + localeName);
                        } else {
                            // if there are no values already there then use ? otherwise append with &
                            String separator = (queryString.isEmpty()) ? "?" : "&";
                            updatedPathValue = queryString + separator + localeGet + localeName;
                        }
                        Window.Location.replace(Window.Location.getPath() + updatedPathValue);
                    }

                    @Override
                    public String getLabel() {
                        return displayName;
                    }
                });
            }
        }
    }
}

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

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.view.MenuView;

/**
 * @since Nov 3, 2014 11:03:38 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MenuPresenter extends AbstractPresenter implements Presenter {

    public MenuPresenter(RootPanel widgetTag) {
        super(widgetTag, new MenuView());
    }

    @Override
    void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.menuScreenTitle(), null);
    }

    @Override
    void setContent(final AppEventListner appEventListner) {
        setUpLocaleOptions(appEventListner, messages.introMenuLabel(), AppEventListner.ApplicationState.intro);
        setUpLocaleOptions(appEventListner, messages.metadataMenuLabel(), AppEventListner.ApplicationState.metadata);
        setUpLocaleOptions(appEventListner, messages.stimulusMenuLabel(), AppEventListner.ApplicationState.stimulus);
        setUpLocaleOptions(appEventListner, messages.reportMenuLabel(), AppEventListner.ApplicationState.report);
        setUpLocaleOptions(appEventListner, messages.feedbackMenuLabel(), AppEventListner.ApplicationState.feedback);
        setUpLocaleOptions(appEventListner, messages.registerMenuLabel(), AppEventListner.ApplicationState.registration);
        setUpLocaleOptions(appEventListner, messages.moreInfoMenuLabel(), AppEventListner.ApplicationState.moreinfo);
        setUpLocaleOptions(appEventListner, messages.localeMenuLabel(), AppEventListner.ApplicationState.locale);
        setUpLocaleOptions(appEventListner, messages.versionMenuLabel(), AppEventListner.ApplicationState.version);
    }

    private void setUpLocaleOptions(final AppEventListner appEventListner, String displayName, final AppEventListner.ApplicationState applicationState) {
        ((MenuView) simpleView).addMenuItem(displayName, new PresenterEventListner() {

            @Override
            public void eventFired(Button button) {
                appEventListner.requestApplicationState(applicationState);
            }
        });
    }

    @Override
    void pageClosing() {
    }
}

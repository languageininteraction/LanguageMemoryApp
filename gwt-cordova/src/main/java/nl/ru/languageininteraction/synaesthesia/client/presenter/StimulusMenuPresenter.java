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

import nl.ru.languageininteraction.language.client.presenter.Presenter;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.model.StimuliGroup;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.service.StimuliProvider;
import nl.ru.languageininteraction.synaesthesia.client.view.MenuView;

/**
 * @since Nov 11, 2014 11:43:00 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class StimulusMenuPresenter extends MenuPresenter implements Presenter {

    private final StimuliProvider stimuliProvider;
    private final UserResults userResults;

    public StimulusMenuPresenter(RootPanel widgetTag, StimuliProvider stimuliProvider, UserResults userResults) {
        super(widgetTag);
        this.stimuliProvider = stimuliProvider;
        this.userResults = userResults;
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.stimulusMenuTitle(), titleBarListner);
    }

    @Override
    protected void setContent(final AppEventListner appEventListner) {
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.setuser);
        for (final StimuliGroup stimuliGroup : stimuliProvider.getStimuli()) {
            final String buttonLabel;
            if (userResults.getStimulusResponseGroup(stimuliGroup) != null) {
                buttonLabel = messages.stimulusMenuDoneButton(stimuliGroup.getGroupLabel());
            } else {
                buttonLabel = messages.stimulusMenuNotDoneButton(stimuliGroup.getGroupLabel());
            }
            ((MenuView) simpleView).addMenuItem(new PresenterEventListner() {

                @Override
                public void eventFired(Button button) {
                    userResults.setPendingStimuliGroup(stimuliGroup);
                    appEventListner.requestApplicationState(AppEventListner.ApplicationState.stimulus);
                }

                @Override
                public String getLabel() {
                    return buttonLabel;
                }

            });
        }
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.report);
    }
}

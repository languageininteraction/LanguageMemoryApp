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
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.service.StimuliProvider;
import nl.ru.languageininteraction.synaesthesia.client.view.MenuView;

/**
 * @since Nov 11, 2014 11:43:00 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class StimulusMenuPresenter extends MenuPresenter implements Presenter {

    private final StimuliProvider stimuliProvider;

    public StimulusMenuPresenter(RootLayoutPanel widgetTag, StimuliProvider stimuliProvider, UserResults userResults) {
        super(widgetTag, userResults);
        this.stimuliProvider = stimuliProvider;
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.stimulusMenuTitle(), titleBarListner);
    }

    private void addMenuItem(final AppEventListner appEventListner, final StimuliGroup stimuliGroup) {
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
                appEventListner.requestApplicationState(AppEventListner.ApplicationState.instructions);
            }

            @Override
            public String getLabel() {
                return buttonLabel;
            }
        }, true);
    }

    @Override
    protected void setContent(final AppEventListner appEventListner) {
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.setuser);
        ((MenuView) simpleView).addMenuLabel(messages.stimulusMenuShortTestLabel(), true);
        final StimuliGroup[] stimuliGroups = stimuliProvider.getStimuli();
        addMenuItem(appEventListner, stimuliGroups[0]);
        addMenuItem(appEventListner, stimuliGroups[1]);
        addMenuItem(appEventListner, stimuliGroups[2]);
        ((MenuView) simpleView).addSeparateMenuPanel();
        ((MenuView) simpleView).addMenuLabel(messages.stimulusMenuLongTestLabelBold(), true);
        ((MenuView) simpleView).addMenuLabel(messages.stimulusMenuLongTestLabel(), false);
        addMenuItem(appEventListner, stimuliGroups[3]);
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.report);
    }
}

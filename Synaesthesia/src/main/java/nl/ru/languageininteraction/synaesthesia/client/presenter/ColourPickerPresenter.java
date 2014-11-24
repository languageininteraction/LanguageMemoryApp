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

import nl.ru.languageininteraction.synaesthesia.client.view.ColourPickerCanvasView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import java.util.ArrayList;
import java.util.Date;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.exception.CanvasError;
import nl.ru.languageininteraction.synaesthesia.client.Messages;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.model.Stimulus;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponse;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponseGroup;

/**
 * @since Oct 10, 2014 9:52:25 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ColourPickerPresenter implements Presenter {

    private final Messages messages = GWT.create(Messages.class);
    private final RootPanel widgetTag;
    private final ArrayList<Stimulus> stimuli;
    private final int maxStimuli;
    private final UserResults userResults;
    private final ColourPickerCanvasView colourPickerCanvasView;
    private final StimuliGroup stimuliGroup;
    private Stimulus currentStimulus = null;
    private long startMs;
    private final int repeatCount;
    private int shownSetCount;
    private int shownCount = 0;

    public ColourPickerPresenter(RootPanel widgetTag, UserResults userResults, int repeatCount) throws CanvasError {
        this.widgetTag = widgetTag;
        this.stimuliGroup = userResults.getPendingStimuliGroup();
        userResults.setPendingStimuliGroup(null);
        this.stimuli = new ArrayList<>(stimuliGroup.getStimuli());
        this.userResults = userResults;
        this.repeatCount = repeatCount;
        maxStimuli = this.stimuli.size();
        colourPickerCanvasView = new ColourPickerCanvasView();
    }

    private void triggerEvent(final AppEventListner appEventListner, final ColourPickerCanvasView colourPickerCanvasView, final AppEventListner.ApplicationState nextState) {
        if (stimuli.isEmpty()) {
            shownSetCount++;
            if (repeatCount > shownSetCount) {
                this.stimuli.addAll(stimuliGroup.getStimuli());
            }
        }
        if (stimuli.isEmpty()) {
            appEventListner.requestApplicationState(nextState);
        } else {
            colourPickerCanvasView.setRandomColour();
            currentStimulus = stimuli.remove((int) (Math.random() * stimuli.size()));
            startMs = System.currentTimeMillis();
            shownCount++;
            colourPickerCanvasView.setStimulus(currentStimulus, messages.stimulusscreenprogresstext(Integer.toString(shownCount), Integer.toString(maxStimuli * repeatCount)));
        }
    }

    @Override
    public void setState(final AppEventListner appEventListner, final AppEventListner.ApplicationState prevState, final AppEventListner.ApplicationState nextState) {
        widgetTag.clear();
        final StimulusResponseGroup stimulusResponseGroup = new StimulusResponseGroup();
        userResults.addStimulusResponseGroup(stimuliGroup, stimulusResponseGroup);
        colourPickerCanvasView.setAcceptButton(new PresenterEventListner() {

            @Override
            public void eventFired(Button button) {
                stimulusResponseGroup.addResponse(currentStimulus, new StimulusResponse(colourPickerCanvasView.getColour(), new Date(), System.currentTimeMillis() - startMs));
                triggerEvent(appEventListner, colourPickerCanvasView, nextState);
            }

            @Override
            public String getLabel() {
                return messages.stimulusscreenselectbutton();
            }
        });
        colourPickerCanvasView.setRejectButton(new PresenterEventListner() {

            @Override
            public void eventFired(Button button) {
                stimulusResponseGroup.addResponse(currentStimulus, new StimulusResponse(null, new Date(), System.currentTimeMillis() - startMs));
                triggerEvent(appEventListner, colourPickerCanvasView, nextState);
            }

            @Override
            public String getLabel() {
                return messages.stimulusscreenrejectbutton();
            }
        });
        colourPickerCanvasView.setInstructions(messages.stimulusscreeninstructions(), messages.helpButtonChar());
        colourPickerCanvasView.resizeView();
        triggerEvent(appEventListner, colourPickerCanvasView, nextState);
        widgetTag.add(colourPickerCanvasView);
    }

    @Override
    public void fireBackEvent() {
    }
}

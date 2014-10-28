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
import com.google.gwt.user.client.ui.RootPanel;
import java.util.ArrayList;
import java.util.Date;
import nl.ru.languageininteraction.synaesthesia.client.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.CanvasError;
import nl.ru.languageininteraction.synaesthesia.client.Messages;
import nl.ru.languageininteraction.synaesthesia.client.Presenter;
import nl.ru.languageininteraction.synaesthesia.client.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.UserResults;
import nl.ru.languageininteraction.synaesthesia.shared.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.shared.Stimulus;
import nl.ru.languageininteraction.synaesthesia.shared.StimulusResponse;
import nl.ru.languageininteraction.synaesthesia.shared.StimulusResponseGroup;

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
    private int repeatCount;

    public ColourPickerPresenter(RootPanel widgetTag, StimuliGroup stimuliGroup, UserResults userResults, int repeatCount) throws CanvasError {
        this.widgetTag = widgetTag;
        this.stimuliGroup = stimuliGroup;
        this.stimuli = new ArrayList<>(stimuliGroup.getStimuli());
        this.userResults = userResults;
        this.repeatCount = repeatCount;
        maxStimuli = this.stimuli.size();
        colourPickerCanvasView = new ColourPickerCanvasView();
    }

    private void triggerEvent(final AppEventListner appEventListner, final ColourPickerCanvasView colourPickerCanvasView, final AppEventListner.ApplicationState nextState) {
        if (stimuli.isEmpty()) {
            repeatCount--;
            if (repeatCount > 0) {
                this.stimuli.addAll(stimuliGroup.getStimuli());
            }
        }
        if (stimuli.isEmpty()) {
            appEventListner.requestApplicationState(nextState);
        } else {
            colourPickerCanvasView.setRandomColour();
            currentStimulus = stimuli.remove((int) (Math.random() * stimuli.size()));
            startMs = System.currentTimeMillis();
            colourPickerCanvasView.setStimulus(currentStimulus, messages.stimulusscreenprogresstext(Integer.toString(maxStimuli - stimuli.size()), Integer.toString(maxStimuli)));
        }
    }

    @Override
    public void setState(final AppEventListner appEventListner, final AppEventListner.ApplicationState prevState, final AppEventListner.ApplicationState nextState) {
        widgetTag.clear();
        final StimulusResponseGroup stimulusResponseGroup = new StimulusResponseGroup();
        userResults.addStimulusResponseGroup(stimuliGroup, stimulusResponseGroup);
        colourPickerCanvasView.setButton(messages.stimulusscreenselectbutton(), new PresenterEventListner() {

            @Override
            public void eventFired() {
                stimulusResponseGroup.addResponse(currentStimulus, new StimulusResponse(colourPickerCanvasView.getColour(), new Date(), System.currentTimeMillis() - startMs));
                triggerEvent(appEventListner, colourPickerCanvasView, nextState);
            }
        });
        colourPickerCanvasView.setButton(messages.stimulusscreenrejectbutton(), new PresenterEventListner() {

            @Override
            public void eventFired() {
                stimulusResponseGroup.addResponse(currentStimulus, new StimulusResponse(null, new Date(), System.currentTimeMillis() - startMs));
                triggerEvent(appEventListner, colourPickerCanvasView, nextState);
            }
        });
        colourPickerCanvasView.setInstructions(messages.stimulusscreeninstructions());
        triggerEvent(appEventListner, colourPickerCanvasView, nextState);
        colourPickerCanvasView.resizeView();
        widgetTag.add(colourPickerCanvasView);
    }
}

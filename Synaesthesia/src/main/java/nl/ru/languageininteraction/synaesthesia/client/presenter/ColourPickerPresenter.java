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
import java.util.List;
import nl.ru.languageininteraction.synaesthesia.client.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.Messages;
import nl.ru.languageininteraction.synaesthesia.client.Presenter;
import nl.ru.languageininteraction.synaesthesia.shared.Stimulus;

/**
 * @since Oct 10, 2014 9:52:25 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ColourPickerPresenter implements Presenter {

    private final Messages messages = GWT.create(Messages.class);
    private final RootPanel widgetTag;
    private final ArrayList<Stimulus> stimuli;
    private final int maxStimuli;

    public ColourPickerPresenter(RootPanel widgetTag, List<Stimulus> stimuli) {
        this.widgetTag = widgetTag;
        this.stimuli = new ArrayList<>(stimuli);
        maxStimuli = this.stimuli.size();
    }

    private void triggerEvent(final AppEventListner appEventListner, final ColourPickerCanvasView colourPickerCanvasView) {
        if (stimuli.isEmpty()) {
            appEventListner.eventFired();
        } else {
            colourPickerCanvasView.setRandomColour();
            colourPickerCanvasView.setStimulus(stimuli.remove((int) (Math.random() * stimuli.size())), messages.stimulusscreenprogresstext(Integer.toString(maxStimuli - stimuli.size()), Integer.toString(maxStimuli)));
        }
    }

    @Override
    public void setState(final AppEventListner appEventListner) {
        widgetTag.clear();
        final ColourPickerCanvasView colourPickerCanvasView = new ColourPickerCanvasView();
        colourPickerCanvasView.setButton(messages.stimulusscreenrejectbutton(), new AppEventListner() {

            @Override
            public void eventFired() {
                triggerEvent(appEventListner, colourPickerCanvasView);
            }
        });
        colourPickerCanvasView.setButton(messages.stimulusscreenselectbutton(), new AppEventListner() {

            @Override
            public void eventFired() {
                triggerEvent(appEventListner, colourPickerCanvasView);
            }
        });
        colourPickerCanvasView.setInstructions(messages.stimulusscreeninstructions());
        triggerEvent(appEventListner, colourPickerCanvasView);
        colourPickerCanvasView.resizeView();
        widgetTag.add(colourPickerCanvasView);
    }
}

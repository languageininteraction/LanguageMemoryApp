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
package nl.ru.languageininteraction.synaesthesia.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @since Oct 10, 2014 9:52:25 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ColourPickerPresenter implements Presenter {

    private final Messages messages = GWT.create(Messages.class);
    private final RootPanel widgetTag;
    private int stimulusCounter = 10;

    public ColourPickerPresenter(RootPanel widgetTag) {
        this.widgetTag = widgetTag;
    }

    public void setState(final AppEventListner appEventListner) {
        widgetTag.clear();
        final ColourPickerCanvasView colourPickerCanvasView = new ColourPickerCanvasView();
        colourPickerCanvasView.setButton(messages.stimulusscreenrejectbutton(), new AppEventListner() {

            public void eventFired() {
                stimulusCounter--;
                if (stimulusCounter > 0) {
                    colourPickerCanvasView.setRandomColour();
                    colourPickerCanvasView.setStimulus(Integer.toString(stimulusCounter), messages.stimulusscreenprogresstext(Integer.toString(stimulusCounter), Integer.toString(10)));
                } else {
                    appEventListner.eventFired();
                }
            }
        });
        colourPickerCanvasView.setButton(messages.stimulusscreenselectbutton(), new AppEventListner() {

            public void eventFired() {
                stimulusCounter--;
                if (stimulusCounter > 0) {
                    colourPickerCanvasView.setRandomColour();
                    colourPickerCanvasView.setStimulus(Integer.toString(stimulusCounter), messages.stimulusscreenprogresstext(Integer.toString(stimulusCounter), Integer.toString(10)));
                } else {
                    appEventListner.eventFired();
                }
            }
        });
        colourPickerCanvasView.setInstructions(messages.stimulusscreeninstructions());
        colourPickerCanvasView.setStimulus(Integer.toString(stimulusCounter), messages.stimulusscreenprogresstext(Integer.toString(stimulusCounter), Integer.toString(10)));
        colourPickerCanvasView.setRandomColour();
        colourPickerCanvasView.resizeView();
        widgetTag.add(colourPickerCanvasView);
    }
}

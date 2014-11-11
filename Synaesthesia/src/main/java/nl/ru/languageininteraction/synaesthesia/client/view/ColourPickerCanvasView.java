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
package nl.ru.languageininteraction.synaesthesia.client.view;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.synaesthesia.client.exception.CanvasError;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.model.ColourData;
import nl.ru.languageininteraction.synaesthesia.client.model.Stimulus;

/**
 * @since Oct 8, 2014 5:09:10 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ColourPickerCanvasView extends AbstractView {

    private final Canvas mainCanvas;
    private final Canvas hueCanvas;
    private final Grid outerGrid;
    private final Grid innerGrid;
    private final Grid pickerPanel;
    private final VerticalPanel stimulusPanel;
    private final VerticalPanel selectedColourPanel;
    private final Button infoButton;
    private Button acceptButton = null;
    private Button rejectButton = null;
    private final Label progressLabel;
    private final int height;
    private final int width;
    private final int barWidth;
    private final int stimulusTextHeight;
    private final int buttonHeight;
    private final int buttonTextHeight;
    private final int buttonWidth;
    private final int selectedColourPanelHeight;
    private final int selectedColourPanelWidth;
    private ColourData selectedColourData = null;

    public ColourPickerCanvasView() throws CanvasError {
        setStylePrimaryName("stimulusScreen");
        final int clientHeight = Window.getClientHeight();
        final int clientWidth = Window.getClientWidth();
        final int minClient = (clientHeight > clientWidth) ? clientWidth : clientHeight;
        height = (int) (minClient * 0.9);
        width = (int) (minClient * 0.8);
        barWidth = (int) (minClient * 0.1);
        stimulusTextHeight = (int) (minClient * 0.09);
        selectedColourPanelHeight = (int) (minClient * 0.25);
        selectedColourPanelWidth = (int) (minClient * 0.47);
        buttonTextHeight = (int) (minClient * 0.05);
        buttonHeight = (int) (minClient * 0.1);
        buttonWidth = (int) (minClient * 0.47);
        stimulusPanel = new VerticalPanel();
        stimulusPanel.addStyleName("stimulusPanel");
        outerGrid = new Grid(2, 2);
        innerGrid = new Grid(5, 2);
        pickerPanel = new Grid(1, 2);
        infoButton = new Button();
        infoButton.setStylePrimaryName("stimulusHelpButton");
        selectedColourPanel = new VerticalPanel();
        progressLabel = new Label();
        mainCanvas = Canvas.createIfSupported();
        hueCanvas = Canvas.createIfSupported();

        if (mainCanvas == null || hueCanvas == null) {
            throw new CanvasError("Failed to create a canvas for the stimulus screen.");
        } else {
            mainCanvas.setCoordinateSpaceHeight(height);
            mainCanvas.setCoordinateSpaceWidth(width);
            mainCanvas.setSize(width + "px", height + "px");
            hueCanvas.setCoordinateSpaceHeight(height);
            hueCanvas.setCoordinateSpaceWidth(barWidth);
            hueCanvas.setSize(barWidth + "px", height + "px");
            final Context2d hueContext2d = hueCanvas.getContext2d();

            CanvasGradient hueGradient = hueContext2d.createLinearGradient(0, 0, 0, height);
            for (int stop = 0; stop <= 10; stop++) {
                hueGradient.addColorStop(stop * 0.1f, "hsl(" + 36 * stop + ",100%,50%);");
            }
            hueContext2d.setFillStyle(hueGradient);
            hueContext2d.fillRect(0, 0, barWidth, height);

            pickerPanel.setCellPadding(5);
            pickerPanel.setWidget(0, 0, mainCanvas);
            pickerPanel.setWidget(0, 1, hueCanvas);
            final Label selectedColourLabel = new Label("");
            selectedColourLabel.setHeight(selectedColourPanelHeight + "px");
            selectedColourLabel.setWidth(selectedColourPanelWidth + "px");
            selectedColourPanel.add(selectedColourLabel);
            mainCanvas.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    event.preventDefault();
                    setColour(event, mainCanvas, selectedColourPanel);
                }
            });
            hueCanvas.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    event.preventDefault();
                    setHue(event, hueCanvas);
                    setColour(event, hueCanvas, selectedColourPanel);
                }
            });
            mainCanvas.addTouchStartHandler(new TouchStartHandler() {

                @Override
                public void onTouchStart(TouchStartEvent event) {
                    event.preventDefault();
                    setColour(event, mainCanvas, selectedColourPanel);
                }
            });
            mainCanvas.addTouchMoveHandler(new TouchMoveHandler() {

                @Override
                public void onTouchMove(TouchMoveEvent event) {
                    event.preventDefault();
                    setColour(event, mainCanvas, selectedColourPanel);
                }
            });
            mainCanvas.addTouchEndHandler(new TouchEndHandler() {

                @Override
                public void onTouchEnd(TouchEndEvent event) {
                    event.preventDefault();
                    setColour(event, mainCanvas, selectedColourPanel);
                }
            });
            hueCanvas.addTouchStartHandler(new TouchStartHandler() {

                @Override
                public void onTouchStart(TouchStartEvent event) {
                    event.preventDefault();
                    setHue(event, hueCanvas);
                    setColour(event, hueCanvas, selectedColourPanel);
                }
            });
            hueCanvas.addTouchMoveHandler(new TouchMoveHandler() {

                @Override
                public void onTouchMove(TouchMoveEvent event) {
                    event.preventDefault();
                    setHue(event, hueCanvas);
                    setColour(event, hueCanvas, selectedColourPanel);
                }
            });
            hueCanvas.addTouchEndHandler(new TouchEndHandler() {

                @Override
                public void onTouchEnd(TouchEndEvent event) {
                    event.preventDefault();
                    setHue(event, hueCanvas);
                    setColour(event, hueCanvas, selectedColourPanel);
                }
            });
        }
        outerGrid.setWidget(0, 0, pickerPanel);
        add(outerGrid);
    }

    public void setRandomColour() {
        final double randomRad = Random.nextDouble() * 2;
        final double red = (Math.sin(randomRad) * 128 + 127);
        final double green = (Math.sin(randomRad - 1.667) * 128 + 127);
        final double blue = (Math.sin(randomRad + 1.667) * 128 + 127);
//        System.out.println("red:" + (red));
//        System.out.println("green:" + (green));
//        System.out.println("blue:" + (blue));
//        System.out.println("red+green+blue:" + (red + green + blue));
        setHue((int) red, (int) green, (int) blue);
        selectedColourData = new ColourData((int) red, (int) green, (int) blue);
        selectedColourPanel.getElement().setAttribute("style", "background:rgb(" + (int) red + "," + (int) green + "," + (int) blue + ")");
    }

    private void setHue(int red, int green, int blue) {
        final Context2d mainContext2d = mainCanvas.getContext2d();
        CanvasGradient linearColour = mainContext2d.createLinearGradient(0, 0, width, 0);
        linearColour.addColorStop(1f, "white");
        linearColour.addColorStop(0f, "rgb(" + red + "," + green + "," + blue + ")");
        mainContext2d.setFillStyle(linearColour);
        mainContext2d.fillRect(0, 0, width, height);

        CanvasGradient linearGrey = mainContext2d.createLinearGradient(0, 0, 0, height);
        linearGrey.addColorStop(1f, "black");
        linearGrey.addColorStop(0f, "rgba(0,0,0,0);");
        mainContext2d.setFillStyle(linearGrey);
        mainContext2d.fillRect(0, 0, width, height);
    }

    public ColourData getColour() {
        return selectedColourData;
    }

    private void setColour(MouseEvent event, Canvas targetCanvas, VerticalPanel targetPanel) {
        setColour(event.getRelativeX(targetCanvas.getElement()), event.getRelativeY(targetCanvas.getElement()), targetCanvas, targetPanel);
    }

    private void setColour(TouchEvent event, Canvas targetCanvas, VerticalPanel targetPanel) {
        if (event.getTouches().length() > 0) {
            final JsArray<Touch> touches = event.getTargetTouches();
            if (touches.length() > 0) {
                Touch touch = touches.get(0);
                setColour(touch.getRelativeX(targetCanvas.getElement()), touch.getRelativeY(targetCanvas.getElement()), targetCanvas, targetPanel);
            }
        }
    }

    private void setColour(int x, int y, Canvas targetCanvas, VerticalPanel targetPanel) {
        x = (x >= targetCanvas.getCoordinateSpaceWidth()) ? targetCanvas.getCoordinateSpaceWidth() - 1 : x;
        y = (y >= targetCanvas.getCoordinateSpaceHeight()) ? targetCanvas.getCoordinateSpaceHeight() - 1 : y;
        final ImageData imageData = targetCanvas.getContext2d().getImageData(x, y, 1, 1);
        final int blue = imageData.getBlueAt(0, 0);
        final int green = imageData.getGreenAt(0, 0);
        final int red = imageData.getRedAt(0, 0);
        if (targetPanel.equals(selectedColourPanel)) {
            selectedColourData = new ColourData(red, green, blue);
        }
        targetPanel.getElement().setAttribute("style", "background:rgb(" + red + "," + green + "," + blue + ")");
    }

    private void setHue(TouchEvent event, Canvas targetCanvas) {
        if (event.getTouches().length() > 0) {
            final JsArray<Touch> touches = event.getTargetTouches();
            if (touches.length() > 0) {
                Touch touch = touches.get(0);
                setHue(touch.getRelativeX(targetCanvas.getElement()), touch.getRelativeY(targetCanvas.getElement()), targetCanvas);
            }
        }
    }

    private void setHue(MouseEvent event, Canvas targetCanvas) {
        setHue(event.getRelativeX(targetCanvas.getElement()), event.getRelativeY(targetCanvas.getElement()), targetCanvas);
    }

    private void setHue(int x, int y, Canvas targetCanvas) {
        x = (x >= targetCanvas.getCoordinateSpaceWidth()) ? targetCanvas.getCoordinateSpaceWidth() - 1 : x;
        y = (y >= targetCanvas.getCoordinateSpaceHeight()) ? targetCanvas.getCoordinateSpaceHeight() - 1 : y;
        final ImageData imageData = targetCanvas.getContext2d().getImageData(x, y, 1, 1);
        final int blue = imageData.getBlueAt(0, 0);
        final int green = imageData.getGreenAt(0, 0);
        final int red = imageData.getRedAt(0, 0);
        setHue(red, green, blue);
    }

    public void setInstructions(final String instructions, final String infoButtonChar) {
        final Label instructionsLabel = new Label(instructions);
        final PopupPanel popupPanel = new PopupPanel(true);
        popupPanel.setStylePrimaryName("stimulusHelpPanel");
        instructionsLabel.setStylePrimaryName("stimulusHelpText");
        popupPanel.setWidget(new ScrollPanel(instructionsLabel));
        infoButton.setText(infoButtonChar);
        popupPanel.addCloseHandler(new CloseHandler<PopupPanel>() {

            @Override
            public void onClose(CloseEvent<PopupPanel> event) {
                infoButton.setEnabled(true);
            }
        });
        infoButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                popupPanel.center();
                infoButton.setEnabled(false);
            }
        });
    }

    public void setStimulus(Stimulus stimulus, String progress) {
        progressLabel.setText(progress);
        stimulusPanel.clear();
        final Label label = new Label(stimulus.getValue());
        label.getElement().getStyle().setFontSize(stimulusTextHeight, Unit.PX);
        stimulusPanel.add(label);
    }

    public void setAcceptButton(String buttonText, final PresenterEventListner presenterListerner) {
        acceptButton = getButton(buttonText, presenterListerner);
    }

    public void setRejectButton(String buttonText, final PresenterEventListner presenterListerner) {
        rejectButton = getButton(buttonText, presenterListerner);
    }

    private Button getButton(String buttonText, final PresenterEventListner presenterListerner) {
        final Button nextButton = new Button(buttonText);
        nextButton.setHeight(buttonHeight + "px");
        nextButton.setWidth(buttonWidth + "px");
        nextButton.addStyleName("stimulusButton");
        nextButton.getElement().getStyle().setFontSize(buttonTextHeight, Unit.PX);
        nextButton.setEnabled(true);
        nextButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                presenterListerner.eventFired(nextButton);
            }
        });
        return nextButton;
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        if (height < width) {
            innerGrid.setWidget(0, 0, stimulusPanel);
            innerGrid.setWidget(1, 0, rejectButton);
            innerGrid.setWidget(2, 0, selectedColourPanel);
            innerGrid.setWidget(3, 0, acceptButton);
            innerGrid.setWidget(4, 0, progressLabel);
            innerGrid.setWidget(4, 1, infoButton);
            outerGrid.setWidget(0, 1, innerGrid);
        } else {
            innerGrid.setWidget(0, 0, stimulusPanel);
            innerGrid.setWidget(1, 0, rejectButton);
            innerGrid.setWidget(0, 1, selectedColourPanel);
            innerGrid.setWidget(1, 1, acceptButton);
            innerGrid.setWidget(2, 0, progressLabel);
            innerGrid.setWidget(2, 1, infoButton);
            outerGrid.setWidget(1, 0, innerGrid);
        }
    }
}

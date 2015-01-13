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
import com.google.gwt.user.client.ui.HasVerticalAlignment;
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
    private final Grid progressPanel;
    private final VerticalPanel stimulusPanel;
    private final VerticalPanel selectedColourPanel;
    private final Button infoButton;
    private final Button quitButton;
    private Button acceptButton = null;
    private Button rejectButton = null;
    private final Label progressLabel;
    private int canvasHeight;
    private int canvasWidth;
    private String currentHueCss;
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
        stimulusTextHeight = (int) (minClient * 0.08);
        selectedColourPanelHeight = (int) (minClient * 0.25);
        selectedColourPanelWidth = (int) (minClient * 0.47);
        buttonTextHeight = (int) (minClient * 0.05);
        buttonHeight = (int) (minClient * 0.1);
        buttonWidth = (int) (minClient * 0.47);
        stimulusPanel = new VerticalPanel();
        stimulusPanel.addStyleName("stimulusPanel");
        stimulusPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        outerGrid = new Grid(2, 2);
        innerGrid = new Grid(6, 2);
        pickerPanel = new Grid(1, 2);
        progressPanel = new Grid(1, 2);
        progressPanel.setWidth("100%");
        infoButton = new Button();
        infoButton.addStyleName("stimulusHelpButton");
        infoButton.getElement().getStyle().setFontSize(buttonTextHeight, Unit.PX);
        quitButton = new Button();
        quitButton.addStyleName("stimulusQuitButton");
        quitButton.getElement().getStyle().setFontSize(buttonTextHeight, Unit.PX);
        selectedColourPanel = new VerticalPanel();
        progressLabel = new Label();
        progressLabel.addStyleName("stimulusProgressLabel");
        mainCanvas = Canvas.createIfSupported();
        hueCanvas = Canvas.createIfSupported();

        if (mainCanvas == null || hueCanvas == null) {
            throw new CanvasError("Failed to create a canvas for the stimulus screen.");
        } else {
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

    private void sizeAndPaintCanvases(final int canvasHeight, final int canvasWidth, final int barWidth) {
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
        mainCanvas.setCoordinateSpaceHeight(canvasHeight);
        mainCanvas.setCoordinateSpaceWidth(canvasWidth);
        mainCanvas.setSize(canvasWidth + "px", canvasHeight + "px");
        hueCanvas.setCoordinateSpaceHeight(canvasHeight);
        hueCanvas.setCoordinateSpaceWidth(barWidth);
        hueCanvas.setSize(barWidth + "px", canvasHeight + "px");
        final Context2d hueContext2d = hueCanvas.getContext2d();

        CanvasGradient hueGradient = hueContext2d.createLinearGradient(0, 0, 0, canvasHeight);
        for (int stop = 0; stop <= 10; stop++) {
            hueGradient.addColorStop(stop * 0.1f, "hsl(" + 36 * stop + ",100%,50%);");
        }
        hueContext2d.setFillStyle(hueGradient);
        hueContext2d.fillRect(0, 0, barWidth, canvasHeight);
    }

    public void setRandomColour() {
        final int randomHue = Random.nextInt(360);
        setHue("hsl(" + randomHue + ",100%,50%)");
        final int red = Random.nextInt(255);
        final int green = Random.nextInt(255);
        final int blue = Random.nextInt(255);
        selectedColourData = new ColourData((int) red, (int) green, (int) blue);
        selectedColourPanel.getElement().setAttribute("style", "background:rgb(" + (int) red + "," + (int) green + "," + (int) blue + ")");
    }

    // todo: remove this if it proves unhelpful on samsung 4.2.2
    private static boolean hueChangeInProgress = false;

    synchronized private void setHue(int red, int green, int blue) {
        setHue("rgb(" + red + "," + green + "," + blue + ")");
    }

    synchronized private void setHue(String colourCss) {
        currentHueCss = colourCss;
        // " Android clearRect / fillRect bug" ???
        // GWT documentation: JavaScript interpreters are single-threaded, so while GWT silently accepts the synchronized keyword, it has no real effect.
        // So we are using a simple boolean which should be adequate most of the time. We could use a timer call back, but we want to keep this simple.
        // However the browser is probably only single threaded anyway.
        if (hueChangeInProgress) {
            return;
        }
        hueChangeInProgress = true;
        final Context2d mainContext2dA = mainCanvas.getContext2d();
        CanvasGradient linearColour = mainContext2dA.createLinearGradient(0, 0, canvasWidth, 0);
        linearColour.addColorStop(1f, "white");
        linearColour.addColorStop(0f, colourCss);
        mainContext2dA.setFillStyle(linearColour);
        mainContext2dA.fillRect(0, 0, canvasWidth, canvasHeight);

        // todo: remove the second context get if it proves unhelpful witht the samsung 4.2.2 issue
        final Context2d mainContext2dB = mainCanvas.getContext2d();
        CanvasGradient linearGrey = mainContext2dB.createLinearGradient(0, 0, 0, canvasHeight);
        linearGrey.addColorStop(1f, "black");
        linearGrey.addColorStop(0f, "rgba(0,0,0,0);");
        mainContext2dB.setFillStyle(linearGrey);
        mainContext2dB.fillRect(0, 0, canvasWidth, canvasHeight);
        hueChangeInProgress = false;
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

    public void setQuitButton(final PresenterEventListner quitListerner) {
        quitButton.setText(quitListerner.getLabel());
        quitButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                event.preventDefault();
                quitListerner.eventFired(quitButton);
            }
        });
        quitButton.addTouchEndHandler(new TouchEndHandler() {

            @Override
            public void onTouchEnd(TouchEndEvent event) {
                event.preventDefault();
                quitListerner.eventFired(quitButton);
            }
        });
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
                event.preventDefault();
                popupPanel.center();
                infoButton.setEnabled(false);
            }
        });
        infoButton.addTouchEndHandler(new TouchEndHandler() {

            @Override
            public void onTouchEnd(TouchEndEvent event) {
                event.preventDefault();
                popupPanel.center();
                infoButton.setEnabled(false);
            }
        });
        instructionsLabel.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                event.preventDefault();
                popupPanel.hide();
                infoButton.setEnabled(true);
            }
        });
        instructionsLabel.addTouchEndHandler(new TouchEndHandler() {

            @Override
            public void onTouchEnd(TouchEndEvent event) {
                event.preventDefault();
                popupPanel.hide();
                infoButton.setEnabled(true);
            }
        });
    }

    public void setStimulus(Stimulus stimulus, String progress) {
        progressLabel.setText(progress);
        stimulusPanel.clear();
        final Label label = new Label(stimulus.getValue());
        final int length = stimulus.getValue().length();
        final int stimulusSpecificHeight = (length > 3) ? stimulusTextHeight : (int) (stimulusTextHeight * 3.0 / length);
        stimulusPanel.setHeight(stimulusTextHeight * 3 + "px");
        label.getElement().getStyle().setFontSize(stimulusSpecificHeight, Unit.PX);
        stimulusPanel.add(label);
    }

    public void setAcceptButton(final PresenterEventListner presenterListerner) {
        acceptButton = getButton(presenterListerner);
    }

    public void setRejectButton(final PresenterEventListner presenterListerner) {
        rejectButton = getButton(presenterListerner);
    }

    private Button getButton(final PresenterEventListner presenterListerner) {
        final Button nextButton = new Button(presenterListerner.getLabel());
        nextButton.setHeight(buttonHeight + "px");
        nextButton.setWidth(buttonWidth + "px");
        nextButton.addStyleName("stimulusButton");
        nextButton.getElement().getStyle().setFontSize(buttonTextHeight, Unit.PX);
        nextButton.setEnabled(true);
        nextButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                event.preventDefault();
                presenterListerner.eventFired(nextButton);
            }
        });
        nextButton.addTouchEndHandler(new TouchEndHandler() {

            @Override
            public void onTouchEnd(TouchEndEvent event) {
                event.preventDefault();
                presenterListerner.eventFired(nextButton);
            }
        });
        return nextButton;
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        if (height < width) {
            int resizedHeight = (int) (height - 50);
            int resizedBarWidth = (int) (resizedHeight * 0.1);
            int resizedWidth = (int) (width - 50) - resizedBarWidth - buttonWidth - buttonHeight; // buttonHeight is used to allow for the info button
            sizeAndPaintCanvases(resizedHeight, resizedWidth, resizedBarWidth);
            setHue(currentHueCss);
            innerGrid.setWidget(0, 0, stimulusPanel);
            innerGrid.setWidget(1, 0, rejectButton);
            innerGrid.setWidget(2, 0, selectedColourPanel);
            innerGrid.setWidget(3, 0, acceptButton);
            innerGrid.setWidget(4, 0, progressLabel);
            progressPanel.setWidget(0, 0, infoButton);
            progressPanel.setWidget(0, 1, quitButton);
            innerGrid.setWidget(5, 0, progressPanel);
            outerGrid.setWidget(0, 1, innerGrid);
        } else {
            int resizedWidth = (int) (width * 0.9 - 50);
            int resizedBarWidth = (int) (width * 0.1);
            int resizedHeight = height - 50 - selectedColourPanelHeight - buttonHeight - buttonHeight;
            sizeAndPaintCanvases(resizedHeight, resizedWidth, resizedBarWidth);
            setHue(currentHueCss);
            innerGrid.setWidget(0, 0, stimulusPanel);
            innerGrid.setWidget(1, 0, rejectButton);
            innerGrid.setWidget(0, 1, selectedColourPanel);
            innerGrid.setWidget(1, 1, acceptButton);
            progressPanel.setWidget(0, 1, progressLabel);
            innerGrid.setWidget(2, 0, progressPanel);
            innerGrid.setWidget(2, 1, quitButton);
            progressPanel.setWidget(0, 0, infoButton);
            outerGrid.setWidget(1, 0, innerGrid);
        }
    }
}

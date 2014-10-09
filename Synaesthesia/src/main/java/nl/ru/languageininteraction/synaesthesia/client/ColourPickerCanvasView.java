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

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @since Oct 8, 2014 5:09:10 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ColourPickerCanvasView extends HorizontalPanel {

    private final Canvas canvas;

    public ColourPickerCanvasView() {
        int height = 300;
        int width = 300;
        canvas = Canvas.createIfSupported();
        if (canvas == null) {
            add(new Label("canvas is unsupported"));
            return;
        } else {
            canvas.setSize(width + "px", height + "px");
            final Context2d context2d = canvas.getContext2d();
            CanvasGradient lingrad = context2d.createLinearGradient(0, 0, width, height);
            lingrad.addColorStop(0.0f, "black");
//            lingrad.addColorStop(0.5f, "#fff");
//            lingrad.addColorStop(0.5f, "#26C000");
            lingrad.addColorStop(1, "#fff");

//            final double columnCount = 10;
//            final double rowCount = 100;
//            for (int row = 0; row < rowCount; row += 2) {
////            for (int column = 0; column < columnCount; column++) {
////                final Element element = getCellFormatter().getElement(row, column);
//                final int rowValue = 255 - (int) (row / (rowCount - 1) * 255);
////                final int columnValue = (int) (column / (columnCount - 1) * 255);
////                final int colourValue = (int) (columnValue * (1 - row / (rowCount - 1)));
////                element.setAttribute("style", "height:5px;width:5px;border:0px none;font-size:1px;color:#FFFFFF;background:rgb(" + colourValue + "," + rowValue + "," + rowValue + ")");
//                CssColor colorGreen = CssColor.make(rowValue, rowValue, rowValue);
//                context2d.setFillStyle(colorGreen);
//                context2d.fillRect(0, ((double) height) / rowCount * row, width, ((double) height) / rowCount);
////            }
//            }
//            CssColor colorGreen = CssColor.make(200, 0, 0);
//            context2d.setFillStyle(colorGreen);
            context2d.setFillStyle(lingrad);
            context2d.fillRect(50, 50, width, height);
            add(canvas);
            final Label selectedColourLabel = new Label("SelectedColour");
            add(selectedColourLabel);
            final Label hoverColourLabel = new Label("HoverColour");
            add(hoverColourLabel);

            canvas.addMouseMoveHandler(new MouseMoveHandler() {

                public void onMouseMove(MouseMoveEvent event) {
                    final ImageData imageData = context2d.getImageData(event.getClientX(), event.getClientY(), 1, 1);
                    final int blue = imageData.getBlueAt(0, 0);
                    final int green = imageData.getGreenAt(0, 0);
                    final int red = imageData.getRedAt(0, 0);
                    hoverColourLabel.getElement().setAttribute("style", "background:rgb(" + red + "," + green + "," + blue + ")");
                }
            });

            canvas.addClickHandler(new ClickHandler() {

                public void onClick(ClickEvent event) {
                    final ImageData imageData = context2d.getImageData(event.getClientX(), event.getClientY(), 1, 1);
                    final int blue = imageData.getBlueAt(0, 0);
                    final int green = imageData.getGreenAt(0, 0);
                    final int red = imageData.getRedAt(0, 0);
                    selectedColourLabel.getElement().setAttribute("style", "background:rgb(" + red + "," + green + "," + blue + ")");
                }
            });
        }
    }
}

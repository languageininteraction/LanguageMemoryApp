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

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;

/**
 * @since Oct 10, 2014 2:40:58 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class AbstractView extends DockLayoutPanel {

    public AbstractView() {
        super(Style.Unit.PX);
        setWidth(Window.getClientWidth() + "px");
        setHeight(Window.getClientHeight() + "px");
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                int height = event.getHeight();
                setHeight(height + "px");
                int width = event.getWidth();
                setWidth(width + "px");
                parentResized(height, width, "px");
            }
        });
    }

    public void resizeView() {
        int height = Window.getClientHeight();
        setHeight(height + "px");
        int width = Window.getClientWidth();
        setWidth(width + "px");
        parentResized(height, width, "px");
    }

    abstract protected void parentResized(int height, int width, String units);
}

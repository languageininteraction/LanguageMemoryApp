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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import nl.ru.languageininteraction.synaesthesia.client.ScreenPresenter.PresenterState;

/**
 * @since Oct 7, 2014 2:06:28 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class SimpleView extends FlowPanel {

    public SimpleView() {
//        super(Unit.EM);
    }

    public void clearAll() {
        clear();
    }

    protected void setDisplayText(String text) {
        add(new HTML(text));
    }

    protected void setButton(String buttonText, final PresenterState clickState, final ScreenPresenter presenter) {
        final Button nextButton = new Button(buttonText);
        nextButton.addStyleName("nextButton");
        nextButton.setEnabled(false);
        add(nextButton);
        nextButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                presenter.setNextState(clickState);
            }
        });
    }

//    DockLayoutPanel p = new DockLayoutPanel();
//p.addNorth(new HTML("header"), 2);
//p.addSouth(new HTML("footer"), 2);
//p.addWest(new HTML("navigation"), 10);
//p.add(new HTML(content));
}

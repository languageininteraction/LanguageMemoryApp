/*
 * Copyright (C) 2015 Language In Interaction
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

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.SingleShotEventListner;

/**
 * @since Feb 24, 2015 2:30:02 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MenuButton extends HorizontalPanel {

    public MenuButton(final PresenterEventListner presenterListerner) {
        final Label headerIcon = new Label();
        headerIcon.addStyleName("headerIcon");
        if (presenterListerner != null) {
            final Label headerArrow = new Label();
            headerArrow.addStyleName("headerArrow");
            this.add(headerArrow);

            final Label headerButton = new Label(presenterListerner.getLabel());
            headerButton.addStyleName("headerButton");
            this.add(headerButton);
            SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

                @Override
                protected void singleShotFired() {
                    presenterListerner.eventFired(null);
                }
            };
            headerArrow.addClickHandler(singleShotEventListner);
            headerButton.addClickHandler(singleShotEventListner);
            headerIcon.addClickHandler(singleShotEventListner);

            headerArrow.addTouchStartHandler(singleShotEventListner);
            headerButton.addTouchStartHandler(singleShotEventListner);
            headerIcon.addTouchStartHandler(singleShotEventListner);
            headerArrow.addTouchMoveHandler(singleShotEventListner);
            headerButton.addTouchMoveHandler(singleShotEventListner);
            headerIcon.addTouchMoveHandler(singleShotEventListner);
            headerArrow.addTouchEndHandler(singleShotEventListner);
            headerButton.addTouchEndHandler(singleShotEventListner);
            headerIcon.addTouchEndHandler(singleShotEventListner);
        }
        this.add(headerIcon);
    }
}

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

import nl.ru.languageininteraction.synaesthesia.client.view.SimpleView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @since Oct 7, 2014 2:17:51 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class IntroPresenter implements Presenter {

    private final Messages messages = GWT.create(Messages.class);
    private final RootPanel widgetTag;
    final SimpleView simpleView = new SimpleView();

    public IntroPresenter(RootPanel widgetTag) {
        this.widgetTag = widgetTag;
    }

    public void setState(final AppEventListner appEventListner) {
        widgetTag.clear();
        simpleView.setButton(messages.nextbutton(), new AppEventListner() {

            public void eventFired() {
                appEventListner.eventFired();
            }

        });
        simpleView.addTitle(messages.introductionscreentitle());
        simpleView.setDisplayText(messages.introductionscreentext());
        simpleView.resizeView();
        widgetTag.add(simpleView);
    }
}

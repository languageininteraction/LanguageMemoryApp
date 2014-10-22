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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.synaesthesia.client.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.Messages;
import nl.ru.languageininteraction.synaesthesia.client.Presenter;
import nl.ru.languageininteraction.synaesthesia.client.view.SimpleView;

/**
 * @since Oct 22, 2014 3:00:25 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ErrorPresenter implements Presenter {

    private final Messages messages = GWT.create(Messages.class);
    private final RootPanel widgetTag;
    private final SimpleView simpleView = new SimpleView();
    private final String errorMessage;

    public ErrorPresenter(RootPanel widgetTag, String errorMessage) {
        this.widgetTag = widgetTag;
        this.errorMessage = errorMessage;
    }

    @Override
    public void setState(final AppEventListner appEventListner) {
        widgetTag.clear();
        simpleView.setButton(messages.nextbutton(), new AppEventListner() {

            @Override
            public void eventFired() {
                appEventListner.eventFired();
            }

        });
        simpleView.addTitle(messages.errorScreenTitle());
        simpleView.setDisplayText(messages.errorScreenText(errorMessage));
        simpleView.resizeView();
        widgetTag.add(simpleView);
    }
}

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
 * @since Oct 7, 2014 2:17:51 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ScreenPresenter {

    private final Messages messages = GWT.create(Messages.class);
    private final RootPanel widgetTag;

    protected enum PresenterState {

        intro, stimulus, feedback, end
    }

    public ScreenPresenter(RootPanel widgetTag) {
        this.widgetTag = widgetTag;
    }

    protected void setState(PresenterState state) {
        switch (state) {
            case intro:
                final SimpleView simpleView = new SimpleView();
                simpleView.setDisplayText(messages.nl_ru_languageininteraction_synaesthesia_introductionscreentext());
                widgetTag.add(simpleView);
                break;
            case feedback:
                break;
        }
    }

    protected void setNextState(PresenterState state) {

    }
}

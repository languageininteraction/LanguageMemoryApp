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

import com.google.gwt.user.client.ui.RootPanel;

/**
 * @since Oct 7, 2014 11:07:35 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AppController implements AppEventListner {

    protected enum ApplicationState {

        start, intro, metadata, stimulus, report, feedback, registration, moreinfo, end
    }
    private final RootPanel widgetTag;
    private Presenter presenter;
    private ApplicationState state = ApplicationState.start;
    private final UserResults userResults;

    public AppController(RootPanel widgetTag) {
        this.widgetTag = widgetTag;
        userResults = new UserResults("todo", "metadata collection screen");
    }

    public void eventFired() {
        switch (state) {
            case start:
                state = ApplicationState.intro;
                this.presenter = new IntroPresenter(widgetTag);
                presenter.setState(this);
                break;
            case intro:
                state = ApplicationState.metadata;
                this.presenter = new MetadataPresenter(widgetTag);
                presenter.setState(this);
                break;
            case metadata:
                state = ApplicationState.stimulus;
                this.presenter = new ColourPickerPresenter(widgetTag);
                presenter.setState(this);
                break;
            case stimulus:
                state = ApplicationState.report;
                this.presenter = new ReportPresenter(widgetTag, userResults);
                presenter.setState(this);
                break;
            case report:
                state = ApplicationState.feedback;
                this.presenter = new FeedbackPresenter(widgetTag);
                presenter.setState(this);
                break;
            case feedback:
                state = ApplicationState.end;
                this.presenter = new ScreenPresenter(widgetTag);
                presenter.setState(this);
                break;
            default:
//                state = ApplicationState.start;
//                presenter.setState(this);
                break;
        }
    }
}

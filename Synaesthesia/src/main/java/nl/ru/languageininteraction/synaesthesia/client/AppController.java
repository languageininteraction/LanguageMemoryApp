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

import nl.ru.languageininteraction.synaesthesia.client.presenter.ReportPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.FeedbackPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.ScreenPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.ColourPickerPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.RegisterPresenter;
import com.google.gwt.user.client.ui.RootPanel;
import java.util.logging.Logger;
import nl.ru.languageininteraction.synaesthesia.client.presenter.ErrorPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.MetadataPresenter;
import nl.ru.languageininteraction.synaesthesia.shared.StimuliGroup;

/**
 * @since Oct 7, 2014 11:07:35 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AppController implements AppEventListner {

    private static final Logger logger = Logger.getLogger(AppController.class.getName());

    protected enum ApplicationState {

        start, intro, metadata, stimulus, report, feedback, registration, moreinfo, end
    }
    private final RootPanel widgetTag;
    private Presenter presenter;
    private ApplicationState state = ApplicationState.start;
    private final UserResults userResults;
    private final StimuliProvider stimuliProvider;

    public AppController(RootPanel widgetTag) {
        this.widgetTag = widgetTag;
        stimuliProvider = new StimuliProvider();
        userResults = new UserResults();
        try {
            final StimuliGroup[] stimuli = stimuliProvider.getStimuli();
            userResults.addDummyResults(stimuli[0]);
            userResults.addDummyResults(stimuli[1]);
            userResults.addDummyResults(stimuli[2]);
        } catch (StimulusError error) {
            logger.severe("failed to create dummy user results:");
            logger.severe(error.getMessage());
        }
    }

    @Override
    public void eventFired() {
        try {
            switch (state) {
                case start:
                    state = ApplicationState.intro;
                    this.presenter = new IntroPresenter(widgetTag);
                    presenter.setState(this);
                    break;
                case intro:
                    state = ApplicationState.metadata;
                    this.presenter = new MetadataPresenter(widgetTag, userResults);
                    presenter.setState(this);
                    break;
                case metadata:
                    state = ApplicationState.stimulus;
                    final StimuliGroup[] stimuli = stimuliProvider.getStimuli();
                    this.presenter = new ColourPickerPresenter(widgetTag, stimuli[0], userResults);
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
                    state = ApplicationState.registration;
                    this.presenter = new RegisterPresenter(widgetTag, userResults);
                    presenter.setState(this);
                    break;
                case registration:
                    state = ApplicationState.end;
                    this.presenter = new ScreenPresenter(widgetTag);
                    presenter.setState(this);
                    break;
                default:
//                state = ApplicationState.start;
//                presenter.setState(this);
                    break;
            }
        } catch (StimulusError | CanvasError error) {
            logger.warning(error.getMessage());
            this.presenter = new ErrorPresenter(widgetTag, error.getMessage());
            presenter.setState(this);
        }
    }
}

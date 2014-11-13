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

import com.google.gwt.core.client.JavaScriptException;
import nl.ru.languageininteraction.synaesthesia.client.service.LocalStorage;
import nl.ru.languageininteraction.synaesthesia.client.service.StimuliProvider;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.exception.CanvasError;
import nl.ru.languageininteraction.synaesthesia.client.presenter.Presenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.IntroPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.ReportPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.FeedbackPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.ScreenPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.ColourPickerPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.RegisterPresenter;
import com.google.gwt.user.client.ui.RootPanel;
import java.util.logging.Logger;
import nl.ru.languageininteraction.synaesthesia.client.presenter.ErrorPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.LocalePresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.MetadataPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.VersionPresenter;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.presenter.MenuPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.StimulusMenuPresenter;

/**
 * @since Oct 7, 2014 11:07:35 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AppController implements AppEventListner {

    private static final Logger logger = Logger.getLogger(AppController.class.getName());

    private final RootPanel widgetTag;
    private Presenter presenter;
    private final UserResults userResults;
    private final StimuliProvider stimuliProvider;

    public AppController(RootPanel widgetTag) {
        this.widgetTag = widgetTag;
        stimuliProvider = new StimuliProvider();
        userResults = new LocalStorage().getStoredData();
    }

    @Override
    public void requestApplicationState(ApplicationState applicationState) {
        try {
            trackView(applicationState.name());
            switch (applicationState) {
                case start:
                case menu:
                    this.presenter = new MenuPresenter(widgetTag);
                    presenter.setState(this, null, null);
                    break;
                case locale:
                    this.presenter = new LocalePresenter(widgetTag);
                    presenter.setState(this, null, null);
                    break;
                case version:
                    this.presenter = new VersionPresenter(widgetTag);
                    presenter.setState(this, null, null);
                    break;
                case intro:
                    this.presenter = new IntroPresenter(widgetTag);
                    presenter.setState(this, null, ApplicationState.metadata);
                    break;
                case metadata:
                    this.presenter = new MetadataPresenter(widgetTag, userResults);
                    presenter.setState(this, null, ApplicationState.stimulus);
                    break;
                case stimulus:
                    if (userResults.getPendingStimuliGroup() == null) {
                        this.presenter = new StimulusMenuPresenter(widgetTag, stimuliProvider, userResults);
                        presenter.setState(this, null, null);
                    } else {
                        trackEvent(applicationState.name(), "show", userResults.getPendingStimuliGroup().getGroupLabel());
                        this.presenter = new ColourPickerPresenter(widgetTag, userResults, 3);
                        presenter.setState(this, null, ApplicationState.stimulus);
                    }
                    break;
                case adddummyresults:
                    final StimuliGroup[] stimuli = stimuliProvider.getStimuli();
                    userResults.addDummyResults(stimuli[0]);
                    userResults.addDummyResults(stimuli[1]);
                    userResults.addDummyResults(stimuli[2]);
                case report:
                    this.presenter = new ReportPresenter(widgetTag, userResults);
                    presenter.setState(this, null, ApplicationState.feedback);
                    break;
                case feedback:
                    this.presenter = new FeedbackPresenter(widgetTag);
                    presenter.setState(this, ApplicationState.report, ApplicationState.registration);
                    break;
                case registration:
                    this.presenter = new RegisterPresenter(widgetTag, userResults);
                    presenter.setState(this, null, ApplicationState.moreinfo);
                    break;
                case moreinfo:
                case end:
                    this.presenter = new ScreenPresenter(widgetTag);
                    presenter.setState(this, ApplicationState.start, null);
                    break;
                default:
                    this.presenter = new ErrorPresenter(widgetTag, "No state for: " + applicationState);
                    presenter.setState(this, ApplicationState.start, applicationState);
                    break;
            }
        } catch (JavaScriptException | CanvasError error) {
            logger.warning(error.getMessage());
            this.presenter = new ErrorPresenter(widgetTag, error.getMessage());
            presenter.setState(this, ApplicationState.start, applicationState);
        }
    }

    public void start() {
        setBackButtonAction();
        requestApplicationState(AppEventListner.ApplicationState.start);
    }

    public void backAction() {
        requestApplicationState(ApplicationState.menu);
    }

    public static native void trackView(String applicationState) /*-{
     if($wnd.analytics) $wnd.analytics.trackView(applicationState);
     }-*/;

    public static native void trackEvent(String applicationState, String label, String value) /*-{
     if($wnd.analytics) $wnd.analytics.trackEvent(applicationState, "view", label, value);
     }-*/;

    private native void setBackButtonAction() /*-{
     var appController = this;
     $doc.addEventListener("backbutton", function(e){
     e.preventDefault();
     appController.@nl.ru.languageininteraction.synaesthesia.client.AppController::backAction()();
     }, false);
     }-*/;
}

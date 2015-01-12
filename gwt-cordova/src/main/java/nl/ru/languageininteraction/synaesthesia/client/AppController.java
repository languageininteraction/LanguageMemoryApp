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
import nl.ru.languageininteraction.synaesthesia.client.presenter.ReportPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.FeedbackPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.MoreInfoPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.ColourPickerPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.RegisterPresenter;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.logging.Logger;
import nl.ru.languageininteraction.synaesthesia.client.presenter.ErrorPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.LocalePresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.MetadataPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.VersionPresenter;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.presenter.InstructionsPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.MenuPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.RegisterDisabledPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.StimulusMenuPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.UserNamePresenter;

/**
 * @since Oct 7, 2014 11:07:35 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AppController implements AppEventListner {

    private static final Logger logger = Logger.getLogger(AppController.class.getName());

    private final RootLayoutPanel widgetTag;
    private Presenter presenter;
    private final UserResults userResults;
    private final StimuliProvider stimuliProvider;

    public AppController(RootLayoutPanel widgetTag) {
        this.widgetTag = widgetTag;
        stimuliProvider = new StimuliProvider();
        userResults = new LocalStorage().getStoredData(stimuliProvider.getDefaultStimuli());
//        userResults.setPendingStimuliGroup(stimuliProvider.getDefaultStimuli());
    }

    @Override
    public void requestApplicationState(ApplicationState applicationState) {
        try {
            trackView(applicationState.name());
            switch (applicationState) {
                case menu:
                    userResults.setPendingStimuliGroup(null);
                    this.presenter = new MenuPresenter(widgetTag, userResults);
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
                case start:
                case intro:
//                    this.presenter = new IntroPresenter(widgetTag);
//                    presenter.setState(this, null, ApplicationState.setuser);
//                    break;
                case setuser:
                    this.presenter = new UserNamePresenter(widgetTag, userResults);
                    presenter.setState(this, null, ApplicationState.stimulus);
                    ((MetadataPresenter) presenter).focusFirstTextBox();
                    break;
                case instructions:
                    this.presenter = new InstructionsPresenter(widgetTag);
                    presenter.setState(this, null, ApplicationState.stimulus);
                    break;
                case stimulus:
                    if (userResults.getPendingStimuliGroup() == null) {
                        this.presenter = new StimulusMenuPresenter(widgetTag, stimuliProvider, userResults);
                        presenter.setState(this, null, null);
                    } else {
                        trackEvent(applicationState.name(), "show", userResults.getPendingStimuliGroup().getGroupLabel());
                        this.presenter = new ColourPickerPresenter(widgetTag, userResults, 3);
                        presenter.setState(this, ApplicationState.menu, ApplicationState.report);
                    }
                    break;
                case adddummyresults:
                    final StimuliGroup[] stimuli = stimuliProvider.getStimuli();
                    userResults.addDummyResults(stimuli[0]);
                    userResults.addDummyResults(stimuli[1]);
                    userResults.addDummyResults(stimuli[2]);
                case report:
                    this.presenter = new ReportPresenter(widgetTag, userResults);
                    if (userResults.canRegister()) {
                        presenter.setState(this, null, ApplicationState.metadata);
                    } else {
                        presenter.setState(this, null, ApplicationState.stimulus);
                    }
                    break;
                case feedback:
                    this.presenter = new FeedbackPresenter(widgetTag, userResults);
                    presenter.setState(this, ApplicationState.report, ApplicationState.metadata);
                    break;
                case metadata:
                    this.presenter = new MetadataPresenter(widgetTag, userResults);
                    presenter.setState(this, null, ApplicationState.registration);
                    ((MetadataPresenter) presenter).focusFirstTextBox();
                    break;
                case registration:
                    if (userResults.getStimuliGroups().isEmpty()) {
                        this.presenter = new RegisterDisabledPresenter(widgetTag);
                        presenter.setState(this, null, ApplicationState.stimulus);
                    } else {
                        this.presenter = new RegisterPresenter(widgetTag, userResults);
                        presenter.setState(this, null, ApplicationState.start);
                    }
                    break;
                case moreinfo:
                    this.presenter = new MoreInfoPresenter(widgetTag);
                    presenter.setState(this, ApplicationState.registration, null);
                    break;
                case end:
                    exitApplication();
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
        addKeyboardEvents();
    }

    public void backAction() {
        presenter.fireBackEvent();
    }

    public void resizeAction() {
        presenter.fireResizeEvent();
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

    private native void addKeyboardEvents() /*-{
     var appController = this;
     if($wnd.Keyboard) {
     $wnd.Keyboard.onshow = function () {
     $doc.getElementById("platformTag").innerHTML = "Keyboard.onshow GWT called";
     appController.@nl.ru.languageininteraction.synaesthesia.client.AppController::resizeAction()();
     }
     $wnd.Keyboard.onhide = function () {
     $doc.getElementById("platformTag").innerHTML = "Keyboard.onhide GWT called";
     appController.@nl.ru.languageininteraction.synaesthesia.client.AppController::resizeAction()();
     }
     }
     }-*/;

    private native void exitApplication() /*-{
     $doc.navigator.app.exitApp();
     }-*/;
}

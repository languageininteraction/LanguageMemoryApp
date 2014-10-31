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

import nl.ru.languageininteraction.synaesthesia.client.view.RegisterView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.Messages;
import nl.ru.languageininteraction.synaesthesia.client.MetadataFields;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.registration.RegistrationListener;
import nl.ru.languageininteraction.synaesthesia.client.registration.RegistrationService;
import nl.ru.languageininteraction.synaesthesia.client.view.SimpleView;

/**
 * @since Oct 21, 2014 5:06:21 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RegisterPresenter implements Presenter {

    private final Messages messages = GWT.create(Messages.class);
    private final MetadataFields mateadataFields = GWT.create(MetadataFields.class);
    private final RootPanel widgetTag;
    private final UserResults userResults;

    public RegisterPresenter(RootPanel widgetTag, UserResults userResults) {
        this.widgetTag = widgetTag;
        this.userResults = userResults;
    }

    @Override
    public void setState(final AppEventListner appEventListner, final AppEventListner.ApplicationState prevState, final AppEventListner.ApplicationState nextState) {
        widgetTag.clear();
        RegisterView registerView = new RegisterView();
        if (prevState != null) {
            registerView.setButton(messages.prevbutton(), new PresenterEventListner() {

                @Override
                public void eventFired() {
                    appEventListner.requestApplicationState(prevState);
                }

            });
        }
        registerView.setButton(messages.registerButton(), new PresenterEventListner() {

            @Override
            public void eventFired() {
                widgetTag.clear();
                final SimpleView simpleView = new SimpleView();
                simpleView.addTitle(messages.registerScreenTitle());
                if (prevState != null) {
                    simpleView.setButton(messages.prevbutton(), new PresenterEventListner() {

                        @Override
                        public void eventFired() {
                            appEventListner.requestApplicationState(prevState);
                        }

                    });
                }
                simpleView.setDisplayText("Connecting");
                final RegistrationService registrationService = new RegistrationService();
                registrationService.submitRegistration(userResults, new RegistrationListener() {

                    @Override
                    public void registrationFailed(Throwable exception) {
                        simpleView.setDisplayText("Registration failed. " + exception.getMessage());
                    }

                    @Override
                    public void registrationComplete() {
                        simpleView.setDisplayText("Registration complete.");
                        if (nextState != null) {
                            simpleView.setButton(messages.nextbutton(), new PresenterEventListner() {

                                @Override
                                public void eventFired() {
                                    appEventListner.requestApplicationState(nextState);
                                }

                            });
                        }
                    }
                });
                simpleView.resizeView();
                widgetTag.add(simpleView);
            }
        });

        registerView.addTitle(messages.registerScreenTitle());
        registerView.addText(messages.registerScreenText());
        registerView.addField(mateadataFields.registrationField1(), userResults.getMetadataValue(mateadataFields.postName1()));
        registerView.addField(mateadataFields.registrationField2(), userResults.getMetadataValue(mateadataFields.postName2()));
        registerView.addField(mateadataFields.registrationField3(), userResults.getMetadataValue(mateadataFields.postName3()));
        registerView.addField(mateadataFields.registrationField4(), userResults.getMetadataValue(mateadataFields.postName4()));
        registerView.resizeView();
        widgetTag.add(registerView);
    }
}

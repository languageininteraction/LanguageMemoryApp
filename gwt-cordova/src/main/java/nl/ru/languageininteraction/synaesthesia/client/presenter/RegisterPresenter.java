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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.synaesthesia.client.view.RegisterView;
import nl.ru.languageininteraction.synaesthesia.client.InformationSheet;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.model.MetadataField;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.registration.RegistrationException;
import nl.ru.languageininteraction.synaesthesia.client.registration.RegistrationListener;
import nl.ru.languageininteraction.synaesthesia.client.registration.RegistrationService;
import nl.ru.languageininteraction.synaesthesia.client.service.MetadataFieldProvider;
import nl.ru.languageininteraction.synaesthesia.client.view.SimpleView;

/**
 * @since Oct 21, 2014 5:06:21 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RegisterPresenter extends AbstractPresenter implements Presenter {

    protected final InformationSheet informationSheet = GWT.create(InformationSheet.class);
    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
    private final UserResults userResults;
    private AppEventListner.ApplicationState nextState;

    public RegisterPresenter(RootLayoutPanel widgetTag, UserResults userResults) {
        super(widgetTag, new RegisterView());
        this.userResults = userResults;
    }

    @Override
    public void setState(AppEventListner appEventListner, AppEventListner.ApplicationState prevState, AppEventListner.ApplicationState nextState) {
        this.nextState = nextState;
        super.setState(appEventListner, prevState, null);
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.registerScreenTitle(), titleBarListner);
    }

    @Override
    protected void setContent(final AppEventListner appEventListner) {
        ((RegisterView) simpleView).addText(messages.registerScreenText());
        for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
            ((RegisterView) simpleView).addField(metadataField.getFieldLabel(), userResults.getMetadataValue(metadataField.getPostName()));
        }
        ((RegisterView) simpleView).addOptionButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return messages.editMetadataLabel();
            }

            @Override
            public void eventFired(Button button) {
                appEventListner.requestApplicationState(AppEventListner.ApplicationState.metadata);
            }
        });

        ((RegisterView) simpleView).addOptionButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return messages.informationSheetLink();
            }

            @Override
            public void eventFired(Button button) {
                appEventListner.requestApplicationState(AppEventListner.ApplicationState.moreinfo);
            }
        });
        ((RegisterView) simpleView).addText(messages.informationSheetCheckBox());
        final CheckBox agreementCheckBox = ((RegisterView) simpleView).addCheckBox("");
        final Button registerButton = addRegisterButton(appEventListner);
        agreementCheckBox.setValue(false);
        registerButton.setEnabled(false);
        agreementCheckBox.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
//                event.preventDefault();
//                event.stopPropagation();
                // on chrome the check box is changed before this code is reached
//                agreementCheckBox.setValue(!registerButton.isEnabled());
                registerButton.setEnabled(agreementCheckBox.getValue());
            }
        });
        agreementCheckBox.addTouchEndHandler(new TouchEndHandler() {

            @Override
            public void onTouchEnd(TouchEndEvent event) {
                event.preventDefault();
                agreementCheckBox.setValue(!agreementCheckBox.getValue());
                registerButton.setEnabled(agreementCheckBox.getValue());
            }
        });
    }

    private Button addRegisterButton(final AppEventListner appEventListner) {
        final Button registerButton = simpleView.setButton(SimpleView.ButtonType.next, new PresenterEventListner() {

            @Override
            public void eventFired(Button button) {
                ((RegisterView) simpleView).clearGui();
                simpleView.removeButton(button);
                simpleView.setDisplayText("Connecting");
                final RegistrationService registrationService = new RegistrationService();
                registrationService.submitRegistration(userResults, new RegistrationListener() {

                    @Override
                    public void registrationFailed(RegistrationException exception) {
                        switch (exception.getErrorType()) {
                            case buildererror:
                                appEventListner.requestApplicationState(AppEventListner.ApplicationState.registrationfailedbuildererror);
                                break;
                            case connectionerror:
                                appEventListner.requestApplicationState(AppEventListner.ApplicationState.registrationfailedconnectionerror);
                                break;
                            case non202response:
                                appEventListner.requestApplicationState(AppEventListner.ApplicationState.registrationfailednon202);
                                break;
                        }
                    }

                    @Override
                    public void registrationComplete() {
                        userResults.clearResults();
                        appEventListner.requestApplicationState(AppEventListner.ApplicationState.registrationcomplete);
                    }
                }, messages.reportDateFormat());
            }

            @Override
            public String getLabel() {
                return messages.registerButton();
            }
        });
        return registerButton;
    }
}

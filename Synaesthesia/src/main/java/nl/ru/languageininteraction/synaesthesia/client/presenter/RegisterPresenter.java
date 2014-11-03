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

import com.google.gwt.user.client.ui.Button;
import nl.ru.languageininteraction.synaesthesia.client.view.RegisterView;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.model.MetadataField;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.registration.RegistrationListener;
import nl.ru.languageininteraction.synaesthesia.client.registration.RegistrationService;
import nl.ru.languageininteraction.synaesthesia.client.service.MetadataFieldProvider;

/**
 * @since Oct 21, 2014 5:06:21 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RegisterPresenter extends AbstractPresenter implements Presenter {

    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
    private final UserResults userResults;

    public RegisterPresenter(RootPanel widgetTag, UserResults userResults) {
        super(widgetTag, new RegisterView());
        this.userResults = userResults;
    }

    @Override
    public void setState(AppEventListner appEventListner, AppEventListner.ApplicationState prevState, AppEventListner.ApplicationState nextState) {
        this.nextState = nextState;
        super.setState(appEventListner, prevState, null);
    }

    @Override
    void pageClosing() {
    }

    @Override
    void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.registerScreenTitle(), titleBarListner);
    }

    @Override
    void setContent(AppEventListner appEventListner) {
        ((RegisterView) simpleView).addText(messages.registerScreenText());
        for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
            ((RegisterView) simpleView).addField(metadataField.getFieldLabel(), userResults.getMetadataValue(metadataField.getPostName()));
        }
        simpleView.setButton(messages.registerButton(), new PresenterEventListner() {

            @Override
            public void eventFired(Button button) {
                ((RegisterView) simpleView).clearGui();
                simpleView.removeButton(button);
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
                    }
                });
            }
        });
    }

}

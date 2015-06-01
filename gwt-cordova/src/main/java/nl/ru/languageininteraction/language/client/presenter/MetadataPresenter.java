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
package nl.ru.languageininteraction.language.client.presenter;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.view.MetadataView;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.service.LocalStorage;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.model.MetadataField;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.service.MetadataFieldProvider;
import nl.ru.languageininteraction.language.client.exception.MetadataFieldException;

/**
 * @since Oct 21, 2014 11:50:56 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataPresenter extends AbstractPresenter implements Presenter {

    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
    protected final UserResults userResults;
    protected PresenterEventListner saveEventListner = null;

    public MetadataPresenter(RootLayoutPanel widgetTag, UserResults userResults) {
        super(widgetTag, new MetadataView());
        this.userResults = userResults;
    }

    @Override
    public void setState(final AppEventListner appEventListner, AppEventListner.ApplicationState prevState, final AppEventListner.ApplicationState nextState) {
        super.setState(appEventListner, prevState, null);
        saveEventListner = new PresenterEventListner() {

            @Override
            public void eventFired(Button button) {
                try {
                    ((MetadataView) simpleView).clearErrors();
                    validateFields();
                    saveFields();
                    appEventListner.requestApplicationState(nextState);
                } catch (MetadataFieldException exception) {
                    ((MetadataView) simpleView).showFieldError(exception.getMetadataField());
                }
            }

            @Override
            public String getLabel() {
                return nextState.label;
            }
        };
        addNextButton();
    }

    protected void addNextButton() {
        ((MetadataView) simpleView).addOptionButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return saveEventListner.getLabel();
            }

            @Override
            public void eventFired(Button button) {
                saveEventListner.eventFired(button);
            }
        });
    }

    protected void validateFields() throws MetadataFieldException {
        for (MetadataField fieldName : ((MetadataView) simpleView).getFieldNames()) {
            String fieldString = ((MetadataView) simpleView).getFieldValue(fieldName);
            fieldName.validateValue(fieldString);
        }
    }

    protected void saveFields() {
        for (MetadataField fieldName : ((MetadataView) simpleView).getFieldNames()) {
            String fieldString = ((MetadataView) simpleView).getFieldValue(fieldName);
            userResults.getUserData().setMetadataValue(fieldName, fieldString);
        }
        new LocalStorage().storeData(userResults);
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        throw new UnsupportedOperationException();
//        simpleView.addTitle(messages.metadataScreenTitle(), titleBarListner);
    }

    @Override
    protected void setContent(AppEventListner appEventListner) {
        throw new UnsupportedOperationException();
//        ((MetadataView) simpleView).addText(messages.metadataScreenText());
//        for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
//            ((MetadataView) simpleView).addField(metadataField, userResults.getUserData().getMetadataValue(metadataField), metadataField.getFieldLabel());
//        }
    }

    public void focusFirstTextBox() {
        ((MetadataView) simpleView).focusFirstTextBox();
    }
}

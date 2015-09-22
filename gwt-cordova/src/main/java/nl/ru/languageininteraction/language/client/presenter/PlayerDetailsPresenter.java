/*
 * Copyright (C) 2015 Language In Interaction
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import nl.ru.languageininteraction.language.client.Messages;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.service.LocalStorage;
import nl.ru.languageininteraction.language.client.service.MetadataFieldProvider;
import nl.ru.languageininteraction.language.client.view.PlayerDetailsView;

/**
 * @since Feb 4, 2015 11:26:01 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class PlayerDetailsPresenter extends AbstractSvgPresenter implements Presenter {

    protected final Messages messages = GWT.create(Messages.class);
    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
    private final PlayerDetailsView playerDetailsView;
    final AppEventListner appEventListner;

    public PlayerDetailsPresenter(RootLayoutPanel widgetTag, UserResults userResults, AudioPlayer audioPlayer, final AppEventListner appEventListner) throws AudioException {
        super(widgetTag, userResults, audioPlayer, new PlayerDetailsView(audioPlayer));
        playerDetailsView = ((PlayerDetailsView) abstractSvgView);
        this.appEventListner = appEventListner;
    }

    @Override
    void configureSvg() {
        String currentAge = userResults.getUserData().getMetadataValue(metadataFieldProvider.ageMetadataField);
        boolean shareAgreed = metadataFieldProvider.shareMetadataField.getControlledVocabulary()[0].equals(userResults.getUserData().getMetadataValue(metadataFieldProvider.shareMetadataField));
        playerDetailsView.setShareData(shareAgreed);
        final String[] ageVocabulary = metadataFieldProvider.ageMetadataField.getControlledVocabulary();
        if (ageVocabulary[0].equals(currentAge)) {
            playerDetailsView.setAge1();
        } else if (ageVocabulary[1].equals(currentAge)) {
            playerDetailsView.setAge2();
        } else if (ageVocabulary[2].equals(currentAge)) {
            playerDetailsView.setAge3();
        } else {
            playerDetailsView.clearAge();
        }

        playerDetailsView.setAge1ButtonListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                userResults.getUserData().setMetadataValue(metadataFieldProvider.ageMetadataField, metadataFieldProvider.ageMetadataField.getControlledVocabulary()[0]);
                playerDetailsView.setAge1();
            }
        });
        playerDetailsView.setAge2ButtonListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                userResults.getUserData().setMetadataValue(metadataFieldProvider.ageMetadataField, metadataFieldProvider.ageMetadataField.getControlledVocabulary()[1]);
                playerDetailsView.setAge2();
            }
        });
        playerDetailsView.setAge3ButtonListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                userResults.getUserData().setMetadataValue(metadataFieldProvider.ageMetadataField, metadataFieldProvider.ageMetadataField.getControlledVocabulary()[2]);
                playerDetailsView.setAge3();
            }
        });
        playerDetailsView.setShareCheckBoxListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
//                boolean shareAgreed = metadataFieldProvider.shareMetadataField.getControlledVocabulary()[0].equals(userResults.getUserData().getMetadataValue(metadataFieldProvider.shareMetadataField));
//                if (shareAgreed) {
////                    userResults.getUserData().setMetadataValue(metadataFieldProvider.shareMetadataField, metadataFieldProvider.shareMetadataField.getControlledVocabulary()[1]);
//                    playerDetailsView.setShareData(Boolean.FALSE);
//                } else {
////                    userResults.getUserData().setMetadataValue(metadataFieldProvider.shareMetadataField, metadataFieldProvider.shareMetadataField.getControlledVocabulary()[0]);
//                    playerDetailsView.setShareData(Boolean.TRUE);
//                }
                appEventListner.requestApplicationState(AppEventListner.ApplicationState.stopSharing);
            }
        });
        playerDetailsView.setEditNameListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                final TextBox userNameBox = new TextBox();
                userNameBox.setStylePrimaryName("popupTextBox");
                userNameBox.setValue(userResults.getUserData().getMetadataValue(metadataFieldProvider.firstNameMetadataField));
                playerDetailsView.showWidgetPopup(new PresenterEventListner() {

                    @Override
                    public String getLabel() {
                        return "";
                    }

                    @Override
                    public void eventFired(Button button) {
                        userResults.getUserData().setMetadataValue(metadataFieldProvider.firstNameMetadataField, userNameBox.getValue());
                        playerDetailsView.setUserNameField(userResults.getUserData().getMetadataValue(metadataFieldProvider.firstNameMetadataField));
                    }
                }, userNameBox);
            }
        });
        playerDetailsView.setAddLanguageListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                final TextBox addLanguageBox = new TextBox();
                addLanguageBox.setStylePrimaryName("popupTextBox");
                playerDetailsView.showWidgetPopup(new PresenterEventListner() {

                    @Override
                    public String getLabel() {
                        return "";
                    }

                    @Override
                    public void eventFired(Button button) {
                        final String storedValue = userResults.getUserData().getMetadataValue(metadataFieldProvider.languagesMetadataField);
                        final String spokenLanguages = (storedValue == null || storedValue.isEmpty()) ? addLanguageBox.getValue() : storedValue + "|" + addLanguageBox.getValue();
                        userResults.getUserData().setMetadataValue(metadataFieldProvider.languagesMetadataField, spokenLanguages);
                        setLanguageLabels();
                    }
                }, addLanguageBox);
            }
        });
        playerDetailsView.setDeleteLanguageListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                final TextBox deleteLanguageBox = new TextBox();
                deleteLanguageBox.setStylePrimaryName("popupTextBox");
                deleteLanguageBox.setValue(userResults.getUserData().getMetadataValue(metadataFieldProvider.languagesMetadataField));
                playerDetailsView.showWidgetPopup(new PresenterEventListner() {

                    @Override
                    public String getLabel() {
                        return "";
                    }

                    @Override
                    public void eventFired(Button button) {
//                        userResults.getUserData().setMetadataValue(metadataFieldProvider.firstNameMetadataField, deleteLanguageBox.getValue());
                        setLanguageLabels();
                    }
                }, deleteLanguageBox);
            }
        });
        final String userNameValue = userResults.getUserData().getMetadataValue(metadataFieldProvider.firstNameMetadataField);
        playerDetailsView.setUserNameField((userNameValue.isEmpty()) ? messages.defaultUserName() : userNameValue);
        playerDetailsView.setUserScoreField(userResults.getUserData().getBestScore());
        setLanguageLabels();
    }

    private void setLanguageLabels() {
        final String[] spokenLanguages = userResults.getUserData().getMetadataValue(metadataFieldProvider.languagesMetadataField).split("\\|");
        String spokenLanguage1 = (spokenLanguages.length > 0) ? spokenLanguages[0] : "";
        String spokenLanguage2 = (spokenLanguages.length > 1) ? spokenLanguages[1] : "";
        String spokenLanguage3 = (spokenLanguages.length > 2) ? spokenLanguages[2] : "";
        String spokenLanguage4 = (spokenLanguages.length > 3) ? spokenLanguages[3] : "";
        playerDetailsView.setSpokenLanguages(spokenLanguage1, spokenLanguage2, spokenLanguage3, spokenLanguage4);
    }

    @Override
    boolean nextEventFired() {
        new LocalStorage().storeData(userResults);
        return true;
    }
}

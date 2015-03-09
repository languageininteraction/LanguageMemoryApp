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

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.service.MetadataFieldProvider;
import nl.ru.languageininteraction.language.client.view.PlayerDetailsView;

/**
 * @since Feb 4, 2015 11:26:01 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class PlayerDetailsPresenter extends AbstractSvgPresenter implements Presenter {

    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
    private final PlayerDetailsView playerDetailsView;

    public PlayerDetailsPresenter(RootLayoutPanel widgetTag, UserResults userResults, AudioPlayer audioPlayer) throws AudioException {
        super(widgetTag, userResults, audioPlayer, new PlayerDetailsView(audioPlayer));
        playerDetailsView = ((PlayerDetailsView) abstractSvgView);
    }

    @Override
    void configureSvg() {
        String currentAge = userResults.getMetadataValue(metadataFieldProvider.ageMetadataField);
        boolean shareAgreed = metadataFieldProvider.shareMetadataField.getControlledVocabulary()[0].equals(userResults.getMetadataValue(metadataFieldProvider.shareMetadataField));
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
                userResults.setMetadataValue(metadataFieldProvider.ageMetadataField, metadataFieldProvider.ageMetadataField.getControlledVocabulary()[0]);
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
                userResults.setMetadataValue(metadataFieldProvider.ageMetadataField, metadataFieldProvider.ageMetadataField.getControlledVocabulary()[1]);
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
                userResults.setMetadataValue(metadataFieldProvider.ageMetadataField, metadataFieldProvider.ageMetadataField.getControlledVocabulary()[2]);
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
                boolean shareAgreed = metadataFieldProvider.shareMetadataField.getControlledVocabulary()[0].equals(userResults.getMetadataValue(metadataFieldProvider.shareMetadataField));
                if (shareAgreed) {
                    userResults.setMetadataValue(metadataFieldProvider.shareMetadataField, metadataFieldProvider.shareMetadataField.getControlledVocabulary()[1]);
                    playerDetailsView.setShareData(Boolean.FALSE);
                } else {
                    userResults.setMetadataValue(metadataFieldProvider.shareMetadataField, metadataFieldProvider.shareMetadataField.getControlledVocabulary()[0]);
                    playerDetailsView.setShareData(Boolean.TRUE);
                }
            }
        });
    }

    @Override
    boolean nextEventFired() {
        return true;
    }
}

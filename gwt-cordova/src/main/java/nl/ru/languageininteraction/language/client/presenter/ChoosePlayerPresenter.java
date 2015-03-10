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
import java.util.ArrayList;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.model.UserData;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.service.LocalStorage;
import nl.ru.languageininteraction.language.client.service.MetadataFieldProvider;
import nl.ru.languageininteraction.language.client.view.ChoosePlayerView;

/**
 * @since Feb 4, 2015 11:25:42 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ChoosePlayerPresenter extends AbstractSvgPresenter implements Presenter {

    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
    final LocalStorage localStorage;
    final AppEventListner appEventListner;

    public ChoosePlayerPresenter(RootLayoutPanel widgetTag, LocalStorage localStorage, UserResults userResults, AudioPlayer audioPlayer, final AppEventListner appEventListner) throws AudioException {
        super(widgetTag, userResults, audioPlayer, new ChoosePlayerView(audioPlayer));
        this.appEventListner = appEventListner;
        this.localStorage = localStorage;
    }

    @Override
    void configureSvg() {
        ((ChoosePlayerView) abstractSvgView).setEditButtonListner(
                new PresenterEventListner() {

                    @Override
                    public String getLabel() {
                        return "";
                    }

                    @Override
                    public void eventFired(Button button) {
                        appEventListner.requestApplicationState(AppEventListner.ApplicationState.playerdetails);
                    }
                });
        ((ChoosePlayerView) abstractSvgView).setGoButtonListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
//                appEventListner.requestApplicationState(AppEventListner.ApplicationState.scores);
                appEventListner.requestApplicationState(AppEventListner.ApplicationState.guessround);
            }
        });
        ((ChoosePlayerView) abstractSvgView).setCreateButtonListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                appEventListner.requestApplicationState(AppEventListner.ApplicationState.createplayer);
            }
        });
        ((ChoosePlayerView) abstractSvgView).setSwitchButtonListner(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                ArrayList<PresenterEventListner> playerListeners = new ArrayList<>();
                for (final UserData userData : localStorage.getUserIdList()) {
                    playerListeners.add(new PresenterEventListner() {

                        @Override
                        public String getLabel() {
                            return userData.getMetadataValue(metadataFieldProvider.firstNameMetadataField);
                        }

                        @Override
                        public void eventFired(Button button) {
                            userResults.setUser(localStorage.getStoredData(userData.getUserId()));
                            ((ChoosePlayerView) abstractSvgView).setUserNameField(userResults.getUserData().getMetadataValue(metadataFieldProvider.firstNameMetadataField));
                        }
                    });
                }
                ((ChoosePlayerView) abstractSvgView).showChoosePlayer(playerListeners);
            }
        }
        );
        ((ChoosePlayerView) abstractSvgView).setUserNameField(userResults.getUserData().getMetadataValue(metadataFieldProvider.firstNameMetadataField));
    }

    @Override
    boolean nextEventFired() {
        return true;
    }
}

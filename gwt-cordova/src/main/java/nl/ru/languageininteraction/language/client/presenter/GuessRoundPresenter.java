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

import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.LanguageDataProvider;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.LanguageSampleListener;
import nl.ru.languageininteraction.language.client.model.RoundData;
import nl.ru.languageininteraction.language.client.model.RoundSample;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.service.LocalStorage;
import nl.ru.languageininteraction.language.client.service.RoundDataProvider;
import nl.ru.languageininteraction.language.client.util.GameState;
import nl.ru.languageininteraction.language.client.view.GuessRoundView;

/**
 * @since Nov 26, 2014 4:12:27 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class GuessRoundPresenter extends AbstractSvgPresenter implements Presenter {

    protected final LanguageDataProvider languageDataProvider;
    private final GuessRoundView guessRoundView;
    private int roundsPlayed = 0;
    private int playerScore = 0;
    private final GameState.PlayerLevel playerLevel;
    final RoundDataProvider roundDataProvider;

    public GuessRoundPresenter(RootLayoutPanel widgetTag, UserResults userResults, final AudioPlayer audioPlayer) throws AudioException {
        super(widgetTag, userResults, audioPlayer, new GuessRoundView(audioPlayer));
        guessRoundView = (GuessRoundView) abstractSvgView;
        languageDataProvider = new LanguageDataProvider();
        playerScore = userResults.getUserData().getBestScore();
        userResults.getGameData().clearGameCounters();
        playerLevel = new GameState().getPlayerLevel(playerScore);
        roundDataProvider = new RoundDataProvider();
    }

    @Override
    boolean nextEventFired() {
        if (playerLevel.getRoundsPerGame() > roundsPlayed) {
            setSamples(playerLevel);
            return false;
        } else {
            new LocalStorage().storeData(userResults);
            userResults.getUserData().addGamePlayed();
            return true;
        }
    }

    @Override
    void configureSvg() {
        setSamples(playerLevel);
        guessRoundView.updateRoundsLabel(0, 0);
    }

    private void setSamples(GameState.PlayerLevel playerLevel) {
        final RoundData roundData = roundDataProvider.getRoundData(playerLevel);
        final long startMs = System.currentTimeMillis();
        guessRoundView.setSampleListeners(new LanguageSampleListener() {

            @Override
            public void eventFired(RoundSample roundSample) {
                roundsPlayed++;
                if (roundSample.isCorrect()) {
                    playerScore = (playerScore == 0) ? 1 : playerScore * 2;
                    userResults.getUserData().updateBestScore(playerScore);
                }
                roundData.setChosenAnswer(roundSample);
                roundData.setDurationMs(System.currentTimeMillis() - startMs);
                userResults.getGameData().addRoundData(roundData);
                guessRoundView.updateRoundsLabel(userResults.getGameData().getRoundsCorrect(), userResults.getGameData().getRoundsPlayed());
            }
        }, roundData);
    }
}

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
package nl.ru.languageininteraction.language.client.util;

/**
 * @since Jan 22, 2015 12:50:26 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class GameState {

    protected static final int MAX_SCORE = 50000;
    protected static final int LEVEL_1_MAX = 500;
    protected static final int LEVEL_2_MAX = 1500;
    protected static final int LEVEL_3_MAX = 3000;
    protected static final int LEVEL_4_MAX = 12000;

    protected static final int LEVEL_1_CHOICES = 2;
    protected static final int LEVEL_4_CHOICES = 5;
    protected static final int LEVEL_3_CHOICES = 4;
    protected static final int LEVEL_2_CHOICES = 3;

    protected static final int LEVEL_4_ROUNDS = 10;
    protected static final int LEVEL_3_ROUNDS = 8;
    protected static final int LEVEL_2_ROUNDS = 6;
    protected static final int LEVEL_1_ROUNDS = 5;

    public enum PlayerLevel {

        level_1(LEVEL_1_ROUNDS, LEVEL_1_CHOICES, LEVEL_1_MAX),
        level_2(LEVEL_2_ROUNDS, LEVEL_2_CHOICES, LEVEL_2_MAX),
        level_3(LEVEL_3_ROUNDS, LEVEL_3_CHOICES, LEVEL_3_MAX),
        level_4(LEVEL_4_ROUNDS, LEVEL_4_CHOICES, LEVEL_4_MAX);
        final private int roundsPerGame;
        final private int choiceCount;
        final private int maxScoreForLevel;

        private PlayerLevel(int roundsPerGame, int choiceCount, int maxScoreForLevel) {
            this.roundsPerGame = roundsPerGame;
            this.choiceCount = choiceCount;
            this.maxScoreForLevel = maxScoreForLevel;
        }

        public int getRoundsPerGame() {
            return roundsPerGame;
        }

        public int getChoiceCount() {
            return choiceCount;
        }

        public int getMaxScoreForLevel() {
            return maxScoreForLevel;
        }
    }

    public enum LanguageStatus {

        endangered(1.5f),
        vigorous(1);
        final private float scoreFactor;

        private LanguageStatus(float scoreFactor) {
            this.scoreFactor = scoreFactor;
        }

        public float getScoreFactor() {
            return scoreFactor;
        }
    }

    public PlayerLevel getPlayerLevel(int playerScore) {
        for (PlayerLevel testLevel : PlayerLevel.values()) {
            if (playerScore < testLevel.getMaxScoreForLevel()) {
                return testLevel;
            }
        }
        return PlayerLevel.level_4;
    }

    public int getScore(boolean playerCorrect, LanguageStatus languageStatus, int choiceCount) {
        return (!playerCorrect) ? 0 : (int) (languageStatus.getScoreFactor() * (choiceCount - 1));
    }
}

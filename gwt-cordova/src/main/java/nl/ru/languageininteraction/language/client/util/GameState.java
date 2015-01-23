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

    public enum PlayerLevel {

        level_1(5, 2),
        level_2(6, 3),
        level_3(8, 4),
        level_4(10, 5);
        final private int roundsPerGame;
        final private int choiceCount;

        private PlayerLevel(int roundsPerGame, int choiceCount) {
            this.roundsPerGame = roundsPerGame;
            this.choiceCount = choiceCount;
        }

        public int getRoundsPerGame() {
            return roundsPerGame;
        }

        public int getChoiceCount() {
            return choiceCount;
        }
    }

    public enum LanguageStatus {

        endangered(1.5f),
        vigourus(1);
        final private float scoreFactor;

        private LanguageStatus(float scoreFactor) {
            this.scoreFactor = scoreFactor;
        }

        public float getScoreFactor() {
            return scoreFactor;
        }
    }

    public int getScore(boolean playerCorrect, LanguageStatus languageStatus, int choiceCount) {
        return (!playerCorrect) ? 0 : (int) (languageStatus.getScoreFactor() * (choiceCount - 1));
    }
}

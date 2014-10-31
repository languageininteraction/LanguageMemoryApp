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
package nl.ru.languageininteraction.synaesthesia.client.util;

import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.model.Stimulus;

/**
 * @since Oct 21, 2014 4:49:43 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ScoreCalculator {

    double tempScoreValue = 0.5;
    private final UserResults userResults;

    public ScoreCalculator(UserResults userResults) {
        this.userResults = userResults;
    }

    public double getScore(Stimulus stimulus) {
        tempScoreValue += 0.1;
        tempScoreValue = (tempScoreValue > 1) ? 0 : tempScoreValue;
        return tempScoreValue;
    }

    public double getScore() {
        return 0.27;
    }

    public double getAccuracy() {
        return 94.44;
    }

    public double getMeanReactionTime() {
        return 1.33;
    }

    public double getReactionTimeDeviation() {
        return 0.41;
    }
}

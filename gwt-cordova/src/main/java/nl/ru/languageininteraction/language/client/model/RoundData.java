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
package nl.ru.languageininteraction.language.client.model;

import java.util.Date;
import nl.ru.languageininteraction.language.client.listener.LanguageSampleListener;

/**
 * @since Jan 28, 2015 4:09:17 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RoundData {

    private final LanguageSampleListener chosenAnswer;
    private final LanguageSampleListener correctAnswer;
    private final LanguageSampleListener[] roundChoices;
    private final Date time;
    private final double durationMs;

    public RoundData(LanguageSampleListener chosenAnswer, LanguageSampleListener correctAnswer, LanguageSampleListener[] roundChoices, Date time, double durationMs) {
        this.chosenAnswer = chosenAnswer;
        this.correctAnswer = correctAnswer;
        this.roundChoices = roundChoices;
        this.time = time;
        this.durationMs = durationMs;
    }

    public LanguageSampleListener getChosenAnswer() {
        return chosenAnswer;
    }

    public LanguageSampleListener getCorrectAnswer() {
        return correctAnswer;
    }

    public LanguageSampleListener[] getRoundChoices() {
        return roundChoices;
    }

    public Date getTime() {
        return time;
    }

    public double getDurationMs() {
        return durationMs;
    }

}

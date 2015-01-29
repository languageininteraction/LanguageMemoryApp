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

import nl.ru.languageininteraction.language.client.LanguageDataProvider.LanguageSample;

/**
 * @since Jan 29, 2015 12:59:31 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RoundSample {

    private final LanguageSample languageSample;
    private final boolean correct;
    private final int sampleIndex;

    public RoundSample(LanguageSample languageSample, boolean correct, int sampleIndex) {
        this.languageSample = languageSample;
        this.correct = correct;
        this.sampleIndex = sampleIndex;
    }

    public LanguageSample getLanguageSample() {
        return languageSample;
    }

    public boolean isCorrect() {
        return correct;
    }

    public int getSampleIndex() {
        return sampleIndex;
    }
}

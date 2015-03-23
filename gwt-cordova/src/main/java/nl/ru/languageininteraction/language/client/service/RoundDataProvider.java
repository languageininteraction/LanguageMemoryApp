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
package nl.ru.languageininteraction.language.client.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import nl.ru.languageininteraction.language.client.LanguageDataProvider;
import nl.ru.languageininteraction.language.client.model.RoundData;
import nl.ru.languageininteraction.language.client.model.RoundSample;
import nl.ru.languageininteraction.language.client.util.GameState;

/**
 * @since Jan 29, 2015 2:05:51 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RoundDataProvider {

    final ArrayList<LanguageDataProvider.LanguageSample> unseenCorrectLanguages;

    public RoundDataProvider() {
        this.unseenCorrectLanguages = new ArrayList<>(Arrays.asList(LanguageDataProvider.LanguageSample.values()));
    }

    protected LanguageDataProvider.LanguageSample getUniqueLanguage(ArrayList<LanguageDataProvider.LanguageSample> availableLanguages) {
        final int availableLength = availableLanguages.size();
        int randomIndex = (int) (Math.random() * (availableLength - 1));
        LanguageDataProvider.LanguageSample selectedLanguage = availableLanguages.remove(randomIndex);
        return selectedLanguage;
    }

    public RoundData getRoundData(GameState.PlayerLevel playerLevel) {
        final ArrayList<LanguageDataProvider.LanguageSample> choiceLanguages = new ArrayList(Arrays.asList(LanguageDataProvider.LanguageSample.values()));
        final int correctSampleIndex = (int) (Math.random() * LanguageDataProvider.soundFileCount);
        int correctChoiceSampleIndex = (int) (Math.random() * LanguageDataProvider.soundFileCount);
        while (correctChoiceSampleIndex == correctSampleIndex) {
            correctChoiceSampleIndex = (int) (Math.random() * LanguageDataProvider.soundFileCount);
        }
        final RoundSample correctSample = new RoundSample(getUniqueLanguage(unseenCorrectLanguages), true, correctSampleIndex);
        final List<RoundSample> roundChoices = new ArrayList<>();
        roundChoices.add(new RoundSample(correctSample.getLanguageSample(), true, correctChoiceSampleIndex));
        for (int choiceIndex = 0; choiceIndex < playerLevel.getChoiceCount(); choiceIndex++) {
            roundChoices.add(new RoundSample(getUniqueLanguage(choiceLanguages), false, (int) (Math.random() * LanguageDataProvider.soundFileCount)));
        }
        return new RoundData(correctSample, roundChoices, new Date());
    }
}

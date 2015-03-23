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
import java.util.Date;
import java.util.HashSet;
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

    final HashSet<LanguageDataProvider.LanguageSample> seenCorrectLanguages;

    public RoundDataProvider() {
        this.seenCorrectLanguages = new HashSet<>();
    }

    protected LanguageDataProvider.LanguageSample getUniqueLanguage(HashSet<LanguageDataProvider.LanguageSample> excludedLanguages) {
        final int languagesLength = LanguageDataProvider.LanguageSample.values().length;
        int randomIndex = (int) (Math.random() * (languagesLength - 1));
        System.out.println("randomIndex: " + randomIndex);
        LanguageDataProvider.LanguageSample selectedLanguage = LanguageDataProvider.LanguageSample.values()[randomIndex];
        while (excludedLanguages.contains(selectedLanguage)) {
            // this assumes that the max number of samples used in a game are reasonably greater than the number of samples available
            randomIndex = (randomIndex < languagesLength - 1) ? randomIndex + 1 : 0;
            selectedLanguage = LanguageDataProvider.LanguageSample.values()[randomIndex];
            System.out.println("randomIndexUpdated: " + randomIndex);
        }
        excludedLanguages.add(selectedLanguage);
        return selectedLanguage;
    }

    public RoundData getRoundData(GameState.PlayerLevel playerLevel) {
        final HashSet<LanguageDataProvider.LanguageSample> seenChoiceLanguages = new HashSet<>();
        final int correctSampleIndex = (int) (Math.random() * LanguageDataProvider.soundFileCount);
        int correctChoiceSampleIndex = (int) (Math.random() * LanguageDataProvider.soundFileCount);
        while (correctChoiceSampleIndex == correctSampleIndex) {
            correctChoiceSampleIndex = (int) (Math.random() * LanguageDataProvider.soundFileCount);
        }
        final RoundSample correctSample = new RoundSample(getUniqueLanguage(seenCorrectLanguages), true, correctSampleIndex);
        final List<RoundSample> roundChoices = new ArrayList<>();
        roundChoices.add(new RoundSample(correctSample.getLanguageSample(), true, correctChoiceSampleIndex));
        for (int choiceIndex = 0; choiceIndex < playerLevel.getChoiceCount(); choiceIndex++) {
            roundChoices.add(new RoundSample(getUniqueLanguage(seenChoiceLanguages), false, (int) (Math.random() * LanguageDataProvider.soundFileCount)));
        }
        return new RoundData(correctSample, roundChoices, new Date());
    }
}

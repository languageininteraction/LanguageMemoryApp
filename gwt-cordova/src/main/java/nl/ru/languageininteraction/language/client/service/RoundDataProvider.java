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

import java.util.Date;
import nl.ru.languageininteraction.language.client.LanguageDataProvider;
import nl.ru.languageininteraction.language.client.model.RoundData;
import nl.ru.languageininteraction.language.client.model.RoundSample;
import nl.ru.languageininteraction.language.client.util.GameState;

/**
 * @since Jan 29, 2015 2:05:51 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RoundDataProvider {

    public RoundData getRoundData(GameState.PlayerLevel playerLevel) {
        return new RoundData(new RoundSample(LanguageDataProvider.LanguageSample.arz, true, 1),
                new RoundSample[]{new RoundSample(LanguageDataProvider.LanguageSample.arz, true, 2),
                    new RoundSample(LanguageDataProvider.LanguageSample.cmn, false, 3),
                    new RoundSample(LanguageDataProvider.LanguageSample.deu, false, 3),
                    new RoundSample(LanguageDataProvider.LanguageSample.fij, false, 3),
                    new RoundSample(LanguageDataProvider.LanguageSample.spa, false, 2)
                }, new Date());
    }
}

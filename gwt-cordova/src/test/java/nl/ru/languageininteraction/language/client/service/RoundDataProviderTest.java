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
import nl.ru.languageininteraction.language.client.LanguageDataProvider;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since Mar 23, 2015 1:38:38 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RoundDataProviderTest {

    /**
     * Test of getUniqueLanguage method, of class RoundDataProvider. Make sure
     * all languages can be retrieved and without error or excessive delay
     * (although efficiency is not likely to be an issue in the current intended
     * use)
     */
    @Test
    public void testGetUniqueLanguage() {
        System.out.println("getUniqueLanguage");
        final int languagesLength = LanguageDataProvider.LanguageSample.values().length;
        System.out.println("languagesLength: " + languagesLength);
        ArrayList<LanguageDataProvider.LanguageSample> availableLanguages = new ArrayList(Arrays.asList(LanguageDataProvider.LanguageSample.values()));
        RoundDataProvider instance = new RoundDataProvider();
        int generatedSampleCount = 0;
        while (availableLanguages.size() > 0) {
            final LanguageDataProvider.LanguageSample uniqueLanguage = instance.getUniqueLanguage(availableLanguages);
            System.out.println(uniqueLanguage + " : " + availableLanguages.size());
            generatedSampleCount++;
        }
        assertEquals(languagesLength, generatedSampleCount);
    }
}

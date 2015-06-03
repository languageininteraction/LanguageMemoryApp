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
package nl.ru.languageininteraction.language.client.service;

import java.util.Arrays;
import java.util.Date;
import nl.ru.languageininteraction.language.client.LanguageDataProvider;
import nl.ru.languageininteraction.language.client.model.RoundData;
import nl.ru.languageininteraction.language.client.model.RoundSample;
import nl.ru.languageininteraction.language.client.model.UserData;
import nl.ru.languageininteraction.language.client.model.UserId;
import nl.ru.languageininteraction.language.client.model.UserResults;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since Oct 31, 2014 4:33:24 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ResultsSerialiserTest {

    private RoundSample getRoundSample(final LanguageDataProvider.LanguageSample languageSample, final int sampleIndex, final boolean correct) {
        return new RoundSample(languageSample, correct, sampleIndex);
    }

    /**
     * Test of serialise method, of class ResultsSerialiser.
     */   
//  This test will fail beause GWT.create cannot run in a normal unit test. So for now we will ignore this test, but at some point it should be mocked. 
//  While this could be done with /*extends GWTTestCase*/ that would make the tests run very slowly for no good reason.
//    @Ignore
    @Test
    public void testSerialise() {
        System.out.println("serialise");
        UserResults userResults = new UserResults(new UserData(new UserId("auserid")));
        final RoundData roundData = new RoundData(getRoundSample(LanguageDataProvider.LanguageSample.cmn, 2, false), Arrays.asList(new RoundSample[]{getRoundSample(LanguageDataProvider.LanguageSample.arz, 3, true), getRoundSample(LanguageDataProvider.LanguageSample.spa, 3, false), getRoundSample(LanguageDataProvider.LanguageSample.deu, 3, true)}), new Date(9999999));
        roundData.setChosenAnswer(getRoundSample(LanguageDataProvider.LanguageSample.fij, 1, true));
        roundData.setDurationMs(123456);
        userResults.getGameData().addRoundData(roundData);
        final RoundData roundData1 = new RoundData(getRoundSample(LanguageDataProvider.LanguageSample.spa, 1, true), Arrays.asList(new RoundSample[]{getRoundSample(LanguageDataProvider.LanguageSample.cmn, 1, false), getRoundSample(LanguageDataProvider.LanguageSample.deu, 1, true), getRoundSample(LanguageDataProvider.LanguageSample.fij, 1, false)}), new Date(9999999));
        roundData1.setChosenAnswer(getRoundSample(LanguageDataProvider.LanguageSample.cmn, 1, false));
        roundData1.setDurationMs(123456);
        userResults.getGameData().addRoundData(roundData1);
        ResultsSerialiser instance = new ResultsSerialiser() {
            @Override
            protected String formatDate(Date date) {
                return date.toString();
            }
        };
        String expResult = "auserid	fij_1	cmn_2	arz_3,spa_3,deu_3,	Thu Jan 01 03:46:39 CET 1970	123456.0\n"
                + "auserid	cmn_1	spa_1	cmn_1,deu_1,fij_1,	Thu Jan 01 03:46:39 CET 1970	123456.0\n";
        String result = instance.serialise(userResults);
        System.out.println(result);
        assertEquals(expResult, result);
    }
}

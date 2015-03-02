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
package nl.ru.languageininteraction.synaesthesia.client.service;

import java.util.Date;
import nl.ru.languageininteraction.language.client.LanguageDataProvider;
import nl.ru.languageininteraction.language.client.model.RoundData;
import nl.ru.languageininteraction.language.client.model.RoundSample;
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
    @Test
    public void testSerialise() {
        System.out.println("serialise");
        UserResults userResults = new UserResults(new StimuliGroup(null, null, null));
        final String postName_email = "postName_email";
        userResults.setMetadataValue(postName_email, "postName@email");
        final ArrayList<Stimulus> arrayList1 = new ArrayList<>();
        arrayList1.add(new Stimulus("a1"));
        arrayList1.add(new Stimulus("b1"));
        arrayList1.add(new Stimulus("c1"));
        arrayList1.add(new Stimulus("d1"));
        arrayList1.add(new Stimulus("e1"));
        arrayList1.add(new Stimulus("f1"));
        final StimuliGroup stimuli1 = new StimuliGroup("test-group-1", "test-group-1", arrayList1);
        final ArrayList<Stimulus> arrayList2 = new ArrayList<>();
        arrayList2.add(new Stimulus("a2"));
        arrayList2.add(new Stimulus("b2"));
        arrayList2.add(new Stimulus("c2"));
        arrayList2.add(new Stimulus("d2"));
        arrayList2.add(new Stimulus("e2"));
        arrayList2.add(new Stimulus("f2"));
        final StimuliGroup stimuli2 = new StimuliGroup("test-group-2", "test-group-2", arrayList2);
        final StimulusResponseGroup stimulusResponseGroup1 = new StimulusResponseGroup();
        userResults.addStimulusResponseGroup(stimuli1, stimulusResponseGroup1);
        for (Stimulus stimulus : arrayList1) {
            stimulusResponseGroup1.addResponse(stimulus, new StimulusResponse(new ColourData(255, 255, 255), new Date(123445), 23));
            stimulusResponseGroup1.addResponse(stimulus, new StimulusResponse(new ColourData(0, 0, 0), new Date(12321), 21));
            stimulusResponseGroup1.addResponse(stimulus, new StimulusResponse(new ColourData(128, 127, 126), new Date(123445), 20));
        }
        final StimulusResponseGroup stimulusResponseGroup2 = new StimulusResponseGroup();
        userResults.addStimulusResponseGroup(stimuli2, stimulusResponseGroup2);
        for (Stimulus stimulus : arrayList2) {
            stimulusResponseGroup2.addResponse(stimulus, new StimulusResponse(new ColourData(255, 255, 0), new Date(9999999), 141));
            stimulusResponseGroup2.addResponse(stimulus, new StimulusResponse(new ColourData(255, 0, 255), new Date(33333), 121));
            stimulusResponseGroup2.addResponse(stimulus, new StimulusResponse(new ColourData(0, 255, 255), new Date(111111), 12));
        }
        ResultsSerialiser instance = new ResultsSerialiser() {
            @Override
            protected String formatDate(Date date) {
                return date.toString();
            }
        };
        String expResult = "postName@email	fij_1	cmn_2	arz_3,spa_3,deu_3,	Thu Jan 01 03:46:39 CET 1970	123456.0\n"
                + "postName@email	cmn_1	spa_1	cmn_1,deu_1,fij_1,	Thu Jan 01 03:46:39 CET 1970	123456.0\n";
        String result = instance.serialise(userResults, postName_email);
        System.out.println(result);
        assertEquals(expResult, result);
    }
}

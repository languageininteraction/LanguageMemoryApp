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

import java.util.ArrayList;
import java.util.Date;
import nl.ru.languageininteraction.synaesthesia.client.exception.StimulusError;
import nl.ru.languageininteraction.synaesthesia.client.model.ColourData;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.model.Stimulus;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponse;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponseGroup;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since Oct 31, 2014 4:33:24 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ResultsSerialiserTest {

    /**
     * Test of serialise method, of class ResultsSerialiser.
     */
    @Test
    public void testSerialise() throws StimulusError {
        System.out.println("serialise");
        UserResults userResults = new UserResults(new StimuliGroup(null, null));
        final String postName_email = "postName_email";
        userResults.setMetadataValue(postName_email, "postName@email");
        final ArrayList<Stimulus> arrayList1 = new ArrayList<>();
        arrayList1.add(new Stimulus("a1"));
        arrayList1.add(new Stimulus("b1"));
        arrayList1.add(new Stimulus("c1"));
        arrayList1.add(new Stimulus("d1"));
        arrayList1.add(new Stimulus("e1"));
        arrayList1.add(new Stimulus("f1"));
        final StimuliGroup stimuli1 = new StimuliGroup("test-group-1", arrayList1);
        final ArrayList<Stimulus> arrayList2 = new ArrayList<>();
        arrayList2.add(new Stimulus("a2"));
        arrayList2.add(new Stimulus("b2"));
        arrayList2.add(new Stimulus("c2"));
        arrayList2.add(new Stimulus("d2"));
        arrayList2.add(new Stimulus("e2"));
        arrayList2.add(new Stimulus("f2"));
        final StimuliGroup stimuli2 = new StimuliGroup("test-group-2", arrayList2);
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
        String expResult = "postName@email	test-group-1	a1	Thu Jan 01 01:02:03 CET 1970	23.0	#ffffff	255	255	255\n"
                + "postName@email	test-group-1	a1	Thu Jan 01 01:00:12 CET 1970	21.0	#000000	0	0	0\n"
                + "postName@email	test-group-1	a1	Thu Jan 01 01:02:03 CET 1970	20.0	#807f7e	128	127	126\n"
                + "postName@email	test-group-1	b1	Thu Jan 01 01:02:03 CET 1970	23.0	#ffffff	255	255	255\n"
                + "postName@email	test-group-1	b1	Thu Jan 01 01:00:12 CET 1970	21.0	#000000	0	0	0\n"
                + "postName@email	test-group-1	b1	Thu Jan 01 01:02:03 CET 1970	20.0	#807f7e	128	127	126\n"
                + "postName@email	test-group-1	c1	Thu Jan 01 01:02:03 CET 1970	23.0	#ffffff	255	255	255\n"
                + "postName@email	test-group-1	c1	Thu Jan 01 01:00:12 CET 1970	21.0	#000000	0	0	0\n"
                + "postName@email	test-group-1	c1	Thu Jan 01 01:02:03 CET 1970	20.0	#807f7e	128	127	126\n"
                + "postName@email	test-group-1	d1	Thu Jan 01 01:02:03 CET 1970	23.0	#ffffff	255	255	255\n"
                + "postName@email	test-group-1	d1	Thu Jan 01 01:00:12 CET 1970	21.0	#000000	0	0	0\n"
                + "postName@email	test-group-1	d1	Thu Jan 01 01:02:03 CET 1970	20.0	#807f7e	128	127	126\n"
                + "postName@email	test-group-1	e1	Thu Jan 01 01:02:03 CET 1970	23.0	#ffffff	255	255	255\n"
                + "postName@email	test-group-1	e1	Thu Jan 01 01:00:12 CET 1970	21.0	#000000	0	0	0\n"
                + "postName@email	test-group-1	e1	Thu Jan 01 01:02:03 CET 1970	20.0	#807f7e	128	127	126\n"
                + "postName@email	test-group-1	f1	Thu Jan 01 01:02:03 CET 1970	23.0	#ffffff	255	255	255\n"
                + "postName@email	test-group-1	f1	Thu Jan 01 01:00:12 CET 1970	21.0	#000000	0	0	0\n"
                + "postName@email	test-group-1	f1	Thu Jan 01 01:02:03 CET 1970	20.0	#807f7e	128	127	126\n"
                + "postName@email	test-group-2	a2	Thu Jan 01 03:46:39 CET 1970	141.0	#ffff00	255	255	0\n"
                + "postName@email	test-group-2	a2	Thu Jan 01 01:00:33 CET 1970	121.0	#ff00ff	255	0	255\n"
                + "postName@email	test-group-2	a2	Thu Jan 01 01:01:51 CET 1970	12.0	#00ffff	0	255	255\n"
                + "postName@email	test-group-2	b2	Thu Jan 01 03:46:39 CET 1970	141.0	#ffff00	255	255	0\n"
                + "postName@email	test-group-2	b2	Thu Jan 01 01:00:33 CET 1970	121.0	#ff00ff	255	0	255\n"
                + "postName@email	test-group-2	b2	Thu Jan 01 01:01:51 CET 1970	12.0	#00ffff	0	255	255\n"
                + "postName@email	test-group-2	c2	Thu Jan 01 03:46:39 CET 1970	141.0	#ffff00	255	255	0\n"
                + "postName@email	test-group-2	c2	Thu Jan 01 01:00:33 CET 1970	121.0	#ff00ff	255	0	255\n"
                + "postName@email	test-group-2	c2	Thu Jan 01 01:01:51 CET 1970	12.0	#00ffff	0	255	255\n"
                + "postName@email	test-group-2	d2	Thu Jan 01 03:46:39 CET 1970	141.0	#ffff00	255	255	0\n"
                + "postName@email	test-group-2	d2	Thu Jan 01 01:00:33 CET 1970	121.0	#ff00ff	255	0	255\n"
                + "postName@email	test-group-2	d2	Thu Jan 01 01:01:51 CET 1970	12.0	#00ffff	0	255	255\n"
                + "postName@email	test-group-2	e2	Thu Jan 01 03:46:39 CET 1970	141.0	#ffff00	255	255	0\n"
                + "postName@email	test-group-2	e2	Thu Jan 01 01:00:33 CET 1970	121.0	#ff00ff	255	0	255\n"
                + "postName@email	test-group-2	e2	Thu Jan 01 01:01:51 CET 1970	12.0	#00ffff	0	255	255\n"
                + "postName@email	test-group-2	f2	Thu Jan 01 03:46:39 CET 1970	141.0	#ffff00	255	255	0\n"
                + "postName@email	test-group-2	f2	Thu Jan 01 01:00:33 CET 1970	121.0	#ff00ff	255	0	255\n"
                + "postName@email	test-group-2	f2	Thu Jan 01 01:01:51 CET 1970	12.0	#00ffff	0	255	255\n";
        String result = instance.serialise(userResults, postName_email);
        assertEquals(expResult, result);
    }

}

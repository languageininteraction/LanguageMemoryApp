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
package nl.ru.languageininteraction.synaesthesia.client;

import com.google.gwt.user.client.Random;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import nl.ru.languageininteraction.synaesthesia.shared.ColourData;
import nl.ru.languageininteraction.synaesthesia.shared.Stimulus;
import nl.ru.languageininteraction.synaesthesia.shared.StimulusResponse;

/**
 * @since Oct 14, 2014 1:11:22 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class UserResults {

    private final String name;
    private final String email;
    private final HashMap<Stimulus, StimulusResponse[]> results = new HashMap<>();

    public UserResults(String name, String email) {
        this.name = name;
        this.email = email;
        for (int mockCount = 0; mockCount < 10; mockCount++) {
            results.put(new Stimulus(Integer.toString(mockCount)),
                    new StimulusResponse[]{
                        new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble()), new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble()), new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble()), new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble())
                    });
        }
    }

    public Set<Stimulus> getStimulus() {
        return results.keySet();
    }

    public StimulusResponse[] getResults(Stimulus stimulus) {
        return results.get(stimulus);
    }

    public int getMaxResponses() {
        int max = 0;
        for (StimulusResponse[] responses : results.values()) {
            max = (max > responses.length) ? max : responses.length;
        }
        return max;
    }
}

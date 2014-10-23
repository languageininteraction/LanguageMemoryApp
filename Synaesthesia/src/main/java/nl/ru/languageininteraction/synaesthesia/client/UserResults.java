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
import nl.ru.languageininteraction.synaesthesia.shared.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.shared.Stimulus;
import nl.ru.languageininteraction.synaesthesia.shared.StimulusResponse;
import nl.ru.languageininteraction.synaesthesia.shared.StimulusResponseGroup;

/**
 * @since Oct 14, 2014 1:11:22 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class UserResults {

    private final HashMap<String, String> metadataValues = new HashMap<>();
    private final HashMap<StimuliGroup, StimulusResponseGroup> results = new HashMap<>();

    public UserResults() {
    }

    public void addDummyResults(String groupName) {
        final StimulusResponseGroup stimulusResponseGroup = new StimulusResponseGroup();
        results.put(new StimuliGroup(groupName), stimulusResponseGroup);
        for (int mockCount = 0; mockCount < 10; mockCount++) {
            stimulusResponseGroup.addResponse(new Stimulus(Integer.toString(mockCount)), new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble()));
            stimulusResponseGroup.addResponse(new Stimulus(Integer.toString(mockCount)), new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble()));
            stimulusResponseGroup.addResponse(new Stimulus(Integer.toString(mockCount)), new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble()));
        }
    }

    public Set<StimuliGroup> getStimuliGroups() {
        return results.keySet();
    }

    public StimulusResponseGroup getStimulusResponseGroup(StimuliGroup stimuliGroup) {
        return results.get(stimuliGroup);
    }

    public void setMetadataValue(String key, String value) {
        metadataValues.put(key, value);
    }

    public String getMetadataValue(String key) {
        return metadataValues.get(key);
    }
}

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
package nl.ru.languageininteraction.synaesthesia.client.model;

import com.google.gwt.user.client.Random;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * @since Oct 14, 2014 1:11:22 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class UserResults {

    private final HashMap<String, String> metadataValues = new HashMap<>();
    private final HashMap<StimuliGroup, StimulusResponseGroup> results = new HashMap<>();
    private StimuliGroup pendingStimuliGroup = null;

    public UserResults() {
    }

    public void addDummyResults(StimuliGroup stimuliGroup) {
        final StimulusResponseGroup stimulusResponseGroup = new StimulusResponseGroup();
        results.put(stimuliGroup, stimulusResponseGroup);
        for (Stimulus stimulus : stimuliGroup.getStimuli()) {
            stimulusResponseGroup.addResponse(stimulus, new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble()));
            stimulusResponseGroup.addResponse(stimulus, new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble()));
            stimulusResponseGroup.addResponse(stimulus, new StimulusResponse(new ColourData(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)), new Date(), Random.nextDouble()));
        }
    }

    public Set<StimuliGroup> getStimuliGroups() {
        return results.keySet();
    }

    public void addStimulusResponseGroup(StimuliGroup stimuliGroup, StimulusResponseGroup stimulusResponseGroup) {
        results.put(stimuliGroup, stimulusResponseGroup);
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

    public Set<String> getMetadataKeys() {
        return metadataValues.keySet();
    }

    public StimuliGroup getPendingStimuliGroup() {
        return pendingStimuliGroup;
    }

    public void setPendingStimuliGroup(StimuliGroup pendingStimuliGroup) {
        this.pendingStimuliGroup = pendingStimuliGroup;
    }
}

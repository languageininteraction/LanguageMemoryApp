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
package nl.ru.languageininteraction.synaesthesia.shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @since Oct 22, 2014 5:28:55 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class StimulusResponseGroup {

    private final HashMap<Stimulus, ArrayList<StimulusResponse>> stimulusResponses = new HashMap<>();

    public void addResponse(Stimulus stimulus, StimulusResponse response) {
        final ArrayList<StimulusResponse> knownResponses = stimulusResponses.get(stimulus);
        if (knownResponses == null) {
            final ArrayList<StimulusResponse> tempResponses = new ArrayList<>();
            tempResponses.add(response);
            stimulusResponses.put(stimulus, tempResponses);
        } else {
            knownResponses.add(response);
        }
    }

    public List<StimulusResponse> getResults(Stimulus stimulus) {
        return Collections.<StimulusResponse>unmodifiableList(stimulusResponses.get(stimulus));
    }

    public int getMaxResponses() {
        int max = 0;
        for (List<StimulusResponse> responses : stimulusResponses.values()) {
            max = (max > responses.size()) ? max : responses.size();
        }
        return max;
    }
}

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

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import nl.ru.languageininteraction.synaesthesia.shared.Stimulus;
import nl.ru.languageininteraction.synaesthesia.shared.StimuliGroup;

/**
 * @since Oct 22, 2014 12:07:20 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class StimuliProvider {

    private final Stimuli stimuli = GWT.create(Stimuli.class);

    public StimuliGroup[] getStimuliNames() {
        return new StimuliGroup[]{new StimuliGroup(stimuli.stimuli1Label()), new StimuliGroup(stimuli.stimuli2Label()), new StimuliGroup(stimuli.stimuli3Label())};
    }

    private ArrayList<Stimulus> getStimulusArray(String stimuliCsv) {
        final ArrayList<Stimulus> arrayList = new ArrayList<>();
        for (String simulus : stimuliCsv.split(",")) {
            arrayList.add(new Stimulus(simulus));
        }
        return arrayList;
    }

    public ArrayList<Stimulus> getStimuli(StimuliGroup stimuliGroup) throws StimulusError {
        String stimuliLabel = stimuliGroup.getGroupLabel();
        if (stimuli.stimuli1Label().equals(stimuliLabel)) {
            return getStimulusArray(stimuli.stimuli1csv());
        } else if (stimuli.stimuli2Label().equals(stimuliLabel)) {
            return getStimulusArray(stimuli.stimuli2csv());
        } else if (stimuli.stimuli3Label().equals(stimuliLabel)) {
            return getStimulusArray(stimuli.stimuli3csv());
        }
        throw new StimulusError("No stimuli for " + stimuliLabel);
    }
}

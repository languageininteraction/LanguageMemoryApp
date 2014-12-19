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

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import nl.ru.languageininteraction.synaesthesia.client.Stimuli;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.model.Stimulus;

/**
 * @since Oct 22, 2014 12:07:20 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class StimuliProvider {

    private final Stimuli stimuli = GWT.create(Stimuli.class);

    public StimuliGroup getDefaultStimuli() {
        return getStimuli()[3];
    }

    public StimuliGroup[] getStimuli() {
        return new StimuliGroup[]{
            new StimuliGroup(stimuli.stimuli0Label(), getStimulusArray(stimuli.stimuli0csv())),
            new StimuliGroup(stimuli.stimuli1Label(), getStimulusArray(stimuli.stimuli1csv())),
            new StimuliGroup(stimuli.stimuli2Label(), getStimulusArray(stimuli.stimuli2csv())),
            new StimuliGroup(stimuli.stimuli3Label(), getStimulusArray(stimuli.stimuli3csv()))
        };
    }

    private ArrayList<Stimulus> getStimulusArray(String stimuliCsv) {
        final ArrayList<Stimulus> arrayList = new ArrayList<>();
        for (String simulus : stimuliCsv.split(",")) {
            arrayList.add(new Stimulus(simulus));
        }
        return arrayList;
    }
}

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

import nl.ru.languageininteraction.language.client.model.Stimulus;
import java.util.ArrayList;

/**
 * @since Nov 10, 2014 2:09:47 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ScoreData {

    private final Stimulus stimulus;
    private final int averageLuminance;
    private final ArrayList<ColourData> colourData;
    private final Float distance;

    public ScoreData(Stimulus stimulus, int averageLuminance, ArrayList<ColourData> colourData, Float distance) {
        this.stimulus = stimulus;
        this.averageLuminance = averageLuminance;
        this.colourData = colourData;
        this.distance = distance;
    }

    public Stimulus getStimulus() {
        return stimulus;
    }

    public int getAverageLuminance() {
        return averageLuminance;
    }

    public ArrayList<ColourData> getColourData() {
        return colourData;
    }

    public Float getDistance() {
        return distance;
    }
}

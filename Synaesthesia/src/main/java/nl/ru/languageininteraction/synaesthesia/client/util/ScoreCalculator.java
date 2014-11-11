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
package nl.ru.languageininteraction.synaesthesia.client.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import nl.ru.languageininteraction.synaesthesia.client.model.ColourData;
import nl.ru.languageininteraction.synaesthesia.client.model.GroupScoreData;
import nl.ru.languageininteraction.synaesthesia.client.model.ScoreData;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.model.Stimulus;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponse;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponseGroup;

/**
 * @since Oct 21, 2014 4:49:43 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ScoreCalculator {

    double tempScoreValue = 0.5;
    private final UserResults userResults;

    public ScoreCalculator(UserResults userResults) {
        this.userResults = userResults;
    }

    public Set<StimuliGroup> getStimuliGroups() {
        return userResults.getStimuliGroups();
    }

    public GroupScoreData calculateScores(StimuliGroup group) {
        ArrayList<ScoreData> scoreList = new ArrayList<>();
        final StimulusResponseGroup stimulusResponseGroup = userResults.getStimulusResponseGroup(group);
        final List<Stimulus> allStimulus = group.getStimuli();
        int columnCount = stimulusResponseGroup.getMaxResponses();
        for (Stimulus stimulus : allStimulus) {
            final ArrayList<ColourData> colourList = new ArrayList<>();
            List<StimulusResponse> responses = stimulusResponseGroup.getResults(stimulus);
            int averageLuminance = 0;
            int validCount = 0;
            // set the last as the previous to provide the correct overlap
            ColourData previousColour = responses.get(responses.size() - 1).getColour();
            ColourData difference = null;
            // loop over all except the first which is already processed
            for (int column = 0; column < columnCount; column++) {
                final ColourData colour = responses.get(column).getColour();
                colourList.add(colour);
                if (colour == null) {
                } else {
                    validCount++;
                    averageLuminance += colour.getLuminance();
                    if (previousColour != null) {
                        difference = (difference == null) ? previousColour.difference(colour) : difference.add(previousColour.difference(colour));
                    }
                    // update the previous only if the current colour is valid
                    previousColour = (colour != null) ? colour : previousColour;
                }
            }
            averageLuminance = (validCount > 0) ? averageLuminance / validCount : 0;
            Float distance = (difference == null) ? null : difference.getLuminance() / (255f * columnCount);
            scoreList.add(new ScoreData(stimulus, averageLuminance, colourList, distance));
        }
        // todo: fill these values
        double score=0;
        double accuracy=0;
        double meanReactionTime=0;
        double reactionTimeDeviation=0;
        return new GroupScoreData(scoreList, score, accuracy, meanReactionTime, reactionTimeDeviation);
    }
}

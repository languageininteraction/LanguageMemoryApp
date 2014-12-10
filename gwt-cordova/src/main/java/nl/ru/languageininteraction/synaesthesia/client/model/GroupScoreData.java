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

import java.util.List;

/**
 * @since Nov 10, 2014 5:38:58 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class GroupScoreData {

    private final List<ScoreData> scoreDataList;
    private final float score;
    private final double accuracy;
    private final double meanReactionTime;
    private final double reactionTimeDeviation;

    public GroupScoreData(List<ScoreData> scoreDataList, float score, double accuracy, double meanReactionTime, double ReactionTimeDeviation) {
        this.scoreDataList = scoreDataList;
        this.score = score;
        this.accuracy = accuracy;
        this.meanReactionTime = meanReactionTime;
        this.reactionTimeDeviation = ReactionTimeDeviation;
    }

    public List<ScoreData> getScoreDataList() {
        return scoreDataList;
    }

    public double getScore() {
        return score;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getMeanReactionTime() {
        return meanReactionTime;
    }

    public double getReactionTimeDeviation() {
        return reactionTimeDeviation;
    }
}

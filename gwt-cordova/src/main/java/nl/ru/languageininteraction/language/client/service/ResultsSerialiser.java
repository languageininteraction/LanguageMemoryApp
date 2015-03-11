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
package nl.ru.languageininteraction.language.client.service;

import java.util.Date;
import nl.ru.languageininteraction.language.client.model.RoundData;
import nl.ru.languageininteraction.language.client.model.RoundSample;
import nl.ru.languageininteraction.language.client.model.UserId;
import nl.ru.languageininteraction.language.client.model.UserResults;

/**
 * @since Oct 31, 2014 3:48:38 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class ResultsSerialiser {

//    private final MetadataFields mateadataFields = GWT.create(MetadataFields.class);
    public String serialise(UserResults userResults) {
        UserId userId = userResults.getUserData().getUserId();
        StringBuilder stringBuilder = new StringBuilder();
        for (RoundData roundData : userResults.getGameData().getGameRoundData()) {
            stringBuilder.append(userId.toString());
            stringBuilder.append("\t");
            stringBuilder.append(roundData.getChosenAnswer().getLanguageSample().getIsoCode());
            stringBuilder.append("_");
            stringBuilder.append(roundData.getChosenAnswer().getSampleIndex());
            stringBuilder.append("\t");
            stringBuilder.append(roundData.getCorrectSample().getLanguageSample().getIsoCode());
            stringBuilder.append("_");
            stringBuilder.append(roundData.getCorrectSample().getSampleIndex());
            stringBuilder.append("\t");
            for (RoundSample roundSample : roundData.getRoundChoices()) {
                stringBuilder.append(roundSample.getLanguageSample().getIsoCode());
                stringBuilder.append("_");
                stringBuilder.append(roundSample.getSampleIndex());
                stringBuilder.append(",");
            }
            stringBuilder.append("\t");
            stringBuilder.append(formatDate(roundData.getTime()));
            stringBuilder.append("\t");
            stringBuilder.append(roundData.getDurationMs());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    protected abstract String formatDate(Date date);
}

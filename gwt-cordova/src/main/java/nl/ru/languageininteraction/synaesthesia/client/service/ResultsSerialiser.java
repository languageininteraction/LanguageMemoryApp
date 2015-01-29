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

import java.util.Date;
import nl.ru.languageininteraction.language.client.listener.LanguageSampleListener;
import nl.ru.languageininteraction.language.client.model.RoundData;
import nl.ru.languageininteraction.language.client.model.UserResults;

/**
 * @since Oct 31, 2014 3:48:38 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class ResultsSerialiser {

//    private final MetadataFields mateadataFields = GWT.create(MetadataFields.class);
    public String serialise(UserResults userResults, String postName_email) {
        StringBuilder stringBuilder = new StringBuilder();
        for (RoundData roundData : userResults.getGameData().getGameRoundData()) {
            stringBuilder.append(userResults.getMetadataValue(postName_email));
            stringBuilder.append("\t");
            stringBuilder.append(roundData.getChosenAnswer().getRoundSample().getLanguageSample().getIsoCode());
            stringBuilder.append("_");
            stringBuilder.append(roundData.getChosenAnswer().getRoundSample().getSampleIndex());
            stringBuilder.append("\t");
            stringBuilder.append(roundData.getCorrectAnswer().getRoundSample().getLanguageSample().getIsoCode());
            stringBuilder.append("_");
            stringBuilder.append(roundData.getCorrectAnswer().getRoundSample().getSampleIndex());
            stringBuilder.append("\t");
            for (LanguageSampleListener roundChoice : roundData.getRoundChoices()) {
                stringBuilder.append(roundChoice.getRoundSample().getLanguageSample().getIsoCode());
                stringBuilder.append("_");
                stringBuilder.append(roundChoice.getRoundSample().getSampleIndex());
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

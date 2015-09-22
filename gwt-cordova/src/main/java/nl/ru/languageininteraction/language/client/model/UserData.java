/*
 * Copyright (C) 2015 Language In Interaction
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
package nl.ru.languageininteraction.language.client.model;

import java.util.HashMap;
import java.util.Set;
import nl.ru.languageininteraction.language.client.exception.MetadataFieldException;
import nl.ru.languageininteraction.language.client.service.MetadataFieldProvider;

/**
 * @since Mar 10, 2015 11:11:43 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class UserData {

    private final HashMap<MetadataField, String> metadataValues = new HashMap<>();
    private final UserId userId;
    private int bestScore = 0;
    private int potentialMaxScore = 0;
    private int gamesPlayed = 0;

    public UserData() {
        this.userId = new UserId();
    }

    public UserData(UserId userId) {
        this.userId = userId;
    }

//    public UserData(String userLabel) {
//        this.userId = new UserId();
//        metadataValues.put(new MetadataFieldProvider().firstNameMetadataField, userLabel);
//    }
    public UserId getUserId() {
        return userId;
    }

//    public void clearMetadata() {
//        metadataValues.clear();
//    }
    public void setMetadataValue(MetadataField metadataField, String value) {
        metadataValues.put(metadataField, value);
    }

    public String getMetadataValue(MetadataField metadataField) {
        final String returnString = metadataValues.get(metadataField);
        return (returnString == null) ? "" : returnString;
    }

    public Set<MetadataField> getMetadataFields() {
        return metadataValues.keySet();
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void addGamePlayed() {
        this.gamesPlayed++;
    }

    public int getScoreRating() {
        final double z = 1.2815;
        // if no games played, return zero
        if (gamesPlayed == 0) {
            return (0);
        }
        final double phat = bestScore / potentialMaxScore;
        // wilson lower bound is between 0 and 1
        final double wilsonLowerBound = ((phat + z * z / (2 * gamesPlayed) - z * Math.sqrt((phat * (1 - phat) + z * z / (4 * gamesPlayed)) / gamesPlayed)) / (1 + z * z / gamesPlayed));
        // scale rating to be between 0 and 10
        // (note: for display, may also need to round to 1 or 2 decimal places)
        return (int) Math.ceil(wilsonLowerBound * 100);
    }

    public int getBestScore() {
        return bestScore;
    }

    public int getPotentialMaxScore() {
        return potentialMaxScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setPotentialMaxScore(int potentialMaxScore) {
        this.potentialMaxScore = potentialMaxScore;
    }

    public void updateBestScore(int bestScore) {
//        setBestScore((getBestScore() < bestScore) ? bestScore : getBestScore());
        setBestScore(getBestScore() + bestScore);
    }

    public void updatePotentialMaxScore(int potentialMaxScore) {
//        setPotentialMaxScore((getPotentialMaxScore() < potentialMaxScore) ? potentialMaxScore : getPotentialMaxScore());
        setPotentialMaxScore(getPotentialMaxScore() + potentialMaxScore);
    }

    public void validateNameField() throws MetadataFieldException {
        final MetadataField firstNameMetadataField = new MetadataFieldProvider().firstNameMetadataField;
        final String nameValue = metadataValues.get(firstNameMetadataField);
        firstNameMetadataField.validateValue(nameValue);
    }
}

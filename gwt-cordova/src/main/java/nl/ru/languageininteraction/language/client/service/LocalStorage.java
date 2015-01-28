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

import nl.ru.languageininteraction.language.client.model.UserResults;
import com.google.gwt.storage.client.Storage;
import nl.ru.languageininteraction.language.client.model.MetadataField;

/**
 * @since Oct 24, 2014 3:01:35 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class LocalStorage {

    private Storage dataStore = null;
    private static final String USER_RESULTS = "UserResults.";
    protected static final String MAX_SCORE = "maxScore";
    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();

    public UserResults getStoredData() {
        UserResults userResults = new UserResults();
        dataStore = Storage.getLocalStorageIfSupported();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                userResults.setMetadataValue(metadataField.getPostName(), getCleanStoredData(USER_RESULTS + metadataField.getPostName()));
            }
        }
        userResults.updateBestScore(getCleanStoredInt(USER_RESULTS + MAX_SCORE));
        return userResults;
    }

    private int getCleanStoredInt(String keyString) {
        final String cleanStoredData = getCleanStoredData(keyString);
        try {
            return Integer.parseInt(cleanStoredData);
        } catch (NumberFormatException exception) {
            return 0;
        }
    }

    private String getCleanStoredData(String keyString) {
        final String storedValue = dataStore.getItem(keyString);
        return (storedValue == null || "undefined".equals(storedValue)) ? "" : storedValue;
    }

    public void clear() {
        dataStore = Storage.getLocalStorageIfSupported();
        if (dataStore != null) {
            dataStore.clear();
        }
    }

    public void storeData(UserResults userResults) {
        dataStore = Storage.getLocalStorageIfSupported();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                dataStore.setItem(USER_RESULTS + metadataField.getPostName(), userResults.getMetadataValue(metadataField.getPostName()));
            }
        }
        dataStore.setItem(USER_RESULTS + MAX_SCORE, Integer.toString(userResults.getGameData().getBestScore()));
    }
}

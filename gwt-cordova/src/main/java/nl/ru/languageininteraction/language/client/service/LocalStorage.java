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
import java.util.ArrayList;
import java.util.List;
import nl.ru.languageininteraction.language.client.model.MetadataField;
import nl.ru.languageininteraction.language.client.model.UserData;
import nl.ru.languageininteraction.language.client.model.UserId;
import nl.ru.languageininteraction.language.client.model.UserLabelData;

/**
 * @since Oct 24, 2014 3:01:35 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class LocalStorage {

    private Storage dataStore = null;
    private static final String USER_RESULTS = "UserResults.";
    private static final String LAST_USER_ID = "LastUserId.";
    private static final String GAME_DATA = "GameData.";
    protected static final String MAX_SCORE = "maxScore";
    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();

    private Storage loadStorage() {
        if (dataStore == null) {
            dataStore = Storage.getLocalStorageIfSupported();
        }
        return dataStore;
    }

    public void clearStoredGameData(UserId userId) {
        loadStorage();
        dataStore.setItem(GAME_DATA + userId.toString(), "");
    }

    public String getStoredGameData(UserId userId) {
        loadStorage();
        return getCleanStoredData(GAME_DATA + userId.toString());
    }

    public void addStoredGameData(UserId userId, String serialisedGameData) {
        loadStorage();
        dataStore.setItem(GAME_DATA + userId.toString(), getCleanStoredData(GAME_DATA + userId.toString()) + serialisedGameData);
    }

    public UserData getStoredData(UserId userId) {
        UserData userData = new UserData(userId);
        loadStorage();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                userData.setMetadataValue(metadataField, getCleanStoredData(USER_RESULTS + userData.getUserId().toString() + "." + metadataField.getPostName()));
            }
        }
        userData.updateBestScore(getCleanStoredInt(USER_RESULTS + userData.getUserId().toString() + "." + MAX_SCORE));
        return userData;
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
        loadStorage();
        if (dataStore != null) {
            dataStore.clear();
        }
    }

    public void storeData(UserResults userResults) {
        loadStorage();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + metadataField.getPostName(), userResults.getUserData().getMetadataValue(metadataField));
            }
        }
        dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + MAX_SCORE, Integer.toString(userResults.getUserData().getBestScore()));
        dataStore.setItem(LAST_USER_ID, userResults.getUserData().getUserId().toString());
    }

    public UserId getLastUserId() {
        loadStorage();
        if (dataStore != null) {
            final String storedUserId = getCleanStoredData(LAST_USER_ID);
            if (!storedUserId.isEmpty()) {
                return new UserId(storedUserId);
            }
        }
        return null;
    }

    public List<UserLabelData> getUserIdList() {
        final String postName = metadataFieldProvider.firstNameMetadataField.getPostName();
        ArrayList<UserLabelData> userIdList = new ArrayList<>();
        loadStorage();
        if (dataStore != null) {

            for (int itemIndex = 0; itemIndex < dataStore.getLength(); itemIndex++) {
                final String key = dataStore.key(itemIndex);
                if (key.endsWith(postName)) {
                    final String userIdString = key.split("\\.")[1];
                    final String cleanStoredData = getCleanStoredData(key);
                    if (!cleanStoredData.isEmpty()) {
                        userIdList.add(new UserLabelData(new UserId(userIdString), cleanStoredData));
                    }
                }
            }
        }
        return userIdList;
    }
}

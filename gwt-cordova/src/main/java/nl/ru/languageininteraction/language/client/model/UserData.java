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

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * @since Mar 10, 2015 11:11:43 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class UserData {

    private final HashMap<MetadataField, String> metadataValues = new HashMap<>();
    private final String userLabel;
    private final String randomIdString;

    public UserData(String userLabel, String randomIdString) {
        this.userLabel = userLabel;
        this.randomIdString = randomIdString;
    }

    public UserData(String userLabel) {
        this.userLabel = userLabel;
        this.randomIdString = getRandomId();
    }

    private String getRandomId() {
        return Long.toHexString(new Date().getTime()) + "-" + Long.toHexString((long) (Math.random() * 0xFFFF)) + "-" + Long.toHexString((long) (Math.random() * 0xFFFF)) + "-" + Long.toHexString((long) (Math.random() * 0xFFFF)) + "-" + Long.toHexString((long) (Math.random() * 0xFFFF));
    }

    public String getUserLabel() {
        return userLabel;
    }

    public String getRandomIdString() {
        return randomIdString;
    }

    public void clearMetadata() {
        metadataValues.clear();
    }

    public void setMetadataValue(MetadataField metadataField, String value) {
        metadataValues.put(metadataField, value);
    }

    public String getMetadataValue(MetadataField metadataField) {
        return metadataValues.get(metadataField);
    }

    public Set<MetadataField> getMetadataFields() {
        return metadataValues.keySet();
    }
}

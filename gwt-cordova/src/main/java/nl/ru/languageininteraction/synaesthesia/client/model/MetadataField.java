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

import java.util.Objects;
import nl.ru.languageininteraction.synaesthesia.client.exception.MetadataFieldException;

/**
 * @since Oct 31, 2014 4:15:16 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataField {

    private final String postName;
    private final String fieldLabel;
    private final String controlledVocabulary;
    private final String controlledRegex;
    private final String controlledMessage;

    public MetadataField(String postName, String fieldLabel, String controlledVocabulary, String controlledRegex, String controlledMessage) {
        this.postName = postName;
        this.fieldLabel = fieldLabel;
        this.controlledVocabulary = controlledVocabulary;
        this.controlledRegex = controlledRegex;
        this.controlledMessage = controlledMessage;
    }

    public String getPostName() {
        return postName;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public String getControlledVocabulary() {
        return controlledVocabulary;
    }

    public void validateValue(String value) throws MetadataFieldException {
        if (controlledRegex == null) {
            return;
        }
        if (value == null || !value.matches(controlledRegex)) {
            throw new MetadataFieldException(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.postName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MetadataField other = (MetadataField) obj;
        return true;
    }
}

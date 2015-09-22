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

import com.google.gwt.core.client.GWT;
import nl.ru.languageininteraction.language.client.MetadataFields;
import nl.ru.languageininteraction.language.client.model.MetadataField;

/**
 * @since Oct 31, 2014 4:15:00 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataFieldProvider {

    private final MetadataFields mateadataFields = GWT.create(MetadataFields.class);
    public final MetadataField ageMetadataField = new MetadataField(mateadataFields.postName_age(), mateadataFields.registrationField_age(), mateadataFields.fieldValues_age(), null, null);
    public final MetadataField shareMetadataField = new MetadataField(mateadataFields.postName_share(), mateadataFields.registrationField_share(), mateadataFields.fieldValues_share(), mateadataFields.controlledRegex_share(), mateadataFields.controlledMessage_share());
    public final MetadataField firstNameMetadataField = new MetadataField(mateadataFields.postName_playername(), mateadataFields.registrationField_playername(), null, mateadataFields.controlledRegex_playername(), mateadataFields.controlledMessage_playername());
    public final MetadataField languagesMetadataField = new MetadataField(mateadataFields.postName_language(), mateadataFields.registrationField_language(), null, null, null);
    public final MetadataField[] metadataFieldArray = new MetadataField[]{
        firstNameMetadataField,
        ageMetadataField,
        languagesMetadataField,
        shareMetadataField
    };
}

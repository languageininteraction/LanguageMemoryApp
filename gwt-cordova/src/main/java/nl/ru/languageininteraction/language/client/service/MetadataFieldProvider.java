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
    public final MetadataField[] metadataFieldArray = new MetadataField[]{
        new MetadataField(mateadataFields.postName_firstname(), mateadataFields.registrationField_firstname(), null),
        new MetadataField(mateadataFields.postName_lastname(), mateadataFields.postName_lastname(), null),
        new MetadataField(mateadataFields.postName_age(), mateadataFields.postName_age(), null),
        new MetadataField(mateadataFields.postName_country(), mateadataFields.postName_country(), null),
        new MetadataField(mateadataFields.postName_language(), mateadataFields.postName_language(), null),
        new MetadataField(mateadataFields.postName_ethnicity(), mateadataFields.postName_ethnicity(), mateadataFields.fieldValues_ethnicity()),
        new MetadataField(mateadataFields.postName_gender(), mateadataFields.postName_gender(), mateadataFields.fieldValues_gender()),
        new MetadataField(mateadataFields.postName_email(), mateadataFields.postName_email(), null)
    };
}

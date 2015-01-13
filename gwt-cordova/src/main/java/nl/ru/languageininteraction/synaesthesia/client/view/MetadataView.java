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
package nl.ru.languageininteraction.synaesthesia.client.view;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import java.util.HashMap;
import java.util.Set;
import nl.ru.languageininteraction.synaesthesia.client.model.MetadataField;

/**
 * @since Oct 21, 2014 11:56:23 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataView extends ComplexView {

    private FlexTable flexTable = null;
    final private HashMap<MetadataField, TextBox> fieldBoxes;
    private TextBox firstTextBox = null;

    public MetadataView() {
        fieldBoxes = new HashMap<>();
    }

    public void addField(final MetadataField metadataField, final String existingValue) {
        if (flexTable == null) {
            flexTable = new FlexTable();
            flexTable.setStylePrimaryName("metadataTable");
            outerPanel.add(flexTable);
        }
        final int rowCount = flexTable.getRowCount();
        flexTable.setWidget(rowCount, 0, new Label(metadataField.getFieldLabel()));
        final TextBox textBox = new TextBox();
        textBox.setStylePrimaryName("metadataOK");
        textBox.setText((existingValue == null) ? "" : existingValue);
        flexTable.setWidget(rowCount, 1, textBox);
        fieldBoxes.put(metadataField, textBox);
        if (firstTextBox == null) {
            firstTextBox = textBox;
        }
    }

    public void focusFirstTextBox() {
        if (firstTextBox != null) {
            firstTextBox.setFocus(true);
        }
    }

    public Set<MetadataField> getFieldNames() {
        return fieldBoxes.keySet();
    }

    public void setFieldValue(MetadataField metadataField, String fieldValue) {
        fieldBoxes.get(metadataField).setValue(fieldValue);
    }

    public String getFieldValue(MetadataField metadataField) {
        return fieldBoxes.get(metadataField).getValue();
    }

    public void showFieldError(MetadataField metadataField) {
        fieldBoxes.get(metadataField).setStylePrimaryName("metadataError");
    }

    public void clearErrors() {
        for (TextBox textBox : fieldBoxes.values()) {
            textBox.setStylePrimaryName("metadataOK");
        }
    }
}

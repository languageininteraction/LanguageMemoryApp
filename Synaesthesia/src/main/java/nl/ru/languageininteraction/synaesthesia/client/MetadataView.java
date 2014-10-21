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
package nl.ru.languageininteraction.synaesthesia.client;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.HashMap;
import java.util.Set;

/**
 * @since Oct 21, 2014 11:56:23 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataView extends SimpleView {

    final private FlexTable flexTable;
    final private HashMap<String, TextBox> fieldBoxes;

    public MetadataView(String instructionsText) {
        flexTable = new FlexTable();
        flexTable.setStylePrimaryName("metadataTable");
        fieldBoxes = new HashMap<>();
        final VerticalPanel verticalPanel = new VerticalPanel();
        HTML instructionsLabel = new HTML(instructionsText);
        verticalPanel.add(instructionsLabel);
        verticalPanel.add(flexTable);
        setContent(verticalPanel);
    }

    public void addField(final String fieldName, final String displayName) {
        final int rowCount = flexTable.getRowCount();
        flexTable.setWidget(rowCount, 0, new Label(displayName));
        final TextBox textBox = new TextBox();
        flexTable.setWidget(rowCount, 1, textBox);
        fieldBoxes.put(fieldName, textBox);
    }

    public Set<String> getFieldNames() {
        return fieldBoxes.keySet();
    }

    public String getFieldValue(String fieldName) {
        return fieldBoxes.get(fieldName).getValue();
    }
}

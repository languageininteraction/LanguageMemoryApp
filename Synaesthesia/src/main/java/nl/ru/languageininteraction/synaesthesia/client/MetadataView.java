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

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.HashMap;

/**
 * @since Oct 21, 2014 11:56:23 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataView extends SimpleView {

    final private VerticalPanel outerPanel;
    final private HashMap<String, TextBox> fieldBoxes;

    public MetadataView() {
        outerPanel = new VerticalPanel();
        fieldBoxes = new HashMap<>();
        Label instructionsLabel = new Label("instructionsLabel");
        outerPanel.add(instructionsLabel);
        setContent(outerPanel);
    }

    public void addField(final String fieldName, final String displayName) {
        final HorizontalPanel fieldPanel = new HorizontalPanel();
        fieldPanel.add(new Label(displayName));
        final TextBox textBox = new TextBox();
        fieldPanel.add(textBox);
        fieldBoxes.put(fieldName, textBox);
        outerPanel.add(fieldPanel);
    }

    public HashMap<String, String> getFieldValues() {
        final HashMap<String, String> fieldValues = new HashMap<>();
        for (String fieldName : fieldBoxes.keySet()) {
            fieldValues.put(fieldName, fieldBoxes.get(fieldName).getValue());
        }
        return fieldValues;
    }
}

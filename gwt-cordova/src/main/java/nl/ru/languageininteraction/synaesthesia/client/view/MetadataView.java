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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.HashMap;
import java.util.Set;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;

/**
 * @since Oct 21, 2014 11:56:23 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataView extends SimpleView {

    final VerticalPanel outerPanel;
    private FlexTable flexTable = null;
    final private HashMap<String, TextBox> fieldBoxes;
    private TextBox firstTextBox = null;

    public MetadataView() {
        outerPanel = new VerticalPanel();
        //outerPanel.setWidth("100%");
        setContent(outerPanel);
        fieldBoxes = new HashMap<>();
    }

    public void addText(String textString) {
        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
        outerPanel.add(html);
    }

    public void addOptionButton(final PresenterEventListner presenterListerner) {
        final Button nextButton = new Button(presenterListerner.getLabel());
        nextButton.addStyleName("optionButton");
        nextButton.setEnabled(true);
        outerPanel.add(nextButton);
        nextButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                presenterListerner.eventFired(nextButton);
            }
        });
    }

    public void addField(final String fieldName, final String displayName, final String existingValue) {
        if (flexTable == null) {
            flexTable = new FlexTable();
            flexTable.setStylePrimaryName("metadataTable");
            outerPanel.add(flexTable);
        }
        final int rowCount = flexTable.getRowCount();
        flexTable.setWidget(rowCount, 0, new Label(displayName));
        final TextBox textBox = new TextBox();
        textBox.setText((existingValue == null) ? "" : existingValue);
        flexTable.setWidget(rowCount, 1, textBox);
        fieldBoxes.put(fieldName, textBox);
        if (firstTextBox == null) {
            firstTextBox = textBox;
        }
    }

    public void focusFirstTextBox() {
        if (firstTextBox != null) {
            firstTextBox.setFocus(true);
        }
    }

    public Set<String> getFieldNames() {
        return fieldBoxes.keySet();
    }

    public void setFieldValue(String fieldName, String fieldValue) {
        fieldBoxes.get(fieldName).setValue(fieldValue);
    }

    public String getFieldValue(String fieldName) {
        return fieldBoxes.get(fieldName).getValue();
    }
}

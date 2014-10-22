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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @since Oct 21, 2014 5:15:19 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RegisterView extends SimpleView {

    final VerticalPanel outerPanel;
    private FlexTable flexTable = null;

    public RegisterView() {
        outerPanel = new VerticalPanel();
        setContent(outerPanel);
    }

    public void addText(String textString) {
        HTML html = new HTML(textString);
        outerPanel.add(html);
    }

    public void addField(final String displayName, final String value) {
        if (flexTable == null) {
            flexTable = new FlexTable();
            flexTable.setStylePrimaryName("metadataTable");
            outerPanel.add(flexTable);
        }
        final int rowCount = flexTable.getRowCount();
        flexTable.setWidget(rowCount, 0, new Label(displayName));
        flexTable.setWidget(rowCount, 1, new HTML(value));
    }
}

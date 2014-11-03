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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;

/**
 * @since Oct 31, 2014 11:36:28 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MenuView extends SimpleView {

    final VerticalPanel outerPanel;
    private FlexTable flexTable = null;

    public MenuView() {
        outerPanel = new VerticalPanel();
        setContent(outerPanel);
    }

    public void addText(String textString) {
        HTML html = new HTML(textString);
        outerPanel.add(html);
    }

    public void addMenuItem(final String itemLabel, final PresenterEventListner menuItemListerner) {
        if (flexTable == null) {
            flexTable = new FlexTable();
            flexTable.setStylePrimaryName("menuTable");
            outerPanel.add(flexTable);
        }
        final Button menuButton = new Button(itemLabel);
        menuButton.addStyleName("menuButton");
        menuButton.setEnabled(true);
        menuButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                menuItemListerner.eventFired(menuButton);
            }
        });
        final int rowCount = flexTable.getRowCount();
        flexTable.setWidget(rowCount, 0, menuButton);
    }
}

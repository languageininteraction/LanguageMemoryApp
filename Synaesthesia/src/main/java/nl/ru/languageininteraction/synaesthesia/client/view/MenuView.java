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

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;

/**
 * @since Oct 31, 2014 11:36:28 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MenuView extends SimpleView {

    final private ArrayList<Button> buttonsArray = new ArrayList<>();
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
        buttonsArray.add(menuButton);
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

    @Override
    protected void parentResized(int height, int width, String units) {
        super.parentResized(height, width, units);
        boolean portrate = height < width;
        final int rowPerColumn = (portrate) ? buttonsArray.size() / 2 : buttonsArray.size();
        final int textHeight = (height / (3 + rowPerColumn)) / 3;
        int row = 0;
        int col = 0;
        flexTable.removeAllRows();
        flexTable.setCellPadding(textHeight / 7);
        for (Button menuButton : buttonsArray) {
            menuButton.getElement().getStyle().setFontSize(textHeight, Style.Unit.PX);
            flexTable.setWidget(row, col, menuButton);
            row++;
            if (portrate) {
                if (row > rowPerColumn) {
                    row = 0;
                    col++;
                    flexTable.setWidget(row, col, new Label(""));
                    col++;
                }
            }
        }
    }

}

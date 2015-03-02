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
package nl.ru.languageininteraction.language.client.view;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.SingleShotEventListner;

/**
 * @since Oct 31, 2014 11:36:28 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MenuView extends ComplexView {

//    final private HashMap<FlexTable, ArrayList<IsWidget>> tableMap = new HashMap<>();
    private FlexTable flexTable = null;
//    final FlexTable outerTable;

//    public MenuView() {
//        outerTable = new FlexTable();
//        outerPanel.add(outerTable);
//    }
    public void addSeparateMenuPanel() {
        flexTable = null;
        checkFlexTableExists();
    }

    private void checkFlexTableExists() {
        if (flexTable == null) {
            flexTable = new FlexTable();
            flexTable.setStylePrimaryName("menuTable");
            outerPanel.setStylePrimaryName("menuOuter");
            outerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
            outerPanel.add(flexTable);
        }
    }

    public void addMenuItem(final PresenterEventListner menuItemListerner, final boolean menuEnabled) {
        checkFlexTableExists();
        final Button menuButton = new Button(new SafeHtmlBuilder().appendEscapedLines(menuItemListerner.getLabel()).toSafeHtml());
//        tableMap.get(flexTable).add(menuButton);
        menuButton.addStyleName("menuButton");
        menuButton.setEnabled(menuEnabled);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (menuButton.isEnabled()) {
                    menuItemListerner.eventFired(menuButton);
                }
            }
        };
        menuButton.addClickHandler(singleShotEventListner);
        menuButton.addTouchStartHandler(singleShotEventListner);
        menuButton.addTouchMoveHandler(singleShotEventListner);
        menuButton.addTouchEndHandler(singleShotEventListner);
        final int rowCount = flexTable.getRowCount();
        flexTable.setWidget(rowCount, 0, menuButton);
    }

    public void addMenuLabel(String textString, boolean boldText) {
        checkFlexTableExists();
        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
        if (boldText) {
            html.addStyleName("highlightedText");
        }
//        tableMap.get(flexTable).add(html);
        final int rowCount = flexTable.getRowCount();
        flexTable.setWidget(rowCount, 0, html);
    }

    // previous versions re aranged the menu based on the screen orientation, however the retrospective feature requests have made this hard to maintain.
//    @Override
//    protected void parentResized(int height, int width, String units) {
//        super.parentResized(height, width, units);
//        boolean portrate = height > width;
//        if (tableMap.size() > 1) {
//            int row = 0;
//            int col = 0;
//            outerTable.removeAllRows();
////        flexTable.setCellPadding(textHeight / 7);
//            for (FlexTable table : tableMap.keySet()) {
//
//                outerTable.setWidget(row, col, table);
//                if (portrate) {
//                    row++;
//                } else {
//                    col++;
//                }
//            }
//        } else {
//            for (FlexTable table : tableMap.keySet()) {
//                ArrayList<IsWidget> buttonsArray = tableMap.get(table);
//                final int maxFontPx = 30;
//                final boolean singleColumnOnly = (buttonsArray.size() * maxFontPx < height);
//                final int rowPerColumn = (portrate || singleColumnOnly) ? buttonsArray.size() : buttonsArray.size() / 2;
//                int textHeight = (height / (3 + rowPerColumn)) / 3;
//                textHeight = (textHeight > maxFontPx) ? maxFontPx : textHeight;
//                int row = 0;
//                int col = 0;
//                table.removeAllRows();
//                table.setCellPadding(textHeight / 7);
//                for (IsWidget menuButton : buttonsArray) {
//                    if (menuButton instanceof Button) {
//                        ((Button) menuButton).getElement().getStyle().setFontSize(textHeight, Style.Unit.PX);
//                    }
//                    table.setWidget(row, col, menuButton);
//                    row++;
//                    if (!portrate) {
//                        if (row > rowPerColumn) {
//                            row = 0;
//                            col++;
//                            table.setWidget(row, col, new Label(""));
//                            col++;
//                        }
//                    }
//                }
//                outerTable.setWidget(0, 0, table);
//            }
//        }
//    }
}

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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Grid;

/**
 * @since Oct 8, 2014 5:09:10 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ColourPickerTableView extends AbstractView {

    public ColourPickerTableView() {
        Grid grid = new Grid(50, 50);
        grid.setBorderWidth(0);
        grid.setCellPadding(0);
        grid.setCellSpacing(0);
        final double columnCount = grid.getColumnCount();
        final double rowCount = grid.getRowCount();
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                final Element element = grid.getCellFormatter().getElement(row, column);
                final int rowValue = 255 - (int) (row / (rowCount - 1) * 255);
                final int columnValue = (int) (column / (columnCount - 1) * 255);
                final int colourValue = (int) (columnValue * (1 - row / (rowCount - 1)));
                element.setAttribute("style", "height:5px;width:5px;border:0px none;font-size:1px;color:#FFFFFF;background:rgb(" + colourValue + "," + rowValue + "," + rowValue + ")");
            }
        }
        add(grid);
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        
    }
}

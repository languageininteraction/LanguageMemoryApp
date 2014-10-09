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

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.client.SafeHtmlTemplates.Template;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Grid;

/**
 * @since Oct 8, 2014 5:09:10 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ColourPickerView extends Grid {

    public interface PickerCellTemplate extends SafeHtmlTemplates {

        @Template("<span style=\"width:2px;height:2px;color:#FFFFFF;background:rgb({0},{1},{2})\">&nbsp;</span>")
        SafeHtml getCell(int red, int green, int blue);
    }
    private static final PickerCellTemplate PICKER_CELL_TEMPLATE = GWT.create(PickerCellTemplate.class);

    public ColourPickerView() {
        super(20, 20);
        setCellPadding(0);
        final double columnCount = getColumnCount();
        final double rowCount = getRowCount();
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                final int rowValue = 255 - (int) (row / (rowCount - 1) * 255);
                final int columnValue = (int) (column / (columnCount - 1) * 255);
                this.setHTML(row, column, PICKER_CELL_TEMPLATE.getCell((int) (columnValue * (1 - row / (rowCount - 1))), rowValue, rowValue));
            }
        }
    }
}

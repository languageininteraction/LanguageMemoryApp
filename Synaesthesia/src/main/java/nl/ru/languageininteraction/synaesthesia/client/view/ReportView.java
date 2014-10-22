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

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.Set;
import nl.ru.languageininteraction.synaesthesia.client.UserResults;
import nl.ru.languageininteraction.synaesthesia.shared.ColourData;
import nl.ru.languageininteraction.synaesthesia.shared.Stimulus;
import nl.ru.languageininteraction.synaesthesia.shared.StimulusResponse;

/**
 * @since Oct 14, 2014 10:57:45 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ReportView extends SimpleView {

    final private HorizontalPanel resultsPanel;
    final VerticalPanel outerPanel;

    public ReportView() {
        outerPanel = new VerticalPanel();
        resultsPanel = new HorizontalPanel();
        resultsPanel.setStylePrimaryName("resultsTablePanel");
        outerPanel.add(resultsPanel);
        setContent(outerPanel);
    }

    public void addText(String textString) {
        HTML html = new HTML(textString);
        outerPanel.add(html);
    }

    public void showResults(UserResults userResults) {
        final Set<Stimulus> allStimulus = userResults.getStimulus();
        int rowCount = allStimulus.size();
        int columnCount = userResults.getMaxResponses();
        for (int dataSet = 0; dataSet < 3; dataSet++) {
            int row = 0;
            final Grid grid = new Grid(rowCount, columnCount + 1);
            for (Stimulus stimulus : allStimulus) {
                StimulusResponse[] responses = userResults.getResults(stimulus);
                for (int column = 0; column < columnCount; column++) {
                    final Label label = new Label(stimulus.getValue());
                    final ColourData colour = responses[column].getColour();
                    String foreground = (colour.getRed() + colour.getGreen() + colour.getBlue() > 128 * 3) ? "black" : "white";
                    label.getElement().setAttribute("style", "color:" + foreground + ";background:rgb(" + colour.getRed() + "," + colour.getGreen() + "," + colour.getBlue() + ")");
                    grid.setWidget(row, column, label);
                }
                final HorizontalPanel bargraphOuter = new HorizontalPanel();
                final HorizontalPanel bargraphInner = new HorizontalPanel();
                bargraphOuter.setPixelSize(100, 10);
                bargraphInner.setPixelSize(100 / 10 * row, 10);
                bargraphOuter.setStyleName("bargraphOuter");
                bargraphInner.setStyleName("bargraphInner");
                bargraphOuter.add(bargraphInner);
                grid.setWidget(row, columnCount, bargraphOuter);
                row++;
            }
            resultsPanel.add(grid);
        }
    }
}

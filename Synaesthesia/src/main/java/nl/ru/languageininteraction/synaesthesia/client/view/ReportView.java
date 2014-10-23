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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.List;
import java.util.Set;
import nl.ru.languageininteraction.synaesthesia.client.UserResults;
import nl.ru.languageininteraction.synaesthesia.shared.ColourData;
import nl.ru.languageininteraction.synaesthesia.shared.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.shared.Stimulus;
import nl.ru.languageininteraction.synaesthesia.shared.StimulusResponse;
import nl.ru.languageininteraction.synaesthesia.shared.StimulusResponseGroup;

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
        for (StimuliGroup group : userResults.getStimuliGroups()) {
            final StimulusResponseGroup stimulusResponseGroup = userResults.getStimulusResponseGroup(group);
            final Set<Stimulus> allStimulus = stimulusResponseGroup.getStimulus();
            int rowCount = allStimulus.size();
            int columnCount = stimulusResponseGroup.getMaxResponses();
            int row = 0;
            final FlexTable grid = new FlexTable();
            grid.setWidget(0, 0, new Label(group.getGroupLabel()));
            grid.getFlexCellFormatter().setColSpan(0, 0, columnCount + 1);
            row++;
            for (Stimulus stimulus : allStimulus) {
                List<StimulusResponse> responses = stimulusResponseGroup.getResults(stimulus);
                for (int column = 0; column < columnCount; column++) {
                    final Label label = new Label(stimulus.getValue());
                    final ColourData colour = responses.get(column).getColour();
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

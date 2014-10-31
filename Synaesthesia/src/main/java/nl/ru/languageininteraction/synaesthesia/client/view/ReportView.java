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
import nl.ru.languageininteraction.synaesthesia.client.util.ScoreCalculator;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import nl.ru.languageininteraction.synaesthesia.client.model.ColourData;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.model.Stimulus;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponse;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponseGroup;

/**
 * @since Oct 14, 2014 10:57:45 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ReportView extends SimpleView {

    final private VerticalPanel resultsPanel;
    final VerticalPanel outerPanel;

    public ReportView() {
        outerPanel = new VerticalPanel();
        resultsPanel = new VerticalPanel();
        resultsPanel.setStylePrimaryName("resultsTablePanel");
        outerPanel.add(resultsPanel);
        setContent(outerPanel);
    }

    public void addText(String textString) {
        HTML html = new HTML(textString);
        outerPanel.add(html);
    }

    public void showResults(UserResults userResults, ScoreCalculator scoreCalculator) {
        for (StimuliGroup group : userResults.getStimuliGroups()) {
            final StimulusResponseGroup stimulusResponseGroup = userResults.getStimulusResponseGroup(group);
            final List<Stimulus> allStimulus = group.getStimuli();
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
                    if (colour == null) {
                        label.getElement().setAttribute("style", "color: grey;background: none;");
                    } else {
                        String foreground = (colour.getRed() + colour.getGreen() + colour.getBlue() > 128 * 3) ? "black" : "white";
                        label.getElement().setAttribute("style", "background:" + foreground + ";color:rgb(" + colour.getRed() + "," + colour.getGreen() + "," + colour.getBlue() + ")");
                    }
                    grid.setWidget(row, column, label);
                }
                final HorizontalPanel bargraphOuter = new HorizontalPanel();
                final HorizontalPanel bargraphInner = new HorizontalPanel();
                bargraphOuter.setPixelSize(100, 10);
                bargraphInner.setPixelSize((int) (100 * scoreCalculator.getScore(stimulus)), 10);
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

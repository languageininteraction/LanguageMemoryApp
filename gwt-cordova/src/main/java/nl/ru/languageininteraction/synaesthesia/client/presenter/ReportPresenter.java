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
package nl.ru.languageininteraction.synaesthesia.client.presenter;

import nl.ru.languageininteraction.language.client.presenter.Presenter;
import com.google.gwt.i18n.client.NumberFormat;
import nl.ru.languageininteraction.synaesthesia.client.view.ReportView;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.model.GroupScoreData;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.util.ScoreCalculator;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;

/**
 * @since Oct 14, 2014 10:57:39 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ReportPresenter extends AbstractPresenter implements Presenter {

    private final UserResults userResults;

    public ReportPresenter(RootPanel widgetTag, UserResults userResults) {
        super(widgetTag, new ReportView());
        this.userResults = userResults;
    }

    @Override
    protected void pageClosing() {
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        ((ReportView) simpleView).addTitle(messages.reportScreenTitle(), titleBarListner);
    }

    @Override
    protected void setContent(AppEventListner appEventListner) {
        final NumberFormat numberFormat2 = NumberFormat.getFormat("0.00");
        final NumberFormat numberFormat3 = NumberFormat.getFormat("0.000");
        final ScoreCalculator scoreCalculator = new ScoreCalculator(userResults);
        for (StimuliGroup stimuliGroup : scoreCalculator.getStimuliGroups()) {
            final GroupScoreData calculatedScores = scoreCalculator.calculateScores(stimuliGroup);
            ((ReportView) simpleView).showResults(stimuliGroup, calculatedScores);
            ((ReportView) simpleView).addText(messages.reportScreenScore(numberFormat2.format(calculatedScores.getScore())));
            ((ReportView) simpleView).addText(messages.reportScreenPostScoreText());
            ((ReportView) simpleView).addText(messages.reportScreenSCT());
            ((ReportView) simpleView).addText(messages.reportScreenSCTaccuracy(numberFormat2.format(calculatedScores.getAccuracy())));
            ((ReportView) simpleView).addText(messages.reportScreenSCTmeanreactionTime(numberFormat3.format(calculatedScores.getMeanReactionTime() / 1000), numberFormat3.format(calculatedScores.getReactionTimeDeviation() / 1000)));
        }
        ((ReportView) simpleView).addText(messages.reportScreenPostSCTtext());
    }
}

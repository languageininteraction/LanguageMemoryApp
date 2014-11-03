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

import nl.ru.languageininteraction.synaesthesia.client.view.ReportView;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
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
    void pageClosing() {
    }

    @Override
    void setTitle(PresenterEventListner titleBarListner) {
        ((ReportView) simpleView).addTitle(messages.reportScreenTitle(), titleBarListner);
    }

    @Override
    void setContent(AppEventListner appEventListner) {
        final ScoreCalculator scoreCalculator = new ScoreCalculator(userResults);
        ((ReportView) simpleView).showResults(userResults, scoreCalculator);
        ((ReportView) simpleView).addText(messages.reportScreenScore(Double.toString(scoreCalculator.getScore())));
        ((ReportView) simpleView).addText(messages.reportScreenPostScoreText());
        ((ReportView) simpleView).addText(messages.reportScreenSCT());
        ((ReportView) simpleView).addText(messages.reportScreenSCTaccuracy(Double.toString(scoreCalculator.getAccuracy())));
        ((ReportView) simpleView).addText(messages.reportScreenSCTmeanreactionTime(Double.toString(scoreCalculator.getMeanReactionTime()), Double.toString(scoreCalculator.getReactionTimeDeviation())));
        ((ReportView) simpleView).addText(messages.reportScreenPostSCTtext());

    }

}

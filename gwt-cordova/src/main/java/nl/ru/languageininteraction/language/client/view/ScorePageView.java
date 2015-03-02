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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import nl.ru.languageininteraction.language.client.ScorePage;
import nl.ru.languageininteraction.language.client.ScorePageBuilder.SvgGroupStates;
import nl.ru.languageininteraction.language.client.ScorePageBuilder.SvgTextElements;
import nl.ru.languageininteraction.language.client.ScorePageBuilder;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;

/**
 * @since Nov 26, 2014 4:11:13 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ScorePageView extends AbstractSvgView {

    protected final ScorePageBuilder scorePageBuilder = new ScorePageBuilder();
    private static final ScorePage SCORE_PAGE = GWT.create(ScorePage.class);

    public ScorePageView(AudioPlayer audioPlayer) throws AudioException {
        super(audioPlayer);
    }

    @Override
    protected void getSvg(SafeHtmlBuilder builder) {
        scorePageBuilder.getSvg(builder);
    }

    @Override
    public void showAudioEnded() {
    }

    @Override
    protected void performClick(final Element targetElement) {
        Element parentElement = targetElement.getParentElement();
        while (parentElement.getParentElement() != null && parentElement.getId().isEmpty()) {
            parentElement = parentElement.getParentElement();
        }
        final String elementId = parentElement.getId();
        label.setText(elementId);
        setUserName(elementId);
        if (!elementId.isEmpty()) {
//            for (SvgTextElements svgTextElement : SvgTextElements.values()) {
//                scorePageBuilder.setLabel(svgTextElement, svgTextElement.name());
//            }
            SvgGroupStates svgGroup = SvgGroupStates.valueOf(elementId);
            switch (svgGroup) {
                case ContinueWithoutSharing:
                case ShareAndContinueButton:
                    nextEventListner.eventFired(null);
                    break;
                case EditProfileButton:
                    backEventListner.eventFired(null);
                    break;
            }
        } else {
//            label.setText(targetElement.getId());
        }
    }

    public void setUserName(String userName) {
        int maxNameLength = SCORE_PAGE.texttspan3285().length();
        // trim the user name so that it fits in the text box
        String shortName = (userName.length() <= maxNameLength) ? userName : userName.substring(0, maxNameLength) + "...";
        scorePageBuilder.setLabel(SvgTextElements.tspan3285, shortName);
    }

    public void setUserScore(int userScore) {
        NumberFormat decimalFormat = NumberFormat.getDecimalFormat();
        String formattedScore = decimalFormat.format(userScore);
        scorePageBuilder.setLabel(SvgTextElements.tspan3303, formattedScore);
    }

    public void setUserLevel(int choicePerRound) {
//        NumberFormat decimalFormat = NumberFormat.getDecimalFormat();
//        String formattedChoiceCount = decimalFormat.format(choicePerRound);
        // todo: the choice / level appears not to be in the new SVG files, check and discard or re add if required.
//        scorePageBuilder.setLabel(SvgTextElements.tspan4237, formattedChoiceCount);
    }

    public void setEndangeredCount(int endangeredCount) {
        NumberFormat decimalFormat = NumberFormat.getDecimalFormat();
        String formattedChoiceCount = decimalFormat.format(endangeredCount);
        scorePageBuilder.setLabel(SvgTextElements.tspan4192, formattedChoiceCount);
    }

    public void setRoundsData(int roundsCorrect, int roundsPlayed) {
        scorePageBuilder.setLabel(SvgTextElements.tspan3289, roundsCorrect + "/" + roundsPlayed);
    }
}

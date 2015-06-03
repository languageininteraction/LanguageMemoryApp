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
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import nl.ru.languageininteraction.language.client.ScorePage;
import nl.ru.languageininteraction.language.client.ScorePageBuilder.SvgGroupStates;
import nl.ru.languageininteraction.language.client.ScorePageBuilder.SvgTextElements;
import nl.ru.languageininteraction.language.client.ScorePageBuilder;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;

/**
 * @since Nov 26, 2014 4:11:13 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ScorePageView extends AbstractSvgView {

    protected final ScorePageBuilder scorePageBuilder = new ScorePageBuilder();
    private static final ScorePage SCORE_PAGE = GWT.create(ScorePage.class);

    protected PresenterEventListner shareContinueListner = null;
    protected PresenterEventListner justContinueListner = null;
    protected PresenterEventListner editUserListner = null;
    protected PresenterEventListner socialMediaListner = null;

    public ScorePageView(AudioPlayer audioPlayer) throws AudioException {
        super(audioPlayer);
    }

    public void setShareContinueListner(PresenterEventListner shareContinueListner) {
        this.shareContinueListner = shareContinueListner;
    }

    public void setJustContinueListner(PresenterEventListner justContinueListner) {
        this.justContinueListner = justContinueListner;
    }

    public void setEditUserListner(PresenterEventListner editUserListner) {
        this.editUserListner = editUserListner;
    }

    public void setSocialMediaListner(PresenterEventListner socialMediaListner) {
        this.socialMediaListner = socialMediaListner;
    }

    @Override
    protected void getSvg(SafeHtmlBuilder builder) {
        scorePageBuilder.getSvg(builder);
    }

    @Override
    public void showAudioEnded() {
    }

    @Override
    protected boolean performClick(final String svgGroupStateString) {
        boolean consumed = false;
        showDebugLabel(svgGroupStateString);
        if (!svgGroupStateString.isEmpty()) {
//            for (SvgTextElements svgTextElement : SvgTextElements.values()) {
//                scorePageBuilder.setLabel(svgTextElement, svgTextElement.name());
//            }
            SvgGroupStates svgGroup = SvgGroupStates.valueOf(svgGroupStateString);
            switch (svgGroup) {
                case ContinueWithoutSharing:
                    justContinueListner.eventFired(null);
                    consumed = true;
                    break;
                case ShareAndContinueButton:
                    shareContinueListner.eventFired(null);
                    consumed = true;
                    break;
                case EditProfileButton:
                    editUserListner.eventFired(null);
                    consumed = true;
                    break;
                case ShareOnSocialMedia:
                    socialMediaListner.eventFired(null);
                    consumed = true;
                    break;
            }
        } else {
//            label.setText(targetElement.getId());
        }
        return consumed;
    }

    public void setUserName(String userName) {
        int maxNameLength = SCORE_PAGE.texttspan3285().length();
        // trim the user name so that it fits in the text box
        String shortName = (userName.length() <= maxNameLength) ? userName : userName.substring(0, maxNameLength) + "...";
        scorePageBuilder.setLabel(SvgTextElements.tspan3285, shortName);
        scorePageBuilder.setLabel(ScorePageBuilder.SvgTextElements.tspan5588, messages.text5586());
        scorePageBuilder.setLabel(ScorePageBuilder.SvgTextElements.tspan5638, messages.highScoresText());
    }

    public void setUserScore(int userScore) {
        NumberFormat decimalFormat = NumberFormat.getDecimalFormat();
        String formattedScore = decimalFormat.format(userScore);
        scorePageBuilder.setLabel(SvgTextElements.tspan3303, formattedScore);
    }

    public void setHighScore(int scoreIndex, String playerName, int highScore) {
        int maxLength = SCORE_PAGE.texttspan5634().length();
        NumberFormat decimalFormat = NumberFormat.getDecimalFormat();
        String formattedScore = decimalFormat.format(highScore);
        String labelText = playerName.substring(0, maxLength - 1 - formattedScore.length()).concat(" ").concat(formattedScore);
//        String.format("%1$" + n + "s", playerName);
        switch (scoreIndex) {
            case 0:
                scorePageBuilder.setLabel(SvgTextElements.tspan4348, labelText);
                break;
            case 1:
                scorePageBuilder.setLabel(SvgTextElements.tspan5630, labelText);
                break;
            case 2:
                scorePageBuilder.setLabel(SvgTextElements.tspan4379, labelText);
                break;
            case 3:
                scorePageBuilder.setLabel(SvgTextElements.tspan5632, labelText);
                break;
            case 4:
                scorePageBuilder.setLabel(SvgTextElements.tspan5634, labelText);
                break;
        }
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

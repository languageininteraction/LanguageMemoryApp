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
import com.google.gwt.user.client.DOM;
import nl.ru.languageininteraction.language.client.GuessRound;
import nl.ru.languageininteraction.language.client.GuessRoundBuilder.SvgGroupStates;
import nl.ru.languageininteraction.language.client.GuessRoundBuilder;
import nl.ru.languageininteraction.language.client.GuessRoundBuilder.SvgTextElements;
import nl.ru.languageininteraction.language.client.Messages;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.LanguageSampleListener;
import nl.ru.languageininteraction.language.client.model.RoundData;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.util.SvgTemplate;

/**
 * @since Nov 26, 2014 4:11:13 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class GuessRoundView extends AbstractSvgView {

    protected final Messages messages = GWT.create(Messages.class);
    private LanguageSampleListener targetSampleListener;
    private RoundData roundData;
    private boolean answerShowing = false;
    private static final GuessRound SVG_DATA = GWT.create(GuessRound.class);

    public GuessRoundView(AudioPlayer audioPlayer) throws AudioException {
        super(audioPlayer);
    }

    protected final GuessRoundBuilder matchLanguageBuilder = new GuessRoundBuilder();

    @Override
    protected void getSvg(SafeHtmlBuilder builder) {
//        matchLanguageBuilder.getSvg(builder);
        matchLanguageBuilder.getDefsTag(builder);
        matchLanguageBuilder.getSvgChoiceArrows(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow1(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow2(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow3(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow4(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow5(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgTargetButton(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgNextRoundButton(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgScoreBox(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgLanguageInfoBox(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgTargetButtonPlay(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton1(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton2(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton3(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton4(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton5(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgCorrectButton(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgIncorrectButton(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButtonPlay1(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButtonPlay2(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButtonPlay3(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButtonPlay4(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButtonPlay5(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButtonDisabled(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrows(builder, SvgTemplate.Visibility.visible);
        matchLanguageBuilder.getSvgChoiceArrow1(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgChoiceArrow2(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgChoiceArrow3(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgChoiceArrow4(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgChoiceArrow5(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgLanguageInfoBox(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgDobesSymbol(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgMap(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgPins(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgTargetButton(builder, SvgTemplate.Visibility.visible);
        matchLanguageBuilder.getSvgTargetButtonPlay(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgNextRoundButton(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgScoreBox(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgTargetButtonPlay(builder, SvgTemplate.Visibility.visible);
        matchLanguageBuilder.getSvgSampleButton1(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButton2(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButton3(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButton4(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButton5(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButtonPlay1(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButtonPlay2(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButtonPlay3(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButtonPlay4(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButtonPlay5(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgIncorrectButton1(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgIncorrectButton2(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgIncorrectButton3(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgIncorrectButton4(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgIncorrectButton5(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgCorrectButton1(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgCorrectButton2(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgCorrectButton3(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgCorrectButton4(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgCorrectButton5(builder, SvgTemplate.Visibility.hidden);
//        matchLanguageBuilder.getSvgCorrectButton(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgIncorrectButton(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButtonPlay(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButtonDisabled(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgNextRoundButton(builder, SvgTemplate.Visibility.hidden);

//        matchLanguageBuilder.getSvgOceania(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgg4704(builder, SvgTemplate.Visibility.hidden);        
    }

    public void setSampleListeners(LanguageSampleListener targetSampleListener, RoundData roundData) {
        this.targetSampleListener = targetSampleListener;
        this.roundData = roundData;
        clearChoices();
    }

    @Override
    public void showAudioEnded() {
        matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
    }

    @Override
    protected boolean performClick(final String svgGroupStateString) {
        boolean consumed = false;
        if (!svgGroupStateString.isEmpty()) {
//            matchLanguageBuilder.setLabel(SvgTextElements.tspan4319, SvgTextElements.tspan4319.name());
//            matchLanguageBuilder.setLabel(SvgTextElements.tspan4326, SvgTextElements.tspan4319.name());
//            matchLanguageBuilder.setLabel(SvgTextElements.tspan4413, SvgTextElements.tspan4319.name());
            showDebugLabel(svgGroupStateString);
            SvgGroupStates svgGroup = SvgGroupStates.valueOf(svgGroupStateString);
            switch (svgGroup) {
                case NextRoundButton:
                    nextEventListner.eventFired(null);
                    consumed = true;
                    break;
                case TargetButtonPlay:
                case SampleButtonPlay1:
                case SampleButtonPlay2:
                case SampleButtonPlay3:
                case SampleButtonPlay4:
                case SampleButtonPlay5:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    audioPlayer.stopAll();
                    consumed = true;
                    break;
                case TargetButton:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    matchLanguageBuilder.showGroup(SvgGroupStates.TargetButtonPlay);
                    if (!answerShowing) {
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                        matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton1);
                        if (roundData.getRoundChoices().size() > 1) {
                            matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton2);
                        }
                        if (roundData.getRoundChoices().size() > 2) {
                            matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton3);
                        }
                        if (roundData.getRoundChoices().size() > 3) {
                            matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton4);
                        }
                        if (roundData.getRoundChoices().size() > 4) {
                            matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton5);
                        }
                    }
                    audioPlayer.playSampleAudio(roundData.getCorrectSample());
                    consumed = true;
                    break;
                case SampleButton1:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    if (!answerShowing) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow1);
//                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButton1);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    }
                    audioPlayer.playSampleAudio(roundData.getRoundChoices().get(0));
                    consumed = true;
                    break;
                case SampleButton2:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    if (!answerShowing) {
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                        matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow2);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    }
                    audioPlayer.playSampleAudio(roundData.getRoundChoices().get(1));
                    consumed = true;
                    break;
                case SampleButton3:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    if (!answerShowing) {
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                        matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow3);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    }
                    audioPlayer.playSampleAudio(roundData.getRoundChoices().get(2));
                    consumed = true;
                    break;
                case SampleButton4:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    if (!answerShowing) {
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                        matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow4);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    }
                    audioPlayer.playSampleAudio(roundData.getRoundChoices().get(3));
                    consumed = true;
                    break;
                case SampleButton5:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay5);
                    if (!answerShowing) {
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                        matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow5);
                    }
                    audioPlayer.playSampleAudio(roundData.getRoundChoices().get(4));
                    consumed = true;
                    break;
                case ChoiceArrow1:
                    targetSampleListener.eventFired(roundData.getRoundChoices().get(0));
                    showCorrectButton();
                    if (!roundData.getRoundChoices().get(0).isCorrect()) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.IncorrectButton1);
                    }
                    showResult();
                    consumed = true;
                    break;
                case ChoiceArrow2:
                    targetSampleListener.eventFired(roundData.getRoundChoices().get(1));
                    showCorrectButton();
                    if (!roundData.getRoundChoices().get(1).isCorrect()) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.IncorrectButton2);
                    }
                    showResult();
                    consumed = true;
                    break;
                case ChoiceArrow3:
                    targetSampleListener.eventFired(roundData.getRoundChoices().get(2));
                    showCorrectButton();
                    if (!roundData.getRoundChoices().get(2).isCorrect()) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.IncorrectButton3);
                    }
                    showResult();
                    consumed = true;
                    break;
                case ChoiceArrow4:
                    targetSampleListener.eventFired(roundData.getRoundChoices().get(3));
                    showCorrectButton();
                    if (!roundData.getRoundChoices().get(3).isCorrect()) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.IncorrectButton4);
                    }
                    showResult();
                    consumed = true;
                    break;
                case ChoiceArrow5:
                    targetSampleListener.eventFired(roundData.getRoundChoices().get(4));
                    showCorrectButton();
                    if (!roundData.getRoundChoices().get(4).isCorrect()) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.IncorrectButton5);
                    }
                    showResult();
                    consumed = true;
                    break;
            }
        } else {
//            label.setText(targetElement.getId());
        }
        return consumed;
    }

    private void showCorrectButton() {
        final int choicecount = roundData.getRoundChoices().size();
        if (choicecount > 0 && roundData.getRoundChoices().get(0).isCorrect()) {
            matchLanguageBuilder.showGroup(SvgGroupStates.CorrectButton1);
        }
        if (choicecount > 1 && roundData.getRoundChoices().get(1).isCorrect()) {
            matchLanguageBuilder.showGroup(SvgGroupStates.CorrectButton2);
        }
        if (choicecount > 2 && roundData.getRoundChoices().get(2).isCorrect()) {
            matchLanguageBuilder.showGroup(SvgGroupStates.CorrectButton3);
        }
        if (choicecount > 3 && roundData.getRoundChoices().get(3).isCorrect()) {
            matchLanguageBuilder.showGroup(SvgGroupStates.CorrectButton4);
        }
        if (choicecount > 4 && roundData.getRoundChoices().get(4).isCorrect()) {
            matchLanguageBuilder.showGroup(SvgGroupStates.CorrectButton5);
        }
    }

    private void clearChoices() {
        answerShowing = false;
        matchLanguageBuilder.hideGroup(SvgGroupStates.LanguageInfoBox);
        matchLanguageBuilder.hideGroup(SvgGroupStates.DobesSymbol);
        matchLanguageBuilder.hideGroup(SvgGroupStates.Map);
        hideChildrenOfGroup(SvgGroupStates.Pins);
        matchLanguageBuilder.hideGroup(SvgGroupStates.IncorrectButton1);
        matchLanguageBuilder.hideGroup(SvgGroupStates.CorrectButton1);
        matchLanguageBuilder.hideGroup(SvgGroupStates.IncorrectButton2);
        matchLanguageBuilder.hideGroup(SvgGroupStates.CorrectButton2);
        matchLanguageBuilder.hideGroup(SvgGroupStates.IncorrectButton3);
        matchLanguageBuilder.hideGroup(SvgGroupStates.CorrectButton3);
        matchLanguageBuilder.hideGroup(SvgGroupStates.IncorrectButton4);
        matchLanguageBuilder.hideGroup(SvgGroupStates.CorrectButton4);
        matchLanguageBuilder.hideGroup(SvgGroupStates.IncorrectButton5);
        matchLanguageBuilder.hideGroup(SvgGroupStates.CorrectButton5);
        matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
        matchLanguageBuilder.hideGroup(SvgGroupStates.NextRoundButton);
//        matchLanguageBuilder.hideGroup(SvgGroupStates.g4704);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButton1);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButton2);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButton3);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButton4);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButton5);
    }

    public void showSingleChildOfGroup(SvgGroupStates group, String chidId) {
        final Element elementById = DOM.getElementById(group.name());
        elementById.setAttribute("style", "visibility:" + SvgTemplate.Visibility.visible);
        Element childElement = elementById.getFirstChildElement();
        while (childElement != null) {
            if (chidId.equals(childElement.getId())) {
                // todo: it would be better to get this from the SVG / properties file
                childElement.setAttribute("style", SVG_DATA.styleaii() + ";visibility:" + SvgTemplate.Visibility.visible);
            } else {
                childElement.setAttribute("style", "visibility:" + SvgTemplate.Visibility.hidden);
            }
            childElement = childElement.getNextSiblingElement();
        }
    }

    public void hideChildrenOfGroup(SvgGroupStates group) {
        final Element elementById = DOM.getElementById(group.name());
        elementById.setAttribute("style", "visibility:" + SvgTemplate.Visibility.hidden);
        Element childElement = elementById.getFirstChildElement();
        while (childElement != null) {
            childElement.setAttribute("style", "visibility:" + SvgTemplate.Visibility.hidden);
            childElement = childElement.getNextSiblingElement();
        }
    }

    private void showResult() {
        answerShowing = true;
        matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
        matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);

        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
        matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
//                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
        matchLanguageBuilder.showGroup(SvgGroupStates.LanguageInfoBox);
        if (roundData.getCorrectSample().getLanguageSample().isDobes()) {
            matchLanguageBuilder.showGroup(SvgGroupStates.DobesSymbol);
        } else {
            matchLanguageBuilder.hideGroup(SvgGroupStates.DobesSymbol);
        }
        matchLanguageBuilder.showGroup(SvgGroupStates.Map);
        showSingleChildOfGroup(SvgGroupStates.Pins, roundData.getCorrectSample().getLanguageSample().getIsoCode());
//        matchLanguageBuilder.showGroup(SvgGroupStates.g4704);
//                    matchLanguageBuilder.showGroup(SvgGroupStates.IncorrectButton);
        matchLanguageBuilder.showGroup(SvgGroupStates.NextRoundButton);
        audioPlayer.stopAll();
        matchLanguageBuilder.setLabel(SvgTextElements.tspan4319, roundData.getCorrectSample().getLanguageSample().getLanguageName());
        NumberFormat decimalFormat = NumberFormat.getDecimalFormat();
        String formattedPopulation = decimalFormat.format(roundData.getCorrectSample().getLanguageSample().getPopulation());
        matchLanguageBuilder.setLabel(SvgTextElements.tspan4326, messages.languagePopulation(formattedPopulation));
    }

    public void updateRoundsLabel(int roundsCorrect, int roundsPlayed, int totalRounds) {
        matchLanguageBuilder.setLabel(SvgTextElements.tspan4413, roundsCorrect + " (" + roundsPlayed + "/" + totalRounds + ")");
    }
}

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
    protected void performClick(final Element targetElement) {

        final Element parentElement = targetElement.getParentElement();
        final String elementId = parentElement.getId();
        if (!elementId.isEmpty()) {
//            matchLanguageBuilder.setLabel(SvgTextElements.tspan4319, SvgTextElements.tspan4319.name());
//            matchLanguageBuilder.setLabel(SvgTextElements.tspan4326, SvgTextElements.tspan4319.name());
//            matchLanguageBuilder.setLabel(SvgTextElements.tspan4413, SvgTextElements.tspan4319.name());
            label.setText(elementId);
            SvgGroupStates svgGroup = SvgGroupStates.valueOf(elementId);
            switch (svgGroup) {
                case NextRoundButton:
                    nextEventListner.eventFired(null);
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
                    break;
                case TargetButton:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    matchLanguageBuilder.showGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton1);
                    if (roundData.getRoundChoices().length > 1) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton2);
                    }
                    if (roundData.getRoundChoices().length > 2) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton3);
                    }
                    if (roundData.getRoundChoices().length > 3) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton4);
                    }
                    if (roundData.getRoundChoices().length > 4) {
                        matchLanguageBuilder.showGroup(SvgGroupStates.SampleButton5);
                    }
                    audioPlayer.playSampleAudio(roundData.getCorrectSample());
                    break;
                case SampleButton1:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow1);
//                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButton1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    audioPlayer.playSampleAudio(roundData.getRoundChoices()[0]);
                    break;
                case SampleButton2:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                    matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    audioPlayer.playSampleAudio(roundData.getRoundChoices()[1]);
                    break;
                case SampleButton3:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                    matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    audioPlayer.playSampleAudio(roundData.getRoundChoices()[2]);
                    break;
                case SampleButton4:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay5);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                    matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow4);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow5);
                    audioPlayer.playSampleAudio(roundData.getRoundChoices()[3]);
                    break;
                case SampleButton5:
                    matchLanguageBuilder.hideGroup(SvgGroupStates.TargetButtonPlay);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.SampleButtonPlay4);
                    matchLanguageBuilder.showGroup(SvgGroupStates.SampleButtonPlay5);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow1);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow2);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow3);
                    matchLanguageBuilder.hideGroup(SvgGroupStates.ChoiceArrow4);
                    matchLanguageBuilder.showGroup(SvgGroupStates.ChoiceArrow5);
                    audioPlayer.playSampleAudio(roundData.getRoundChoices()[4]);
                    break;
                case ChoiceArrow1:
                    targetSampleListener.eventFired(roundData.getRoundChoices()[0]);
                    showResult();
                    break;
                case ChoiceArrow2:
                    targetSampleListener.eventFired(roundData.getRoundChoices()[1]);
                    showResult();
                    break;
                case ChoiceArrow3:
                    targetSampleListener.eventFired(roundData.getRoundChoices()[2]);
                    showResult();
                    break;
                case ChoiceArrow4:
                    targetSampleListener.eventFired(roundData.getRoundChoices()[3]);
                    showResult();
                    break;
                case ChoiceArrow5:
                    targetSampleListener.eventFired(roundData.getRoundChoices()[4]);
                    showResult();
                    break;
            }
        } else {
//            label.setText(targetElement.getId());
        }
    }

    private void clearChoices() {
        matchLanguageBuilder.hideGroup(SvgGroupStates.LanguageInfoBox);
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

    private void showResult() {
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
//        matchLanguageBuilder.showGroup(SvgGroupStates.g4704);
//                    matchLanguageBuilder.showGroup(SvgGroupStates.IncorrectButton);
        matchLanguageBuilder.showGroup(SvgGroupStates.NextRoundButton);
        audioPlayer.stopAll();
        matchLanguageBuilder.setLabel(SvgTextElements.tspan4319, roundData.getCorrectSample().getLanguageSample().getLanguageName());
        NumberFormat decimalFormat = NumberFormat.getDecimalFormat();
        String formattedPopulation = decimalFormat.format(roundData.getCorrectSample().getLanguageSample().getPopulation());
        matchLanguageBuilder.setLabel(SvgTextElements.tspan4326, messages.languagePopulation(formattedPopulation));
    }

    public void updateRoundsLabel(int roundsCorrect, int roundsPlayed) {
        matchLanguageBuilder.setLabel(SvgTextElements.tspan4413, roundsCorrect + "/" + roundsPlayed);
    }
}

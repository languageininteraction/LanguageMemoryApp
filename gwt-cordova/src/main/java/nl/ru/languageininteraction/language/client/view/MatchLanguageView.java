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

import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import nl.ru.languageininteraction.language.client.MatchLanguageBuilder;
import nl.ru.languageininteraction.language.client.MatchLanguageBuilder.SvgGroupStates;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.util.SvgTemplate;

/**
 * @since Nov 26, 2014 4:11:13 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MatchLanguageView extends AbstractSvgView {

    public MatchLanguageView(AudioPlayer audioPlayer) throws AudioException {
        super(audioPlayer);
    }
    protected final MatchLanguageBuilder matchLanguageBuilder = new MatchLanguageBuilder();

    @Override
    protected void getSvg(SafeHtmlBuilder builder) {
        //        matchLanguageBuilder.getSvg(builder);
//        matchLanguageBuilder.getSvgChoiceArrows(builder, SvgTemplate.Visibility.visible);
        matchLanguageBuilder.getSvgChoiceArrow1(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgChoiceArrow2(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgChoiceArrow3(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgChoiceArrow4(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgChoiceArrow5(builder, SvgTemplate.Visibility.hidden);
//        matchLanguageBuilder.getSvgLanguageInfoBox(builder, SvgTemplate.Visibility.visible);
        matchLanguageBuilder.getSvgTargetButton(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgNextRoundButton(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgScoreBox(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgTargetButtonPlay(builder, SvgTemplate.Visibility.visible);
        matchLanguageBuilder.getSvgSampleButton1(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButton2(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButton3(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButton4(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgSampleButton5(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgCorrectButton(builder, SvgTemplate.Visibility.hidden);
        matchLanguageBuilder.getSvgIncorrectButton(builder, SvgTemplate.Visibility.hidden);
//        matchLanguageBuilder.getSvgSampleButtonPlay(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButtonDisabled(builder, SvgTemplate.Visibility.visible);
    }

    @Override
    protected void performClick(final Element targetElement) {

        final Element parentElement = targetElement.getParentElement();
        final String elementId = parentElement.getId();
        if (!elementId.isEmpty()) {
//            label.setText(elementId);
            SvgGroupStates svgGroup = SvgGroupStates.valueOf(elementId);
            switch (svgGroup) {
                case TargetButton:
                    showGroup(SvgGroupStates.ChoiceArrow1);
                    showGroup(SvgGroupStates.ChoiceArrow2);
                    showGroup(SvgGroupStates.ChoiceArrow3);
                    showGroup(SvgGroupStates.ChoiceArrow4);
                    showGroup(SvgGroupStates.ChoiceArrow5);
                    showGroup(SvgGroupStates.SampleButton1);
                    showGroup(SvgGroupStates.SampleButton2);
                    showGroup(SvgGroupStates.SampleButton3);
                    showGroup(SvgGroupStates.SampleButton4);
                    showGroup(SvgGroupStates.SampleButton5);
                    audioPlayer.playSampleAudio1();
                    break;
                case SampleButton1:
                    showGroup(SvgGroupStates.ChoiceArrow1);
                    hideGroup(SvgGroupStates.ChoiceArrow2);
                    hideGroup(SvgGroupStates.ChoiceArrow3);
                    hideGroup(SvgGroupStates.ChoiceArrow4);
                    hideGroup(SvgGroupStates.ChoiceArrow5);
                    showGroup(SvgGroupStates.IncorrectButton);
                    showGroup(SvgGroupStates.CorrectButton);
                    audioPlayer.playSampleAudio2();
                    break;
                case SampleButton2:
                    hideGroup(SvgGroupStates.ChoiceArrow1);
                    showGroup(SvgGroupStates.ChoiceArrow2);
                    hideGroup(SvgGroupStates.ChoiceArrow3);
                    hideGroup(SvgGroupStates.ChoiceArrow4);
                    hideGroup(SvgGroupStates.ChoiceArrow5);
                    showGroup(SvgGroupStates.IncorrectButton);
                    showGroup(SvgGroupStates.CorrectButton);
                    audioPlayer.playSampleAudio3();
                    break;
                case SampleButton3:
                    hideGroup(SvgGroupStates.ChoiceArrow1);
                    hideGroup(SvgGroupStates.ChoiceArrow2);
                    showGroup(SvgGroupStates.ChoiceArrow3);
                    hideGroup(SvgGroupStates.ChoiceArrow4);
                    hideGroup(SvgGroupStates.ChoiceArrow5);
                    showGroup(SvgGroupStates.IncorrectButton);
                    showGroup(SvgGroupStates.CorrectButton);
                    audioPlayer.playSampleAudio1();
                    break;
                case SampleButton4:
                    hideGroup(SvgGroupStates.ChoiceArrow1);
                    hideGroup(SvgGroupStates.ChoiceArrow2);
                    hideGroup(SvgGroupStates.ChoiceArrow3);
                    showGroup(SvgGroupStates.ChoiceArrow4);
                    hideGroup(SvgGroupStates.ChoiceArrow5);
                    showGroup(SvgGroupStates.IncorrectButton);
                    showGroup(SvgGroupStates.CorrectButton);
                    audioPlayer.playSampleAudio2();
                    break;
                case SampleButton5:
                    hideGroup(SvgGroupStates.ChoiceArrow1);
                    hideGroup(SvgGroupStates.ChoiceArrow2);
                    hideGroup(SvgGroupStates.ChoiceArrow3);
                    hideGroup(SvgGroupStates.ChoiceArrow4);
                    showGroup(SvgGroupStates.ChoiceArrow5);
                    showGroup(SvgGroupStates.IncorrectButton);
                    showGroup(SvgGroupStates.CorrectButton);
                    audioPlayer.playSampleAudio3();
                    break;
            }
        } else {
//            label.setText(targetElement.getId());
        }
    }
}

/*
 * Copyright (C) 2015 Language In Interaction
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

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import nl.ru.languageininteraction.language.client.GuessRound_TutorialBuilder;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.util.SvgTemplate;

/**
 * @since Feb 4, 2015 1:22:32 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class TutorialView extends AbstractSvgView {

    protected final GuessRound_TutorialBuilder svgBuilder = new GuessRound_TutorialBuilder();
    protected final PresenterEventListner infoButtonListner;
    protected final PresenterEventListner goButtonListner;
    GuessRound_TutorialBuilder.SvgGroupStates currentState = GuessRound_TutorialBuilder.SvgGroupStates.Tutorial1;

    public TutorialView(PresenterEventListner infoButtonListner, PresenterEventListner goButtonListner, AudioPlayer audioPlayer) throws AudioException {
        super(audioPlayer);
        this.infoButtonListner = infoButtonListner;
        this.goButtonListner = goButtonListner;
    }

    @Override
    protected void getSvg(SafeHtmlBuilder builder) {
        svgBuilder.getDefsTag(builder);
        svgBuilder.getSvgBackground(builder, SvgTemplate.Visibility.visible);
        svgBuilder.getSvgMapBackground(builder, SvgTemplate.Visibility.visible);
        svgBuilder.getSvgTutorialBackGround(builder, SvgTemplate.Visibility.visible);
        svgBuilder.getSvgTutorial1(builder, SvgTemplate.Visibility.visible);
        svgBuilder.getSvgTutorial2(builder, SvgTemplate.Visibility.hidden);
        svgBuilder.getSvgTutorial3(builder, SvgTemplate.Visibility.hidden);
        svgBuilder.getSvgTutorial4(builder, SvgTemplate.Visibility.hidden);
    }

    @Override
    protected boolean performClick(final String svgGroupStateString) {
        boolean consumed = false;
        label.setText(svgGroupStateString);
        switch (currentState) {
            case Tutorial1:
                consumed = true;
                svgBuilder.showGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial2);
                svgBuilder.hideGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial1);
                svgBuilder.hideGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial3);
                svgBuilder.hideGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial4);
                currentState = GuessRound_TutorialBuilder.SvgGroupStates.Tutorial2;
                break;
            case Tutorial2:
                consumed = true;
                svgBuilder.showGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial3);
                svgBuilder.hideGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial1);
                svgBuilder.hideGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial2);
                svgBuilder.hideGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial4);
                currentState = GuessRound_TutorialBuilder.SvgGroupStates.Tutorial3;
                break;
            case Tutorial3:
                consumed = true;
                svgBuilder.showGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial4);
                svgBuilder.hideGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial1);
                svgBuilder.hideGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial2);
                svgBuilder.hideGroup(GuessRound_TutorialBuilder.SvgGroupStates.Tutorial3);
                currentState = GuessRound_TutorialBuilder.SvgGroupStates.Tutorial4;
                break;
            case Tutorial4:
                consumed = true;
                goButtonListner.eventFired(null);
                break;
        }
        return consumed;
    }

    @Override
    public void showAudioEnded() {
    }
}

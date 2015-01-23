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

        final Element parentElement = targetElement.getParentElement();
        final String elementId = parentElement.getId();
        label.setText(elementId);
        if (!elementId.isEmpty()) {
            for (SvgTextElements svgTextElement : SvgTextElements.values()) {
                scorePageBuilder.setLabel(svgTextElement, svgTextElement.name());
            }
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
}

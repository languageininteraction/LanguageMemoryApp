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
import nl.ru.languageininteraction.language.client.StartScreenBuilder;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;

/**
 * @since Feb 4, 2015 1:22:32 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class StartScreenView extends AbstractSvgView {

    protected final StartScreenBuilder svgBuilder = new StartScreenBuilder();
    protected final PresenterEventListner infoButtonListner;
    protected final PresenterEventListner goButtonListner;
    protected final PresenterEventListner settingsButtonListner;

    public StartScreenView(PresenterEventListner infoButtonListner, PresenterEventListner goButtonListner, PresenterEventListner settingsButtonListner, AudioPlayer audioPlayer) throws AudioException {
        super(audioPlayer);
        this.infoButtonListner = infoButtonListner;
        this.goButtonListner = goButtonListner;
        this.settingsButtonListner = settingsButtonListner;
    }

    @Override
    protected void getSvg(SafeHtmlBuilder builder) {
        svgBuilder.getSvg(builder);
    }

    @Override
    protected boolean performClick(final String svgGroupStateString) {
        boolean consumed = false;
        label.setText(svgGroupStateString);
        StartScreenBuilder.SvgGroupStates svgGroup = StartScreenBuilder.SvgGroupStates.valueOf(svgGroupStateString);
        switch (svgGroup) {
            case InfoButton:
                consumed = true;
                infoButtonListner.eventFired(null);
                break;
            case SettingsButton:
                consumed = true;
                settingsButtonListner.eventFired(null);
                break;
            case LQLogo:
            case GoButton:
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

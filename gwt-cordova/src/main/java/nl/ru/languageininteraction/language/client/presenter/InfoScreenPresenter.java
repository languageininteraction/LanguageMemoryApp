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
package nl.ru.languageininteraction.language.client.presenter;

import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.view.InfoScreenView;

/**
 * @since Feb 4, 2015 11:26:18 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class InfoScreenPresenter extends AbstractSvgPresenter implements Presenter {

    public InfoScreenPresenter(RootLayoutPanel widgetTag, UserResults userResults, AudioPlayer audioPlayer) throws AudioException {
        super(widgetTag, userResults, audioPlayer, new InfoScreenView(audioPlayer));
    }

    @Override
    void configureSvg() {
    }

    @Override
    boolean nextEventFired() {
        return true;
    }
}

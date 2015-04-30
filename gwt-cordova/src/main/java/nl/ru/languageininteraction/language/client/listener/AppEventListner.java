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
package nl.ru.languageininteraction.language.client.listener;

import com.google.gwt.core.client.GWT;
import nl.ru.languageininteraction.language.client.Messages;

/**
 * @since Oct 8, 2014 11:01:07 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public interface AppEventListner {

    final Messages messages = GWT.create(Messages.class);

    enum ApplicationState {

        start(messages.startButton()),
        locale(messages.localeMenuLabel()),
        version(messages.versionMenuLabel()),
        @Deprecated
        intro(messages.introMenuLabel()),
        @Deprecated
        instructions(messages.instructionsMenuLabel()),
        menu(messages.menuButton()),
        @Deprecated
        setuser(messages.setUserMenuLabel()),
        //        adddummyresults("Add Dummy Results"), // todo: remove this option when testing complete
        //        stimulus(messages.stimulusMenuLabel()),
        tutorial("Tutorial"),
        tutorialorguessround("First Run Tutorial or Game"),
        explaindatasharing("Explain Data Sharing Screen"),
        guessround("Guess"),
        scores("Scores"),
        matchlanguage("Match"),
        chooseplayer("Choose Player"),
        playerdetails("Player Details"),
        createplayer("New Player"),
        startscreen("Start"),
        infoscreen("Information"),
        @Deprecated
        map(messages.mapMenuLabel()),
        @Deprecated
        autotyp_regions("AutotypRegions"),
        @Deprecated
        alien("Alien"),
        @Deprecated
        report(messages.reportMenuLabel()),
        @Deprecated
        feedback(messages.feedbackMenuLabel()),
        @Deprecated
        metadata(messages.metadataMenuLabel()),
        registration(messages.registerMenuLabel()),
        highscoresubmitted(""),
        highscoresfailednon202(""),
        highscoresfailedbuildererror(""),
        highscoresfailedconnectionerror(""),
        @Deprecated
        moreinfo(messages.moreInfoMenuLabel()),
        end(messages.exitButton());

        final public String label;

        ApplicationState(String label) {
            this.label = label;
        }
    }

    public void requestApplicationState(ApplicationState applicationState);
}

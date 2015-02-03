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
package nl.ru.languageininteraction.synaesthesia.client.listener;

import com.google.gwt.core.client.GWT;
import nl.ru.languageininteraction.synaesthesia.client.Messages;

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
        intro(messages.introMenuLabel()),
        instructions(messages.instructionsMenuLabel()),
        menu(messages.menuButton()),
        setuser(messages.setUserMenuLabel()),
        adddummyresults("Add Dummy Results"), // todo: remove this option when testing complete
        stimulusshow(messages.stimulusShowMenuLabel()),
        stimulusselect(messages.stimulusSelectMenuLabel()),
        report(messages.reportMenuLabel()),
        feedback(messages.feedbackMenuLabel()),
        metadata(messages.metadataMenuLabel()),
        registration(messages.registerMenuLabel()),
        registrationcomplete(""),
        registrationfailednon202(""),
        registrationfailedbuildererror(""),
        registrationfailedconnectionerror(""),
        moreinfo(messages.moreInfoMenuLabel()),
        end(messages.exitButton());

        final public String label;

        ApplicationState(String label) {
            this.label = label;
        }
    }

    public void requestApplicationState(ApplicationState applicationState);
}

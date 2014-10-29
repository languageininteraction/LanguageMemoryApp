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
package nl.ru.languageininteraction.synaesthesia.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.synaesthesia.client.Version;

/**
 * @since Oct 29, 2014 4:42:44 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class VersionPresenter extends AbstractPresenter {

    private final Version version = GWT.create(Version.class);

    public VersionPresenter(RootPanel widgetTag) {
        super(widgetTag);
    }

    @Override
    void setTitle() {
        simpleView.addTitle(messages.applicationVersion());
    }

    @Override
    void setContent() {
        simpleView.setDisplayText("Version: " + version.majorVersion() + "."
                + version.minorVersion() + "."
                + version.buildVersion() + "-"
                + version.projectVersion() + "<BR>"
                + "Compile Date: " + version.compileDate() + "<BR>"
                + "Last Commit Date: " + version.lastCommitDate());
    }
}

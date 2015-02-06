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
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.synaesthesia.client.Version;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.view.ComplexView;

/**
 * @since Oct 29, 2014 4:42:44 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class VersionPresenter extends AbstractPresenter {

    private final Version version = GWT.create(Version.class);

    public VersionPresenter(RootLayoutPanel widgetTag) {
        super(widgetTag, new ComplexView());
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.versionScreenTitle(), titleBarListner);
    }

    @Override
    protected void setContent(final AppEventListner appEventListner) {
//        ((ComplexView) simpleView).addImage(UriUtils.fromString("images/icon.png"), 10);
        ((ComplexView) simpleView).addHighlightedText(messages.aboutScreenTextAtitle());
        ((ComplexView) simpleView).addText(messages.aboutScreenTextAbody());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addHighlightedText(messages.aboutScreenTextBtitle());
        ((ComplexView) simpleView).addText(messages.aboutScreenTextBbody());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addHighlightedText(messages.aboutScreenTextCtitle());
        ((ComplexView) simpleView).addText(messages.aboutScreenTextCBody());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addText(messages.aboutScreenTextC1point());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addText(messages.aboutScreenTextC2point());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addText(messages.aboutScreenTextC3point());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addText(messages.aboutScreenTextC4point());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addText(messages.aboutScreenTextC5point());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addLink(messages.mpiLinkText(), messages.mpiLink());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addHighlightedText(messages.aboutScreenTextJtitle());
        ((ComplexView) simpleView).addText("Version: " + version.majorVersion() + "."
                + version.minorVersion() + "."
                + version.buildVersion() + "-"
                + version.projectVersion() + "\n"
                + "Compile Date: " + version.compileDate() + "\n"
                + "Last Commit Date: " + version.lastCommitDate());
        ((ComplexView) simpleView).addFooterImage(UriUtils.fromString("images/LiI_logo_rgb.jpg"), 100);
    }
}

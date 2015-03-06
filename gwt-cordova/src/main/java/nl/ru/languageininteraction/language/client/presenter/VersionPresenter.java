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
package nl.ru.languageininteraction.language.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.Version;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.view.ComplexView;

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
//        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addImage(UriUtils.fromString("images/LIL-logo-diap.png"), messages.languageInteractionUrl(), 100);
//        ((ComplexView) simpleView).addImage(UriUtils.fromString("images/icon.png"), 10);
//        ((ComplexView) simpleView).addHighlightedText(messages.aboutScreenTextAheading());

        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextAbody());
//        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addOptionButton(new PresenterEventListner() {
//
//            @Override
//            public String getLabel() {
//                return messages.aboutScreenTextAlink();
//            }
//
//            @Override
//            public void eventFired(Button button) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextBheading());
        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextBbody());
//        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextCheading());
        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextCbody());
//        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextDheading());
        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextDbody());
        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextEheading());
        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextEbody());
//        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addHighlightedText(messages.aboutScreenTextCheading());
//        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextCBody());
//        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextC1point());
//        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextC2point());
//        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextC3point());
//        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextC4point());
//        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextC5point());
//        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addLink(messages.mpiLinkText(), messages.mpiLink());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addHtmlText(messages.aboutScreenTextJheading());
        ((ComplexView) simpleView).addText("Version: " + version.majorVersion() + "."
                + version.minorVersion() + "."
                + version.buildVersion() + "-"
                + version.projectVersion() + "\n"
                + "Compile Date: " + version.compileDate() + "\n"
                + "Last Commit Date: " + version.lastCommitDate());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addImage(UriUtils.fromString("images/LIL-logo-diap.png"), messages.languageInteractionUrl(), 100);
    }
}

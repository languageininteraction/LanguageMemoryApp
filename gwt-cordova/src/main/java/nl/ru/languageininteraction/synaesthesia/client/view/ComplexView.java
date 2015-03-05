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
package nl.ru.languageininteraction.synaesthesia.client.view;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.SingleShotEventListner;

/**
 * @since Jan 8, 2015 5:01:05 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ComplexView extends SimpleView {

    final VerticalPanel outerPanel;

    public ComplexView() {
        outerPanel = new VerticalPanel();
        setContent(outerPanel);
    }

    public void clearGui() {
        outerPanel.clear();
    }

    public void addText(String textString) {
        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
        outerPanel.add(html);
    }

    public void addHtmlText(String textString) {
        HTML html = new HTML(new SafeHtmlBuilder().appendHtmlConstant(textString).toSafeHtml());
        outerPanel.add(html);
    }

    public void addHighlightedText(String textString) {
        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
        html.addStyleName("highlightedText");
        outerPanel.add(html);
    }

    public void addPadding() {
        outerPanel.add(new HTML("&nbsp;"));
    }

    public void addImage(SafeUri imagePath, final String linkUrl, int percentWidth) {
        final Image image = new Image(imagePath);
        image.setWidth(percentWidth + "%");
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                Window.open(linkUrl, "_system", "");
            }
        };
        image.addClickHandler(singleShotEventListner);
        image.addTouchStartHandler(singleShotEventListner);
        image.addTouchMoveHandler(singleShotEventListner);
        image.addTouchEndHandler(singleShotEventListner);
        outerPanel.add(image);
    }

    public void addLink(String label, final String target) {
        final Anchor anchor = new Anchor(new SafeHtmlBuilder().appendEscapedLines(label).toSafeHtml());
        // this link relies on the org.apache.cordova.inappbrowser which offers secure viewing of external html pages and handles user navigation such as back navigation.
        // in this case the link will be opend in the system browser rather than in the cordova application.
        outerPanel.add(anchor);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                Window.open(target, "_system", "");
            }
        };
        anchor.addClickHandler(singleShotEventListner);
        anchor.addTouchStartHandler(singleShotEventListner);
        anchor.addTouchMoveHandler(singleShotEventListner);
        anchor.addTouchEndHandler(singleShotEventListner);
        anchor.addStyleName("pageLink");
    }

    public void addOptionButton(final PresenterEventListner presenterListerner) {
        final Button nextButton = new Button(presenterListerner.getLabel());
        nextButton.addStyleName("optionButton");
        nextButton.setEnabled(true);
        outerPanel.add(nextButton);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (nextButton.isEnabled()) {
                    presenterListerner.eventFired(nextButton);
                }
                resetSingleShot();
            }
        };
        nextButton.addClickHandler(singleShotEventListner);
        nextButton.addTouchStartHandler(singleShotEventListner);
        nextButton.addTouchMoveHandler(singleShotEventListner);
        nextButton.addTouchEndHandler(singleShotEventListner);
    }
}

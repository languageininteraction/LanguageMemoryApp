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
package nl.ru.languageininteraction.synaesthesia.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;

/**
 * @since Oct 21, 2014 5:15:19 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RegisterView extends SimpleView {

    final VerticalPanel outerPanel;
    private FlexTable flexTable = null;

    public RegisterView() {
        outerPanel = new VerticalPanel();
        setContent(outerPanel);
    }

    public void clearGui() {
        outerPanel.clear();
        flexTable = null;
    }

    public void addText(String textString) {
        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
        outerPanel.add(html);
    }

    public void addField(final String displayName, final String value) {
        if (flexTable == null) {
            flexTable = new FlexTable();
            flexTable.setStylePrimaryName("metadataTable");
            outerPanel.add(flexTable);
        }
        final int rowCount = flexTable.getRowCount();
        flexTable.setWidget(rowCount, 0, new Label(displayName));
        flexTable.setWidget(rowCount, 1, new HTML(new SafeHtmlBuilder().appendEscapedLines(value).toSafeHtml()));
    }

    public void addLink(String label, final String target) {
        final Anchor anchor = new Anchor(new SafeHtmlBuilder().appendEscapedLines(label).toSafeHtml());
        // this link relies on the org.apache.cordova.inappbrowser which offers secure viewing of external html pages and handles user navigation such as back navigation.
        // in this case the link will be opend in the system browser rather than in the cordova application.
        outerPanel.add(anchor);
        anchor.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                Window.open(target, "_system", "");
            }
        });
        anchor.addStyleName("pageLink");
    }

    public CheckBox addCheckBox(String label) {
        final CheckBox checkBox = new CheckBox(new SafeHtmlBuilder().appendEscapedLines(label).toSafeHtml());
        outerPanel.add(checkBox);
        return checkBox;
    }

    public void addOptionButton(final PresenterEventListner presenterListerner) {
        final Button nextButton = new Button(presenterListerner.getLabel());
        nextButton.addStyleName("optionButton");
        nextButton.setEnabled(true);
        outerPanel.add(nextButton);
        nextButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                presenterListerner.eventFired(nextButton);
            }
        });
    }
}

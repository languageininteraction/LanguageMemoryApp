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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.language.client.MatchLanguageBuilder;

/**
 * @since Nov 26, 2014 4:11:13 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MatchLanguageView extends SimpleView {

    final VerticalPanel verticalPanel = new VerticalPanel();
    private int height;
    private int width;

    protected final MatchLanguageBuilder matchLanguageBuilder = new MatchLanguageBuilder();

    @Override
    protected void parentResized(int height, int width, String units) {
        super.parentResized(height, width, units);
    }

    public void setupScreen() {
        final Label label = new Label("clicked labels show here");
        verticalPanel.add(label);
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        height = Window.getClientHeight();
        width = Window.getClientWidth();
        builder.append(SafeHtmlUtils.fromTrustedString("<style>.overlay {pointer-events: none;}</style>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<svg id='ocean' height='" + height + "px' width='" + width + "px' >"));
        matchLanguageBuilder.getSvg(builder);
        builder.append(SafeHtmlUtils.fromTrustedString("</svg>"));
        final HTML html = new HTML(builder.toSafeHtml());
        html.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                final Element targetElement = Element.as(event.getNativeEvent().getEventTarget());
                final Element parentElement = targetElement.getParentElement();
                if (!parentElement.getId().isEmpty()) {
                    label.setText(parentElement.getId());
                } else {
                    label.setText(targetElement.getId());
                }
            }
        });
        verticalPanel.add(html);
        setContent(verticalPanel);
    }
}

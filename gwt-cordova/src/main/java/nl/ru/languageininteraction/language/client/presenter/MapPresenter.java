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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.view.SimpleView;

/**
 * @since Nov 26, 2014 4:12:27 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MapPresenter extends AbstractPresenter implements Presenter {

    final VerticalPanel verticalPanel = new VerticalPanel();

    public MapPresenter(RootPanel widgetTag) {
        super(widgetTag, new SimpleView());
    }

    @Override
    public void pageClosing() {

    }

    @Override
    public void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.mapScreenTitle(), titleBarListner);
    }

    @Override
    public void setContent(AppEventListner appEventListner) {
        final Label label = new Label("click a land mass");
        verticalPanel.add(label);
//        String[] items = new String[]{"one", "two", "three"};
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
//        for (String item : items) {
//            builder.appendEscaped(item).appendHtmlConstant("<br/>");
//        }
        builder.append(SafeHtmlUtils.fromTrustedString("<style>.overlay {pointer-events: none;}</style>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<svg width='600' height='300' id='ocean'>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='250' y='150' height='5px' width='5px' fill='blue'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='255' y='170' height='5px' width='5px' fill='blue'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='10' y='160' height='5px' width='5px' fill='blue'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='30' y='150' height='5px' width='5px' fill='blue'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='20' y='150' height='5px' width='5px' fill='blue'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='continent' x='0' y='10'height='88px' width='88px' fill='green'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='asia' x='250' y='50' height='88px' width='88px' fill='green'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='europe' x='150' y='10' height='88px' width='88px' fill='green'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='africa' x='100' y='110' height='88px' width='88px' fill='green'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='australia' x='400' y='100' height='88px' width='88px' fill='green'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='newzealand' x='500' y='150' height='20px' width='10px' fill='green'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<line id='horizontal' class='overlay' x1='0' y1='100' x2='600' y2='100' style='stroke:rgb(255,0,0);stroke-width:2'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<line id='vertical' class='overlay' x1='100' y1='0' x2='100' y2='200' style='stroke:rgb(255,0,0);stroke-width:2'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("</svg>"));
        final HTML html = new HTML(builder.toSafeHtml());
        html.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                label.setText(Element.as(event.getNativeEvent().getEventTarget()).getId());
                final Element horizontalLine = Document.get().getElementById("horizontal");
                final Element verticalLine = Document.get().getElementById("vertical");
                final Element svgElement = Document.get().getElementById("ocean");
                final int relativeX = event.getRelativeX(svgElement);
                final int relativeY = event.getRelativeY(svgElement);
                horizontalLine.setAttribute("y1", String.valueOf(relativeY));
                horizontalLine.setAttribute("y2", String.valueOf(relativeY));
                verticalLine.setAttribute("x1", String.valueOf(relativeX));
                verticalLine.setAttribute("x2", String.valueOf(relativeX));
            }
        });
        verticalPanel.add(html);
//        verticalPanel.add(new HTML(builder.toSafeHtml()));
        simpleView.setContent(verticalPanel);
    }
}

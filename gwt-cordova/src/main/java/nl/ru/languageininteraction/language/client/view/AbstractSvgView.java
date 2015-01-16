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
package nl.ru.languageininteraction.language.client.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.language.client.MatchLanguageBuilder;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import static nl.ru.languageininteraction.language.client.view.SimpleView.HEADER_SIZE;

/**
 * @since Jan 15, 2015 5:30:25 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class AbstractSvgView extends SimpleView {

    final VerticalPanel verticalPanel = new VerticalPanel();
    protected final AudioPlayer audioPlayer;
    final protected Label label = new Label("clicked labels show here");

    public AbstractSvgView(final AudioPlayer audioPlayer) throws AudioException {
        this.audioPlayer = audioPlayer;
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        final Element diagramElement = DOM.getElementById(MatchLanguageBuilder.SvgGroupStates.diagram.name());
        if (diagramElement != null) {
            diagramElement.setAttribute("height", height - (HEADER_SIZE * 3) + units);
            diagramElement.setAttribute("width", width + units);
        }
        super.parentResized(height, width, units);
    }

//    private void showGroup(SvgGroupStates group) {
////        DOM.getElementById(group.name()).setAttribute("style", "visibility:" + SvgTemplate.Visibility.visible);
//        SafeHtmlBuilder builder = new SafeHtmlBuilder();
//        matchLanguageBuilder.getSvgChoiceArrow1(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow2(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow3(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow4(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow5(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton1(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton2(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton3(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton4(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton5(builder, SvgTemplate.Visibility.visible);
////        final HTML html = new HTML(builder.toSafeHtml());
//        DOM.getElementById(SvgGroupStates.diagram.name()).setInnerSafeHtml(builder.toSafeHtml());
//    }
//
//    private void hideGroup(SvgGroupStates group) {
////        DOM.getElementById(group.name()).setAttribute("style", "visibility:" + SvgTemplate.Visibility.hidden);
//        DOM.getElementById(SvgGroupStates.ChoiceArrow2.name()).removeFromParent();
//    }
    public void setupScreen() {
        verticalPanel.add(label);
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        builder.append(SafeHtmlUtils.fromTrustedString("<style>.overlay {pointer-events: none;}</style>"));
        int height = Window.getClientHeight() - (HEADER_SIZE * 3);
        int width = Window.getClientWidth();
        builder.append(SafeHtmlUtils.fromTrustedString("<svg id='" + MatchLanguageBuilder.SvgGroupStates.diagram.name() + "' height='" + height + "px' width='" + width + "px' >"));
        getSvg(builder);
        builder.append(SafeHtmlUtils.fromTrustedString("</svg>"));
        final HTML html = new HTML(builder.toSafeHtml());
        html.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                performClick(Element.as(event.getNativeEvent().getEventTarget()));
            }
        });
        html.addTouchEndHandler(new TouchEndHandler() {

            @Override
            public void onTouchEnd(TouchEndEvent event) {
                performClick(Element.as(event.getNativeEvent().getEventTarget()));
            }
        });
        html.setStylePrimaryName("svgPanel");
        verticalPanel.add(html);
        setContent(verticalPanel);
    }

    abstract protected void getSvg(SafeHtmlBuilder builder);

    abstract protected void performClick(final Element targetElement);
}

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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.language.client.MatchLanguage;
import nl.ru.languageininteraction.language.client.util.SvgTemplate;

/**
 * @since Nov 26, 2014 4:11:13 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MatchLanguageView extends SimpleView {

    final VerticalPanel verticalPanel = new VerticalPanel();
    private int height;
    private int width;

    protected final MatchLanguage matchLanguage = GWT.create(MatchLanguage.class);
    private static final SvgTemplate SVG_TEMPLATE = GWT.create(SvgTemplate.class);

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
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idChoiceArrows(), matchLanguage.transformChoiceArrows()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6130(), matchLanguage.stylepath6130(), matchLanguage.datapath6130()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idChoiceArrow1(), matchLanguage.transformChoiceArrow1()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath3987(), matchLanguage.stylepath3987(), matchLanguage.datapath3987()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idChoiceArrow2(), matchLanguage.transformChoiceArrow2()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath4045(), matchLanguage.stylepath4045(), matchLanguage.datapath4045()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idChoiceArrow3(), matchLanguage.transformChoiceArrow3()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5883(), matchLanguage.stylepath5883(), matchLanguage.datapath5883()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idChoiceArrow4(), matchLanguage.transformChoiceArrow4()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath4049(), matchLanguage.stylepath4049(), matchLanguage.datapath4049()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idChoiceArrow5(), matchLanguage.transformChoiceArrow5()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath4047(), matchLanguage.stylepath4047(), matchLanguage.datapath4047()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idLanguageInfoBox(), matchLanguage.transformLanguageInfoBox()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformrect4409(), matchLanguage.stylerect4409(), matchLanguage.datarect4409()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath4321(), matchLanguage.stylepath4321(), matchLanguage.datapath4321()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idTargetButton(), matchLanguage.transformTargetButton()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath2985(), matchLanguage.stylepath2985(), matchLanguage.datapath2985()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath3914(), matchLanguage.stylepath3914(), matchLanguage.datapath3914()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath3918(), matchLanguage.stylepath3918(), matchLanguage.datapath3918()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath3922(), matchLanguage.stylepath3922(), matchLanguage.datapath3922()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath3924(), matchLanguage.stylepath3924(), matchLanguage.datapath3924()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath3806(), matchLanguage.stylepath3806(), matchLanguage.datapath3806()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idNextRoundButton(), matchLanguage.transformNextRoundButton()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath4935(), matchLanguage.stylepath4935(), matchLanguage.datapath4935()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idScoreBox(), matchLanguage.transformScoreBox()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath4446(), matchLanguage.stylepath4446(), matchLanguage.datapath4446()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idTargetButtonPlay(), matchLanguage.transformTargetButtonPlay()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5426(), matchLanguage.stylepath5426(), matchLanguage.datapath5426()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5430(), matchLanguage.stylepath5430(), matchLanguage.datapath5430()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5434(), matchLanguage.stylepath5434(), matchLanguage.datapath5434()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5438(), matchLanguage.stylepath5438(), matchLanguage.datapath5438()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5440(), matchLanguage.stylepath5440(), matchLanguage.datapath5440()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idSampleButton1(), matchLanguage.transformSampleButton1()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5452(), matchLanguage.stylepath5452(), matchLanguage.datapath5452()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5456(), matchLanguage.stylepath5456(), matchLanguage.datapath5456()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5460(), matchLanguage.stylepath5460(), matchLanguage.datapath5460()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5464(), matchLanguage.stylepath5464(), matchLanguage.datapath5464()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5466(), matchLanguage.stylepath5466(), matchLanguage.datapath5466()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5468(), matchLanguage.stylepath5468(), matchLanguage.datapath5468()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idSampleButton2(), matchLanguage.transformSampleButton2()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5955(), matchLanguage.stylepath5955(), matchLanguage.datapath5955()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5959(), matchLanguage.stylepath5959(), matchLanguage.datapath5959()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5963(), matchLanguage.stylepath5963(), matchLanguage.datapath5963()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5967(), matchLanguage.stylepath5967(), matchLanguage.datapath5967()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5969(), matchLanguage.stylepath5969(), matchLanguage.datapath5969()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5971(), matchLanguage.stylepath5971(), matchLanguage.datapath5971()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idSampleButton3(), matchLanguage.transformSampleButton3()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5975(), matchLanguage.stylepath5975(), matchLanguage.datapath5975()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5979(), matchLanguage.stylepath5979(), matchLanguage.datapath5979()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5983(), matchLanguage.stylepath5983(), matchLanguage.datapath5983()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5987(), matchLanguage.stylepath5987(), matchLanguage.datapath5987()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5989(), matchLanguage.stylepath5989(), matchLanguage.datapath5989()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5991(), matchLanguage.stylepath5991(), matchLanguage.datapath5991()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idSampleButton4(), matchLanguage.transformSampleButton4()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5995(), matchLanguage.stylepath5995(), matchLanguage.datapath5995()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5999(), matchLanguage.stylepath5999(), matchLanguage.datapath5999()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6003(), matchLanguage.stylepath6003(), matchLanguage.datapath6003()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6007(), matchLanguage.stylepath6007(), matchLanguage.datapath6007()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6009(), matchLanguage.stylepath6009(), matchLanguage.datapath6009()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6011(), matchLanguage.stylepath6011(), matchLanguage.datapath6011()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idSampleButton5(), matchLanguage.transformSampleButton5()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6015(), matchLanguage.stylepath6015(), matchLanguage.datapath6015()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6019(), matchLanguage.stylepath6019(), matchLanguage.datapath6019()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6023(), matchLanguage.stylepath6023(), matchLanguage.datapath6023()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6027(), matchLanguage.stylepath6027(), matchLanguage.datapath6027()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6029(), matchLanguage.stylepath6029(), matchLanguage.datapath6029()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath6031(), matchLanguage.stylepath6031(), matchLanguage.datapath6031()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idCorrectButton(), matchLanguage.transformCorrectButton()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath3989(), matchLanguage.stylepath3989(), matchLanguage.datapath3989()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformrect4005(), matchLanguage.stylerect4005(), matchLanguage.datarect4005()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idIncorrectButton(), matchLanguage.transformIncorrectButton()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath3993(), matchLanguage.stylepath3993(), matchLanguage.datapath3993()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformrect4000(), matchLanguage.stylerect4000(), matchLanguage.datarect4000()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idSampleButtonPlay(), matchLanguage.transformSampleButtonPlay()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5470(), matchLanguage.stylepath5470(), matchLanguage.datapath5470()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5474(), matchLanguage.stylepath5474(), matchLanguage.datapath5474()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5478(), matchLanguage.stylepath5478(), matchLanguage.datapath5478()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5482(), matchLanguage.stylepath5482(), matchLanguage.datapath5482()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5484(), matchLanguage.stylepath5484(), matchLanguage.datapath5484()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(matchLanguage.idSampleButtonDisabled(), matchLanguage.transformSampleButtonDisabled()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5483(), matchLanguage.stylepath5483(), matchLanguage.datapath5483()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5485(), matchLanguage.stylepath5485(), matchLanguage.datapath5485()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5487(), matchLanguage.stylepath5487(), matchLanguage.datapath5487()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5489(), matchLanguage.stylepath5489(), matchLanguage.datapath5489()));
        builder.append(SVG_TEMPLATE.pathTag(matchLanguage.transformpath5491(), matchLanguage.stylepath5491(), matchLanguage.datapath5491()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
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

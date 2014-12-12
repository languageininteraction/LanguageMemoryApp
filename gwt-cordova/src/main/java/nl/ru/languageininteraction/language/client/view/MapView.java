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
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.language.client.AutotypRegions;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.util.SvgTemplate;

/**
 * @since Nov 26, 2014 4:11:13 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MapView extends SimpleView {

    final VerticalPanel verticalPanel = new VerticalPanel();
    private float zoomFactor = 1f;
    private int height;
    private int width;
    private String units;

    protected final AutotypRegions autotypRegions = GWT.create(AutotypRegions.class);
    private static final SvgTemplate SVG_TEMPLATE = GWT.create(SvgTemplate.class);

    public void addZoom() {
        setButton(ButtonType.menu, new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "-";
            }

            @Override
            public void eventFired(Button button) {
//                final Element svgElement = Document.get().getElementById("zoomableGroup");
                zoomFactor *= 0.5;
//                svgElement.setAttribute("transform", "scale(" + zoomFactor + ")");
                final Element svgElement = Document.get().getElementById("ocean");
                if (svgElement != null) {
                    svgElement.setAttribute("width", Float.toString(width * zoomFactor));
                    svgElement.setAttribute("height", Float.toString(height * zoomFactor));
                    Document.get().getElementById("zoomableGroup").setAttribute("transform", "scale(" + zoomFactor + ")");
                }
            }
        });
        setButton(ButtonType.menu, new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "+";
            }

            @Override
            public void eventFired(Button button) {
//                final Element svgElement = Document.get().getElementById("zoomableGroup");
                zoomFactor *= 1.5;
//                svgElement.setAttribute("transform", "scale(" + zoomFactor + ")");
                final Element svgElement = Document.get().getElementById("ocean");
                if (svgElement != null) {
                    svgElement.setAttribute("width", Float.toString(width * zoomFactor));
                    svgElement.setAttribute("height", Float.toString(height * zoomFactor));
                    Document.get().getElementById("zoomableGroup").setAttribute("transform", "scale(" + zoomFactor + ")");
                }
            }
        });
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        super.parentResized(height, width, units);
//        final Element svgElement = Document.get().getElementById("ocean");
//        if (svgElement != null) {
//            svgElement.setAttribute("width", width + units);
//            svgElement.setAttribute("height", height + units);
//        }
//   
    }

    public void addMap() {
        final Label label = new Label("click a land mass");
        verticalPanel.add(label);
//        String[] items = new String[]{"one", "two", "three"};
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
//        for (String item : items) {
//            builder.appendEscaped(item).appendHtmlConstant("<br/>");
//        }
        height = Window.getClientHeight();
        width = Window.getClientWidth();
        builder.append(SafeHtmlUtils.fromTrustedString("<style>.overlay {pointer-events: none;}</style>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<svg id='ocean' height='" + height + "px' width='" + width + "px' >"));
        builder.append(SafeHtmlUtils.fromTrustedString("<g id='zoomableGroup'>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<g transform='scale(0.3)'>"));
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idIndic(), autotypRegions.transformIndic()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97259(), autotypRegions.stylepath97259(), autotypRegions.datapath97259()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idBasinandPlains(), autotypRegions.transformBasinandPlains()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath107611(), autotypRegions.stylepath107611(), autotypRegions.datapath107611()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idAlaskaOregon(), autotypRegions.transformAlaskaOregon()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath107614(), autotypRegions.stylepath107614(), autotypRegions.datapath107614()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idCalifornia(), autotypRegions.transformCalifornia()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath107582(), autotypRegions.stylepath107582(), autotypRegions.datapath107582()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idMesoamerica(), autotypRegions.transformMesoamerica()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97265(), autotypRegions.stylepath97265(), autotypRegions.datapath97265()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idSESouthAmerica(), autotypRegions.transformSESouthAmerica()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath107722(), autotypRegions.stylepath107722(), autotypRegions.datapath107722()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idNESouthAmerica(), autotypRegions.transformNESouthAmerica()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97311(), autotypRegions.stylepath97311(), autotypRegions.datapath97311()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idAndean(), autotypRegions.transformAndean()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath107701(), autotypRegions.stylepath107701(), autotypRegions.datapath107701()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idNAustralia(), autotypRegions.transformNAustralia()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97635(), autotypRegions.stylepath97635(), autotypRegions.datapath97635()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idGreenland(), autotypRegions.transformGreenland()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath140696(), autotypRegions.stylepath140696(), autotypRegions.datapath140696()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idSAustralia(), autotypRegions.transformSAustralia()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath107441(), autotypRegions.stylepath107441(), autotypRegions.datapath107441()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath99393(), autotypRegions.stylepath99393(), autotypRegions.datapath99393()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idNAfrica(), autotypRegions.transformNAfrica()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath140264(), autotypRegions.stylepath140264(), autotypRegions.datapath140264()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idSAfrica(), autotypRegions.transformSAfrica()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath140076(), autotypRegions.stylepath140076(), autotypRegions.datapath140076()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idGreaterMesopotamia(), autotypRegions.transformGreaterMesopotamia()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97275(), autotypRegions.stylepath97275(), autotypRegions.datapath97275()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idInnerAsia(), autotypRegions.transformInnerAsia()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97287(), autotypRegions.stylepath97287(), autotypRegions.datapath97287()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath140112(), autotypRegions.stylepath140112(), autotypRegions.datapath140112()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idEurope(), autotypRegions.transformEurope()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97263(), autotypRegions.stylepath97263(), autotypRegions.datapath97263()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97585(), autotypRegions.stylepath97585(), autotypRegions.datapath97585()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97977(), autotypRegions.stylepath97977(), autotypRegions.datapath97977()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath98383(), autotypRegions.stylepath98383(), autotypRegions.datapath98383()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath99041(), autotypRegions.stylepath99041(), autotypRegions.datapath99041()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath100331(), autotypRegions.stylepath100331(), autotypRegions.datapath100331()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97431(), autotypRegions.stylepath97431(), autotypRegions.datapath97431()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath98187(), autotypRegions.stylepath98187(), autotypRegions.datapath98187()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath98391(), autotypRegions.stylepath98391(), autotypRegions.datapath98391()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath100255(), autotypRegions.stylepath100255(), autotypRegions.datapath100255()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath100829(), autotypRegions.stylepath100829(), autotypRegions.datapath100829()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath101047(), autotypRegions.stylepath101047(), autotypRegions.datapath101047()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath101127(), autotypRegions.stylepath101127(), autotypRegions.datapath101127()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath101193(), autotypRegions.stylepath101193(), autotypRegions.datapath101193()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath101671(), autotypRegions.stylepath101671(), autotypRegions.datapath101671()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath140654(), autotypRegions.stylepath140654(), autotypRegions.datapath140654()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97911(), autotypRegions.stylepath97911(), autotypRegions.datapath97911()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath141650(), autotypRegions.stylepath141650(), autotypRegions.datapath141650()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath142374(), autotypRegions.stylepath142374(), autotypRegions.datapath142374()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath143814(), autotypRegions.stylepath143814(), autotypRegions.datapath143814()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath144104(), autotypRegions.stylepath144104(), autotypRegions.datapath144104()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath140764(), autotypRegions.stylepath140764(), autotypRegions.datapath140764()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath140714(), autotypRegions.stylepath140714(), autotypRegions.datapath140714()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath143158(), autotypRegions.stylepath143158(), autotypRegions.datapath143158()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath144174(), autotypRegions.stylepath144174(), autotypRegions.datapath144174()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idSoutheast_Asia(), autotypRegions.transformSoutheast_Asia()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97365(), autotypRegions.stylepath97365(), autotypRegions.datapath97365()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idNorthCoastAsia(), autotypRegions.transformNorthCoastAsia()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97393(), autotypRegions.stylepath97393(), autotypRegions.datapath97393()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idENorthAmerica(), autotypRegions.transformENorthAmerica()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath107580(), autotypRegions.stylepath107580(), autotypRegions.datapath107580()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idPapua(), autotypRegions.transformPapua()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97649(), autotypRegions.stylepath97649(), autotypRegions.datapath97649()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idAfricanSavanah(), autotypRegions.transformAfricanSavanah()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath13257(), autotypRegions.stylepath13257(), autotypRegions.datapath13257()));
        builder.append(SVG_TEMPLATE.groupTagEnd());
        builder.append(SVG_TEMPLATE.groupTag(autotypRegions.idOceania(), autotypRegions.transformOceania()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath107904(), autotypRegions.stylepath107904(), autotypRegions.datapath107904()));
        builder.append(SVG_TEMPLATE.pathTag(autotypRegions.transformpath97885(), autotypRegions.stylepath97885(), autotypRegions.datapath97885()));
        builder.append(SVG_TEMPLATE.groupTagEnd());

        //        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='250' y='150' height='5px' width='5px' fill='blue'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='255' y='170' height='5px' width='5px' fill='blue'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='10' y='160' height='5px' width='5px' fill='blue'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='30' y='150' height='5px' width='5px' fill='blue'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='island' x='20' y='150' height='5px' width='5px' fill='blue'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='continent' x='0' y='10'height='88px' width='88px' fill='green'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='asia' x='250' y='50' height='88px' width='88px' fill='green'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='europe' x='150' y='10' height='88px' width='88px' fill='green'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='africa' x='100' y='110' height='88px' width='88px' fill='green'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='australia' x='400' y='100' height='88px' width='88px' fill='green'/>"));
//        builder.append(SafeHtmlUtils.fromTrustedString("<rect id='newzealand' x='500' y='150' height='20px' width='10px' fill='green'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("</g>"));
        builder.append(SafeHtmlUtils.fromTrustedString("</g>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<line id='horizontal' class='overlay' x1='0' y1='100' x2='" + width + "' y2='100' style='stroke:rgb(255,0,0);stroke-width:2'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<line id='vertical' class='overlay' x1='100' y1='0' x2='100' y2='" + height + "' style='stroke:rgb(255,0,0);stroke-width:2'/>"));
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
                final Element horizontalLine = Document.get().getElementById("horizontal");
                final Element verticalLine = Document.get().getElementById("vertical");
                final Element svgElement = Document.get().getElementById("ocean");
                final int relativeX = event.getRelativeX(svgElement);
                final int relativeY = event.getRelativeY(svgElement);
                horizontalLine.setAttribute("y1", String.valueOf(relativeY));
                horizontalLine.setAttribute("y2", String.valueOf(relativeY));
                horizontalLine.setAttribute("x2", String.valueOf(width));
                verticalLine.setAttribute("x1", String.valueOf(relativeX));
                verticalLine.setAttribute("x2", String.valueOf(relativeX));
                verticalLine.setAttribute("y2", String.valueOf(height));
            }
        });
        verticalPanel.add(html);
//        verticalPanel.add(new HTML(builder.toSafeHtml()));
        setContent(verticalPanel);
    }
}

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
package nl.ru.languageininteraction.language.client.util;

import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.client.SafeHtmlTemplates.Template;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * @since Dec 10, 2014 4:44:03 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public interface SvgTemplate extends SafeHtmlTemplates {

    enum Visibility {

        hidden, visible, inherit
    }

    @Template("<svg>{0}</svg>")
    SafeHtml svgTag(SafeHtml svgBody);

    @Template("<g id=\"{0}\" transform=\"{1}\">")
    SafeHtml groupTag(String id, String transform);

    @Template("<g id=\"{0}\" transform=\"{1}\" style=\"visibility:{2}\">")
    SafeHtml groupTag(String id, String transform, Visibility visibility);

    @Template("</g>")
    SafeHtml groupTagEnd();

    @Template("<path transform=\"{0}\" style=\"{1}\" d=\"{2}\" />")
    SafeHtml pathTag(String transform, String style, String data);

    @Template("<path transform=\"{0}\" style=\"{1}\" d=\"{2}\" id=\"{3}\"/>")
    SafeHtml pathTag(String transform, String style, String data, String id);

    @Template("<tspan id=\"{0}\" x=\"{1}\" y=\"{2}\" style=\"{3}\" xml:space=\"preserve\">{4}</tspan>")
    SafeHtml tspanTag(String id, String x, String y, String style, String text);

    @Template("<text x=\"{0}\" y=\"{1}\" transform=\"{2}\" style=\"{3}\" >")
    SafeHtml textTag(String x, String y, String transform, String style);

    @Template("</text>")
    SafeHtml textTagEnd();

    @Template("<stop offset=\"{0}\" style=\"{1}\"/>")
    SafeHtml stopTag(String offset, String style);

    @Template("<linearGradient id=\"{0}\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:href=\"{1}\" x1=\"{2}\" y1=\"{3}\" x2=\"{4}\" y2=\"{5}\" gradientTransform=\"{6}\" gradientUnits=\"{7}\">")
    SafeHtml linearGradientTag(String id, String xlinkHref, String x1, String y1, String x2, String y2, String gradientTransform, String gradientUnits);

    @Template("</linearGradient>")
    SafeHtml linearGradientTagEnd();

    @Template("<defs>")
    SafeHtml defsTag();

    @Template("</defs>")
    SafeHtml defsTagEnd();

    @Template("<rect x=\"{0}\" y=\"{1}\" rx=\"{2}\" ry=\"{3}\" width=\"{4}\" height=\"{5}\" style=\"{6}\" />")
    SafeHtml rectTag(String x, String y, String rx, String ry, String width, String height, String style);

    @Template("<circle cx=\"{0}\" cy=\"{1}\" r=\"{2}\" style=\"{3}\" />")
    SafeHtml circleTag(String cx, String cy, String r, String style);

    @Template("<ellipse cx=\"{0}\" cy=\"{1}\" rx=\"{2}\" ry=\"{3}\" style=\"{4}\" transform=\"{5}\" />")
    SafeHtml ellipseTag(String cx, String cy, String rx, String ry, String style, String transform);
}

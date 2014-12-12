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

    @Template("<svg>{0}</svg>")
    SafeHtml svgTag(SafeHtml svgBody);

    @Template("<g id=\"{0}\" transform=\"{1}\">")
    SafeHtml groupTag(String id, String transform);

    @Template("</g>")
    SafeHtml groupTagEnd();

    @Template("<path transform=\"{0}\" style=\"{1}\" d=\"{2}\" />")
    SafeHtml pathTag(String transform, String style, String data);
}

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
package nl.ru.languageininteraction.language.client.model;

/**
 * @since Oct 14, 2014 1:17:38 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ColourData {

    private final int red;
    private final int green;
    private final int blue;

    public ColourData(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getLuminance() {
        return (red + green + blue) / 3;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public String getHexValue() {
        // String format is not available in GWT so we manualy format the hex instead
//        return String.format("#%02x%02x%02x", red, green, blue);
        return "#"
                + addPadding(Integer.toHexString(red))
                + addPadding(Integer.toHexString(green))
                + addPadding(Integer.toHexString(blue));
    }

    private String addPadding(String hexValue) {
        return (hexValue.length() == 1) ? "0" + hexValue : hexValue;
    }
}

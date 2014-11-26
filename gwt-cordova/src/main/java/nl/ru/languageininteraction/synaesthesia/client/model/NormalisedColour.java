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
package nl.ru.languageininteraction.synaesthesia.client.model;

/**
 * @since Nov 17, 2014 5:52:52 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class NormalisedColour {

    private final float red;
    private final float green;
    private final float blue;

    public NormalisedColour(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public NormalisedColour(ColourData colourData) {
        this.red = colourData.getRed() / (255f);
        this.green = colourData.getGreen() / (255f);
        this.blue = colourData.getBlue() / (255f);
    }

    public NormalisedColour add(NormalisedColour other) {
//        System.out.println("<td>+</td>");
        return new NormalisedColour(other.red + this.red, other.green + this.green, other.blue + this.blue);
    }

    public NormalisedColour subtract(NormalisedColour other) {
//        System.out.println("<td>-</td>");
        return new NormalisedColour(other.red - this.red, other.green - this.green, other.blue - this.blue);
    }

    public NormalisedColour difference(NormalisedColour other) {
//        System.out.println("<td>abs(" + other.red + "-" + this.red + "), abs(" + other.green + "-" + this.green + "), abs(" + other.blue + "-" + this.blue + ")</td>");
        return new NormalisedColour(Math.abs(other.red - this.red), Math.abs(other.green - this.green), Math.abs(other.blue - this.blue));
    }

    public float getSum() {
//        System.out.println("<td>" + red + "+" + green + "+" + blue + "</td>");
        return red + green + blue;
    }
}

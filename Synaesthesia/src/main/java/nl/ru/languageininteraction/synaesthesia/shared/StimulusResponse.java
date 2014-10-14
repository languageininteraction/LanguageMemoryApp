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
package nl.ru.languageininteraction.synaesthesia.shared;

import java.util.Date;

/**
 * @since Oct 14, 2014 1:23:24 PM (creation date)
 * @author peterwithers
 */
public class StimulusResponse {

    private final ColourData colour;
    private final Date time;
    private final double durationMs;

    public StimulusResponse(ColourData colour, Date time, double durationMs) {
        this.colour = colour;
        this.time = time;
        this.durationMs = durationMs;
    }

    public ColourData getColour() {
        return colour;
    }

    public Date getTime() {
        return time;
    }

    public double getDurationMs() {
        return durationMs;
    }

}

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
package nl.ru.languageininteraction.language.client.model;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @since Mar 12, 2015 10:35:28 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class HighScoreData extends JavaScriptObject {

    protected HighScoreData() {
    }

    public final native int getCount() /*-{ return this.scores.length; }-*/;

    public final native String getPlayerName(int index) /*-{ return this.scores[index].player; }-*/;

    public final native int getPlayerHighScore(int index) /*-{ return this.scores[index].highscore; }-*/;
}

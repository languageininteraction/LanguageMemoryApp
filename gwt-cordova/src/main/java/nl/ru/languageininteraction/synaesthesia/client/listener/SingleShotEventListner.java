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
package nl.ru.languageininteraction.synaesthesia.client.listener;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;

/**
 * @since Feb 11, 2015 4:34:40 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class SingleShotEventListner implements PresenterEventListner {

    private boolean singleShotConsumed = false;

    @Override
    final public void eventFired(final Button button) {
        if (!singleShotConsumed) {
            Scheduler.get().scheduleDeferred(new Command() {
                @Override
                public void execute() {
                    singleShotFired(button);
                    singleShotConsumed = false;
                }
            });
        }
        singleShotConsumed = true;
    }

    protected abstract void singleShotFired(Button button);
}

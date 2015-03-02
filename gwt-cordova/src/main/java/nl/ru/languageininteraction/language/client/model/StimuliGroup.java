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

import java.util.List;
import java.util.Objects;

/**
 * @since Oct 22, 2014 5:08:11 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class StimuliGroup {

    private final String groupLabel;
    private final String postName;
    private final List<Stimulus> stimuli;

    public StimuliGroup(String groupLabel, String postName, List<Stimulus> stimuli) {
        this.groupLabel = groupLabel;
        this.postName = postName;
        this.stimuli = stimuli;
    }

    public String getPostName() {
        return postName;
    }

    public List<Stimulus> getStimuli() {
        return stimuli;
    }

    public String getGroupLabel() {
        return groupLabel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.groupLabel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StimuliGroup other = (StimuliGroup) obj;
        if (!Objects.equals(this.groupLabel, other.groupLabel)) {
            return false;
        }
        return true;
    }

}

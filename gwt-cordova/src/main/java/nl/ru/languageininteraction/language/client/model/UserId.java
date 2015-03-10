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

import java.util.Date;
import java.util.Objects;

/**
 * @since Mar 10, 2015 2:33:35 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class UserId {

    private final String randomIdString;

    public UserId(String idString) {
        this.randomIdString = idString;
    }

    public UserId() {
        this.randomIdString = getRandomId();
    }

    private String getRandomId() {
        return Long.toHexString(new Date().getTime()) + "-" + Long.toHexString((long) (Math.random() * 0xFFFF)) + "-" + Long.toHexString((long) (Math.random() * 0xFFFF)) + "-" + Long.toHexString((long) (Math.random() * 0xFFFF)) + "-" + Long.toHexString((long) (Math.random() * 0xFFFF));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.randomIdString);
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
        final UserId other = (UserId) obj;
        if (!Objects.equals(this.randomIdString, other.randomIdString)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return randomIdString;
    }
}

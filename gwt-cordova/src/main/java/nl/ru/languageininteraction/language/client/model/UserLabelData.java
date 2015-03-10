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

/**
 * @since Mar 10, 2015 3:51:42 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class UserLabelData {

    private final UserId userId;
    private final String userName;

    public UserLabelData(UserId userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

}

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
package nl.ru.languageininteraction.synaesthesia.client.service;

/**
 * @since Dec 11, 2014 3:47:25 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class SocialMediaPost {

    public native void postText(String postText) /*-{
     var message = {
     text: postText
     };
     $wnd.socialmessage.send(message);
     }-*/;

    public native void makeEmail(String postSubject, String postText) /*-{
     var message = {
     subject: postSubject,
     text: postText,
     activityTypes: ["Mail"]
     };
     $wnd.socialmessage.send(message);
     }-*/;

    public native void postImage(String postText, String imageUrl) /*-{
     var message = {
     image: imageUrl,
     text: postText
     };
     $wnd.socialmessage.send(message);
     }-*/;

    public native void postUrl(String postText, String locationUrl) /*-{
     var message = {
     url: locationUrl,
     text: postText
     };
     $wnd.socialmessage.send(message);
     }-*/;
}

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
package nl.ru.languageininteraction.language.client.service;

import com.google.gwt.media.client.Audio;
import nl.ru.languageininteraction.language.client.exception.AudioException;

/**
 * @since Jan 6, 2015 10:27:57 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AudioPlayer {

    private final Audio audioPlayer;

    public AudioPlayer() throws AudioException {
        audioPlayer = Audio.createIfSupported();
        if (audioPlayer == null) {
            throw new AudioException("audio not supportered");
        }
    }

    public void playSampleAudio1() {
        audioPlayer.setSrc("media/Sample1.wav");
        audioPlayer.setCurrentTime(0);
        audioPlayer.play();
    }

    public void playSampleAudio2() {
        audioPlayer.setSrc("media/Sample2.wav");
        audioPlayer.setCurrentTime(0);
        audioPlayer.play();
    }

    public void playSampleAudio3() {
        audioPlayer.setSrc("media/Sample3.wav");
        audioPlayer.setCurrentTime(0);
        audioPlayer.play();
    }

    public void stopAll() {
        audioPlayer.pause();
        audioPlayer.setCurrentTime(0);
        audioPlayer.play();
    }
}

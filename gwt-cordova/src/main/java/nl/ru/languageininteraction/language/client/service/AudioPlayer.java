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

    private final Audio sampleAudio1;
    private final Audio sampleAudio2;
    private final Audio sampleAudio3;

    public AudioPlayer() throws AudioException {
        sampleAudio1 = Audio.createIfSupported();
        sampleAudio2 = Audio.createIfSupported();
        sampleAudio3 = Audio.createIfSupported();
        if (sampleAudio1 == null || sampleAudio2 == null || sampleAudio3 == null) {
            throw new AudioException("audio not supportered");
        }
        sampleAudio1.setSrc("media/Sample1.wav");
        sampleAudio2.setSrc("media/Sample2.wav");
        sampleAudio3.setSrc("media/Sample3.wav");
    }

    public void playSampleAudio1() {
        sampleAudio1.setCurrentTime(0);
        sampleAudio1.play();
    }

    public void playSampleAudio2() {
        sampleAudio2.setCurrentTime(0);
        sampleAudio2.play();
    }

    public void playSampleAudio3() {
        sampleAudio3.setCurrentTime(0);
        sampleAudio3.play();
    }

    public void stopAll() {
        sampleAudio1.pause();
        sampleAudio2.pause();
        sampleAudio3.pause();
    }
}

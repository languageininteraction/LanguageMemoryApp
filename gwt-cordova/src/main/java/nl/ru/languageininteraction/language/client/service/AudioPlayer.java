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

import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.media.client.Audio;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.AudioEventListner;
import nl.ru.languageininteraction.language.client.listener.AudioExceptionListner;
import nl.ru.languageininteraction.language.client.model.RoundSample;

/**
 * @since Jan 6, 2015 10:27:57 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AudioPlayer {

    private Audio audioPlayer;
    private AudioEventListner audioEventListner = null;
    final private AudioExceptionListner audioExceptionListner;

    public AudioPlayer(AudioExceptionListner audioExceptionListner) throws AudioException {
        this.audioExceptionListner = audioExceptionListner;
        createPlayer();
    }

    private void createPlayer() throws AudioException {
        audioPlayer = Audio.createIfSupported();
        if (audioPlayer == null) {
            throw new AudioException("audio not supportered");
        }
        final AudioElement audioElement = audioPlayer.getAudioElement();
        onEndedSetup(audioElement);
    }

    private native void onEndedSetup(final AudioElement audioElement) /*-{
     var audioPlayer = this;
     audioElement.addEventListener("ended", function(){
     audioPlayer.@nl.ru.languageininteraction.language.client.service.AudioPlayer::onEndedAction()();
     }, false);
     }-*/;

    public void onEndedAction() {
        if (audioEventListner != null) {
            audioEventListner.audioEnded();
        }
    }

    public void addOnEndedListener(AudioEventListner audioEventListner) {
        this.audioEventListner = audioEventListner;
    }

    public void playSampleAudio(RoundSample roundSample) {
        final String[] soundFiles = roundSample.getLanguageSample().getSoundFiles();
        playSample(soundFiles[roundSample.getSampleIndex()]);
    }

    private void playSample(String sample) {
        if (audioPlayer == null) {
            try {
                createPlayer();
            } catch (AudioException audioException) {
                audioExceptionListner.audioExceptionFired(audioException);
                return;
            }
        }
        audioPlayer.setSrc(sample);
        audioPlayer.setCurrentTime(0);
        audioPlayer.play();
    }

    public void stopAll() {
//        audioPlayer.pause();
        if (audioPlayer != null) {
            audioPlayer.setSrc("");
        }
        onEndedAction();
    }
}

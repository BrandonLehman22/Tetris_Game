package main;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import javax.sound.sampled.LineEvent.Type;

public class Sound {

    Clip musicClip;
    URL url[] = new URL[10];
    public static int musicLevel = 0;

    public Sound() {

        url[0] = getClass().getResource("/res/background.wav");
        url[1] = getClass().getResource("/res/background2.wav");
        url[2] = getClass().getResource("/res/delete line.wav");
        url[3] = getClass().getResource("/res/touch floor.wav");
        url[4] = getClass().getResource("/res/gameover.wav");
        url[5] = getClass().getResource("/res/rotation.wav");

    }

    public void play(int i, boolean music) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url[i]);
            Clip clip = AudioSystem.getClip();
            
            if (music) {
                musicClip = clip;
            }

            clip.open(ais);
            clip.addLineListener(new LineListener() {
                @Override
                public void update (LineEvent event) {
                    if (event.getType() == Type.STOP) {
                        clip.close();
                    }
                }
            });
            ais.close();
            clip.start();
            
        }
        catch(Exception e) {

        }
    }

    public void loop() {
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        musicClip.stop();
        musicClip.close();
    }
}

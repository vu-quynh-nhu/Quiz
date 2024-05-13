import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class StartButtonSoundEffect {
    Clip clip;

    //Method to set Audio File for StartButton
    public void setFile(String path) {
        try {
            //create new File and set path
            File file = new File(path);
            //load audio
            AudioInputStream startSound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(startSound);
        } catch (Exception e) {

        }
    }

    public void startSoundEffect() {
        //Start from beginning
        clip.setFramePosition(0);
        //start audio
        clip.start();
    }

    
}

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameSoundEffect {
    Clip clip;

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
        clip.setFramePosition(0);
        clip.start();
    }
}

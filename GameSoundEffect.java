import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//Klasse zum Abspielen von SoundEffekten
public class GameSoundEffect {
    //Deklarierung der Variable Clip
    Clip clip;

    public void setFile(String path) {
        try {
            //ein neues Dokument wird mit dem übergebenen Path erstellt
            File file = new File(path);
            //Der AudioStream wird geladen und dem Clip hinzugefügt
            AudioInputStream startSound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(startSound);
        } catch (Exception e) {

        }
    }

    public void startSoundEffect() {
        clip.setFramePosition(0);
        //Der Clip wird abgespielt
        clip.start();
    }
}

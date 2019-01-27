package pacman;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicThread {
    private File file = new File("music/getStar.wav");
    private File file1 = new File("music/start.wav");
    private File file2 = new File("music/eat.wav");
    private File file3 = new File("music/win.wav");

    public void play(File file) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PlaySoundGetStar() {
        play(file);
    }

    public void PlayStart() {
        play(file1);
    }

    public void PlayEat() {
        play(file2);
    }

    public void PlayWin() {
        play(file3);
    }
}

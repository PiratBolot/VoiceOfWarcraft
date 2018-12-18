package WarcraftVoice.sounds;

import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.util.Function;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class Sounds {
    public final Sound background;

    public final Sound open;

    @NotNull
    @Contract(" -> new")
    public static Sounds create() {
        return new Sounds(filePath -> new Sound(loadBytes(filePath), filePath));
    }

    @NotNull
    @Contract("_ -> new")
    public static Sounds createSilent(final SilentSound.Listener listener) {
        return new Sounds(filePath -> new SilentSound(loadBytes(filePath), filePath, listener));
    }

    private Sounds(Function<String, Sound> load) {
        background = load.fun("/Music/Soundtracks/pcm_human.wav");
        orcs = load.fun("/Music/Soundtracks/pcm_orc.wav");
        elves = load.fun("/Music/Soundtracks/pcm_elves.wav");
        scourge = load.fun("/Music/Soundtracks/pcm_scourge.wav");
        open = load.fun("/Music/Neutrals/Ogr/pcm_ogr_1.wav");
    }

    @NotNull
    private static byte[] loadBytes(String fileName) {
        try {
            InputStream inputStream =
                    Sounds.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) throw new RuntimeException("Cannot find " + fileName);
            return StreamUtil.loadFromStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

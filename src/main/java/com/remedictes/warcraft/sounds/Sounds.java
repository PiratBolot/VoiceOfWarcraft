package sounds;

import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.util.Function;

import java.io.IOException;
import java.io.InputStream;

public class Sounds {

    public static Sounds create(final boolean actionSoundsEnabled, final boolean backgroundMusicEnabled) {
        return new Sounds(config -> {
            boolean enabled = (config.isBackgroundMusic && backgroundMusicEnabled)
                    || (!config.isBackgroundMusic && actionSoundsEnabled);
            return enabled ?
                    new Sound(loadBytes(config.filePath), config.filePath) :
                    new SilentSound(loadBytes(config.filePath), config.filePath, SilentSound.Listener.none);
        });
    }

    public static Sounds createSilent(final SilentSound.Listener listener) {
        return new Sounds(config -> new SilentSound(loadBytes(config.filePath), config.filePath, listener));
    }

    private Sounds(Function<Config, Sound> load) {
//        oneUp = load.fun(new Config("/fridaymario/sounds/smb_1-up.au"));
    }

    private static byte[] loadBytes(String fileName) {
        try {
            InputStream inputStream = Sounds.class.getResourceAsStream(fileName);
            if (inputStream == null) throw new RuntimeException("Cannot find " + fileName);
            return StreamUtil.loadFromStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Config {
        public final String filePath;
        public final boolean isBackgroundMusic;

        public Config(String filePath) {
            this(filePath, false);
        }

        public Config(String filePath, boolean isBackgroundMusic) {
            this.filePath = filePath;
            this.isBackgroundMusic = isBackgroundMusic;
        }
    }
}

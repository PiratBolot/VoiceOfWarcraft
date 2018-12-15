package WarcraftVoice.sounds;

import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.util.Function;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class Sounds {
    public final Sound alliance;
    public final Sound orcs;
    public final Sound elves;
    public final Sound scourge;

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
        alliance = load.fun("/home/elvis_emplada/WarcraftVoice/src/main/java/WarcraftVoice/sounds/human.au");
        orcs = load.fun("/home/elvis_emplada/WarcraftVoice/src/main/java/WarcraftVoice/sounds/orc.au");
        elves = load.fun("/home/elvis_emplada/WarcraftVoice/src/main/java/WarcraftVoice/sounds/elves.au");
        scourge = load.fun("/home/elvis_emplada/WarcraftVoice/src/main/java/WarcraftVoice/sounds/scourge.au");
    }

    @NotNull
    private static byte[] loadBytes(String fileName) {
        try {
            InputStream inputStream = new FileInputStream(new File(fileName));
//            InputStream inputStream = Sounds.class.getResourceAsStream(fileName);
            if (inputStream == null) throw new RuntimeException("Cannot find " + fileName);
            return StreamUtil.loadFromStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

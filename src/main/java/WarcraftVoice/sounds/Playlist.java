package WarcraftVoice.sounds;

import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.util.Function;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class Playlist {
    private Race currentRace;

    public final Sound background;
    public final Sound compSucc;
    public final Sound compFail;
    public final Sound onUnitTestSucc;
    public final Sound onUnitTestFail;
    public final Sound onVcsCommit;
    public final Sound onVcsUpdate;
    public final Sound onVcsPush;
    public final Sound onVcsPushFail;
    public final Sound gameover;


    @NotNull
    @Contract(" -> new")
    public static Playlist create(Race race) {
        return new Playlist(race, filePath -> new Sound(loadBytes(filePath), filePath));
    }

    @NotNull
    @Contract("_ -> new")
    public static Playlist createSilent(Race race, final SilentSound.Listener listener) {
        return new Playlist(race, filePath -> new SilentSound(loadBytes(filePath), filePath,
                listener));
    }

    private Playlist(Race race, Function<String, Sound> load) {
        this.currentRace = race;
        if (race == Race.ORC) {
            background = load.fun("/Music/Soundtracks/pcm_orc.wav");
            compSucc = load.fun("Music/Orcs/Grunt/grunt_16.wav");
            compFail = load.fun("Music/Orcs/Mechanic/mechanic_27.wav");
            onUnitTestSucc = load.fun("Music/Orcs/Mechanic/mechanic_7.wav");
            onUnitTestFail = load.fun("Music/Orcs/Drek'tar/drek_13.wav");
            onVcsCommit = load.fun("Music/Orcs/Batrider/bat_7.wav");
            onVcsPush = load.fun("Music/Orcs/Batrider/bat_8.wav");
            onVcsPushFail = load.fun("Music/Orcs/Pandaren/ravio_17.wav");
            onVcsUpdate = load.fun("Music/Orcs/Batrider/bat_11.wav");
            gameover = load.fun("/Music/Orcs/Mechanic/mechanic_17.wav");
        } else if (race == Race.ALLIANCE) {
            background = load.fun("/Music/Soundtracks/pcm_human.wav");
            compSucc = load.fun("Music/Orcs/Grunt/grunt_16.wav");
            compFail = load.fun("Music/Orcs/Mechanic/mechanic_27.wav");
            onUnitTestSucc = load.fun("Music/Orcs/Mechanic/mechanic_7.wav");
            onUnitTestFail = load.fun("Music/Orcs/Drek'tar/drek_13.wav");
            onVcsCommit = load.fun("Music/Orcs/Batrider/bat_7.wav");
            onVcsPush = load.fun("Music/Orcs/Batrider/bat_8.wav");
            onVcsPushFail = load.fun("Music/Orcs/Pandaren/ravio_17.wav");
            onVcsUpdate = load.fun("Music/Orcs/Batrider/bat_11.wav");
            gameover = load.fun("/Music/Orcs/Mechanic/mechanic_17.wav");
        } else if (race == Race.SCOURGE) {
            background = load.fun("/Music/Soundtracks/pcm_scourge.wav");
            compSucc = load.fun("Music/Orcs/Grunt/grunt_16.wav");
            compFail = load.fun("Music/Orcs/Mechanic/mechanic_27.wav");
            onUnitTestSucc = load.fun("Music/Orcs/Mechanic/mechanic_7.wav");
            onUnitTestFail = load.fun("Music/Orcs/Drek'tar/drek_13.wav");
            onVcsCommit = load.fun("Music/Orcs/Batrider/bat_7.wav");
            onVcsPush = load.fun("Music/Orcs/Batrider/bat_8.wav");
            onVcsPushFail = load.fun("Music/Orcs/Pandaren/ravio_17.wav");
            onVcsUpdate = load.fun("Music/Orcs/Batrider/bat_11.wav");
            gameover = load.fun("/Music/Orcs/Mechanic/mechanic_17.wav");
        } else {
            background = load.fun("/Music/Soundtracks/pcm_elves.wav");
            compSucc = load.fun("Music/Elves/Hippogryph/hippo1.wav");
            compFail = load.fun("Music/Elves/RavenDruid/druid7.wav");
            onUnitTestSucc = load.fun("Music/Orcs/Mechanic/mechanic_7.wav");
            onUnitTestFail = load.fun("Music/Orcs/Drek'tar/drek_13.wav");
            onVcsCommit = load.fun("Music/Orcs/Batrider/bat_7.wav");
            onVcsPush = load.fun("Music/Orcs/Batrider/bat_8.wav");
            onVcsPushFail = load.fun("Music/Orcs/Pandaren/ravio_17.wav");
            onVcsUpdate = load.fun("Music/Orcs/Batrider/bat_11.wav");
            gameover = load.fun("/Music/Orcs/Mechanic/mechanic_17.wav");
        }
    }

    @NotNull
    private static byte[] loadBytes(String fileName) {
        try {
            InputStream inputStream =
                    Playlist.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) throw new RuntimeException("Cannot find " + fileName);
            return StreamUtil.loadFromStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Race getCurrentRace() {
        return currentRace;
    }
}

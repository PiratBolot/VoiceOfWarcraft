package WarcraftVoice;

import WarcraftVoice.listeners.*;
import WarcraftVoice.sounds.Sound;
import WarcraftVoice.sounds.Sounds;

import java.util.HashMap;
import java.util.Map;

public class ActionListeningSoundPlayer implements
        Compilation.Listener, Refactoring.Listener, UnitTests.Listener, VcsActions.Listener, AllActions.Listener {

    private final Sounds sounds;
    private final Listener listener;
    private final Map<String, Sound> soundsByAction;
    private final Map<String, Sound> soundsByRefactoring;
    private boolean compilationFailed;
    private boolean stopped;


    public ActionListeningSoundPlayer(Sounds sounds, Listener listener) {
        this.sounds = sounds;
        this.listener = listener;
        this.soundsByAction = editorSounds(sounds);
        this.soundsByRefactoring = refactoringSounds(sounds);
    }

    public ActionListeningSoundPlayer init() {
        sounds.alliance.playInBackground();
        return this;
    }

    public void stop() {
        if (stopped) return;
        stopped = true;
        sounds.alliance.stop();
        sounds.orcs.stop();
//        sounds.gameover.play();
    }

    public void stopAndWait() {
        if (stopped) return;
        stopped = true;
        sounds.alliance.stop();
        sounds.orcs.stop();
//        sounds.gameover.playAndWait();
    }

    @Override public void onAction(String actionId) {
        Sound sound = soundsByAction.get(actionId);
        if (sound != null) {
            sound.play();
        } else {
            listener.unmappedAction(actionId);
        }
    }

    @Override public void onRefactoring(String refactoringId) {
        Sound sound = soundsByRefactoring.get(refactoringId);
        if (sound != null) {
            sound.play();
        } else {
//            sounds.coin.play();
            listener.unmappedRefactoring(refactoringId);
        }
    }

    @Override public void compilationSucceeded() {
//        sounds.oneUp.play();
        if (compilationFailed) {
            compilationFailed = false;
            sounds.alliance.playInBackground();
            sounds.orcs.stop();
        }
    }

    @Override public void compilationFailed() {
//        sounds.oneDown.play();
        if (!compilationFailed) {
            compilationFailed = true;
            sounds.orcs.playInBackground();
            sounds.alliance.stop();
        }
    }

    @Override public void onUnitTestSucceeded() {
//        sounds.oneUp.play();
    }

    @Override public void onUnitTestFailed() {
//        sounds.oneDown.play();
    }

    @Override public void onVcsCommit() {
//        sounds.powerupAppears.play();
    }

    @Override public void onVcsUpdate() {
//        sounds.powerup.play();
    }

    @Override public void onVcsPush() {
//        sounds.powerup.play();
    }

    @Override public void onVcsPushFailed() {
//        sounds.oneDown.play();
    }

    private static Map<String, Sound> refactoringSounds(Sounds sounds) {
        Map<String, Sound> result = new HashMap<String, Sound>();
//        result.put("refactoring.rename", sounds.coin);
//        result.put("refactoring.extractVariable", sounds.coin);
//        result.put("refactoring.extract.method", sounds.coin);
//        result.put("refactoring.inline.local.variable", sounds.coin);
//        result.put("refactoring.safeDelete", sounds.coin);
//        result.put("refactoring.introduceParameter", sounds.coin);
        return result;
    }

    private static Map<String, Sound> editorSounds(Sounds sounds) {
        Map<String, Sound> result = new HashMap<String, Sound>();



        return result;
    }


    public interface Listener {
        void unmappedAction(String actionId);

        void unmappedRefactoring(String refactoringId);
    }
}
package WarcraftVoice.sounds;

public class SilentSound extends Sound {
    private final Listener listener;

    public SilentSound(byte[] bytes, String name, Listener listener) {
        super(bytes, name);
        this.listener = listener;
    }

    @Override
    public Sound play() {
        listener.playing(name);
        return this;
    }

    @Override
    public Sound playAndWait() {
        listener.playing(name);
        return this;
    }

    @Override
    public Sound playInBackground() {
        listener.playing(name);
        return this;
    }

    @Override
    public Sound stop() {
        listener.stopped("stopped: " + name);
        return this;
    }

    @Override
    public String toString() {
        return "SilentLogSound{name='" + name + '\'' + '}';
    }

    public interface Listener {
        void playing(String soundName);
        void stopped(String soundName);
    }
}

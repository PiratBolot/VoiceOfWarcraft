package WarcraftVoice.sounds;

public class SilentSound extends Sound {
    private final Listener listener;

    public SilentSound(byte[] bytes, String name, Listener listener) {
        super(bytes, name);
        this.listener = listener;
    }

    @Override
    public Sound play() {
        listener.playing(getName());
        return this;
    }

    @Override
    public Sound playAndWait() {
        listener.playing(getName());
        return this;
    }

    @Override
    public Sound playInBackground() {
        listener.playing(getName());
        return this;
    }

    @Override
    public Sound stop() {
        listener.stopped("stopped: " + getName());
        return this;
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return "SilentLogSound{name='" + getName() + '\'' + '}';
    }

    public interface Listener {
        void playing(String soundName);
        void stopped(String soundName);
    }
}

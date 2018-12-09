package sounds;

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

    public interface Listener {
        Listener none = new Listener() {
            @Override public void playing(String soundName) {}
            @Override public void stopped(String soundName) {}
        };

        void playing(String soundName);
        void stopped(String soundName);
    }

    @Override
    public String toString() {
        return "SilentLogSound{name='" + getName() + '\'' + '}';
    }


}

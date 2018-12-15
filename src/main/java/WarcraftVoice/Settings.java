package WarcraftVoice;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("UnusedDeclaration")
@State(name = "WarcraftVoiceConfig", storages = {@Storage(value = "$APP_CONFIG$/warcraft_config.xml")})
public class Settings implements PersistentStateComponent<Settings> {
    public Boolean pluginEnabled = true;

    public static Settings getInstance() {
        return ServiceManager.getService(Settings.class);
    }

    @Nullable @Override public Settings getState() {
        return this;
    }

    @Override public void loadState(Settings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public boolean isPluginEnabled() {
        return pluginEnabled;
    }

    public void setPluginEnabled(Boolean pluginEnabled) {
        this.pluginEnabled = pluginEnabled;
    }
}

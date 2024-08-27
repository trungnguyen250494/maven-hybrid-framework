package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:environmentConfigs/${envName}.properties" })
public interface EnvironmentConfig extends Config {
    @Key("App.Url")
    String appUrl();

    @Key("App.Username")
    String appUsername();

    @Key("App.Password")
    String appPassword();
}

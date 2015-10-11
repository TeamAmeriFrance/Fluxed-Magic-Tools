package amerifrance.fluxedmagictools;

import lombok.Getter;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    private static Configuration config;

    @Getter private static boolean loggingEnabled;

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;

        category = "General";
        config.addCustomCategoryComment(category, "General settings");
        loggingEnabled = config.getBoolean("enableLogging", category, true, "Allows logging of additional information. Severe errors will bypass this.");

        config.save();
    }
}

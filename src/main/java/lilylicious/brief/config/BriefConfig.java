package lilylicious.brief.config;

import lilylicious.brief.utils.BriefLogger;
import net.minecraftforge.common.config.Configuration;
import scala.Int;

import java.io.File;

public class BriefConfig {

    public static boolean enableDebugLog;

    public static void init(File configFile) {
        Configuration config = new Configuration(configFile);
        try {
            config.load();

            //Misc
            enableDebugLog = config.getBoolean("DebugLog", "misc", false, "Enables debug logging");

            //Difficulty
            //defaultAOEMode = config.getBoolean("AOEMode", "difficulty", false, "Make AOEMode default for both fishstoppers");

            //Values (default, min, max)
            //highlightRadius = config.getInt("highlightRadius", "values", 5, 1, 64, "Radius of highlight");


            BriefLogger.logInfo("Loaded configuration file.");
        } catch (Exception e) {
            BriefLogger.logFatal("Caught exception while loading config file!");
            e.printStackTrace();
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
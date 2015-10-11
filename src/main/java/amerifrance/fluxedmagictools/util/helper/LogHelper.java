package amerifrance.fluxedmagictools.util.helper;

import amerifrance.fluxedmagictools.ConfigHandler;
import amerifrance.fluxedmagictools.FluxedMagicTools;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {

    private static Logger logger = LogManager.getLogger(FluxedMagicTools.getMODID());

    /**
     * @param info - String to log to the info level
     */

    public static void info(Object info) {
        if (ConfigHandler.isLoggingEnabled())
            logger.info(info);
    }

    /**
     * @param error - String to log to the error level
     */

    public static void error(Object error) {
        if (ConfigHandler.isLoggingEnabled())
            logger.error(error);
    }

    /**
     * @param debug - String to log to the debug level
     */

    public static void debug(Object debug) {
        if (ConfigHandler.isLoggingEnabled())
            logger.debug(debug);
    }

    public static Logger getLogger() {
        return logger;
    }
}

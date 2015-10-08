package alexndr.api.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import alexndr.api.core.APISettings;

/**
 * @author AleXndrTheGr8st
 */
public class LogHelper{
	private static Logger logger;
	
	private static boolean verbose;
	
	/**
	 * Sets up the logger, setting the verbose setting to depend on a toggle.
	 * Only needs to be called by SimpleCore API, after APISettings is loaded.
	 */
	public static void loggerSetup() {
		verbose = APISettings.verboseLogging.asBoolean();
	}
	
	/**
	 * Gets the Logger currently in use.
	 * @return Logger in use
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Logs a message of Level "INFO" under the default Logger name.
	 * If using default LogHelper, default Logger name is "SimpleCore".
	 * @param message String that will be output to the console
	 */
	public static void info(String message) {
		logger = LogManager.getLogger("SimpleCore");
		logger.log(Level.INFO, message);
	}
	
	/**
	 * Logs a message of Level "INFO" under the specified Logger name.
	 * @param loggerName String that becomes the Logger name
	 * @param message String that will be output to the console
	 */
	public static void info(String loggerName, String message) {
		logger = LogManager.getLogger(loggerName);
		logger.log(Level.INFO, message);
	}
	
	/**
	 * Logs a message of Level "WARN" under the default Logger name.
	 * If using default LogHelper, default Logger name is "SimpleCore".
	 * @param message String that will be output to the console
	 */
	public static void warning(String message) {
		logger = LogManager.getLogger("SimpleCore");
		logger.log(Level.WARN, message);
	}
	
	/**
	 * Logs a message of Level "WARN" under the specified Logger name.
	 * @param loggerName String that becomes the Logger name
	 * @param message String that will be output to the console
	 */
	public static void warning(String loggerName, String message) {
		logger = LogManager.getLogger(loggerName);
		logger.log(Level.WARN, message);
	}
	
	/**
	 * Logs a message of Level "ERROR" under the default Logger name.
	 * If using default LogHelper, default Logger name is "SimpleCore".
	 * @param message String that will be output to the console
	 */
	public static void severe(String message) {
		logger = LogManager.getLogger("SimpleCore");
		logger.log(Level.ERROR, message);
	}
	
	/**
	 * Logs a message of Level "ERROR" under the specified Logger name.
	 * @param loggerName String that becomes the Logger name
	 * @param message String that will be output to the console
	 */
	public static void severe(String loggerName, String message) {
		logger = LogManager.getLogger(loggerName);
		logger.log(Level.ERROR, message);
	}
	
	/**
	 * Logs a verbose message under the default Logger name.
	 * Verbose messages only show in the console if Verbose Logging is enabled.
	 * If using default LogHelper, default Logger name is "SimpleCore".
	 * @param message String that will be output to the console
	 */
	public static void verbose(String message) {
		if(verbose) {
			logger = LogManager.getLogger("SimpleCore");
			logger.log(Level.INFO, message);
		}
	}
	
	/**
	 * Logs a verbose message under the specified Logger name.
	 * Verbose messages only show in the console if Verbose Logging is enabled.
	 * @param loggerName String that becomes the Logger name
	 * @param message String that will be output to the console
	 */
	public static void verbose(String loggerName, String message) {
		if(verbose) {
			logger = LogManager.getLogger(loggerName);
			logger.log(Level.INFO, message);
		}
	}
	
	/**
	 * Logs a verbose Exception.
	 * This allows the stack trace to be printed only if Verbose Logging is enabled.
	 * @param e Exception to be verbosely printed.
	 */
	public static void verboseException(Exception e) {
		if(verbose) {
			e.printStackTrace();
		}
	}
}
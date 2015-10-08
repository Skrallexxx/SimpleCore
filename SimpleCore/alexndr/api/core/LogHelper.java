package alexndr.api.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author AleXndrTheGr8st
 */
public class LogHelper 
{
	private static Logger logger;
	
	/**
	 * Logs a message of Level "INFO" under log name "SimpleCore".
	 * @param message String that will be output to the console.
	 */
	public static void info(String message)
	{
		logger = LogManager.getLogger("SimpleCore");
		logger.log(Level.INFO, message);
	}
	
	/**
	 * Logs a message of Level "INFO" under the specified log name (loggerName).
	 * @param loggerName String that becomes the log name.
	 * @param message String that will be output to the console.
	 */
	public static void info(String loggerName, String message)
	{
		logger = LogManager.getLogger(loggerName);
		logger.log(Level.INFO, message);
	}
	
	/**
	 * Logs a message of Level "WARN" under log name "SimpleCore".
	 * @param message String that will be output to the console.
	 */
	public static void warning(String message)
	{
		logger = LogManager.getLogger("SimpleCore");
		logger.log(Level.WARN, message);
	}
	
	/**
	 * Logs a message of Level "WARN" under the specified log name (loggerName).
	 * @param loggerName String that becomes the log name.
	 * @param message String that will be output to the console.
	 */
	public static void warning(String loggerName, String message)
	{
		logger = LogManager.getLogger(loggerName);
		logger.log(Level.WARN, message);
	}
	
	/**
	 * Logs a message of Level "ERROR" under log name "SimpleCore".
	 * @param message String that will be output to the console.
	 */
	public static void severe(String message)
	{
		logger = LogManager.getLogger("SimpleCore");
		logger.log(Level.ERROR, message);
	}
	
	/**
	 * Logs a message of Level "ERROR" under the specified log name (loggerName).
	 * @param loggerName String that becomes the log name.
	 * @param message String that will be output to the console.
	 */
	public static void severe(String loggerName, String message)
	{
		logger = LogManager.getLogger(loggerName);
		logger.log(Level.ERROR, message);
	}
	
	/**
	 * Logs a message of Level "INFO" under log name "SimpleCore" ONLY IF verbose logging is enabled in "API Settings.cfg".
	 * @param message String that will be output to the console.
	 */
	public static void verboseInfo(String message)
	{
		logger = LogManager.getLogger("SimpleCore");
		if(APISettings.enableVerboseLogging)
		{
			logger.log(Level.INFO, message);
		}
	}
	
	/**
	 * Logs a message of Level "INFO" under the specified log name (loggerName) ONLY IF verbose logging is enabled in "API Settings.cfg".
	 * @param loggerName String that becomes the log name.
	 * @param message String that will be output to the console.
	 */
	public static void verboseInfo(String loggerName, String message)
	{
		logger = LogManager.getLogger(loggerName);
		if(APISettings.enableVerboseLogging)
		{
			logger.log(Level.INFO, message);
		}
	}
	
	/**
	 * Logs a message of Level "WARN" under log name "SimpleCore" ONLY IF verbose logging is enabled in "API Settings.cfg".
	 * @param message String that will be output to the console.
	 */
	public static void verboseWarning(String message)
	{
		logger = LogManager.getLogger("SimpleCore");
		if(APISettings.enableVerboseLogging)
		{
			logger.log(Level.WARN, message);
		}
	}
	
	/**
	 * Logs a message of Level "INFO" under the specified log name (loggerName) ONLY IF verbose logging is enabled in "API Settings.cfg".
	 * @param loggerName String that becomes the log name.
	 * @param message String that will be output to the console.
	 */
	public static void verboseWarning(String loggerName, String message)
	{
		logger = LogManager.getLogger(loggerName);
		if(APISettings.enableVerboseLogging)
		{
			logger.log(Level.WARN, message);
		}
	}
}

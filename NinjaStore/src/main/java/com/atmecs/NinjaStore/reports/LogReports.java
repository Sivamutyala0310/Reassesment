package com.atmecs.NinjaStore.reports;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.atmecs.NinjaStore.constants.ConstantFilePaths;

	/**
	 * 
	 * creating reports  for the test script.
	 *
	 */

public class LogReports 
{
		Logger logger = null;

		public LogReports() {
			getlogger();
			logger = Logger.getLogger(LogReports.class.getName());
		}

		public void getlogger() {
			PropertyConfigurator.configure(ConstantFilePaths.LOG4J_FILE);
		}

		public void info(String message) {
			logger.info(message);
		}

		public void info(int length)
		{
			logger.info(length);
			
		}
	}



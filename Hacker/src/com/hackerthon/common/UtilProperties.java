package com.hackerthon.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;


public class UtilProperties {

	public static final Properties p = new Properties();

	static {
		try {
			p.load(UtilQuery.class.getResourceAsStream(Constants.PROPERTIESFILE_PATH));
		} catch (Exception e) {
			
		}
	}
}

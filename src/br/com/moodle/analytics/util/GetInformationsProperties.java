/**
 * 
 */
package br.com.moodle.analytics.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Pedro
 * This class provide informations from config.properties.
 * This file contains informations about database 
 * and other static values used in severals parts this App
 */
public class GetInformationsProperties {
	/**
	 * properties used this class
	 */
	private static Properties prop = new Properties();
	
	public String getPropertyValue(String key)
	{
		try {
		FileInputStream ip = new FileInputStream("config.properties");
		prop.load(ip);
		return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Properties setPropertiesDatabase()
	{
		Properties properties = new Properties();
		properties.setProperty("user", getPropertyValue("user"));
		properties.setProperty("password", getPropertyValue("password"));
		properties.setProperty("useSSL", getPropertyValue("useSSL"));
		properties.setProperty("useTimezone", getPropertyValue("useTimezone"));
		properties.setProperty("serverTimezone", getPropertyValue("serverTimezone"));
		properties.setProperty("autoReconnect", getPropertyValue("autoReconnect"));
		
		return properties;
	}
	
}

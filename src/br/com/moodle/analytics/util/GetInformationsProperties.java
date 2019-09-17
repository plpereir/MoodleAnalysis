/**
 * 
 */
package br.com.moodle.analytics.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
	
	public String getPropertyValue(InputStream in, String key)
	{
		try {
		if (in == null)
		{
		 FileInputStream f = new FileInputStream("config.properties");
			prop.load(f);
		} else
		{
			prop.load(in);
		}
		return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Properties setPropertiesDatabase(InputStream in)
	{
		Properties properties = new Properties();
		properties.setProperty("user", getPropertyValue(in,"user"));
		properties.setProperty("password", getPropertyValue(in,"password"));
		properties.setProperty("useSSL", getPropertyValue(in,"useSSL"));
		properties.setProperty("useTimezone", getPropertyValue(in,"useTimezone"));
		properties.setProperty("serverTimezone", getPropertyValue(in,"serverTimezone"));
		properties.setProperty("autoReconnect", getPropertyValue(in,"autoReconnect"));
		
		return properties;
	}
	
}
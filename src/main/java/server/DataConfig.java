package server;

import java.io.InputStream;
import java.util.Properties;


public class DataConfig {

	private static final String PROPERTIES_FILE = "properties"; 
	private static String mail; 
	private static String password; 
	private static String connectUrl; 
	private static String searchUrl; 
	
	
	public static void getInstanceConfig() {
		Properties properties = new Properties();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierConfiguration = classLoader.getResourceAsStream(PROPERTIES_FILE);

		if (fichierConfiguration == null) {
			System.out.println("The properties file " + PROPERTIES_FILE + " wasn't found." );
		}

		try {
			properties.load(fichierConfiguration);
			mail =  properties.getProperty("MAIL");
			password =  properties.getProperty("PASSWORD");
			connectUrl = properties.getProperty("CONNECT_URL");
			searchUrl = properties.getProperty("SEARCHPEOPLE_URL");
		}catch(Exception e) {
			e.getStackTrace();
		}
	}

	public static String getConnectUrl() {
		return connectUrl;
	}

	public static String getSearchUrl() {
		return searchUrl;
	}

	public static String getMail() {
		return mail;
	}

	public static String getPassword() {
		return password;
	}

	
}

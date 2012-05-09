package org.saiku.sdw.client.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientUtil {

	/**
	 * 
	 * @param url
	 * @return InputStream
	 */
	public InputStream executeGetMethod(URL url) {
		try{
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
	
			return conn.getInputStream();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}

}

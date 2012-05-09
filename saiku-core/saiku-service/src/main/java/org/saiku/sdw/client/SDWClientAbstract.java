package org.saiku.sdw.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.saiku.sdw.client.util.HttpClientUtil;

public abstract class SDWClientAbstract implements SDWMetadataClient{
	
	/**
	 * 
	 * @param url
	 * @return  XML result in ByteArrayInputStream format.
	 */
	public InputStream deriveInputStreamFrom(URL url) {
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		try{
			
			is = httpClientUtil.executeGetMethod(url);
			
			StringBuilder stringBuilder = new StringBuilder();
			
			isr = new InputStreamReader(is);
			in = new BufferedReader(isr);
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				stringBuilder.append(inputLine);
			
			return new ByteArrayInputStream(stringBuilder.toString().getBytes());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(is != null)is.close();
			} catch (IOException e) {e.printStackTrace();}
			try {
				if(isr != null)isr.close();
			} catch (IOException e) {e.printStackTrace();}
			try {
				if(in != null)in.close();
			} catch (IOException e) {e.printStackTrace();}
		}
		
		return null;
	}
	
}

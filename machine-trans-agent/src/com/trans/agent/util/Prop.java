package com.trans.agent.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;



public class Prop{
	private Properties prop;

	public Prop(String path) throws IOException{
		prop = load(path);
	}

	public Prop(URL url) throws IOException, URISyntaxException{
		prop = load(url);
	}

	public Properties load(URL url) throws IOException, URISyntaxException {
		Properties mainProperties = new Properties();
		FileInputStream file;
		file = new FileInputStream(new File(url.toURI()));
		mainProperties.load(file);
		file.close();
		return mainProperties;
	}

	public Properties load(String path) throws IOException {
		Properties mainProperties = new Properties();
		FileInputStream file;
		file = new FileInputStream(path);
		mainProperties.load(new InputStreamReader(file, Charset.forName("UTF-8")));
		file.close();
		return mainProperties;
	}

	public void clear(){
		prop.clear();
	}

	public String get(String key){
		String v = prop.getProperty(key);
		if(v==null)
			return null;

		if(v.isEmpty())
			return null;

		return v;
	}

	public int getInt(String key){
		String v = get(key);
		if(v==null)
			return 0;

		return Integer.parseInt(v);
	}

	public float getFloat(String key){
		String v = get(key);
		if(v==null)
			return 0;

		return Float.parseFloat(v);
	}

	public double getDouble(String key){
		String v = get(key);
		if(v==null)
			return 0;

		return Double.parseDouble(v);
	}

	public Properties getProp() {
		return prop;
	}
}


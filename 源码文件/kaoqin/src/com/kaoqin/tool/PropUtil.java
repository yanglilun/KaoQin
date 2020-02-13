package com.kaoqin.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropUtil {

	public static Properties getProp() throws IOException {
		Properties prop = new Properties();
		InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(inputStream);
		return prop;
	}
}

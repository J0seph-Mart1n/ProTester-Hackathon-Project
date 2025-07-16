package com.test.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {
	public Properties readProperty(String filepath) throws IOException {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(filepath);
		props.load(fis);
		return props;
	}
}

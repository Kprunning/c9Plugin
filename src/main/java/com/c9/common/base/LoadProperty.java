package com.c9.common.base;

import java.io.*;
import java.util.Properties;


public class LoadProperty {
	/**
	 * 获取配置文件
	 * @param path
	 * @return 
	 */
	public Properties loadPro(String path)  {
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			properties.load(reader);
		} catch (Exception e) {
			System.out.println("配置文件加载失败");
		}
		return properties;
	}
}

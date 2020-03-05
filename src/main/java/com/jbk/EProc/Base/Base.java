package com.jbk.EProc.Base;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

public class Base {
	
	public static Properties prop;
	protected String currDir;
	Logger log;

	@BeforeSuite
	public void loadProperties() {
		
		//log4j
		/*log = Logger.getLogger("Base"); 
		 PropertyConfigurator.configure("log4j.properties");*/
		 
		//loadProperties();
		prop = new Properties();
		try {
			currDir = System.getProperty("user.dir");
			System.out.println("currDir" + currDir);
			FileInputStream fis = new FileInputStream(
					currDir+"/src/resource/java/com/jbk/EProc/Properties/config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


}

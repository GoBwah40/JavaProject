package com.devoir;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class mainRename {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String pathFile = "C:\\eclipse\\workspace_eclipse\\Devoir001\\config_rename.properties";
		FileInputStream in;
		in = new FileInputStream(pathFile);
		Properties prop = new Properties();
		prop.load(in);
		RenameMe tp1 = new RenameMe(prop);
		getProperties(prop);
		tp1.renameAll("tests");
		tp1.undoRenameAll("tests");
	}
	
	
	public static void getProperties (Properties prop) {
		System.out.print("Prefix => " + prop.getProperty("PREFIX"));
		System.out.print("     <============>     ");
		System.out.println("Suffix => " + prop.getProperty("SUFFIX"));
	}
}

package com.devoir;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class RenameMe {

	Properties property;
	String prefix = "XXX_";
	String suffix = "_XXX";
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	//Constructor
	public RenameMe(Properties prop) throws IOException {
		property = prop;
		prefix = setPrefix();
		suffix = setSuffix();
	}
	
	//Constructor
	public RenameMe() {}
	
	/**
	 * Set attribut PREFIX
	 * @return PREFIX
	 * @throws IOException
	 */
	public String setPrefix() throws IOException {
		String pathFile = "C:\\eclipse\\workspace_eclipse\\Devoir001\\";
		String file = "config_rename.properties";
		FileInputStream in;
		in = new FileInputStream(pathFile + File.separator + file);
		this.property.load(in);
		in.close();

		String valeur = property.getProperty("PREFIX");
		return valeur;
	}
	
	/**
	 * Set attribut SUFFIX
	 * @return SUFFIX
	 * @throws IOException
	 */
	public String setSuffix() throws IOException {
		String pathFile = "C:\\eclipse\\workspace_eclipse\\Devoir001\\";
		String file = "config_rename.properties";
		FileInputStream in;
		in = new FileInputStream(pathFile + File.separator + file);
		this.property.load(in);
		in.close();

		String valeur = property.getProperty("SUFFIX");
		return valeur;
	}
	
	//============================= Getters/Setters/Constructor==================>

	/**
	 * Rename all files with properties PREFIX and SUFFIX in directory 
	 * @param dossier directory in which one want to rename all files
	 * @throws IOException
	 */
	public void renameAll(String dossier) throws IOException {
		File mainFolder = new File(dossier);
		String pathFile = "C:\\eclipse\\workspace_eclipse\\Devoir001\\";

		List<File> files = (List<File>) FileUtils.listFiles(new File(mainFolder.getAbsolutePath()),
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			File newFile = new File(renameFile(mainFolder.getName(), pathFile, file.getName()));
			file.renameTo(newFile);
		}
	}

	/**
	 * Add prefix and suffix to file's name
	 * @param mainFolder mainFile folder with files who want to modif
	 * @param pathFile pathFile path to the folder
	 * @param file file file who want to add prefix and suffix
	 * @return the name of the file with prefix and suffix
	 */
	public String renameFile(String mainFolder, String pathFile, String file) {
		String name = File.separator + prefix + getOnlyName(file) + suffix;
		String ext = "." + FilenameUtils.getExtension(file);
		String newFileName = pathFile + mainFolder + name + ext;
		return newFileName;
	}

	/**
	 * Rename all files in directory with properties PREFIX and SUFFIX
	 * @param dossier directory in which one want to rename all files
	 */
	public void undoRenameAll(String dossier) {
		File mainFolder = new File(dossier);
		String pathFile = "C:\\eclipse\\workspace_eclipse\\Devoir001\\";

		List<File> files = (List<File>) FileUtils.listFiles(new File(mainFolder.getAbsolutePath()),
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			File newFile = new File(replacePrefixSuffix(mainFolder.getName(), pathFile, file.getName()));
			file.renameTo(newFile);
		}
	}

	/**
	 * Retire prefix and suffix
	 * @param mainFile folder with files who want to modif
	 * @param pathFile path to the folder
	 * @param file file who want to retire prefix and suffix
	 * @return the name of the file without prefix and suffix
	 */
	public String replacePrefixSuffix(String mainFile, String pathFile, String file) {
		String ext = "." + FilenameUtils.getExtension(file);
		String name = getOnlyName(file);
		while (name.contains(prefix) && name.contains(suffix)) {
			name = renameFile(name);
		}
		String newFileName =  name + ext;
		return newFileName;
	}

	/**
	 * Replace PREFIX and SUFFIX by void
	 * @param name file who want to rename
	 * @return
	 */
	private String renameFile(String name) {
		name = name.replace(prefix, "");
		name = name.replace(suffix, "");
		return name;
	}

	/**
	 * Give ton name of a file without his extension
	 * @param str name of file
	 * @return the file's name without the extension
	 */
	private String getOnlyName(String str) {

		if (str == null)
			return null;
		int pos = str.lastIndexOf(".");
		if (pos == -1)
			return str;
		return str.substring(0, pos);
	}

}

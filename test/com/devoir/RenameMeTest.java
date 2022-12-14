package com.devoir;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;


class RenameMeTest {
	
	private RenameMe renameMe;
	private String mainFolder;
	private String pathFile;

	@BeforeEach
	void setUp() throws Exception {
		
		renameMe = new RenameMe();
		renameMe.setPrefix("toto_");
		renameMe.setSuffix("_titi");
		mainFolder = "dossier";
		pathFile = "C:\\eclipse\\workspace_eclipse\\Devoir001\\";
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testRenameFile() {
		final String goodnewname = pathFile + mainFolder + File.separator + "toto_totofaitduvelo_titi.txt";
		String newName = renameMe.renameFile(mainFolder, pathFile, "totofaitduvelo.txt");
		assertThat(newName, equalTo(goodnewname));
	}
	
	@Test
	void testReplacePrefixSuffix() {
		final String goodnewname = pathFile + mainFolder + File.separator + "totofaitduvelo.txt";
		String newNameWithModif = renameMe.renameFile(mainFolder, pathFile, "totofaitduvelo.txt");
		String newName = renameMe.replacePrefixSuffix(mainFolder, pathFile, newNameWithModif);
		assertThat(newName, equalTo(goodnewname));
	}

}

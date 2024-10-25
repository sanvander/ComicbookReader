package org.ComicReaderSander.Model;

import java.io.File;

public class ComicCBR extends Comic {

	public ComicCBR(int ComicID, String Name, int NumberOfPages, int DisplayedPage, String FilePath) {
		super(ComicID, Name, NumberOfPages, DisplayedPage, FilePath, "CBR");
	}

	public void parseComic() {

	}

	public void extractToDirectory(File file, File destDir) {
	}

}

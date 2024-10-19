package org.ComicReaderSander.Model;
import java.io.File;
import java.util.Random;

public abstract class Comic {

	private int comicID;

	private String name;

	private int numberOfPages;

	private int displayedPage;

	private File filePath;

	public Comic(int ComicID, String Name, int NumberOfPages, int DisplayedPage, File FilePath) {
		this.comicID = ComicID;
		this.name = Name;
		this.numberOfPages = NumberOfPages;
		this.displayedPage = DisplayedPage;
		this.filePath = FilePath;
	}

	public void openComic(Comic comic) {

	}

	public static int GenerateComicID() {
		Random rand = new Random();
		return rand.nextInt(1000000);
	}

	public abstract void parseComic();

}

package org.ComicReaderSander.Model;
import java.io.File;
import java.util.Random;

public abstract class Comic {

	private int comicID;

	private String name;

	private int numberOfPages;

	private int displayedPage;

	private File filePath;

	private String fileType;

	public Comic(int ComicID, String Name, int NumberOfPages, int DisplayedPage, File FilePath, String FileType) {
		this.comicID = ComicID;
		this.name = Name;
		this.numberOfPages = NumberOfPages;
		this.displayedPage = DisplayedPage;
		this.filePath = FilePath;
		this.fileType = FileType;
	}

	public int getComicID() {
		return comicID;
	}

	public void setComicID(int comicID) {
		this.comicID = comicID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getDisplayedPage() {
		return displayedPage;
	}

	public void setDisplayedPage(int displayedPage) {
		this.displayedPage = displayedPage;
	}

	public File getFilePath() {
		return filePath;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void openComic(Comic comic) {

	}

	public static int GenerateComicID() {
		Random rand = new Random();
		return rand.nextInt(1000000);
	}

	public abstract void parseComic();
	public abstract void extractToDirectory(File file, File destDir);

}

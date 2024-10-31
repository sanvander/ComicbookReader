package org.ComicReaderSander.Model;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Random;

public abstract class Comic {

	private Integer comicID;

	private String name;

	private int numberOfPages;

	private int displayedPage;

	private String filePath;

	private String fileType;


	public Comic(Integer ComicID, String Name, int NumberOfPages, int DisplayedPage, String FilePath, String FileType) {
		this.comicID = ComicID;
		this.name = Name;
		this.numberOfPages = NumberOfPages;
		this.displayedPage = DisplayedPage;
		this.filePath = FilePath;
		this.fileType = FileType;
	}

	public Comic(String Name, int NumberOfPages, int DisplayedPage, String FilePath, String FileType) {
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public abstract void parseComic();
	public abstract void extractToDirectory(File file, File destDir);
	public abstract List<BufferedImage> getFrames(Comic comic);

}

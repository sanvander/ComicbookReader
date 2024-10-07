package org.ComicReaderSander.Model;

public abstract class Comic {

	private int comicID;

	private String name;

	private int numberOfPages;

	private int displayedPage;

	private String filePath;

	public void openComic(Comic comic) {

	}

	public abstract void parseComic();

}

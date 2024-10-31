package org.ComicReaderSander.Controller;

import org.ComicReaderSander.Model.*;
import org.ComicReaderSander.View.SimpleComicUI;

import java.awt.image.BufferedImage;
import java.util.List;

public class Controller {
	IComicLibraryServices comicLibrary;
	IFileImporterServices fileImporter;
	private final SimpleComicUI view;

	public Controller(SimpleComicUI view, IComicLibraryServices comicLibrary, FileImporter fileImporter) {
		this.view = view;
		this.comicLibrary = comicLibrary;
		this.fileImporter = fileImporter;
	}

	public void handleOpenComic(Comic comic) {
		List<BufferedImage> frames = comic.getFrames(comic);
		view.showComicReader(comic, frames, 0);
	}

	public void handleNextPage(Comic comic) {
		try {
			List<BufferedImage> frames = comic.getFrames(comic);
			comic.setDisplayedPage(comic.getDisplayedPage() + 1);
			view.showComicReader(comic, frames, comic.getDisplayedPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlePreviousPage(Comic comic) {
		try {
			List<BufferedImage> frames = comic.getFrames(comic);
			comic.setDisplayedPage(comic.getDisplayedPage() - 1);
			view.showComicReader(comic, frames, comic.getDisplayedPage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void handleDeleteAllComics() { //TODO: implement handleDeleteAllComics
		comicLibrary.deleteAllComics();
		view.showHomescreen();
	}

	public void handleCloseReader() {
		view.showHomescreen();
	}

	public void handleImportComic(){
		fileImporter.importFile();
		view.showHomescreen();

	}

	public List<Comic> handleGetAllComics(){
		return comicLibrary.getAllComics();
	}

	public void handleDeleteComic(Comic comic){
		comicLibrary.deleteComic(comic);
		view.showHomescreen();
	}
}

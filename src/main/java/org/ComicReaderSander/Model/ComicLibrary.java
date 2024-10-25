package org.ComicReaderSander.Model;


import java.util.ArrayList;
import java.util.List;

public class ComicLibrary implements IComicLibraryServices {


	public List<Comic> getAllComics() {
		List<Comic> allComics = new ArrayList<Comic>();
        allComics.addAll(ComicNHLComicRepo.getAllComics());
		allComics.addAll(ComicCBZRepo.getAllComics());
		allComics.addAll(ComicCBRRepo.getAllComics());
		return allComics;
	}




	public void deleteComic() {

	}

}

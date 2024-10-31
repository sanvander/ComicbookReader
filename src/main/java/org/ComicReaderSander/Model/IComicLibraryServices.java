package org.ComicReaderSander.Model;

import java.util.List;

public interface IComicLibraryServices {

    List<Comic> getAllComics();
    void deleteAllComics();
    void deleteComicFromDB(Comic comic);
    void deleteComic(Comic comic);
}

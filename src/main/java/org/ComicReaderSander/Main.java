package org.ComicReaderSander;

import org.ComicReaderSander.Model.ComicLibrary;
import org.ComicReaderSander.Model.*;
import org.ComicReaderSander.Model.SQLite;
import org.ComicReaderSander.View.SimpleComicUI;
import org.ComicReaderSander.Model.ComicNHLComic;


//TODO: voor alle comic repos een get all statement maken.

public class Main {

    public static void main(String[] args) {
        //Initialize the database
        ComicNHLComicRepo.createTable();
        ComicCBRRepo.createTable();
        ComicCBZRepo.createTable();


        ComicLibrary comicLibrary = new ComicLibrary();
        for (Comic comic : comicLibrary.getAllComics()) {
            System.out.println(comic.getName());
        }
        //ComicNHLComicRepo.getAllNHLComics();

        //SimpleComicUI UI = new SimpleComicUI();
        //UI.setVisible(true);
    }
}
package org.ComicReaderSander;

import org.ComicReaderSander.Model.ComicLibrary;
import org.ComicReaderSander.Model.*;
import org.ComicReaderSander.Model.SQLite;
import org.ComicReaderSander.View.SimpleComicUI;
import org.ComicReaderSander.Model.ComicNHLComic;
import org.ComicReaderSander.Controller.Controller;


public class Main {

    public static void main(String[] args) {
        //Initialize the database
        ComicNHLComicRepo.createTable();
        ComicCBRRepo.createTable();
        ComicCBZRepo.createTable();

        IComicLibraryServices comicLibrary = new ComicLibrary();
        SimpleComicUI view = new SimpleComicUI();
        FileImporter fileImporter = new FileImporter();
        Controller controller = new Controller(view, comicLibrary, fileImporter);
        view.setController(controller);

        view.showHomescreen();



    }
}
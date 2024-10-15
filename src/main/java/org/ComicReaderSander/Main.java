package org.ComicReaderSander;

import org.ComicReaderSander.Model.FileHandler;
import org.ComicReaderSander.View.SimpleComicUI;
import org.ComicReaderSander.Model.ComicGIF;


public class Main {

    public static void main(String[] args) {
        ComicGIF comic = new ComicGIF();
        //comic.parseComic();
        //comic.extractZip();
        FileHandler.importFile();
        FileHandler.createMapForComic("X-Men 005 (2024) (digital) (Marika-Empire).cbz");
        SimpleComicUI UI = new SimpleComicUI();
        UI.setVisible(true);
    }
}
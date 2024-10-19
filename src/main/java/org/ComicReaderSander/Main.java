package org.ComicReaderSander;

import org.ComicReaderSander.View.SimpleComicUI;
import org.ComicReaderSander.Model.ComicNHLComic;


public class Main {

    public static void main(String[] args) {
        // ComicNHLComic comic = new ComicNHLComic();
        //comic.parseComic();
        //comic.extractZip();
        //FileHandler.importFile();
        //FileHandler.createMapForComic("X-Men 005 (2024) (digital) (Marika-Empire).cbz");

        SimpleComicUI UI = new SimpleComicUI();
        UI.setVisible(true);
    }
}
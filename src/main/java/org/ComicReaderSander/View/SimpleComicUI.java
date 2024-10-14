package org.ComicReaderSander.View;
import javax.swing.*;
import java.awt.*;


public class SimpleComicUI extends JFrame implements IComicReaderUI {
	private String state;

	public SimpleComicUI(){
		//Define title and what to do on close
		setTitle("Comic Reader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set the size of the window to the size of the screen using Toolkit from awt
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);

		//Calling selfmade function to show the homescreen
		showHomescreen();





	}

	public void showHomescreen() {
		Homescreen homescreenPanel = new Homescreen();
		add(homescreenPanel);
		setVisible(true);
	}

	public void showComic() {

	}

	public void addComic() {

	}

	public void deleteComic() {

	}

	public void nextPage() {

	}

	public void previousPage() {

	}

	public void closeReader() {

	}

	public void showFileNotAcceptedPopup() {

	}

	public void showDeleteComicPopup() {

	}

}

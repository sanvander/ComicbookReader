package org.ComicReaderSander.View;
import org.ComicReaderSander.Controller.Controller;
import org.ComicReaderSander.Model.Comic;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class SimpleComicUI extends JFrame implements IComicReaderUI {
	private String state;
	private JPanel currentPanel;
	private Controller controller;

	public SimpleComicUI(){
		//Define title and what to do on close
		setTitle("Comic Reader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set the size of the window to the size of the screen using Toolkit from awt
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);

		//Calling selfmade function to show the homescreen
		//showHomescreen();



	}

	public void showHomescreen() {
		Homescreen homescreenPanel = new Homescreen(controller);
		add(homescreenPanel);
		setPanel(homescreenPanel);
	}

	public void showComicReader(Comic comic, List<BufferedImage> frames, int openedPage) {
		ComicReader comicReaderPanel = new ComicReader(controller, comic, frames, openedPage);
		add(comicReaderPanel);
		setPanel(comicReaderPanel);

	}


	public void setPanel(JPanel newPanel) {
		if (currentPanel != null) {
			remove(currentPanel);
		}
		currentPanel = newPanel;
		add(currentPanel);
		revalidate();
		repaint();
		setVisible(true);
	}

	public void reloadPanel() {
		currentPanel.revalidate();
		currentPanel.repaint();
		setVisible(true);
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

	public void setController(Controller controller) {
		this.controller = controller;
	}


}

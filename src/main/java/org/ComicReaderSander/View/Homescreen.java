package org.ComicReaderSander.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homescreen extends JPanel {

    private JPanel Homescreen;

    public Homescreen() {
        // Initializing homescreen

        setBackground(new Color(33,33,33,255));

        // Sets grid layout, 0 rows means dynamic amount of rows
        setLayout(new GridLayout(0, 8, 10, 10));

    }


}

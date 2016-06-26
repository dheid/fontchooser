package com.connectina.swing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class FontSelectionActionListener implements ActionListener, Serializable {

    private final FontChooser chooser;
    private Font font;

    public FontSelectionActionListener(FontChooser c) {
        chooser = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        font = chooser.getSelectedFont();
    }

    public Font getFont() {
        return font;
    }
}

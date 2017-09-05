package io.github.dheid.fontchooser.listeners;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

import io.github.dheid.fontchooser.FontContainer;


/**
 * Created by dheid on 4/1/17.
 */
public class StyleListSelectionListener implements ListSelectionListener {

    private final FontContainer fontContainer;

    public StyleListSelectionListener(FontContainer fontContainer) {
        this.fontContainer = fontContainer;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedStyle = fontContainer.getSelectedStyle();
            Font newFont = fontContainer.getSelectedFont().deriveFont(selectedStyle);
            fontContainer.setSelectedFont(newFont);
            fontContainer.setPreviewFont(fontContainer.getSelectedFont());
        }
    }
}

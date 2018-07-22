package org.drjekyll.fontchooser.listeners;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

import org.drjekyll.fontchooser.FontContainer;


/**
 * Created by dheid on 4/1/17.
 */
public class SizeListSelectionListener implements ListSelectionListener {

    private final FontContainer fontContainer;

    public SizeListSelectionListener(FontContainer fontContainer) {
        this.fontContainer = fontContainer;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            float newSize = fontContainer.getSelectedSize();
            Font newFont = fontContainer.getSelectedFont().deriveFont(newSize);
            fontContainer.setSelectedFont(newFont);
            fontContainer.setPreviewFont(fontContainer.getSelectedFont());
        }
    }
}

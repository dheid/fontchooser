package com.connectina.swing.listeners;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

import com.connectina.swing.FontContainer;


/**
 * Created by dheid on 4/1/17.
 */
public class FamilyListSelectionListener implements ListSelectionListener {

    private final FontContainer fontContainer;

    public FamilyListSelectionListener(FontContainer fontContainer) {
        this.fontContainer = fontContainer;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Font sel = new Font(
                fontContainer.getSelectedFamily(),
                fontContainer.getSelectedStyle(),
                (int)fontContainer.getSelectedSize());

            fontContainer.setSelectedFont(sel);
            fontContainer.setPreviewFont(fontContainer.getSelectedFont());
        }
    }
}

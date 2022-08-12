package org.drjekyll.fontchooser.listeners;

import java.awt.Font;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lombok.RequiredArgsConstructor;
import org.drjekyll.fontchooser.FontContainer;


/**
 * Created by dheid on 4/1/17.
 */
@RequiredArgsConstructor
public class FamilyListSelectionListener implements ListSelectionListener {

    private final FontContainer fontContainer;

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Font oldFont = fontContainer.getSelectedFont();
            Font newFont = new Font(
                fontContainer.getSelectedFamily(),
                oldFont.getStyle(),
                (int) fontContainer.getSelectedSize());

            fontContainer.setSelectedFont(newFont);
            fontContainer.setPreviewFont(newFont);
        }
    }
}

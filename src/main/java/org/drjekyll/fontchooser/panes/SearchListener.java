package org.drjekyll.fontchooser.panes;

import lombok.RequiredArgsConstructor;
import org.drjekyll.fontchooser.model.FamilyListModel;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Locale;

@RequiredArgsConstructor
public class SearchListener extends KeyAdapter {

    private final FamilyListModel familyListModel;

    private final FamilyPane familyPane;

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField searchField = (JTextField) e.getSource();
        String searchString = searchField.getText().toLowerCase(Locale.ENGLISH);
        familyListModel.findFirst(searchString).ifPresent(familyPane::setSelectedFamily);
    }

}

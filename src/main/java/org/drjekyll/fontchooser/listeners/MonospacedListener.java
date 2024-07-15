package org.drjekyll.fontchooser.listeners;

import lombok.RequiredArgsConstructor;
import org.drjekyll.fontchooser.model.FamilyListModel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@RequiredArgsConstructor
public class MonospacedListener implements ItemListener {

    private final FamilyListModel familyListModel;

    @Override
    public void itemStateChanged(ItemEvent e) {
        familyListModel.setMonospacedOnly(e.getItemSelectable().getSelectedObjects() != null);
    }
}

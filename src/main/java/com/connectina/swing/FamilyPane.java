package com.connectina.swing;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

public class FamilyPane extends JScrollPane {

    private JList<String> familyList = new JList<>();

    public FamilyPane() {

        DefaultListModel<String> familyListModel = new DefaultListModel<>();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] families = ge.getAvailableFontFamilyNames();

        for (String family : families) {
            familyListModel.addElement(family);
        }

        familyList.setModel(familyListModel);
        familyList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setMinimumSize(new Dimension(80, 50));
        setPreferredSize(new Dimension(240, 150));
        setViewportView(familyList);
    }

    public void setSelectedFamily(String family) {
        familyList.setSelectedValue(family, true);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        familyList.addListSelectionListener(listener);
    }

    public String getSelectedFamily() {
        return familyList.getSelectedValue();
    }

}

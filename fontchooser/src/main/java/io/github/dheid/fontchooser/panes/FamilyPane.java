package io.github.dheid.fontchooser.panes;

import io.github.dheid.fontchooser.FontFamilies;
import io.github.dheid.fontchooser.FontFamily;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;


public class FamilyPane extends JScrollPane {

    private final JList<String> familyList = new JList<>();

    public FamilyPane() {

        DefaultListModel<String> familyListModel = new DefaultListModel<>();

        FontFamilies fontFamilies = FontFamilies.getInstance();
        for (FontFamily fontFamily : fontFamilies) {
            familyListModel.addElement(fontFamily.getName());
        }

        familyList.setModel(familyListModel);
        familyList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        familyList.setCellRenderer(new ToolTipCellRenderer());

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

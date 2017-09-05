package io.github.dheid.fontchooser.panes;

import io.github.dheid.fontchooser.Style;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.util.ResourceBundle;


public class StylePane extends JScrollPane {

    private JList<String> styleList = new JList<>();

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("FontChooser");

    public StylePane() {

        DefaultListModel<String> styleListModel = new DefaultListModel<>();

        for (Style style : Style.values()) {
            styleListModel.addElement(resourceBundle.getString(style.getBundleKey()));
        }

        styleList.setModel(styleListModel);
        styleList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setMinimumSize(new Dimension(60, 120));
        setPreferredSize(new Dimension(80, 150));
        setViewportView(styleList);

    }

    public void addListSelectionListener(ListSelectionListener listener) {
        styleList.addListSelectionListener(listener);
    }

    public void setSelectedStyle(Style style) {
        styleList.setSelectedIndex(style.ordinal());
    }

    public int getSelectedStyle() {
        return Style.values()[styleList.getSelectedIndex()].ordinal();
    }

}

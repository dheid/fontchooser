package org.drjekyll.fontchooser.panes;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import org.drjekyll.fontchooser.FontFamilies;
import org.drjekyll.fontchooser.FontFamily;
import org.drjekyll.fontchooser.model.FontSelectionModel;


public class StylePane extends JScrollPane implements ChangeListener {

    private static final long serialVersionUID = -176731082795772255L;

    private final JList<Font> styleList = new JList<>();

    private final DefaultListModel<Font> styleListModel;

    private String family;

    public StylePane() {

        styleListModel = new DefaultListModel<>();
        styleList.setModel(styleListModel);
        styleList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        styleList.setCellRenderer(new StyleCellRenderer());

        setMinimumSize(new Dimension(140, 50));
        setPreferredSize(new Dimension(160, 100));
        setViewportView(styleList);

    }

    public void addListSelectionListener(ListSelectionListener listener) {
        styleList.addListSelectionListener(listener);
    }

    public void removeListSelectionListener(ListSelectionListener listener) {
        styleList.removeListSelectionListener(listener);
    }

    public void setSelectedStyle(Font font) {
        styleList.setSelectedValue(font, true);
    }

    public Font getSelectedStyle() {
        return styleList.getSelectedValue();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        FontSelectionModel fontSelectionModel = (FontSelectionModel) e.getSource();
        Font selectedFont = fontSelectionModel.getSelectedFont();
        loadFamily(selectedFont.getFamily());
    }

    public void loadFamily(String family) {
        if (Objects.equals(this.family, family)) {
            return;
        }

        this.family = family;

        FontFamilies fontFamilies = FontFamilies.getInstance();
        FontFamily fontFamily = fontFamilies.get(family);

        if (fontFamily != null) {
            ListSelectionListener[] selectionListeners = styleList.getListSelectionListeners();
            removeSelectionListeners(selectionListeners);
            updateListModel(fontFamily.getStyles());
            addSelectionListeners(selectionListeners);
        }

    }

    private void updateListModel(Iterable<Font> fonts) {
        styleListModel.clear();
        for (Font font : fonts) {
            styleListModel.addElement(font);
        }
    }

    private void addSelectionListeners(ListSelectionListener[] selectionListeners) {
        for (ListSelectionListener listener : selectionListeners) {
            styleList.addListSelectionListener(listener);
        }
    }

    private void removeSelectionListeners(ListSelectionListener[] selectionListeners) {
        for (ListSelectionListener listener : selectionListeners) {
            styleList.removeListSelectionListener(listener);
        }
    }
}

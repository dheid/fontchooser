package io.github.dheid.fontchooser.panes;

import io.github.dheid.fontchooser.FontFamilies;
import io.github.dheid.fontchooser.FontFamily;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


public class FamilyPane extends JPanel {

    private final JList<String> familyList = new JList<>();

    private JTextField searchField;

    private final SearchListener searchListener;

    public FamilyPane() {

        DefaultListModel<String> familyListModel = new DefaultListModel<>();
        FontFamilies fontFamilies = FontFamilies.getInstance();
        searchListener = new SearchListener(this);
        for (FontFamily fontFamily : fontFamilies) {
            String name = fontFamily.getName();
            familyListModel.addElement(name);
            searchListener.addFamilyName(name);
        }

        initializeList(familyListModel);

        setMinimumSize(new Dimension(80, 50));
        setPreferredSize(new Dimension(240, 100));

        setLayout(new GridBagLayout());
        addSearchField();
        addScrollPane();
    }

    private void initializeList(ListModel<String> familyListModel) {
        familyList.setModel(familyListModel);
        familyList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        familyList.setCellRenderer(new ToolTipCellRenderer());
    }

    private void addSearchField() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.weightx = 1.0;

        searchField = new JTextField();
        searchField.requestFocus();
        searchField.addKeyListener(searchListener);
        add(searchField, gridBagConstraints);
    }

    private void addScrollPane() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;

        JScrollPane scrollPane = new JScrollPane(familyList);
        add(scrollPane, gridBagConstraints);
    }

    public void setSelectedFamily(String family) {
        familyList.setSelectedValue(family, true);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        familyList.addListSelectionListener(listener);
    }

    public void removeListSelectionListener(ListSelectionListener listener) {
        familyList.removeListSelectionListener(listener);
    }

    public String getSelectedFamily() {
        return familyList.getSelectedValue();
    }

}

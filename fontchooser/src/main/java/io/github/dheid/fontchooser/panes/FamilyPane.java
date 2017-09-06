package io.github.dheid.fontchooser.panes;

import io.github.dheid.fontchooser.FontFamilies;
import io.github.dheid.fontchooser.FontFamily;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class FamilyPane extends JPanel {

    private final JList<String> familyList = new JList<>();

    private final List<String> fontFamilyNames = new ArrayList<>();
    private JTextField searchField;

    public FamilyPane() {

        DefaultListModel<String> familyListModel = new DefaultListModel<>();

        FontFamilies fontFamilies = FontFamilies.getInstance();
        for (FontFamily fontFamily : fontFamilies) {
            String name = fontFamily.getName();
            familyListModel.addElement(name);
            fontFamilyNames.add(name);
        }

        familyList.setModel(familyListModel);
        familyList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        familyList.setCellRenderer(new ToolTipCellRenderer());

        setMinimumSize(new Dimension(80, 50));
        setPreferredSize(new Dimension(240, 150));

        setLayout(new GridBagLayout());
        addSearchField();
        addScrollPane();
    }

    private void addSearchField() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.weightx = 1.0;

        searchField = new JTextField();
        searchField.requestFocus();
        searchField.addKeyListener(new SearchListener());
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

    public String getSelectedFamily() {
        return familyList.getSelectedValue();
    }

    private class SearchListener extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            String searchString = searchField.getText();
            Optional<String> first = fontFamilyNames.stream()
                .filter(fontFamilyName -> fontFamilyName.toLowerCase(Locale.getDefault()).startsWith(searchString))
                .findFirst();

            first.ifPresent(s -> familyList.setSelectedValue(s, true));

        }
    }
}

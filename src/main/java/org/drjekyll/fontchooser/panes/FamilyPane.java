package org.drjekyll.fontchooser.panes;

import org.drjekyll.fontchooser.listeners.MonospacedListener;
import org.drjekyll.fontchooser.model.FamilyListModel;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
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

    private static final long serialVersionUID = 5705429171724237594L;

    private final JList<String> familyList = new JList<>();

    private final SearchListener searchListener;

    private final JCheckBox monospacedCheckBox = new JCheckBox();

    private final MonospacedListener monospacedListener;

    public FamilyPane() {

        FamilyListModel familyListModel = new FamilyListModel(this);
        searchListener = new SearchListener(familyListModel, this);

        initializeList(familyListModel);
        monospacedListener = new MonospacedListener(familyListModel);

        setMinimumSize(new Dimension(80, 50));
        setPreferredSize(new Dimension(240, 160));

        setLayout(new GridBagLayout());
        addSearchField();
        addMonospacedCheckBox();
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

        JTextField searchField = new JTextField();
        searchField.setBorder(BorderFactory.createEmptyBorder());
        searchField.requestFocus();
        searchField.addKeyListener(searchListener);
        add(new JScrollPane(searchField), gridBagConstraints);
    }

    private void addMonospacedCheckBox() {
        monospacedCheckBox.setText("Monospaced only");
        monospacedCheckBox.setDisplayedMnemonicIndex(0);
        monospacedCheckBox.addItemListener(monospacedListener);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(monospacedCheckBox, gridBagConstraints);
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

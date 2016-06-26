package com.connectina.swing;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import static com.connectina.swing.FontChooser.DEFAULT_FONT_SIZE;

public class SizePane extends JPanel {

    private JList<Integer> sizeList = new JList<>();
    private JSpinner sizeSpinner = new JSpinner();
    private final DefaultListModel<Integer> sizeListModel;

    public SizePane() {
        setLayout(new GridBagLayout());

        sizeListModel = new DefaultListModel<>();
        int size = 6;
        int step = 1;
        int ceil = 14;
        do {
            sizeListModel.addElement(size);
            if (size == ceil) {
                ceil += ceil;
                step += step;
            }
            size += step;
        } while (size <= 128);

        sizeList.setModel(sizeListModel);
        sizeList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sizeSpinner.addChangeListener(event -> {

                Integer value = (Integer) sizeSpinner.getValue();
                int index = ((DefaultListModel<Integer>) sizeList.getModel()).indexOf(value);
                if (index > -1) {
                    sizeList.setSelectedValue(value, true);
                } else {
                    sizeList.clearSelection();
                }

        });
        sizeList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int index = ((DefaultListModel<Integer>) sizeList.getModel()).indexOf(sizeList.getSelectedValue());
                if (index > -1) {
                    sizeSpinner.setValue((Integer) sizeList.getSelectedValue());
                }
            }
        });

        int spinnerHeight = (int) sizeSpinner.getPreferredSize().getHeight();
        sizeSpinner.setPreferredSize(new Dimension(60, spinnerHeight));
        sizeSpinner.setModel(new SpinnerNumberModel(DEFAULT_FONT_SIZE, 6, 128, 1));
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints1.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints1.weighty = 1.0;
        gridBagConstraints1.insets = new Insets(0, 0, 6, 0);
        add(sizeSpinner, gridBagConstraints1);

        JScrollPane sizeScrollPane = new JScrollPane();
        sizeScrollPane.setMinimumSize(new Dimension(50, 120));
        sizeScrollPane.setPreferredSize(new Dimension(60, 150));
        sizeScrollPane.setViewportView(sizeList);
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints2.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints2.weighty = 1.0;
        add(sizeScrollPane, gridBagConstraints2);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        sizeList.addListSelectionListener(listener);
    }

    public void setSelectedSize(int size) {
        if (sizeListModel.contains(size)) {
            sizeList.setSelectedValue(size, true);
        }
        sizeSpinner.setValue(size);
    }

    public int getSelectedSize() {
        return (Integer) sizeSpinner.getValue();
    }

}

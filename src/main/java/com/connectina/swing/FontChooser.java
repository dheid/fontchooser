/*
 * A font chooser JavaBean component.
 * Copyright (C) 2009 Dr Christos Bohoris
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 3 as published by the Free Software Foundation;
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 * swing@connectina.com
 */
package com.connectina.swing;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ResourceBundle;

/**
 * Provides a pane of controls designed to allow a user to
 * select a {@code Font}.
 *
 * @author Christos Bohoris
 * @see Font
 */
public class FontChooser extends JPanel {
    public static final int DEFAULT_FONT_SIZE = 12;
    private static final String SELECTION_MODEL_PROPERTY = "selectionModel";

    private FontSelectionModel selectionModel;
    private JList<String> familyList = new JList<>();
    private JLabel previewAreaLabel = new JLabel();
    private JList<Integer> sizeList = new JList<>();
    private JSpinner sizeSpinner = new JSpinner();
    private JList<String> styleList = new JList<>();
    private JPanel fontPanel = new JPanel();
    private JLabel familyLabel = new JLabel();
    private JLabel styleLabel = new JLabel();
    private JLabel sizeLabel = new JLabel();
    private JScrollPane familyScrollPane = new JScrollPane();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("FontChooser");
    private JLabel previewLabel = new JLabel();
    private JPanel previewPanel = new JPanel();
    private JPanel previewAreaPanel = new JPanel();

    /**
     * Creates a FontChooser pane with an initial default Font
     * (Sans Serif, Plain, 12).
     */
    public FontChooser() {
        this(new Font(Font.SANS_SERIF, Font.PLAIN, DEFAULT_FONT_SIZE));
    }

    /**
     * Creates a FontChooser pane with the specified initial Font.
     *
     * @param initialFont the initial Font set in the chooser
     */
    public FontChooser(Font initialFont) {
        this(new DefaultFontSelectionModel(initialFont));
    }

    /**
     * Creates a FontChooser pane with the specified
     * {@code FontSelectionModel}.
     *
     * @param model the {@code FontSelectionModel} to be used
     */
    public FontChooser(FontSelectionModel model) {
        selectionModel = model;
        setLayout(new BorderLayout());
        initComponents();
        initPanel();
    }

    private static String getFontStyleName(int index, ResourceBundle bundle) {
        String result;
        switch (index) {
            case 0:
                result = bundle.getString("style.plain");
                break;
            case 1:
                result = bundle.getString("style.bold");
                break;
            case 2:
                result = bundle.getString("style.italic");
                break;
            case 3:
                result = bundle.getString("style.bolditalic");
                break;
            default:
                result = bundle.getString("style.plain");
        }

        return result;
    }

    /**
     * Gets the current Font value from the FontChooser.
     * By default, this delegates to the model.
     *
     * @return the current Font value of the FontChooser
     */
    public Font getSelectedFont() {
        return selectionModel.getSelectedFont();
    }

    /**
     * Sets the current font of the FontChooser to the specified font.
     * The {@code FontSelectionModel} will fire a {@code ChangeEvent}
     *
     * @param font the font to be set in the font chooser
     * @see JComponent#addPropertyChangeListener
     */
    public void setSelectedFont(Font font) {
        selectionModel.setSelectedFont(font);
    }

    /**
     * Returns the data model that handles Font selections.
     *
     * @return a {@code FontSelectionModel} object
     */
    public FontSelectionModel getSelectionModel() {
        return selectionModel;
    }

    /**
     * Sets the model containing the selected Font.
     *
     * @param newModel the new {@code FontSelectionModel} object
     */
    public void setSelectionModel(FontSelectionModel newModel) {
        FontSelectionModel oldModel = selectionModel;
        selectionModel = newModel;
        firePropertyChange(SELECTION_MODEL_PROPERTY, oldModel, newModel);
    }

    /**
     * Adds a {@code ChangeListener} to the model.
     *
     * @param l the {@code ChangeListener} to be added
     */
    public void addChangeListener(ChangeListener l) {
        selectionModel.addChangeListener(l);
    }

    /**
     * Removes a {@code ChangeListener} from the model.
     *
     * @param l the {@code ChangeListener} to be removed
     */
    public void removeChangeListener(ChangeListener l) {
        selectionModel.removeChangeListener(l);
    }

    private void initPanel() {
        initFontFamilyNames();
        initFontStyles();
        initFontSizes();
        initPreviewAreaLabel();
    }

    private void initPreviewAreaLabel() {
        previewAreaLabel.setFont(selectionModel.getSelectedFont());
        previewAreaLabel.setText(resourceBundle.getString("font.preview.text"));
        previewAreaLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initFontSizes() {
        DefaultListModel<Integer> sizeListModel = new DefaultListModel<>();
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
        Integer selectedSize = selectionModel.getSelectedFont().getSize();
        if (sizeListModel.contains(selectedSize)) {
            sizeList.setSelectedValue(selectedSize, true);
        }
        sizeList.addListSelectionListener(new SizeListSelectionListener());
        sizeSpinner.addChangeListener(new SizeSpinnerListener());
        sizeSpinner.setValue(selectedSize);
    }

    private void initFontStyles() {
        DefaultListModel<String> styleListModel = new DefaultListModel<>();
        styleListModel.addElement(getFontStyleName(Font.PLAIN, resourceBundle));
        styleListModel.addElement(getFontStyleName(Font.BOLD, resourceBundle));
        styleListModel.addElement(getFontStyleName(Font.ITALIC, resourceBundle));
        styleListModel.addElement(getFontStyleName(Font.BOLD + Font.ITALIC, resourceBundle));
        styleList.setModel(styleListModel);
        styleList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        styleList.setSelectedIndex(selectionModel.getSelectedFont().getStyle());
        styleList.addListSelectionListener(new StyleListSelectionListener());
    }

    private void initFontFamilyNames() {
        DefaultListModel<String> familyListModel = new DefaultListModel<>();
        selectionModel.getAvailableFontNames().forEach(familyListModel::addElement);
        familyList.setModel(familyListModel);
        familyList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        familyList.setSelectedValue(selectionModel.getSelectedFont().getName(), true);
        familyList.addListSelectionListener(new FamilyListSelectionListener());
    }

    private void initComponents() {
        initFontPanel();
        initFamilyLabel();
        initStyleLabel();
        initSizeLabel();
        initFamilyScrollPane();
        initStyleScrollPane();
        initSizeSpinner();
        initSizeScrollPane();
        initPreviewLabel();
        initPreviewPanel();
        initPreviewAreaPanel();
    }

    private void initPreviewAreaPanel() {
        previewAreaPanel.setBackground(new Color(255, 255, 255));
        previewAreaPanel.setBorder(BorderFactory.createEtchedBorder());
        previewAreaPanel.setPreferredSize(new Dimension(200, 80));
        previewAreaPanel.setLayout(new BorderLayout());
        previewAreaPanel.add(previewAreaLabel, BorderLayout.CENTER);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        previewPanel.add(previewAreaPanel, gridBagConstraints);
    }

    private void initPreviewPanel() {
        previewPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        previewPanel.add(previewLabel, gridBagConstraints);
        add(previewPanel, BorderLayout.PAGE_END);
    }

    private void initPreviewLabel() {
        previewLabel.setDisplayedMnemonic(resourceBundle.getString("font.preview.mnemonic").charAt(0));
        previewLabel.setText(resourceBundle.getString("font.preview"));
    }

    private void initSizeScrollPane() {
        JScrollPane sizeScrollPane = new JScrollPane();
        sizeScrollPane.setMinimumSize(new Dimension(50, 120));
        sizeScrollPane.setPreferredSize(new Dimension(60, 150));
        sizeScrollPane.setViewportView(sizeList);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 11, 0);
        fontPanel.add(sizeScrollPane, gridBagConstraints);
    }

    private void initSizeSpinner() {
        int spinnerHeight = (int) sizeSpinner.getPreferredSize().getHeight();
        sizeSpinner.setPreferredSize(new Dimension(60, spinnerHeight));
        sizeSpinner.setModel(new SpinnerNumberModel(DEFAULT_FONT_SIZE, 6, 128, 1));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        fontPanel.add(sizeSpinner, gridBagConstraints);
    }

    private void initStyleScrollPane() {
        JScrollPane styleScrollPane = new JScrollPane();
        styleScrollPane.setMinimumSize(new Dimension(60, 120));
        styleScrollPane.setPreferredSize(new Dimension(80, 150));
        styleScrollPane.setViewportView(styleList);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 11, 11);
        fontPanel.add(styleScrollPane, gridBagConstraints);
    }

    private void initFamilyScrollPane() {
        familyScrollPane.setMinimumSize(new Dimension(80, 50));
        familyScrollPane.setPreferredSize(new Dimension(240, 150));
        familyScrollPane.setViewportView(familyList);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 11, 11);
        fontPanel.add(familyScrollPane, gridBagConstraints);
    }

    private void initSizeLabel() {
        sizeLabel.setLabelFor(sizeList);
        sizeLabel.setDisplayedMnemonic(resourceBundle.getString("font.size.mnemonic").charAt(0));
        sizeLabel.setText(resourceBundle.getString("font.size"));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        fontPanel.add(sizeLabel, gridBagConstraints);
    }

    private void initStyleLabel() {
        styleLabel.setLabelFor(styleList);
        styleLabel.setDisplayedMnemonic(resourceBundle.getString("font.style.mnemonic").charAt(0));
        styleLabel.setText(resourceBundle.getString("font.style"));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 11);
        fontPanel.add(styleLabel, gridBagConstraints);
    }

    private void initFamilyLabel() {
        familyLabel.setLabelFor(familyList);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 11);
        fontPanel.add(familyLabel, gridBagConstraints);
        familyLabel.setDisplayedMnemonic(resourceBundle.getString("font.family.mnemonic").charAt(0));
        familyLabel.setText(resourceBundle.getString("font.family"));
    }

    private void initFontPanel() {
        fontPanel.setLayout(new GridBagLayout());
        add(fontPanel, BorderLayout.CENTER);
    }

    private class FamilyListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Font sel = new Font(familyList.getSelectedValue(),
                        styleList.getSelectedIndex(),
                        Integer.parseInt(sizeSpinner.getValue().toString()));
                selectionModel.setSelectedFont(sel);
                previewAreaLabel.setFont(selectionModel.getSelectedFont());
            }
        }
    }

    private class StyleListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                selectionModel.setSelectedFont(selectionModel.getSelectedFont()
                        .deriveFont(styleList.getSelectedIndex()));

                previewAreaLabel.setFont(selectionModel.getSelectedFont());
            }
        }
    }

    private class SizeListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int index = ((DefaultListModel<Integer>) sizeList.getModel()).indexOf(sizeList.getSelectedValue());
                if (index > -1) {
                    sizeSpinner.setValue((Integer) sizeList.getSelectedValue());
                }
                float newSize = Float.parseFloat(sizeSpinner.getValue().toString());
                Font newFont = selectionModel.getSelectedFont().deriveFont(newSize);
                selectionModel.setSelectedFont(newFont);

                previewAreaLabel.setFont(selectionModel.getSelectedFont());
            }
        }
    }

    private class SizeSpinnerListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            Integer value = (Integer) sizeSpinner.getValue();
            int index = ((DefaultListModel<Integer>) sizeList.getModel()).indexOf(value);
            if (index > -1) {
                sizeList.setSelectedValue(value, true);
            } else {
                sizeList.clearSelection();
            }
        }
    }
}

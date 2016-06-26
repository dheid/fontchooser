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

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
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

    private JPanel fontPanel = new JPanel();
    private JLabel familyLabel = new JLabel();
    private JLabel styleLabel = new JLabel();
    private JLabel sizeLabel = new JLabel();
    private FamilyPane familyPane = new FamilyPane();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("FontChooser");
    private JLabel previewLabel = new JLabel();
    private JPanel previewPanel = new JPanel();
    private PreviewPane previewPane = new PreviewPane();
    private StylePane stylePane = new StylePane();
    private SizePane sizePane = new SizePane();

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
        addComponents();
        initPanes();

        previewPane.setPreviewFont(selectionModel.getSelectedFont());
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

    private void initPanes() {
        familyPane.setSelectedFamily(selectionModel.getSelectedFont().getName());
        familyPane.addListSelectionListener(new FamilyListSelectionListener());

        stylePane.setSelectedStyle(Style.of(selectionModel.getSelectedFont()));
        stylePane.addListSelectionListener(new StyleListSelectionListener());

        sizePane.addListSelectionListener(new SizeListSelectionListener());
        sizePane.setSelectedSize(selectionModel.getSelectedFont().getSize());
    }

    private void addComponents() {
        addFontPanel();
        addFamilyLabel();
        addStyleLabel();
        addSizeLabel();
        addFamilyPane();
        addStylePane();
        addSizePane();
        addPreviewLabel();
        addPreviewPane();
        initPreviewAreaPanel();
    }

    private void initPreviewAreaPanel() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        previewPanel.add(previewPane, gridBagConstraints);
    }

    private void addPreviewPane() {
        previewPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        previewPanel.add(previewLabel, gridBagConstraints);
        add(previewPanel, BorderLayout.PAGE_END);
    }

    private void addPreviewLabel() {
        previewLabel.setDisplayedMnemonic(resourceBundle.getString("font.preview.mnemonic").charAt(0));
        previewLabel.setText(resourceBundle.getString("font.preview"));
    }

    private void addSizePane() {
        GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints3.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints3.weighty = 1.0;
        gridBagConstraints3.insets = new Insets(0, 0, 11, 0);
        fontPanel.add(sizePane, gridBagConstraints3);
    }

    private void addStylePane() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 11, 11);
        fontPanel.add(stylePane, gridBagConstraints);
    }

    private void addFamilyPane() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 11, 11);
        fontPanel.add(familyPane, gridBagConstraints);
    }

    private void addSizeLabel() {
        sizeLabel.setLabelFor(sizePane);
        sizeLabel.setDisplayedMnemonic(resourceBundle.getString("font.size.mnemonic").charAt(0));
        sizeLabel.setText(resourceBundle.getString("font.size"));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        fontPanel.add(sizeLabel, gridBagConstraints);
    }

    private void addStyleLabel() {
        styleLabel.setLabelFor(stylePane);
        styleLabel.setDisplayedMnemonic(resourceBundle.getString("font.style.mnemonic").charAt(0));
        styleLabel.setText(resourceBundle.getString("font.style"));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 11);
        fontPanel.add(styleLabel, gridBagConstraints);
    }

    private void addFamilyLabel() {
        familyLabel.setLabelFor(familyPane);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 11);
        fontPanel.add(familyLabel, gridBagConstraints);
        familyLabel.setDisplayedMnemonic(resourceBundle.getString("font.family.mnemonic").charAt(0));
        familyLabel.setText(resourceBundle.getString("font.family"));
    }

    private void addFontPanel() {
        fontPanel.setLayout(new GridBagLayout());
        add(fontPanel, BorderLayout.CENTER);
    }

    private class FamilyListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Font sel = new Font(familyPane.getSelectedFamily(), stylePane.getSelectedStyle().ordinal(),
                        sizePane.getSelectedSize());
                selectionModel.setSelectedFont(sel);
                previewPane.setPreviewFont(selectionModel.getSelectedFont());
            }
        }
    }

    private class StyleListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                selectionModel.setSelectedFont(selectionModel.getSelectedFont()
                        .deriveFont(stylePane.getSelectedStyle().ordinal()));
                previewPane.setPreviewFont(selectionModel.getSelectedFont());
            }
        }
    }

    private class SizeListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                float newSize = sizePane.getSelectedSize();
                Font newFont = selectionModel.getSelectedFont().deriveFont(newSize);
                selectionModel.setSelectedFont(newFont);
                previewPane.setPreviewFont(selectionModel.getSelectedFont());
            }
        }
    }


}

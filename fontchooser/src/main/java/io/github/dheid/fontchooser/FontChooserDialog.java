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
package io.github.dheid.fontchooser;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

/**
 * A dialog containing a {@code FontChooser} as well as OK and
 * Cancel buttons.
 *
 * @author Christos Bohoris
 */
public class FontChooserDialog extends JDialog {

    public FontChooserDialog() {
        initDialog();
    }

    public FontChooserDialog(Frame owner) {
        super(owner);
        initDialog();
    }

    public FontChooserDialog(Frame owner, boolean modal) {
        super(owner, modal);
        initDialog();
    }

    public FontChooserDialog(Frame owner, String title) {
        super(owner, title);
        initDialog();
    }

    public FontChooserDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        initDialog();
    }

    public FontChooserDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        initDialog();
    }

    public FontChooserDialog(Dialog owner) {
        super(owner);
        initDialog();
    }

    public FontChooserDialog(Dialog owner, boolean modal) {
        super(owner, modal);
        initDialog();
    }

    public FontChooserDialog(Dialog owner, String title) {
        super(owner, title);
        initDialog();
    }

    public FontChooserDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
        initDialog();
    }

    public FontChooserDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        initDialog();
    }

    public FontChooserDialog(Window owner) {
        super(owner);
        initDialog();
    }

    public FontChooserDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);
        initDialog();
    }

    public FontChooserDialog(Window owner, String title) {
        super(owner, title);
        initDialog();
    }

    public FontChooserDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
        initDialog();
    }

    public FontChooserDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
        initDialog();
    }

    private FontChooser chooser = new FontChooser();
    private JButton cancelButton = new JButton();
    private JButton okButton = new JButton();
    private ResourceBundle bundle = ResourceBundle.getBundle("FontChooserDialog");
    private boolean cancelSelected;

    private void initDialog() {
        initComponents();
        getRootPane().setDefaultButton(okButton);
        okButton.requestFocusInWindow();

        cancelButton.addActionListener(event -> cancelSelected = true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancelSelected = true;
            }
        });
    }

    private void initComponents() {

        JPanel chooserPanel = new JPanel();
        chooserPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 11));
        chooserPanel.setLayout(new BorderLayout(0, 12));
        chooserPanel.add(chooser, BorderLayout.CENTER);
        add(chooserPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(7, 7, 6, 6));
        controlPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        add(controlPanel, BorderLayout.PAGE_END);

        okButton.setMnemonic(bundle.getString("action.ok.mnemonic").charAt(0));
        okButton.setText(bundle.getString("action.ok"));
        okButton.addActionListener(event -> {
            dispose();
        });
        controlPanel.add(okButton);

        cancelButton.setMnemonic(bundle.getString("action.cancel.mnemonic").charAt(0));
        cancelButton.setText(bundle.getString("action.cancel"));
        cancelButton.addActionListener(event -> {
            cancelSelected = true;
            dispose();
        });
        controlPanel.add(cancelButton);

        pack();
    }

    public Font getSelectedFont() {
        return chooser.getSelectedFont();
    }

    public boolean isCancelSelected() {
        return cancelSelected;
    }
}

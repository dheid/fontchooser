package com.connectina.swing;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ResourceBundle;

public class PreviewPane extends JPanel {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("FontChooser");

    private JLabel previewAreaLabel = new JLabel();

    public PreviewPane() {
        previewAreaLabel.setText(resourceBundle.getString("font.preview.text"));
        previewAreaLabel.setHorizontalAlignment(SwingConstants.CENTER);

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEtchedBorder());
        setPreferredSize(new Dimension(200, 80));
        setLayout(new BorderLayout());
        add(previewAreaLabel, BorderLayout.CENTER);
    }

    public void setPreviewFont(Font font) {
        previewAreaLabel.setFont(font);
    }
}

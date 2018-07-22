package org.drjekyll.fontchooser.panes;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ResourceBundle;

public class PreviewPane extends JScrollPane {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("FontChooser");

    private final JTextArea previewText = new JTextArea();

    public PreviewPane() {
        previewText.setText(resourceBundle.getString("font.preview.text"));
        previewText.setBorder(BorderFactory.createCompoundBorder(
            previewText.getBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5))
        );
        setPreferredSize(new Dimension(200, 80));
        setViewportView(previewText);
    }

    public void setPreviewFont(Font font) {
        previewText.setFont(font);
    }

}

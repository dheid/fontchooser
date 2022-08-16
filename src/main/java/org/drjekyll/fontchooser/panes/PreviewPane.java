package org.drjekyll.fontchooser.panes;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PreviewPane extends JScrollPane {

    private static final long serialVersionUID = -854781598723857579L;

    private final JTextArea previewText = new JTextArea();

    public PreviewPane() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("FontChooser");
        previewText.setText(resourceBundle.getString("font.preview.text"));
        setPreviewTextBorder();
        setPreferredSize(new Dimension(200, 80));
        setViewportView(previewText);
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setPreviewTextBorder();
    }

    private void setPreviewTextBorder() {
        if (previewText == null) return;
        previewText.setBorder(BorderFactory.createCompoundBorder(
            previewText.getBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5))
        );
    }

    public void setPreviewFont(Font font) {
        previewText.setFont(font);
    }

}

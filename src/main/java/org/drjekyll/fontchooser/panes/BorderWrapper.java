package org.drjekyll.fontchooser.panes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class BorderWrapper implements Border {

    private final Border border;

    public static Border makeNonUIResource(Border border) {
        if (border == null || border instanceof UIResource) {
            return null;
        }
        return new BorderWrapper(border);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        border.paintBorder(c, g, x, y, width, height);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return border.getBorderInsets(c);
    }

    @Override
    public boolean isBorderOpaque() {
        return border.isBorderOpaque();
    }
}

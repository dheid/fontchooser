package org.drjekyll.fontchooser.panes;

import lombok.RequiredArgsConstructor;

import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import java.awt.*;

@RequiredArgsConstructor
class BorderWrapper implements Border {

    private final Border border;

    public static Border makeNonUIResource(Border border) {
        if (border == null || border instanceof UIResource) return null;
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

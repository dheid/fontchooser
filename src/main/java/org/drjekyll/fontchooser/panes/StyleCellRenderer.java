package org.drjekyll.fontchooser.panes;

import java.awt.Component;
import java.awt.Font;
import java.util.regex.Pattern;
import javax.swing.JList;

public class StyleCellRenderer extends ToolTipCellRenderer {

    private static final Pattern NON_LETTER_OR_DIGIT = Pattern.compile("^\\W+");

    @Override
    public Component getListCellRendererComponent(
        JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus
    ) {
        String style = null;
        if (value instanceof Font) {
            Font font = (Font) value;
            if (font.getName().trim().equalsIgnoreCase(font.getFamily().trim())) {
                style = "Default";
            } else {
                style = NON_LETTER_OR_DIGIT.matcher(font.getName().replaceFirst(font.getFamily(), "")).replaceFirst("").trim();
            }
        }
        return super.getListCellRendererComponent(list, style, index, isSelected, cellHasFocus);
    }
}

package org.drjekyll.fontchooser.panes;

import javax.swing.*;
import java.awt.*;

public class ToolTipCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JComponent listCellRendererComponent = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        listCellRendererComponent.setToolTipText(value.toString());
        return listCellRendererComponent;
    }
}

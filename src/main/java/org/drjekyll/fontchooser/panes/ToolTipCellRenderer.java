package org.drjekyll.fontchooser.panes;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;

public class ToolTipCellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 6420358947653897358L;

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JComponent listCellRendererComponent = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        listCellRendererComponent.setToolTipText(value.toString());
        return listCellRendererComponent;
    }
}

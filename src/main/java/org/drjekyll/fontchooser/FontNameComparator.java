package org.drjekyll.fontchooser;

import java.awt.Font;
import java.io.Serializable;
import java.util.Comparator;

public class FontNameComparator implements Comparator<Font>, Serializable {

    private static final long serialVersionUID = 1143602375442062028L;

    @Override
    public int compare(Font o1, Font o2) {
        return o1.getName().compareTo(o2.getName());
    }

}

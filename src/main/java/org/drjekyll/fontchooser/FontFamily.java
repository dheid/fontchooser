package org.drjekyll.fontchooser;

import java.awt.Font;
import java.util.Collection;
import java.util.TreeSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FontFamily {

    private final String name;

    private final Collection<Font> styles = new TreeSet<>(new FontNameComparator());

    public boolean add(Font font) {
        return styles.add(font);
    }

    public String getName() {
        return name;
    }

}

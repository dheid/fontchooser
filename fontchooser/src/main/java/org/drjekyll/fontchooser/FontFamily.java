package org.drjekyll.fontchooser;

import java.awt.Font;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class FontFamily implements Iterable<Font> {

    private final String name;

    private Set<Font> styles = new TreeSet<>(new FontNameComparator());

    public FontFamily(String name) {
        this.name = name;
    }

    public boolean add(Font font) {
        return styles.add(font);
    }

    public String getName() {
        return name;
    }

    @Override
    public Iterator<Font> iterator() {
        return styles.iterator();
    }
}

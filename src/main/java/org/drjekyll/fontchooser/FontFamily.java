package org.drjekyll.fontchooser;

import java.awt.Font;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FontFamily implements Iterable<Font> {

    private final String name;

    private final Collection<Font> styles = new TreeSet<>(new FontNameComparator());

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

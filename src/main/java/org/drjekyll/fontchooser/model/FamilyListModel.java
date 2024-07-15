package org.drjekyll.fontchooser.model;

import lombok.RequiredArgsConstructor;
import org.drjekyll.fontchooser.FontFamilies;
import org.drjekyll.fontchooser.FontFamily;

import javax.swing.AbstractListModel;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class FamilyListModel extends AbstractListModel<String> {

    private static final char[] MONOSPACE_CHARS = {'0', '1', 'x', 'W'};

    private final FontFamilies fontFamilies = FontFamilies.getInstance();

    private final Component component;

    private List<String> fontFamilyNames;

    private List<String> monospacedFontFamilyNames;

    private boolean monospacedOnly;

    @Override
    public int getSize() {
        initialize();
        return (monospacedOnly ? monospacedFontFamilyNames : fontFamilyNames).size();
    }

    @Override
    public String getElementAt(int index) {
        initialize();
        return (monospacedOnly ? monospacedFontFamilyNames : fontFamilyNames).get(index);
    }

    public Optional<String> findFirst(CharSequence searchString) {
        initialize();
        List<String> families = monospacedOnly ? monospacedFontFamilyNames : fontFamilyNames;
        for (String family : families) {
            if (family.toLowerCase(Locale.ENGLISH).contains(searchString)) {
                return Optional.of(family);
            }
        }
        return Optional.empty();
    }

    private void initialize() {
        if (monospacedOnly) {
            if (monospacedFontFamilyNames == null) {
                monospacedFontFamilyNames = new ArrayList<>(128);
                for (FontFamily fontFamily : fontFamilies) {
                    String name = fontFamily.getName();
                    if (isMonospace(fontFamily)) {
                        monospacedFontFamilyNames.add(name);
                    }
                }
                monospacedFontFamilyNames.sort(Comparator.naturalOrder());
            }
        } else {
            if (fontFamilyNames == null) {
                fontFamilyNames = new ArrayList<>(fontFamilies.size());
                for (FontFamily fontFamily : fontFamilies) {
                    fontFamilyNames.add(fontFamily.getName());
                }
                fontFamilyNames.sort(Comparator.naturalOrder());
            }
        }
    }

    private boolean isMonospace(FontFamily fontFamily) {
        FontMetrics metrics = component.getFontMetrics(new Font(fontFamily.getName(), Font.PLAIN, 12));
        int width = metrics.charWidth(MONOSPACE_CHARS[0]);
        return width > 1 && IntStream.range(1, MONOSPACE_CHARS.length)
            .noneMatch(i -> Math.abs(metrics.charWidth(MONOSPACE_CHARS[i]) - width) > 2);
    }

    public void setMonospacedOnly(boolean monospacedOnly) {
        this.monospacedOnly = monospacedOnly;
        fireContentsChanged(this, -1, -1);
    }
}

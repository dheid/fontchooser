package com.connectina.swing;

import java.awt.Font;

public enum Style {
    PLAIN("style.plain"), BOLD("style.bold"), ITALIC("style.italic"), BOLD_ITALIC("style.bolditalic");

    private final String bundleKey;

    Style(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public String getBundleKey() {
        return bundleKey;
    }

    public static Style of(Font font) {
        return values()[font.getStyle()];
    }
}

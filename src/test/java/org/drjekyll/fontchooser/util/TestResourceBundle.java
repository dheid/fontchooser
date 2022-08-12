package org.drjekyll.fontchooser.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;
import java.util.ResourceBundle;

public class TestResourceBundle extends ResourceBundle {

    private final String key;
    private final Object object;

    public TestResourceBundle(String key, Object object) {
        this.key = key;
        this.object = object;
    }

    @Override
    protected Object handleGetObject(String key) {
        if (Objects.equals(this.key, key)) {
            return object;
        }
        return null;
    }

    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(Collections.singleton(key));
    }
}

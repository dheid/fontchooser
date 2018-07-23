package org.drjekyll.fontchooser.util;

import org.junit.Test;

import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResourceBundleUtilTest {

    private static final String KEY = "key";

    private final ResourceBundle resourceBundle = new TestResourceBundle(KEY, "string");

    @Test
    public void returnsFirstChar() {

        char firstChar = new ResourceBundleUtil(resourceBundle).getFirstChar(KEY);
        assertThat(firstChar, is('s'));

    }


}

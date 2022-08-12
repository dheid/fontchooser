package org.drjekyll.fontchooser.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ResourceBundle;
import org.junit.jupiter.api.Test;

public class ResourceBundleUtilTest {

    private static final String KEY = "key";

    private final ResourceBundle resourceBundle = new TestResourceBundle(KEY, "string");

    @Test
    public void returnsFirstChar() {

        char firstChar = new ResourceBundleUtil(resourceBundle).getFirstChar(KEY);
        assertThat(firstChar).isEqualTo('s');

    }


}

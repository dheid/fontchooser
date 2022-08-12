package org.drjekyll.fontchooser.util;

import java.util.ResourceBundle;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResourceBundleUtil {

    private final ResourceBundle resourceBundle;

    public char getFirstChar(String key) {
        String bundleString = resourceBundle.getString(key);
        return bundleString.charAt(0);
    }

}

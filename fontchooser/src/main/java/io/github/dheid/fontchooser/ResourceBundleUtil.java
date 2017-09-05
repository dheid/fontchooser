package io.github.dheid.fontchooser;

import java.util.ResourceBundle;

public class ResourceBundleUtil {

    public static char getFirstChar(ResourceBundle resourceBundle, String key) {
        String bundleString = resourceBundle.getString(key);
        return bundleString.charAt(0);
    }

}

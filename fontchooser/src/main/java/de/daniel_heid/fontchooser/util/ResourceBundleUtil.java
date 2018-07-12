package de.daniel_heid.fontchooser.util;

import java.util.ResourceBundle;

public class ResourceBundleUtil {

    public static char getFirstChar(ResourceBundle resourceBundle, String key) {
        String bundleString = resourceBundle.getString(key);
        return bundleString.charAt(0);
    }

}

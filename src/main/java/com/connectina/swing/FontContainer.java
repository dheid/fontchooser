package com.connectina.swing;

import java.awt.Font;


/**
 * Created by dheid on 4/1/17.
 */
public interface FontContainer {

    int getSelectedStyle();

    float getSelectedSize();

    String getSelectedFamily();

    Font getSelectedFont();

    void setSelectedFont(Font font);

    void setPreviewFont(Font font);

}

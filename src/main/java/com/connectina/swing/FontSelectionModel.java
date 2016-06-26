/*
 * A font chooser JavaBean component.
 * Copyright (C) 2009 Dr Christos Bohoris
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 3 as published by the Free Software Foundation;
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 * swing@connectina.com
 */
package com.connectina.swing;

import javax.swing.event.ChangeListener;
import java.awt.Font;
import java.util.List;

/**
 * A model that supports selecting a {@code Font}.
 *
 * @author Christos Bohoris
 * @see Font
 */
public interface FontSelectionModel {

    /**
     * Returns the selected {@code Font} which should be
     * non-{@code null}.
     *
     * @return the selected {@code Font}
     * @see #setSelectedFont
     */
    Font getSelectedFont();

    /**
     * Sets the selected font to {@code font}.
     * Note that setting the font to {@code null}
     * is undefined and may have unpredictable results.
     * This method fires a state changed event if it sets the
     * current font to a new non-{@code null} font.
     *
     * @param font the new {@code Font}
     * @see #getSelectedFont
     * @see #addChangeListener
     */
    void setSelectedFont(Font font);

    /**
     * Gets the available font names.
     * Returns a list containing the names of all font families in this
     * {@code GraphicsEnvironment} localized for the default locale,
     * as returned by {@code Locale.getDefault()}.
     *
     * @return a list of String containing font family names localized for the
     * default locale, or a suitable alternative name if no name exists
     * for this locale
     */
    List<String> getAvailableFontNames();

    /**
     * Adds {@code listener} as a listener to changes in the model.
     *
     * @param listener the {@code ChangeListener} to be added
     */
    void addChangeListener(ChangeListener listener);

    /**
     * Removes {@code listener} as a listener to changes in the model.
     *
     * @param listener the {@code ChangeListener} to be removed
     */
    void removeChangeListener(ChangeListener listener);

}

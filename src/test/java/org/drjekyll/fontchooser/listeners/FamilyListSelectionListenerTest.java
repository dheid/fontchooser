package org.drjekyll.fontchooser.listeners;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Font;
import javax.swing.event.ListSelectionEvent;
import org.drjekyll.fontchooser.FontContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * Created by dheid on 4/1/17.
 */
@ExtendWith(MockitoExtension.class)
public class FamilyListSelectionListenerTest {

    private static final String NAME = Font.DIALOG;
    private static final int STYLE = 1;
    private static final float SIZE = 2.0f;

    @InjectMocks
    private FamilyListSelectionListener familyListSelectionListener;

    @Mock
    private FontContainer fontContainer;

    @Mock
    private ListSelectionEvent listSelectionEvent;

    @Mock
    private Font font;

    @Captor
    private ArgumentCaptor<Font> fontArgumentCaptor;

    @Test
    public void updatesCurrentFont() {

        when(fontContainer.getSelectedSize()).thenReturn(SIZE);
        when(fontContainer.getSelectedFont()).thenReturn(font);
        when(fontContainer.getSelectedFamily()).thenReturn(NAME);
        when(font.getStyle()).thenReturn(STYLE);

        familyListSelectionListener.valueChanged(listSelectionEvent);

        verify(fontContainer).setSelectedFont(fontArgumentCaptor.capture());

        Font actualFont = fontArgumentCaptor.getValue();
        assertThat(actualFont).isEqualTo(new Font(NAME, STYLE, (int) SIZE));

        verify(fontContainer).setPreviewFont(actualFont);

    }

}

package com.connectina.swing.listeners;

import javax.swing.event.ListSelectionEvent;
import java.awt.Font;

import com.connectina.swing.FontContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by dheid on 4/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
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
    public void updatesCurrentFont() throws Exception {

        Font expectedFont = new Font(NAME, STYLE, (int)SIZE);

        when(fontContainer.getSelectedFamily()).thenReturn(NAME);
        when(fontContainer.getSelectedStyle()).thenReturn(STYLE);
        when(fontContainer.getSelectedSize()).thenReturn(SIZE);
        when(fontContainer.getSelectedFont()).thenReturn(font);

        familyListSelectionListener.valueChanged(listSelectionEvent);

        verify(fontContainer).setSelectedFont(fontArgumentCaptor.capture());
        verify(fontContainer).setPreviewFont(font);

        Font actualFont = fontArgumentCaptor.getValue();
        assertThat(actualFont, is(expectedFont));

    }

}

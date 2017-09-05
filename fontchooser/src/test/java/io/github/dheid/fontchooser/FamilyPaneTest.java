package io.github.dheid.fontchooser;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;


/**
 * Created by dheid on 4/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class FamilyPaneTest {

    @InjectMocks
    private FamilyPane familyPane;

    @Mock
    private ListSelectionListener listener;

    @Captor
    private ArgumentCaptor<ListSelectionEvent> listSelectionEventArgumentCaptor;

    @Test
    public void modifiesSelectedValue() throws Exception {
        String expectedFamily = Font.DIALOG;

        familyPane.setSelectedFamily(expectedFamily);
        String actualFamily = familyPane.getSelectedFamily();

        assertThat(actualFamily, is(expectedFamily));
    }

    @Test
    public void doesNotAcceptUnknownValue() throws Exception {
        String expectedFamily = "This font family does not exist.";

        familyPane.setSelectedFamily(expectedFamily);
        String actualFamily = familyPane.getSelectedFamily();

        assertThat(actualFamily, is(nullValue()));
    }

    @Test
    public void involvesListener() {
        familyPane.addListSelectionListener(listener);

        familyPane.setSelectedFamily(Font.DIALOG);

        verify(listener).valueChanged(listSelectionEventArgumentCaptor.capture());
        ListSelectionEvent listSelectionEvent = listSelectionEventArgumentCaptor.getValue();
        assertThat(listSelectionEvent.getValueIsAdjusting(), is(false));
    }

}

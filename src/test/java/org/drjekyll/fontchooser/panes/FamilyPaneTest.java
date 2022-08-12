package org.drjekyll.fontchooser.panes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.awt.Font;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * Created by dheid on 4/1/17.
 */
@ExtendWith(MockitoExtension.class)
public class FamilyPaneTest {

    private FamilyPane familyPane;

    @Mock
    private ListSelectionListener listener;

    @Captor
    private ArgumentCaptor<ListSelectionEvent> listSelectionEventArgumentCaptor;

    @BeforeEach
    void setUp() {
        familyPane = new FamilyPane();
        familyPane.addListSelectionListener(listener);
    }

    @Test
    public void modifiesSelectedValue() {
        String expectedFamily = Font.DIALOG;

        familyPane.setSelectedFamily(expectedFamily);
        String actualFamily = familyPane.getSelectedFamily();

        assertThat(actualFamily).isEqualTo(expectedFamily);
    }

    @Test
    public void doesNotAcceptUnknownValue() {
        String expectedFamily = "This font family does not exist.";

        familyPane.setSelectedFamily(expectedFamily);
        String actualFamily = familyPane.getSelectedFamily();

        assertThat(actualFamily).isNull();
    }

    @Test
    public void involvesListener() {
        familyPane.addListSelectionListener(listener);

        familyPane.setSelectedFamily(Font.DIALOG);

        verify(listener, atLeastOnce()).valueChanged(listSelectionEventArgumentCaptor.capture());
        ListSelectionEvent listSelectionEvent = listSelectionEventArgumentCaptor.getValue();
        assertThat(listSelectionEvent.getValueIsAdjusting()).isFalse();
    }

}

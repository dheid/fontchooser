package org.drjekyll.fontchooser.panes;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchListenerTest {

    private static final String TEXT = "text";

    private static final String FAMILY_NAME = "familyName";

    @Mock
    private FamilyPane familyPane;

    @Mock
    private KeyEvent keyEvent;

    @Mock
    private JTextField textField;

    @InjectMocks
    private SearchListener searchListener;

    @Test
    public void doesNothingIfFamilyNotFound() {

        givenSourceIsTextField();
        givenText(TEXT);
        givenFamilyName(FAMILY_NAME);

        whenKeyTyped();

        thenGetsSourceFromEvent();
        thenGetsTextFromTextField();
        verifyNoInteractions(familyPane);

    }

    @Test
    public void setsFontOnFamilyIfFound() {

        givenSourceIsTextField();
        givenText(FAMILY_NAME);
        givenFamilyName(FAMILY_NAME);

        whenKeyTyped();

        thenGetsSourceFromEvent();
        thenGetsTextFromTextField();
        verify(familyPane).setSelectedFamily(FAMILY_NAME);

    }

    private void whenKeyTyped() {
        searchListener.keyTyped(keyEvent);
    }

    private void givenFamilyName(String familyName) {
        searchListener.addFamilyName(familyName);
    }

    private void givenText(String text) {
        given(textField.getText()).willReturn(text);
    }

    private void givenSourceIsTextField() {
        given(keyEvent.getSource()).willReturn(textField);
    }

    private void thenGetsTextFromTextField() {
        verify(textField).getText();
    }

    private void thenGetsSourceFromEvent() {
        verify(keyEvent).getSource();
    }

}

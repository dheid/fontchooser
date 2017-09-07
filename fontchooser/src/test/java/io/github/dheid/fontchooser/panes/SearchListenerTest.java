package io.github.dheid.fontchooser.panes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.JTextField;
import java.awt.event.KeyEvent;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
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
    public void doesNothingIfFamilyNotFound() throws Exception {

        givenSourceIsTextField();
        givenText(TEXT);
        givenFamilyName(FAMILY_NAME);

        whenKeyTyped();

        thenGetsSourceFromEvent();
        thenGetsTextFromTextField();
        verifyZeroInteractions(familyPane);

    }

    @Test
    public void setsFontOnFamilyIfFound() throws Exception {

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

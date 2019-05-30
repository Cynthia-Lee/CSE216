import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateProfilesButtonTest {
    CreateProfilesButton b1;
    Name n1;
    Name n2;
    Name n3;
    PhoneNumber p1;
    PhoneNumber p2;
    PhoneNumber p3;

    @Before
    public void setUp() {
        JFXPanel panel = new JFXPanel();

        b1 = new CreateProfilesButton();

        n1 = new Name();
        p1 = new PhoneNumber();
        n2 = new Name();
        p2 = new PhoneNumber();
        n3 = new Name();
        p3 = new PhoneNumber();
    }

    @After
    public void tearDown() {
        b1 = null;

        n1 = null;
        p1 = null;
        n2 = null;
        p2 = null;
        n3 = null;
        p3 = null;
    }

    // The “Create Profiles” button is, indeed, disabled until all the text areas have some text in them.
    @Test
    public void enableBind() {
        // enableBind(Name n1, PhoneNumber p1, Name n2, PhoneNumber p2, Name n3, PhoneNumber p3)
        n1.setText("n");
        p1.setText("p");
        n2.setText("n");
        p2.setText("p");
        n3.setText("n");
        // p3 has no text

        b1.enableBind(n1, p1, n2, p2, n3, p3);
        assertTrue(b1.isDisable());

        p3.setText("p");
        assertFalse(b1.isDisable());

        n1.setText("Ok Name");
        n2.setText("Nn Ame");
        n3.setText("Yam Yam");
        assertFalse(b1.isDisable());

        n2.setText("");
        assertTrue(b1.isDisable());
    }

}

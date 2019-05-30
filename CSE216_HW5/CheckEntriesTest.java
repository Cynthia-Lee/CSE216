import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckEntriesTest {
    CreateProfilesButton b1;
    Name n1;
    Name n2;
    Name n3;
    PhoneNumber p1;
    PhoneNumber p2;
    PhoneNumber p3;
    CheckEntries ce;

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

        // CheckEntries ce = new CheckEntries(n1, p1, n2, p2, n3, p3);
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

    // if there is invalid information, that the error box does indeed pop up
    @Test
    public void isValid() {
        n1.setText("First Last");
        n2.setText("H H");
        n3.setText("One Two");
        p1.setText("(333) 333-1258");
        p2.setText("(512) 381-2370");
        p3.setText("(106) 242-1609");

        CheckEntries ce = new CheckEntries(n1, p1, n2, p2, n3, p3);
        assertTrue(ce.isValid());
        assertEquals("Data Saved", ce.windowTitle);

        n2.setText("Name Name");
        assertTrue(ce.isValid());
        assertEquals("Data Saved", ce.windowTitle);

        n3.setText("one Two"); // not valid
        assertFalse(ce.isValid());
        ce = new CheckEntries(n1, p1, n2, p2, n3, p3);
        assertEquals("Invalid input error", ce.windowTitle);

        p3.setText("103 214-1258"); // not valid
        assertFalse(ce.isValid());
        assertEquals("Invalid input error", ce.windowTitle);

        n3.setText("One Two");
        p1.setText("email@gmail.com"); // not valid
        assertFalse(ce.isValid());
        assertEquals("Invalid input error", ce.windowTitle);
    }

    // If there is no invalid information, the text boxes all become uneditable, and the final box saying
    // “Data Saved” does, indeed, pop-up.
    @Test
    public void confirmEntries() {
        // invalid
        n1.setText("Op Op");
        n2.setText("H");
        n3.setText("The That");
        p1.setText("(");
        p2.setText("(843) 621-2370");
        p3.setText("(521) 205-4909");
        CheckEntries ce = new CheckEntries(n1, p1, n2, p2, n3, p3);
        assertEquals("Invalid input error", ce.windowTitle);
        assertFalse(ce.isValid());
        ce.confirmEntries();
        assertTrue(n1.isEditable());
        assertTrue(n2.isEditable());
        assertTrue(n3.isEditable());
        assertTrue(p1.isEditable());
        assertTrue(p2.isEditable());
        assertTrue(p3.isEditable());

        // valid
        n1.setText("Grace Hopper");
        n2.setText("Ham Ham");
        n3.setText("Polly Parrot");
        p1.setText("(151) 372-2737");
        p2.setText("(190) 135-3583");
        p3.setText("(132) 235-4681");
        ce = new CheckEntries(n1, p1, n2, p2, n3, p3);
        assertEquals("Data Saved", ce.windowTitle);
        ce.confirmEntries();
        assertTrue(ce.isValid());
        assertFalse(n1.isEditable());
        assertFalse(n2.isEditable());
        assertFalse(n3.isEditable());
        assertFalse(p1.isEditable());
        assertFalse(p2.isEditable());
        assertFalse(p3.isEditable());
    }

}

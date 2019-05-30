import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NameTest {
    Name n1;
    Name n2;
    Name n3;
    Name n4;
    Name n5;
    Name n6;
    Name n7;
    Name n8;
    Name n9;
    Name n10;
    Name n11;

    @Before
    public void setUp() {
        JFXPanel panel = new JFXPanel();

        n1 = new Name();
        n2 = new Name();
        n3 = new Name();
        n4 = new Name();
        n5 = new Name();
        n6 = new Name();
        n7 = new Name();
        n8 = new Name();
        n9 = new Name();
        n10 = new Name();
        n11 = new Name();

        n1.setText("hi There"); // lower start, upper start
        n2.setText("B b"); // upper start, lower start
        n3.setText("ABC Ab"); // all upper, normal
        n4.setText("ONE TWO");// everything upper
        n5.setText("First LaSt");
        n6.setText("R R R"); // more than two words
        n7.setText("Really Longnameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"); // name longer than 20 characters
        n8.setText(""); // empty string
        n9.setText("& #"); // not letters

        n10.setText("Bob Dole"); // valid name
        n11.setText("Correct Name"); // valid name
    }

    @After
    public void tearDown() {
        n1 = null;
        n2 = null;
        n3 = null;
        n4 = null;
        n5 = null;
        n6 = null;
        n7 = null;
        n8 = null;
        n9 = null;
        n10 = null;
        n11 = null;
    }

    @Test
    public void setPromptText() {
        assertEquals("Name", n1.getPromptText());
        assertEquals("Name", n2.getPromptText());
        assertEquals("Name", n3.getPromptText());
        assertEquals("Name", n4.getPromptText());
    }

    @Test
    public void isValid() {
        assertFalse(n1.isValid());
        assertFalse(n2.isValid());
        assertFalse(n3.isValid());
        assertFalse(n4.isValid());
        assertFalse(n5.isValid());
        assertFalse(n6.isValid());
        assertFalse(n7.isValid());
        assertFalse(n8.isValid());
        assertFalse(n9.isValid());

        assertTrue(n10.isValid());
        assertTrue(n11.isValid());
    }

    // check that valid and invalid names are in black and red
    @Test
    public void validityColor() {
        n1.validityColor(); // n1 invalid
        n2.validityColor(); // n2 invalid

        n10.validityColor(); // n10 valid
        n11.validityColor(); // n11 valid

        assertEquals("-fx-text-inner-color: red;", n1.getStyle());
        assertEquals("-fx-text-inner-color: red;", n2.getStyle());
        assertEquals("-fx-text-inner-color: black;", n10.getStyle());
        assertEquals("-fx-text-inner-color: black;", n11.getStyle());
    }

}

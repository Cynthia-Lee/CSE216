import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PhoneNumberTest {
    PhoneNumber p1;
    PhoneNumber p2;
    PhoneNumber p3;
    PhoneNumber p4;
    PhoneNumber p5;
    PhoneNumber p6;
    PhoneNumber p7;
    PhoneNumber p8;
    PhoneNumber p9;
    PhoneNumber p10;

    @Before
    public void setUp() {
        JFXPanel panel = new JFXPanel();

        p1 = new PhoneNumber();
        p2 = new PhoneNumber();
        p3 = new PhoneNumber();
        p4 = new PhoneNumber();
        p5 = new PhoneNumber();
        p6 = new PhoneNumber();
        p7 = new PhoneNumber();
        p8 = new PhoneNumber();
        p9 = new PhoneNumber();
        p10 = new PhoneNumber();

        // "(###) ###-####"

        p1.setText(""); // empty
        p2.setText("123 456-7199"); // numbers without the parenthesis
        p3.setText("(361) 812-32814"); // extra number at the end
        p4.setText("(182) 28-2847"); // only 2 numbers in the middle
        p5.setText("(12) 123-2157"); //only 2 numbers in the first part
        p6.setText("(125) 381 1083"); // no dash
        p7.setText("(326)195-3057"); // no space
        p8.setText("jane.doe@gmail.com");// letters

        p9.setText("(631) 100-9871"); // valid (631) 100-9871
        p10.setText("(532) 444-2098"); // valid (532) 444-2098
    }

    @After
    public void tearDown() {
        p1 = null;
        p2 = null;
        p3 = null;
        p4 = null;
        p5 = null;
        p6 = null;
        p7 = null;
        p8 = null;
        p9 = null;
        p10 = null;
    }


    // (###) ###-####
    @Test
    public void setPromptText() {
        assertEquals("(###) ###-####", p1.getPromptText());
        assertEquals("(###) ###-####", p2.getPromptText());
        assertEquals("(###) ###-####", p3.getPromptText());
        assertEquals("(###) ###-####", p4.getPromptText());
    }

    @Test
    public void isValid() {
        assertFalse(p1.isValid());
        assertFalse(p2.isValid());
        assertFalse(p3.isValid());
        assertFalse(p4.isValid());
        assertFalse(p5.isValid());
        assertFalse(p6.isValid());
        assertFalse(p7.isValid());
        assertFalse(p8.isValid());

        assertTrue(p9.isValid());
        assertTrue(p10.isValid());
    }

    // check that valid and invalid numbers are in black and red
    @Test
    public void validityColor() {
        p1.validityColor(); // p1 invalid
        p2.validityColor(); // p2 invalid

        p9.validityColor(); // p9 valid
        p10.validityColor(); // p10 valid

        assertEquals("-fx-text-inner-color: red;", p1.getStyle());
        assertEquals("-fx-text-inner-color: red;", p2.getStyle());
        assertEquals("-fx-text-inner-color: black;", p9.getStyle());
        assertEquals("-fx-text-inner-color: black;", p10.getStyle());
    }

}

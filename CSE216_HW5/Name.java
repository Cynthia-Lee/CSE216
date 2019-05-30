import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name extends TextField {
    // Name is a TextField

    public Name() {
        this.setPromptText("Name");
    }

    public boolean isValid() {
        String[] parts = this.getText().split(" ");
        if(parts.length == 2 && this.getText().length() <= 20) {
            // name is valid if it consists of two words
            // only first letter of each word is upper case
            // the entire name must also be no more than 20 characters in length
            Pattern p = Pattern.compile("[A-Z][a-z]*");
            Matcher m1 = p.matcher(parts[0]);
            boolean fnUpper = m1.matches();
            Matcher m2 = p.matcher(parts[1]);
            boolean lnUpper = m2.matches();
            return fnUpper && lnUpper;
        } else {
            return false;
        }
    }

    public void validityColor() {
        if (this.isValid() == false) {
            this.setStyle("-fx-text-inner-color: red;");
        } else {
            this.setStyle("-fx-text-inner-color: black;");
        }
    }

    public void addValidityCheck() {
        this.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!this.isFocused()) { // user
                this.validityColor();
            } else {
                this.setStyle("-fx-text-inner-color: black;");
            }
        });
    }

    // The information entered by a user must be checked for validity, as defined earlier. This validity check
    // must not require the user to do anything other than simply move beyond a particular text box. For
    // example, if the user types an invalid name and just presses tab (or uses the mouse) to move to the next
    // box. From a user’s perspective, such a movement away from the box indicates that the information
    // has been completely provided. In case the information is invalid, the text must be rendered in red
    // immediately after (but not during) the information is entered. The validity checking (for names) is
    // shown in Fig. 4. You must do this for names as well as phone numbers.

}

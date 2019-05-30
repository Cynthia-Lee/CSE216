import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber extends TextField {
    // PhoneNumber is a TextField

    public PhoneNumber() {
        this.setPromptText("(###) ###-####");
    }

    public boolean isValid() {
        // it is a 10 digit number formatted as a string in the form
        // “(###) ###-####”, where each # is a single digit
        Pattern p = Pattern.compile("\\(\\d\\d\\d\\)\\s\\d\\d\\d-\\d\\d\\d\\d");
        Matcher m = p.matcher(this.getText());
        return m.matches();
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

}

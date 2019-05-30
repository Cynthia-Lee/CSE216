import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Button;

public class CreateProfilesButton extends Button {

    public CreateProfilesButton() {
        super("Create Profiles");
        this.setDisable(true);
    }

    public void enableBind(Name n1, PhoneNumber p1, Name n2, PhoneNumber p2, Name n3, PhoneNumber p3) {
        BooleanBinding boolBind = n1.textProperty().isEmpty().or(p1.textProperty().isEmpty())
                .or(n2.textProperty().isEmpty()).or(p2.textProperty().isEmpty())
                .or(n3.textProperty().isEmpty()).or(p3.textProperty().isEmpty());
        this.disableProperty().bind(boolBind);
    }

    /*
    public void createEnable(Name n1, PhoneNumber p1, Name n2, PhoneNumber p2, Name n3, PhoneNumber p3) {
        if (n1.getText().isEmpty() && p1.getText().isEmpty() && n2.getText().isEmpty() && p2.getText().isEmpty() && n3.getText().isEmpty() && p3.getText().isEmpty()) {
            this.setDisable(true);
        } else {
            this.setDisable(false);
        }
    }
    */

    // name1.setOnMouseExited(event -> System.out.println("Mouse Exited"));
    /*
    name1.setOnMouseExited(e -> {
        if (name1.isValid() == false) {
            name1.setStyle("-fx-text-inner-color: red;");
        } else {
            name1.setStyle(null);
        }
    });
    */
}

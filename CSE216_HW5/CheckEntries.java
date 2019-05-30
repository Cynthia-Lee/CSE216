import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CheckEntries {
    Name n1;
    PhoneNumber p1;
    Name n2;
    PhoneNumber p2;
    Name n3;
    PhoneNumber p3;
    String windowTitle;

    public CheckEntries(Name n1, PhoneNumber p1, Name n2, PhoneNumber p2, Name n3, PhoneNumber p3) {
        this.n1 = n1;
        this.p1 = p1;
        this.n2 = n2;
        this.p2 = p2;
        this.n3 = n3;
        this.p3 = p3;

        if(this.isValid()) {
            windowTitle = "Data Saved";
        } else {
            windowTitle = "Invalid input error";
        }
    }

    public boolean isValid() {
        if(n1.isValid() && p1.isValid() && n2.isValid() && p2.isValid() && n3.isValid() && p3.isValid()) {
            return true;
        } else {
            return false;
        }
    }

    public void displayPopUp() {
        if(this.isValid()) {
            windowTitle = "Data Saved";
            validInputWindow();
        } else {
            windowTitle = "Invalid input error";
            invalidInputWindow();
        }
    }

    public void confirmEntries() {
        if(this.isValid()) {
            this.uneditableEntries(n1, p1, n2, p2, n3, p3);
        }
    }

    /*
    public void confirmEntries(CreateProfilesButton create) {
        create.setOnAction(e -> {
            if(this.isValid()) {
                ValidInput v = new ValidInput();
                uneditableEntries(n1, p1, n2, p2, n3, p3);
                v.showAndWait();
            } else {
                InvalidInput i = new InvalidInput();
                i.showAndWait();
            }
        });
    }
    */

    public void uneditableEntries(Name n1, PhoneNumber p1, Name n2, PhoneNumber p2, Name n3, PhoneNumber p3) {
        n1.setEditable(false);
        p1.setEditable(false);
        n2.setEditable(false);
        p2.setEditable(false);
        n3.setEditable(false);
        p3.setEditable(false);
    }

    public void validInputWindow() {
        Alert v = new Alert(Alert.AlertType.CONFIRMATION, "The profiles have been saved and added to the database." , ButtonType.CLOSE);
        v.setTitle("Data Saved");
        v.setHeaderText(null);
        v.showAndWait();
    }

    public void invalidInputWindow() {
        Alert iv = new Alert(Alert.AlertType.ERROR, "INVALID INPUT: you have attempted to provide one or more invalid input(s). Please correct the information displayed in red and retry.", ButtonType.CLOSE);
        iv.setTitle("Invalid input error");
        iv.setHeaderText(null);
        iv.showAndWait();
    }

}

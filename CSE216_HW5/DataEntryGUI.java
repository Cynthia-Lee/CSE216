import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DataEntryGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        // Place nodes in the pane at the positions, column, row
        // name and phone number type in boxes
        Name name1 = new Name();
        Name name2 = new Name();
        Name name3 = new Name();

        PhoneNumber number1 = new PhoneNumber();
        PhoneNumber number2 = new PhoneNumber();
        PhoneNumber number3 = new PhoneNumber();

        pane.add(name1, 0,0);
        pane.add(number1, 1,0);
        pane.add(name2, 0,1);
        pane.add(number2, 1,1);
        pane.add(name3, 0,2);
        pane.add(number3, 1,2);

        // Button
        // Button create = new Button("Create Profiles");
        CreateProfilesButton create = new CreateProfilesButton();
        create.enableBind(name1, number1, name2, number2, name3, number3);

        name1.addValidityCheck();
        number1.addValidityCheck();
        name2.addValidityCheck();
        number2.addValidityCheck();
        name3.addValidityCheck();
        number3.addValidityCheck();

        CheckEntries check = new CheckEntries(name1, number1, name2, number2, name3, number3);
        create.setOnAction(e -> {
            check.confirmEntries();
            check.displayPopUp();
        });

        /*
        create.setOnAction(e -> {
            if(check.isValid()) {
                ValidInput v = new ValidInput();
                check.uneditableEntries(name1, number1, name2, number2, name3, number3);
                v.showAndWait();
            } else {
                InvalidInput i = new InvalidInput();
                i.showAndWait();
            }
        });
        */

        // check.confirmEntries(create);

        /*
        create.setOnAction(e -> {
            if((name1.isValid() && number1.isValid() && name2.isValid() && number2.isValid() && name3.isValid() && number3.isValid())) {
                ValidInput v = new ValidInput(name1, number1, name2, number2, name3, number3);
                v.showAndWait();
            } else {
                InvalidInput i = new InvalidInput();
                i.showAndWait();
            }
        });
        */

        root.getChildren().addAll(pane, create);
        Scene scene = new Scene(root,400, 200);

        primaryStage.setTitle("Data Entry GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

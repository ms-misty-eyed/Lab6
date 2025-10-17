package Lab6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise2 extends Application
{
    @Override
    public void start(Stage stage){
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        Label salary = new Label("Enter Salary");
        TextField tf1 = new TextField();
        Button btSalary = new Button("Add Salary");
        Label salaryTotal = new Label("0 Salary added to the file");
        Button btDone = new Button("Done");

        vbox.getChildren().addAll(salary,tf1,btSalary,salaryTotal,btDone);

        Scene scene = new Scene(vbox,200,200);
        stage.setTitle("Exercise 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

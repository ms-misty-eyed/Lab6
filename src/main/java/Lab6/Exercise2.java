package Lab6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class Exercise2 extends Application
{
    private int numSal = 0;

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
        Label showSalaries = new Label("");
        StringBuilder allSalaries = new StringBuilder();

        btSalary.setOnAction(e -> {
            String input = tf1.getText();

            try {
                double sal = Double.parseDouble(input);

                try (DataOutputStream output = new DataOutputStream(
                        new FileOutputStream("salaries.dat", true))) {

                    output.writeDouble(sal);
                    numSal++;
                    salaryTotal.setText(numSal + " Salary added to the file");
                }

            } catch (NumberFormatException ex) {
                salaryTotal.setText("Salary is invalid");
            } catch (IOException ex) {
                salaryTotal.setText("Error writing to file");
            }
        });

        btDone.setOnAction(e -> {
            try (DataInputStream input = new DataInputStream(new FileInputStream("salaries.dat"))) {
                while (true) {
                    double value = input.readDouble();
                    if (allSalaries.length() > 0) {
                        allSalaries.append(", ");
                    }
                    allSalaries.append(value);
                }
            } catch (IOException ex) {
                salaryTotal.setText("Error writing to file");
            }
            showSalaries.setText(allSalaries.toString());
        });

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

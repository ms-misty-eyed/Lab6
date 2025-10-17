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
    private double totalValue = 0;

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
        Label count = new Label("");
        Label total = new Label("");
        Label average = new Label("");

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
                salaryTotal.setText("Error writing to file in btSalary");
            }
        });

        btDone.setOnAction(e -> {
            try (DataInputStream input = new DataInputStream(new FileInputStream("salaries.dat"))) {
                while (true) {
                    double value = input.readDouble();
                    totalValue += value;
                    if (allSalaries.length() > 0) {
                        allSalaries.append(", ");
                    }
                    allSalaries.append(value);
                }
            } catch (EOFException ex) {
                // end of file reached — normal, stop reading
                salaryTotal.setText("Finito");
            } catch (IOException ex) {
                salaryTotal.setText("Error writing to file in btDone");
            }
            showSalaries.setText("Salaries: " + allSalaries.toString());
            count.setText("Count: " + Integer.toString(numSal));
            total.setText("Total: " + Double.toString(totalValue));
            average.setText("Average: " + Double.toString(totalValue/numSal));
        });

        vbox.getChildren().addAll(salary,tf1,btSalary,salaryTotal,btDone, showSalaries, count, total, average);

        //reset
        Button btReset = new Button("Reset");
        vbox.getChildren().add(btReset);

        btReset.setOnAction(e -> {
            // Clear the text field
            tf1.clear();

            // Reset the counter
            numSal = 0;

            // Clear the labels
            salaryTotal.setText("0 Salary added to the file");
            showSalaries.setText("");
            count.setText("");
            total.setText("");
            average.setText("");
            allSalaries.setLength(0);

            // Optional: clear the file
            File file = new File("salaries.dat");
            if (file.exists()) {
                file.delete(); // deletes the file completely
            }
        });


        Scene scene = new Scene(vbox,200,200);
        stage.setTitle("Exercise 1");
        stage.setScene(scene);
        stage.show();



    }




    public static void main(String[] args) {
        launch(args);
    }
}

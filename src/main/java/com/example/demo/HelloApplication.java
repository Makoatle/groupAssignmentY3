package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private static final int SIZE = 9;
    private int[][] puzzle = {
            {0, 0, 0, 0, 5, 6, 7, 8, 0},
            {0, 0, 3, 4, 5, 6, 7, 8, 0},
            {3, 3, 3, 4, 5, 0, 0, 0, 9},
            {4, 0, 4, 4, 5, 6, 7, 8, 0},
            {5, 5, 0, 5, 5, 6, 7, 8, 9},
            {6, 6, 6, 6, 6, 6, 7, 8, 9},
            {0, 7, 7, 7, 0, 7, 7, 8, 9},
            {8, 8, 8, 8, 0, 8, 8, 0, 9},
            {9, 0, 9, 9, 9, 0, 9, 9, 0}

    };
    private int[][] solution = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {2, 2, 3, 4, 5, 6, 7, 8, 9},
            {3, 3, 3, 4, 5, 6, 7, 8, 9},
            {4, 4, 4, 4, 5, 6, 7, 8, 9},
            {5, 5, 5, 5, 5, 6, 7, 8, 9},
            {6, 6, 6, 6, 6, 6, 7, 8, 9},
            {7, 7, 7, 7, 7, 7, 7, 8, 9},
            {8, 8, 8, 8, 8, 8, 8, 8, 9},
            {9, 9, 9, 9, 9, 9, 9, 9, 9}
    };
    private TextField[][] grid = new TextField[SIZE][SIZE];

    @Override
    public void start(Stage primaryStage) {
        GridPane root = createGridPane();

        Button checkButton = createCheckButton();

        VBox vbox = new VBox(10, root, checkButton);
        Scene scene = new Scene(vbox, 400, 450);
        scene.setFill(Color.BLUE); // Set the background color of the scene

        primaryStage.setTitle("Sudoku Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY))); // Set background color of gridPane
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                TextField textField = new TextField();
                textField.setPrefWidth(40);
                textField.setPrefHeight(40);
                textField.setAlignment(javafx.geometry.Pos.CENTER);

                if (puzzle[i][j] != 0) {
                    textField.setText(String.valueOf(puzzle[i][j]));
                    textField.setEditable(false);
                }

                grid[i][j] = textField;
                gridPane.add(textField, j, i);
            }
        }
        return gridPane;
    }

    private Button createCheckButton() {
        Button checkButton = new Button("Check");
        checkButton.setOnAction(e -> checkSolution());
        return checkButton;
    }

    private void checkSolution() {
        int[][] userSolution = getUserSolution();

        if (checkEqual(userSolution, solution)) {
            showAlert(Alert.AlertType.INFORMATION, "Congratulations!", " Congradulations!!!! You have solved the Sudoku puzzle!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Your solution is incorrect. Please try again.");
        }
    }

    private int[][] getUserSolution() {
        int[][] userSolution = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String input = grid[i][j].getText();
                try {
                    userSolution[i][j] = Integer.parseInt(input);
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return userSolution;
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean checkEqual(int[][] arr1, int[][] arr2) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

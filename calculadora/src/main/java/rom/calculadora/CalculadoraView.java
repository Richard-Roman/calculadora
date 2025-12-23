package rom.calculadora;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CalculadoraView {

    private final VBox root;
    private final TextField display;
    private final CalculadoraUIController controller;

    public CalculadoraView() {
        controller = new CalculadoraUIController();

        display = new TextField("0");
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setEditable(false);
        display.setPrefHeight(60);

        GridPane botones = crearBotonera();

        root = new VBox(10, display, botones);
        root.setPadding(new Insets(10));
    }

    private GridPane crearBotonera() {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        String[][] etiquetas = {
            {"%", "√", "x²", "1/x"},
            {"CE", "C", "←", "÷"},
            {"7", "8", "9", "×"},
            {"4", "5", "6", "−"},
            {"1", "2", "3", "+"},
            {"±", "0", ".", "="}
        };

        for (int fila = 0; fila < etiquetas.length; fila++) {
            for (int col = 0; col < etiquetas[fila].length; col++) {
                Button btn = new Button(etiquetas[fila][col]);
                btn.setPrefSize(70, 50);
                btn.setOnAction(e ->
                    controller.procesarEntrada(btn.getText(), display)
                );
                grid.add(btn, col, fila);
            }
        }
        return grid;
    }

    public Parent getRoot() {
        return root;
    }
}

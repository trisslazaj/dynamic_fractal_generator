package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rendering.FractalManager;
import javafx.geometry.Pos;


public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        int canvasWidth = 800;
        int canvasHeight = 600;

        // Create the canvas
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        FractalManager fractalManager = new FractalManager(canvas);
        fractalManager.drawFractal();

        // Create zoom buttons
        Button zoomInButton = new Button("Zoom In");
        Button zoomOutButton = new Button("Zoom Out");
        Button resetZoomButton = new Button("Reset Zoom");

        // Set button actions
        zoomInButton.setOnAction(e -> {
            fractalManager.zoom(1.1); // Zoom in
            fractalManager.drawFractal(); // Redraw fractal
        });

        zoomOutButton.setOnAction(e -> {
            fractalManager.zoom(1 / 1.1); // Zoom out
            fractalManager.drawFractal(); // Redraw fractal
        });

        resetZoomButton.setOnAction(e -> {
            fractalManager.resetZoom(); // Reset zoom to initial values
            fractalManager.drawFractal(); // Redraw fractal
        });

        // Add buttons to a VBox
        VBox controls = new VBox(10, zoomInButton, zoomOutButton, resetZoomButton);
        controls.setAlignment(Pos.CENTER);

        // Combine canvas and controls into an HBox
        HBox root = new HBox(10, canvas, controls);
        root.setAlignment(Pos.CENTER);

        // Create the scene
        Scene scene = new Scene(root, canvasWidth + 200, canvasHeight);
        primaryStage.setTitle("Dynamic Fractal Generator");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
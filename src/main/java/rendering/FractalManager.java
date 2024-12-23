package rendering;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
// import javafx.scene.paint.Color;
import utils.ColourMapper;

public class FractalManager {
    private final Canvas canvas;
    private double zoomLevel = 1.0;
    private double minX = -2.5, maxX = 1.0, minY = -1.0, maxY = 1.0;


    public FractalManager(Canvas canvas) {
        this.canvas = canvas;
    }

    public void zoom(double zoomFactor) {
        zoomLevel *= zoomFactor;

        // Clamp zoom level to prevent excessive zooming
        zoomLevel = Math.max(0.1, Math.min(zoomLevel, 10000.0));

        double centerX = (minX + maxX) / 2;
        double centerY = (minY + maxY) / 2;
        double width = (maxX - minX) / zoomFactor;
        double height = (maxY - minY) / zoomFactor;

        minX = centerX - width / 2;
        maxX = centerX + width / 2;
        minY = centerY - height / 2;
        maxY = centerY + height / 2;
    }

    public void resetZoom() {
        zoomLevel = 1.0;
        minX = -2.5;
        maxX = 1.0;
        minY = -1.0;
        maxY = 1.0;
    }
    
    
    public void drawFractal() {
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
        int maxIterations = (int) (1000 * Math.log10(zoomLevel + 1)); // Dynamic iterations based on zoom

        // Create a colour mapper with smoother colours
        ColourMapper colourMapper = new ColourMapper(maxIterations);

        // Get the GraphicsContext and PixelWriter
        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelWriter pixelWriter = gc.getPixelWriter();

        // Render the Mandelbrot fractal
        MandelbrotRenderer renderer = new MandelbrotRenderer(width, height, maxIterations, colourMapper);
        renderer.render(pixelWriter, minX, maxX, minY, maxY);
    }
}

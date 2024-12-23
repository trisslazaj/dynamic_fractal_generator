package utils;

import javafx.scene.paint.Color;

public class ColourMapper {
    private final int maxIterations;

    public ColourMapper(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public Color map(int iteration) {
        if (iteration >= maxIterations) {
            return Color.BLACK;
        }

        double t = (double) iteration / maxIterations;
        double r = 9 * (1 - t) * t * t * t;
        double g = 15 * (1 - t) * (1 - t) * t * t;
        double b = 8.5 * (1 - t) * (1 - t) * (1 - t) * t;

        return Color.color(clamp(r), clamp(g), clamp(b));
    }

    private double clamp(double value) {
        return Math.min(1.0, Math.max(0.0, value));
    }
}
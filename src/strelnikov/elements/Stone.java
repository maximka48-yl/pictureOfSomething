package strelnikov.elements;

import java.awt.*;

public class Stone {
    private int x0;
    private int y0;
    private int width;
    private int height;
    private int amplitude;
    private double division;
    private Color color;
    private int k;
    private boolean outline;

    /**
     * *
     *
     * @param x0
     * @param y0
     * @param width
     * @param height
     * @param division should be between 0 & 1: 0 < division < 1
     */
    public Stone(int x0, int y0, int width, int height, int amplitude, double division, Color color, boolean flip, boolean outline) {
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = height;
        this.amplitude = amplitude;
        this.division = division;
        this.color = color;
        this.k = flip ? -1 : 1;
        this.outline = outline;
    }
    public Stone(int x0, int y0, int width, int height, int amplitude, double division, Color color, boolean flip) {
        this(x0, y0, width, height, amplitude, division, color, flip, false);
    }

    public Stone(int x0, int y0, int width, int height, int amplitude, double division, Color color) {
        this(x0, y0, width, height, amplitude, division, color, false);
    }

    public void draw(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        Polygon p = new Polygon();
        for (int i = 0; i < width * division; i++) {
            p.addPoint(x0 + i,
                    (int) (-k * Math.sin(Math.PI * (i / (width * division / amplitude))) * amplitude - k * (height / (division * width)) * (i) + (k == -1 ? 0 : y0)));
        }
        for (int i = (int) (width * division); i <= width; i++) {
            p.addPoint(x0 + i,
                    (int) (-k * Math.sin(Math.PI * (i / (width * (1 - division) / amplitude))) * amplitude + k * (height / ((1 - division) * width)) * (i) - k * height / (1 - division) + (k == -1 ? 0 : y0)));
        }
        graphics.setColor(color);
        graphics.fill(p);
        graphics.draw(p);
        if (outline) {
            graphics.setColor(Color.BLACK);
            graphics.draw(p);
        }
    }
}
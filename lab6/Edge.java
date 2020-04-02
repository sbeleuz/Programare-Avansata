import java.awt.*;
import java.awt.geom.Line2D;

public class Edge extends Line2D.Double {
    private Color color;

    public Edge(Point x, Point y, Color color) {
        super(x, y);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

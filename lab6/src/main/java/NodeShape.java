import java.awt.*;
import java.awt.geom.Ellipse2D;

public class NodeShape extends Ellipse2D.Double {
    private Color color;

    public NodeShape(double x0, double y0, double radius, Color color) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}

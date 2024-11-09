package decoder;

import java.util.List;
import java.util.Arrays;

public class Position {
    private double x;
    private double y;
    private double z;

    // Getters and Setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public List<Double> getCoordinates() {
        return Arrays.asList(x, y, z);
    }
}
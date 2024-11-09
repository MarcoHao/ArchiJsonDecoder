package decoder;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Segments {
    private Metadata metadata;
    private String type;
    private String uuid;
    private List<Double> matrix;  // 矩阵是一个List，可以存储16个元素
    private Properties properties;
    private Position  position;
    private int size;
    private List<Double> coordinates; // 坐标是一个List，可以存储一组双精度数字
    private List<Map<String,Double>> points;
    private boolean filled;
    private boolean closed;

    // Constructor

    public Segments() {
    }

    // Getters and setters

    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public List<Double> getMatrix() {
        return matrix;
    }
    public void setMatrix(List<Double> matrix) {
        this.matrix = matrix;
    }
    public Properties getProperties() {
        return properties;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public List<Double> getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
    public void setPoints(List<Double> coordinates) {
        List<Map<String,Double>> points = new ArrayList<>();

        for (int i = 0; i < coordinates.size(); i += 3) {
            Map<String,Double> tempPoint = new HashMap<>();
            tempPoint.put("x", coordinates.get(i));
            tempPoint.put("y", -coordinates.get(i + 1));
            tempPoint.put("z", -coordinates.get(i + 2));
            points.add(tempPoint);
        }

        this.points = points;
    }
    public List<Map<String,Double>> getPoints() {
           return points;
    }
    public boolean isFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public boolean isClosed() {
        return closed;
    }
    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}

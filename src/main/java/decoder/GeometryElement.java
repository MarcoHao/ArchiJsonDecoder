package decoder;

import java.util.List;
import java.util.Map;

public class GeometryElement  {
    private Metadata metadata;
    private String type;
    private String uuid;
    private List<Double> matrix;  // 一个4x4矩阵用于表示图形的变换
    private Map<String, Object> properties;
    private Position position;
    private Double height;
    private Segments segments;

    // Getters and Setters

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
    public Map<String, Object> getProperties() {
        return properties;
    }
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
    public List<Double> getPosition() {
        return position.getCoordinates();
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Double getHeight() {
        return height;
    }
    public void setHeight(Double height) {
        this.height = height;
    }
    public Segments getSegments() {
        return segments;
    }
    public void setSegments(Segments segments) {
        this.segments = segments;
    }
 }

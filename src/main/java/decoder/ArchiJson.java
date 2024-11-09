package decoder;

import java.util.List;
import java.util.Map;

public class ArchiJson {
    private List<GeometryElement> geometryElements;
    private Map<String, Object> properties;

    // Getters and Setters

    public List<GeometryElement> getGeometryElements() {
        return geometryElements;
    }

    public void setGeometryElements(List<GeometryElement> geometryElements) {
        this.geometryElements = geometryElements;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Integer getLengthOfGeo() {
        return geometryElements.size();
    }
}

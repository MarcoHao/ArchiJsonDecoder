package decoder;

import java.util.Map;

public class Properties {
    private boolean showEdge;
    private double height;
    private int color;
    private Map<String,String> tags;
    private String types;

    // Getters and Setters

    public boolean isShowEdge() {
        return showEdge;
    }

    public void setShowEdge(boolean showEdge) {
        this.showEdge = showEdge;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String,String> tags) {
        this.tags = tags;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}

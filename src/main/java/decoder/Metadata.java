package decoder;

public class Metadata {
    private String protocol;
    private double version;
    private String type;

    // Getters and Setters

    public String getProtocol() {
        return protocol;
    }

    public double getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package visualizer;

import decoder.ArchiJson;
import decoder.Decoder;
import processing.core.PApplet;
import peasy.PeasyCam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 负责接受Decoder中的几何信息，渲染到Processing画布上

public class Visualizer extends PApplet {
    PeasyCam cam;  // 创建一个用于控制视角的PeasyCam
    private List<List<Map<String,Double>>> allPoints;  // 负责接受和存储所有的几何点信息
    private List<Double> allHeights;  // 负责接受和存储所有的Geo高度信息
    private List<Shape> shapes;

    // Constructor,接受archiJson对象
    public Visualizer(List<List<Map<String, Double>>> allPoints, List<Double> allHeights){
        this.allPoints = allPoints;
        this.allHeights = allHeights;
    }

    public void settings(){
        size(800,600, P3D);

    }
    // 初始化setup(),加载数据
    public void setup() {
        cam = new PeasyCam(this, 500);  // 创建PeasyCam
        cam.setMaximumDistance(50);
        cam.setMaximumDistance(1000);

//        System.out.println("GetShapeSize:" + allPoints.size());

        if (allPoints == null) {
            System.out.println("Error: Failed to get points.");
        }

        shapes = new ArrayList<>();

        // 一次性创建shape对象，保证渲染的连续性
        for (int i = 0; i < allPoints.size(); i++) {
            List<Map<String, Double>> points = allPoints.get(i);
            float height = allHeights.get(i).floatValue();
            System.out.println("Creating Shape with " + points.size() + " points");  // 输出每个Shape的点数
            Shape shape = new Shape(this, points, height);
            shapes.add(shape);
        }
        System.out.println("Finished Creating Shapes……");

        cam.setActive(true);
        cam.lookAt(0, 0, 0);

    }

    public void draw() {
        background(255);
//        translate(width / 2, height / 2);
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            shape.drawGeometry();  // 绘制每个shape的几何体
            System.out.println("DrawingGeoIndex: " + i);
        }
    }
}

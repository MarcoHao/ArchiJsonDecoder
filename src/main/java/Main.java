import decoder.ArchiJson;
import decoder.Decoder;
import processing.core.PApplet;
import visualizer.Visualizer;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Map;

public class Main {
    public static List<List<Map<String, Double>>> allPoints;
    public static List<Double> allHeights;
    public static List<Integer> allColors;

    // 主程序
    public static void main(String[] args){
        // Step1：解析ArchiJson数据
        ArchiJson archiJson = Decoder.readArchiJson("./Block.archijson");
        System.out.println("Finished Decoding");

        // Step2.1：获取Geo的点信息
        System.out.println("Getting Segments'coordinates");
        allPoints = Decoder.getGeometrySegmentsPoints(archiJson);
        System.out.println("Total shapes: " + allPoints.size());
        // Step2.2：获取Geo的高度信息
        allHeights = Decoder.getGeometryHeights(archiJson);
        // Step2.3：获取Geo的颜色信息
        allColors = Decoder.getGeometryColor(archiJson);
        // Step2.4：检查输出
        for (int i = 0; i < allPoints.size(); i++) {
            System.out.println("Shape " + i + " 's points size: " + allPoints.get(i).size());
            System.out.println("Shape" + i + " 's height is " + allHeights.get(i));
            System.out.println("Shape" + i + "'s color is " + allColors.get(i));
        }

//        Step3：启动processing的图形渲染部分，由Visualizer类完成，完全解耦的过程
        Visualizer visualizer = new Visualizer(allPoints, allHeights);
        PApplet.runSketch(new String[]{"Visualizer"}, visualizer);
    }
}
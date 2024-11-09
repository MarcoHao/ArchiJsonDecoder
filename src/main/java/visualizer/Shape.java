package visualizer;

import processing.core.PApplet;
import peasy.PeasyCam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// 负责处理单个几何元素，并且转化为shape对象，然后返回给Visualizer类中进行渲染

public class Shape  {
    private PApplet applet;
    private PeasyCam cam;
    private List<Map<String, Double>> points;  // 传入点的坐标
    private List<int[]> edges;  // 边界的索引列表？
    private List<int[]> faces;  // 面的索引列表
    private float height;

    // Constructor
    public Shape(PApplet applet, List<Map<String, Double>> points, float height){
        super();
        this.applet = applet;
        setPoints(points);
        this.height = height;
        extrude(height);
    }


    // 用于获取每个点的坐标
    public void setPoints(List<Map<String, Double>> points) {
        this.points = points;  // 这里的points应该是只有一个列表的，就是对应geometry的segments的那个列表，直接在外部遍历
        this.edges = new ArrayList<>();
        this.faces = new ArrayList<>();
        generateEdges();
        generateFaces();
        System.out.println("Points size: " + points.size());
    }

    // 开始绘制
    public void drawGeometry() {
        applet.lights();  // 启用光源

        // 绘制点
//        applet.stroke(0);
//        for (Map<String,Double> point : points) {
//            float x = point.get("x").floatValue();
//            float y = point.get("y").floatValue();
//            float z = point.get("z").floatValue();
//            applet.point(x, y, z);
//        }

        // 绘制面
        applet.fill(153, 51, 34, 255);
        applet.stroke(153, 51, 34, 255);
        for (int[] faceIndex : faces) {
            // 获取面的顶点序列，开始绘制形状
            applet.beginShape();
            for (int index : faceIndex) {
                // 获取面的各个顶点，添加进入shape中
                Map<String, Double> point = points.get(index);  // 获取面所在的点列表
                applet.vertex(point.get("x").floatValue(), point.get("y").floatValue(), point.get("z").floatValue());
//                System.out.println("DrawingProcess" + index);
            }
            applet.endShape(PApplet.CLOSE);
        }

        // 绘制边界
        for (int[] edge : edges) {
            // 获取边界的各个线的点序列
            // 获取起点，终点
            Map<String, Double> startPoint = points.get(edge[0]);
            Map<String, Double> endPoint = points.get(edge[1]);
            applet.line(startPoint.get("x").floatValue(), startPoint.get("y").floatValue(), startPoint.get("z").floatValue(),
                 endPoint.get("x").floatValue(), endPoint.get("y").floatValue(), endPoint.get("z").floatValue());
        }
    }

    // 生成挤出的面和连接的面
    public void extrude(float height) {
        List<Map<String,Double>> extrudedPoints = new ArrayList<>();
        for (Map<String,Double> point : points) {
            Map<String,Double> extrudePoint = new HashMap<>(point);  // 复制了一个point
            extrudePoint.put("z", point.get("z") + height);
            extrudedPoints.add(extrudePoint);
        }

        // 将挤出的点加入到points中
        points.addAll(extrudedPoints);

        // 创建挤出连接线
        for (int i = 0; i < points.size()/2; i++) {
            edges.add(new int[]{i, i+ points.size()/2});  // 这里points.size()/2是因为重新更新之后的points已经是包括了两个部分的点了，所以是原来的点的规模
        }

        // 创建连接面
        for (int i = 0; i < points.size()/2 - 1; i++) {
            faces.add(new int[]{i, i + 1, i + 1 + points.size()/2, i+ points.size()/2});  // 逆时针加入点位，int[]列表
        }

        // 创建顶面
        List<Integer> topFace = new ArrayList<>();
        for (int i = points.size()/2; i < points.size(); i++) {
            topFace.add(i);
        }
        faces.add(topFace.stream().mapToInt(i -> i).toArray());
    }

    // 生成边界的索引列表
    private void generateEdges() {
        for (int i = 0; i < points.size(); i++) {
            int next = (i + 1) % points.size();
            edges.add(new int[]{i, next});  // 形成一个[startPoint，endPoint]的列表之后然后添加到边的总体列表int[]中
        }
//        System.out.println("Edges generated: " + edges.size());
    }

    // 生成面的索引列表
    private void generateFaces() {
        // 整个点集构成一个面
        List<Integer> face = new ArrayList<>();
        for (int i = 0; i < points.size(); i ++) {
            face.add(i);
        }
        faces.add(face.stream().mapToInt(i -> i).toArray());  // 将 List 转换为 int[],提升运行速度
//        System.out.println("Faces generated: " + faces.size());
    }

}

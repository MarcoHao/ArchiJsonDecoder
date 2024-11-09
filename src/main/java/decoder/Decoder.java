package decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Decoder {
    private static List<List<Map<String,Double>>> allPoints = new ArrayList<>();

    // 基础的获取方法
    private static ArchiJson readArchiJsonBase(String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
//
        ArchiJson archiJson = null;
        try {
            // 从文件中读取 JSON 数据并转换为ArchiJson对象
            archiJson  = objectMapper.readValue(new File(filepath), ArchiJson.class);
            System.out.println("ArchiJson loaded successfully.");
        } catch (IOException e) {
            System.out.println("读取 JSON 文件时出错：" + e.getMessage());
            e.printStackTrace();
        }
        return archiJson;
    }
    // 带检查的获取方法
    public static ArchiJson readArchiJson(String filepath){
        // 读取json
        ArchiJson archiJson = readArchiJsonBase(filepath);

        // 检查结果
        if (archiJson != null) {
            System.out.println("成功读取数据： " + archiJson);
            System.out.println("读取到的几何列表长度为：" + archiJson.getLengthOfGeo());
            return archiJson;
        } else {
            System.out.println("读取数据失败！");
            return null;
        }
    }
    // 获取几何位置
    public static void getGeometryPosition(ArchiJson archiJson) {
        for (int i = 0; i < archiJson.getLengthOfGeo(); i++) {
            System.out.println(archiJson.getGeometryElements().get(i).getPosition());
        }
    }
//     获取几何标签
    public static void getGeometryTags(ArchiJson archiJson) {
        for (int i = 0; i < archiJson.getLengthOfGeo(); i++) {
            LinkedHashMap tags = (LinkedHashMap) archiJson.getGeometryElements().get(i).getProperties().get("tags");
        }
    }
    // 获取几何边线坐标
    public static List<List<Map<String, Double>>> getGeometrySegmentsPoints(ArchiJson archiJson) {
        for (int i = 0; i < archiJson.getLengthOfGeo(); i++) {
            // 获取Segments对象
            Segments segment =  archiJson.getGeometryElements().get(i).getSegments();

            // 确保setPoints已经被调用，初始化 points
            if (segment.getCoordinates() != null && segment.getPoints() == null) {
                segment.setPoints(segment.getCoordinates());
            }
            // 获取几何点信息
            List<Map<String,Double>> tempPoints = segment.getPoints();
            if (tempPoints != null) {
                allPoints.add(tempPoints);
            }
        }
        return allPoints;
    }
    // 获取matrix信息
    public static void getGeometryMatrix(ArchiJson archiJson) {
        for (int i = 0; i < archiJson.getLengthOfGeo(); i++) {
            System.out.println(archiJson.getGeometryElements().get(i).getMatrix());
        }
    }
    // 获取高度信息
    public static List<Double> getGeometryHeights (ArchiJson archiJson) {
        List<Double> allHeights = new ArrayList<>();
        for (int i = 0; i < archiJson.getLengthOfGeo(); i++) {
            allHeights.add(archiJson.getGeometryElements().get(i).getHeight());
        }
        return allHeights;
    }
    // 获取颜色信息
    public static List<Integer> getGeometryColor (ArchiJson archiJson) {
        List<Integer> allColors = new ArrayList<>();
        for (int i = 0; i < archiJson.getLengthOfGeo(); i++) {
            Object colorValue = archiJson.getGeometryElements().get(i).getProperties().get("color");
            if (colorValue != null) {
                allColors.add(Integer.valueOf(colorValue.toString()));
            } else {
                allColors.add(1000);
            }
        }
        return allColors;
    }
}

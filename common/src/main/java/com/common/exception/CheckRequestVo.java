package com.common.exception;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


/**
 * 参数校验
 * @author 念着倒才子傻
 */
public class CheckRequestVo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2","3");
        PointsToCsvFile(list);
    }

    private static void PointsToCsvFile(List<String> pointsList){
        if (pointsList!=null && pointsList.size() > 0){
            // 表格头
            String[] headArr = new String[]{"PointId", "X", "Y"};
            //CSV文件路径及名称
//            LocalDateTime localDateTime = LocalDateTime.now();
//            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            // CSV文件路径
            String filePath = "E:\\TestCsvDirectory";
            // CSV文件名称
            String fileName = "CSV_"+ 123 +".csv";
            File csvFile = null;
            BufferedWriter csvWriter = null;
            try {
                // 读取文件
                csvFile = new File(filePath + File.separator + fileName);
                // 文件不存在
                if (!csvFile.exists()){
                    System.out.println("不存在");
                    File parent = csvFile.getParentFile(); 
                    if (parent != null && !parent.exists()) {
                        parent.mkdirs();
                    }
                    csvFile.createNewFile();
                }else {
                    // 更改原来文件名称
                    String fileNameBack = "CSV_"+ 1234 +"_back.csv";
                    File fileBack = new File(filePath + File.separator + fileNameBack);
                    csvFile.renameTo(fileBack);
                }
                // GB2312使正确读取分隔符","
                csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile),  StandardCharsets.UTF_8), 1024);
                // 写入文件头部标题行
                csvWriter.newLine();
                csvWriter.write(String.join(",", headArr));

                // 写入文件内容
                for (String points : pointsList) {
                    csvWriter.newLine();
                    csvWriter.write(points+","+points+", ,"+points);
                }
//                csvWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    csvWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


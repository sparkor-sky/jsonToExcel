package com.wuxi.tools;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Json to Excel
 */
public final class JsonFileToExcel {

    private static final int DEFAULT_ROW_COUNT = 100000;

    public static void trans(String from){
        trans(from, null, new HashMap<>(), DEFAULT_ROW_COUNT);
    }

    public static void trans(String from, String timeField){
        trans(from, timeField, new HashMap<>(), DEFAULT_ROW_COUNT);
    }

    public static void trans(String from, Map<String, String> rowTitle){
        trans(from, null, rowTitle, DEFAULT_ROW_COUNT);
    }

    /**
     * Source file content must be json list with out "[" or "]", and each line must be a json object that not end with "," !
     * @param from source file path of json
     * @param timeField the key name of timeField
     * @param rowTitle the key is the fileName or jsonObject, the value is the target row title in excel
     */
    public static void trans(String from, String timeField, Map<String, String> rowTitle, int rowLimit){
        File file = new File(from);
        String targetDir = truncationDir(from);
        int targetPart = 1;
        FileInputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String str;
        try {
            inputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            retry:
            for (;;) {
                long rowCount = 0;
                List<String> jsonList = new LinkedList<>();
                String targetPath = targetDir + "data-0" + targetPart + ".xlsx";

                while ((str = bufferedReader.readLine()) != null) {
                    if (StringUtils.isNotBlank(str)) {
                        rowCount++;
                        jsonList.add(str.trim());
                    } else {
                        continue;
                    }
                    if(rowCount >= rowLimit){
                        writeData(jsonList, targetPath, timeField, rowTitle);
                        targetPart++;
                        jsonList.clear();
                        continue retry;
                    }
                }
                writeData(jsonList, targetPath, timeField, rowTitle);
                break;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(bufferedReader);
        }
    }

    private static void writeData(List<String> jsonList, String targetPath, String timeField, Map<String, String> rowTitle){
        if(CollectionUtils.isEmpty(jsonList)){
            return;
        }
        final JsonParser parser = new JsonParser();
        List<JsonObject> jsonObjectList = jsonList.stream().map(e -> parser.parse(e).getAsJsonObject()).collect(Collectors.toList());
        writeExcel(jsonObjectList, targetPath, timeField, rowTitle);
        System.out.println("success write file: " + targetPath + "! count: " + jsonList.size());
    }

    private static XSSFWorkbook genBook(List<JsonObject> datas, List<String> keyList, String timeField, Map<String, String> rowTitle){
        XSSFWorkbook result = new XSSFWorkbook();
        if (CollectionUtils.isNotEmpty(datas)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            XSSFSheet sheet = result.createSheet();
            // 创建表头
            XSSFRow rowHeader = sheet.createRow(0);
            for (int i = 0; i < keyList.size(); i++) {
                XSSFCell cell = rowHeader.createCell(i);
                String value = keyList.get(i);
                if(null != rowTitle && rowTitle.size() > 0){
                    String title = rowTitle.get(value);
                    if(StringUtils.isNotBlank(title)){
                        value = title;
                    }
                }
                cell.setCellValue(value);
            }
            for (int i = 0; i < datas.size(); i++) {
                XSSFRow row = sheet.createRow(i + 1);
                JsonObject json = datas.get(i);
                for (int j = 0; j < keyList.size(); j++) {
                    String key = keyList.get(j);
                    String value = "-";
                    if (StringUtils.isNotBlank(timeField) && timeField.equalsIgnoreCase(key)) {
                        JsonElement e = json.get(key);
                        try{
                            if(null != e){
                                long time = e.getAsLong();
                                if (time < 32525372800L) {
                                    time = time * 1000;
                                }
                                value = format.format(new Date(time));
                            }
                        }catch (NumberFormatException ex){
                            value = e.getAsString();
                        }
                    } else {
                        JsonElement e = json.get(key);
                        if(null != e){
                            value = e.getAsString();
                        }
                    }
                    row.createCell(j).setCellValue(value);
                }
            }

        } else {
            XSSFSheet sheet = result.createSheet();
            // 创建表头
            XSSFRow rowHeader = sheet.createRow(0);
            XSSFCell cell = rowHeader.createCell(0);
            cell.setCellValue("无数据");
        }
        return result;
    }

    /**
     * @param datas json data list
     * @param targetPath target excel file path
     */
    private static void writeExcel(List<JsonObject> datas, String targetPath, String timeField, Map<String, String> rowTitle) {
        List<String> keyList = genKeyList(datas);
        XSSFWorkbook excel = genBook(datas, keyList, timeField, rowTitle);

        try(FileOutputStream out = new FileOutputStream(targetPath)){
            excel.write(out);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static List<String> genKeyList(List<JsonObject> datas) {
        if(CollectionUtils.isEmpty(datas)){
            new LinkedList<>();
        }
        Gson gson = new Gson();
        List<Map> maps = datas.stream().map(e -> gson.fromJson(e, Map.class)).collect(Collectors.toList());
        Set<String> keys = new HashSet<>();
        maps.forEach(e -> keys.addAll(e.keySet()));
        keys.removeIf(Objects::isNull);
        return new ArrayList<>(keys);
    }

    public static String truncationDir(String from) {
        String[] parts = from.split(File.separator);

        return from.replace(parts[parts.length - 1], "");
    }
}

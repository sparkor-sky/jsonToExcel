import com.wuxi.tools.JsonFileToExcel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This project contains a simple tool that can convert a sql file that export from mysql
 * to excel, and is a java maven project.You can find demo sql file in resource/demoFile/*
 */
public class ToolClient {
    /**
     * The file path of source sql file that export from mysql
     * */
    private static String sqlSource = "/Users/liwuxi/Desktop/nielsen.txt";

    public static void main(String[] args){
//        String targetJsonFilePath = genTargetPath();

//        // 删除旧数据
//        clean(targetJsonFilePath);
//
//        // sql文件转 json文件
//        List<String> keyList = genKeyList();
//
//        SqlFileToJsonFile.trans(sqlSource, targetJsonFilePath, new DefaultSqlLineToJson("(", "),"), keyList);

        // json 转excel
        String timeField = "timestamp";
        Map<String, String> keyTitleMap = genTitleMap();

        JsonFileToExcel.trans("/Users/liwuxi/Desktop/nielsen.txt", timeField, keyTitleMap, 100000);
    }

    /**
     * clean old target dir data
     * */
    private static void clean(String targetJsonFilePath) {
        File file = new File(targetJsonFilePath);
        boolean res = deleteDir(file.getParentFile());

        System.out.println("clean dir: " + targetJsonFilePath + ", clean result: " + res);
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * the target dir path of json file
     * */
    private static String genTargetPath() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        return truncationDir(sqlSource) + "target" + File.separator + format.format(new Date()) + File.separator + "json.txt";
    }

    public static String truncationDir(String from) {
        String[] parts = from.split(File.separator);

        return from.replace(parts[parts.length - 1], "");
    }

    private static List<String> genKeyList(){
        List<String> keyList = new LinkedList<>();
        keyList.add("id");
        keyList.add("reward_id");
        keyList.add("reward_setting_id");
        keyList.add("questionnaire_name");
        keyList.add("customer");
        keyList.add("quantity");
        keyList.add("phone");
        keyList.add("reward_phone");
        keyList.add("state");
        keyList.add("reason");
        keyList.add("distributed_time");
        keyList.add("exchanged_time");
        keyList.add("distributed_time");
        keyList.add("db_update_time");
        keyList.add("db_create_time");
        return keyList;
    }

    private static Map<String, String> genTitleMap(){
        Map<String, String> keyTitleMap = new HashMap<>();
        keyTitleMap.put("timestamp", "时间");
        return keyTitleMap;
    }
}

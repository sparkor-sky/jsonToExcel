package com.wuxi.tools;

import com.wuxi.sqlLine.SqlLineToJson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

public final class SqlFileToJsonFile {
    public static void trans(String sourceFile, String targetFile, SqlLineToJson lineToJson, List<String> keyList){
        File file = new File(sourceFile);

        File newFile = new File(targetFile);
        if(!newFile.getParentFile().exists()){
            if(!newFile.getParentFile().mkdirs()){
                System.out.println("error create dir: " + newFile.getParentFile().getAbsolutePath());
                return;
            }
        }

        if(!newFile.exists()){
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        long count = 0;
        String str;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(newFile);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            while ((str = bufferedReader.readLine()) != null) {
                if (StringUtils.isNotBlank(str)) {
                    count++;
                    String line = lineToJson.lineToString(str, keyList);
                    bufferedWriter.newLine();
                    bufferedWriter.write(line);
                }
            }
            bufferedWriter.flush();
            bufferedReader.close();
            inputStream.close();
            System.out.println("success trans sql file to json file! count: " + count);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(bufferedReader);
        }
    }
}

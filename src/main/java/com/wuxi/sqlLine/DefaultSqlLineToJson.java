package com.wuxi.sqlLine;

import com.google.gson.JsonObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class DefaultSqlLineToJson extends LineToJson {
    private String prefix;
    private String suffix;


    public DefaultSqlLineToJson(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * @param line line shoule like "(353756,100000,101093,'2020年疫情调查问卷','zsdx',5,13275462883,13275462883,3,'',1589003025987,1589003248635,'2020-05-09 05:48:29','2020-05-09 05:44:00','',2),"
     * @return
     */
    @Override
    public String lineToString(String line, List<String> keyList) {
        if(StringUtils.isNotBlank(prefix)){
            line = line.replace(prefix, "");
        }
        if(StringUtils.isNotBlank(suffix)){
            line = line.replace(suffix, "");
        }

        String[] parts = line.split(",");

        JsonObject object = new JsonObject();
        if(CollectionUtils.isNotEmpty(keyList)){
            for (int i = 0; i < keyList.size(); i++) {
                object.addProperty(keyList.get(i), parts[i]);
            }
        }

        return object.toString();
    }
}

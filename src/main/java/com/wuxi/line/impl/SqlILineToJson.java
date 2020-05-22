package com.wuxi.line.impl;

import com.google.gson.JsonObject;
import com.wuxi.line.ILineToJson;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SqlILineToJson implements ILineToJson {
    private String prefix;
    private String suffix;
    private List<String> keyList;


    public SqlILineToJson(String prefix, String suffix, List<String> keyList) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.keyList = keyList;
    }

    /**
     * @param line line shoule like "(353756,100000,101093,'2020年疫情调查问卷','zsdx',5,13275462883,13275462883,3,'',1589003025987,1589003248635,'2020-05-09 05:48:29','2020-05-09 05:44:00','',2),"
     * @return
     */
    @Override
    public String lineToString(String line) {
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

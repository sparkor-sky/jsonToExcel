package com.wuxi.line.impl;

import com.google.gson.JsonParser;
import com.wuxi.line.ILineToJson;
import org.apache.commons.lang3.StringUtils;

public class EsILineToJson implements ILineToJson {
    private String prefix;
    private String suffix;

    private static final JsonParser parser = new JsonParser();

    public EsILineToJson(){}

    public EsILineToJson(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public String lineToString(String line) {
        try {
            if(StringUtils.isNotBlank(prefix)){
                line = line.replace(prefix, "");
            }
            if(StringUtils.isNotBlank(suffix)){
                line = line.replace(suffix, "");
            }

            return parser.parse(line).getAsJsonObject().get("_source").getAsJsonObject().toString();
        }catch (Exception e){
            System.out.println("error parse es line: " + line + " , cause:" + e.getMessage());
        }
        throw new IllegalArgumentException();
    }
}

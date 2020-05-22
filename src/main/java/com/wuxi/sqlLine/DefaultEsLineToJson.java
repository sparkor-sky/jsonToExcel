package com.wuxi.sqlLine;

import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.junit.internal.Throwables;

import java.util.List;

public class DefaultEsLineToJson extends LineToJson {
    private String prefix;
    private String suffix;

    private static final JsonParser parser = new JsonParser();

    public DefaultEsLineToJson(){}

    public DefaultEsLineToJson(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * @param line line shoule like "{ "_index": "ques_backflow_monitor_data","_type": "data_backflow","_id": "AXH4nLjDbZavDk9jAZoo","_score": 5.680634,"_source": { "dataType": "ques","data": { "deviceId": "","event": 2,"id": "UeXqiTIjhdabrlvIk4qP","qiguaitype": "from-landing-page","end_question": "","test": "","code": "","batch": "","phone": "","app": "ctr","channel": "testchannel"},"timestamp": 1589013949,"id": "UeXqiTIjhdabrlvIk4qP"}}
     * @return
     */
    @Override
    public String lineToString(String line, List<String> keyList) {
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

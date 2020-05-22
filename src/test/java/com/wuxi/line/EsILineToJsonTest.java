package com.wuxi.line;

import com.wuxi.line.impl.EsILineToJson;
import org.junit.Test;

import java.util.ArrayList;

public class EsILineToJsonTest {

    private static final String esLine = "{ \"_index\": \"ques_backflow_monitor_data\",\"_type\": \"data_backflow\",\"_id\": \"AXH4nLjDbZavDk9jAZoo\",\"_score\": 5.680634,\"_source\": { \"dataType\": \"ques\",\"data\": { \"deviceId\": \"\",\"event\": 2,\"id\": \"UeXqiTIjhdabrlvIk4qP\",\"qiguaitype\": \"from-landing-page\",\"end_question\": \"\",\"test\": \"\",\"code\": \"\",\"batch\": \"\",\"phone\": \"\",\"app\": \"ctr\",\"channel\": \"testchannel\"},\"timestamp\": 1589013949,\"id\": \"UeXqiTIjhdabrlvIk4qP\"}}";
    private static final String esLine2 = "{ \"_index\": \"ques_backflow_monitor_data\",\"_type\": \"data_backflow\",\"_id\": \"AXISK2a0FBLNaeC-8mdz\",\"_score\": 5.680634,\"_source\": { \"dataType\": \"q: \"\",\"test\": \"\",\"code\": \"d03553b6c695a83d46fa91b2d1daae6c\",\"batch\": \"op.wj_xm_first_t1_20200512_1001.1717.2_927UID920Equal0\",\"phone\": \"e828f57a1f28bef70b08c3d3aa3ff272\",\"app\": \"miui\",\"channel\": \"10017172\"},\"timestamp\": 1589286113,\"id\": \"UeXqiTIjhdabrlvIk4qP\"}}";

    private ILineToJson ILineToJson = new EsILineToJson();

    @Test
    public void lineToString() {
        System.out.println(ILineToJson.lineToString(esLine2));
    }
}

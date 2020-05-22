package com.wuxi.sqlLine;

import java.util.List;

public abstract class LineToJson {

    /**
     * @param line
     * @param keyList
     * @return
     */
    public abstract String lineToString(String line, List<String> keyList);
}

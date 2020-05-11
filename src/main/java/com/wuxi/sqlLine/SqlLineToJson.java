package com.wuxi.sqlLine;

import java.util.List;

public abstract class SqlLineToJson {

    /**
     * @param line
     * @param keyList
     * @return
     */
    public abstract String lineToString(String line, List<String> keyList);
}

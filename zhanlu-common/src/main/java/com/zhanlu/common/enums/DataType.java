package com.zhanlu.common.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据类型
 */
public enum DataType {

    Byte(byte.class), Char(char.class), Int(int.class), Long(long.class), String(String.class), Float(float.class), Double(float.class),
    Boolean(boolean.class), Date(java.util.Date.class), Timestamp(java.sql.Timestamp.class);

    private Class<?> key;

    DataType(Class<?> key) {
        this.key = key;
    }

    public Class<?> getKey() {
        return this.key;
    }

    public static List<String> names() {
        DataType[] nameList = DataType.values();
        List<String> items = new ArrayList<>(nameList.length);
        for (DataType value : nameList) {
            items.add(value.name());
        }
        return items;
    }

    public static List<Class<?>> keys() {
        DataType[] values = DataType.values();
        List<Class<?>> items = new ArrayList<>(values.length);
        for (DataType value : values) {
            items.add(value.getKey());
        }
        return items;
    }

    public static Map<String, Class<?>> namesMap() {
        DataType[] values = DataType.values();
        Map<String, Class<?>> items = new LinkedHashMap<>(values.length);
        for (DataType value : values) {
            items.put(value.name(), value.getKey());
        }
        return items;
    }

    public static Map<Class<?>, String> keysMap() {
        DataType[] values = DataType.values();
        Map<Class<?>, String> items = new LinkedHashMap<>(values.length);
        for (DataType value : values) {
            items.put(value.getKey(), value.name());
        }
        return items;
    }

}

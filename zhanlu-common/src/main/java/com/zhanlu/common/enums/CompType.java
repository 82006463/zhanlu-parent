package com.zhanlu.common.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据比较类型
 */
public enum CompType {

    Eq("eq"), Like("like"), Gt("gt"), Gte("gte"), Lt("lt"), Lte("lte"), Ne("ne"), In("in"), And("and"), Or("or");

    private String key;

    CompType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static List<String> names() {
        CompType[] values = CompType.values();
        List<String> items = new ArrayList<>(values.length);
        for (CompType value : values) {
            items.add(value.name());
        }
        return items;
    }

    public static List<String> keys() {
        CompType[] values = CompType.values();
        List<String> items = new ArrayList<>(values.length);
        for (CompType value : values) {
            items.add(value.getKey());
        }
        return items;
    }

    public static Map<String, String> namesMap() {
        CompType[] values = CompType.values();
        Map<String, String> items = new LinkedHashMap<>(values.length);
        for (CompType value : values) {
            items.put(value.name(), value.getKey());
        }
        return items;
    }

    public static Map<String, String> keysMap() {
        CompType[] values = CompType.values();
        Map<String, String> items = new LinkedHashMap<>(values.length);
        for (CompType value : values) {
            items.put(value.getKey(), value.name());
        }
        return items;
    }

}

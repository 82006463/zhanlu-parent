package com.zhanlu.common.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 标签类型
 */
public enum TagType {

    Input("input"), Date("date"), Timestamp("timestamp"), Textarea("textarea"), Select("select"), Radio("radio"), Checkbox("checkbox");

    private String key;

    TagType(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public static List<String> names() {
        TagType[] values = TagType.values();
        List<String> names = new ArrayList<>(values.length);
        for (TagType value : values) {
            names.add(value.name());
        }
        return names;
    }

    public static List<String> keys() {
        TagType[] values = TagType.values();
        List<String> items = new ArrayList<>(values.length);
        for (TagType value : values) {
            items.add(value.getKey());
        }
        return items;
    }

    public static Map<String, String> namesMap() {
        TagType[] values = TagType.values();
        Map<String, String> items = new LinkedHashMap<>(values.length);
        for (TagType value : values) {
            items.put(value.name(), value.getKey());
        }
        return items;
    }

    public static Map<String, String> keysMap() {
        TagType[] values = TagType.values();
        Map<String, String> items = new LinkedHashMap<>(values.length);
        for (TagType value : values) {
            items.put(value.getKey(), value.name());
        }
        return items;
    }

}

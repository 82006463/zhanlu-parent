package com.zhanlu.common.page;

import com.zhanlu.common.enums.CompType;
import com.zhanlu.common.enums.DataType;
import com.zhanlu.common.util.ConvertUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询的过滤器
 */
public class QueryItem {

    private static Map<String, String> COMP_TYPE_MAP = CompType.namesMap();
    private static Map<String, Class<?>> DATA_TYPE_MAP = DataType.namesMap();

    private String dataType;
    private String compType;
    private String fieldName;
    private Object fieldVal;
    private List<QueryItem> subItems;

    public QueryItem(String attrName, Object attrVal) {
        if (attrName.contains(" OR ")) {
            fieldName = attrName;
            fieldVal = attrVal;
            compType = "or";
            dataType = "String";
            String[] orArr = attrName.split(" OR ");
            subItems = new ArrayList<>(orArr.length);
            for (String or : orArr) {
                subItems.add(new QueryItem(or, attrVal));
            }
        } else {
            String[] attrNameArr = attrName.split("_");
            fieldName = attrName.replace(attrNameArr[0] + "_" + attrNameArr[1] + "_", "");
            compType = attrNameArr[0];
            dataType = attrNameArr[1];
            if (attrVal != null && attrVal instanceof String && attrVal.toString().trim().length() > 0) {
                if (compType.equalsIgnoreCase("In")) {
                    List<Object> dataList = new ArrayList<>(1);
                    dataList.add(ConvertUtils.convertStringToObject(attrVal.toString(), DataType.valueOf(dataType).getKey()));
                    fieldVal = dataList;
                } else {
                    fieldVal = ConvertUtils.convertStringToObject(attrVal.toString(), DataType.valueOf(dataType).getKey());
                }
            } else {
                fieldVal = attrVal;
            }
        }
    }

    public static QueryItem buildItem(String attrName, String attrVal) {
        String[] attrNameArr = attrName.split("_");
        if (!COMP_TYPE_MAP.containsKey(attrNameArr[0]) || !DATA_TYPE_MAP.containsKey(attrNameArr[1])) {
            return null;
        }
        return new QueryItem(attrName, attrVal);
    }

    public static List<QueryItem> buildItems(Map<String, String[]> paramMap) {
        List<QueryItem> items = new ArrayList<>(2);
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            if (entry.getValue() == null || entry.getValue()[0] == null || entry.getValue()[0].trim().length() == 0) {
                continue;
            }
            QueryItem searchItem = QueryItem.buildItem(entry.getKey(), entry.getValue()[0]);
            if (searchItem == null) {
                continue;
            }
            items.add(searchItem);
        }
        return items;
    }

    public String getDataType() {
        return dataType;
    }

    public String getCompType() {
        return compType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldVal() {
        return fieldVal;
    }

    public List<QueryItem> getSubItems() {
        return subItems;
    }

}

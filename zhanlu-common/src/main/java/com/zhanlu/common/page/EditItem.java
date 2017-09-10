package com.zhanlu.common.page;

import com.zhanlu.common.enums.DataType;
import com.zhanlu.common.util.ConvertUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 编辑页面项
 */
public class EditItem {

    private Map<String, String> itemMap = new LinkedHashMap<>();
    private String dataType;
    private String fieldName;
    private Object fieldVal;

    public EditItem(Map<String, String> itemMap, String attrName, String[] attrValArr) {
        this.itemMap = itemMap;
        this.fieldName = attrName;
        this.dataType = this.itemMap.get("dataType");
        String tagType = this.itemMap.get("tagType");
        String isArray = this.itemMap.get("isArray");
        if (attrValArr != null) {
            if (tagType.equals("subForm")) {
                fieldVal = attrValArr;
            } else {
                if (dataType.equalsIgnoreCase("String")) {
                    fieldVal = isArray.equals("1") ? attrValArr : attrValArr[0];
                } else if (isArray.equals("1") || isArray.contains("yes")) {
                    Object[] tmpArr = new Object[attrValArr.length];
                    for (int i = 0; i < tmpArr.length; i++) {
                        if (attrValArr[i] == null || attrValArr[i].trim().length() == 0) {
                            continue;
                        }
                        tmpArr[i] = ConvertUtils.convertStringToObject(attrValArr[i], DataType.valueOf(dataType).getKey());
                    }
                    fieldVal = tmpArr;
                } else {
                    if (attrValArr[0] != null && attrValArr[0].trim().length() > 0) {
                        try {
                            fieldVal = ConvertUtils.convertStringToObject(attrValArr[0], DataType.valueOf(dataType).getKey());
                        } catch (Exception e) {
                            fieldVal = attrValArr[0];
                        }
                    }
                }
            }
        } else if (dataType.equalsIgnoreCase("String")) {
            //fieldVal = "";
        }
    }

    public static EditItem buildItem(Map<String, String> itemMap, String attrName, String[] attrValArr) {
        if (attrValArr == null || attrValArr[0] == null) {
            return null;
        }
        return new EditItem(itemMap, attrName, attrValArr);
    }

    public static List<EditItem> buildItems(Map<String, Map<String, String>> itemsMap, Map<String, String[]> paramMap) {
        List<EditItem> items = new ArrayList<>(paramMap.size());
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            Map<String, String> itemMap = itemsMap.get(entry.getKey());
            if (!itemsMap.containsKey(entry.getKey()))
                continue;
            EditItem item = EditItem.buildItem(itemMap, entry.getKey(), entry.getValue());
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }

    public static Map<String, Object> toMap(Map<String, Map<String, String>> itemsMap, Map<String, String[]> paramMap) {
        List<EditItem> items = EditItem.buildItems(itemsMap, paramMap);
        Map<String, Object> resultMap = new LinkedHashMap<>(items.size());
        for (EditItem item : items) {
            resultMap.put(item.getFieldName(), item.getFieldVal());
        }
        return resultMap;
    }

    public String getDataType() {
        return dataType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldVal() {
        return fieldVal;
    }

}

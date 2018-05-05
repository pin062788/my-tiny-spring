package top.wj2yx.tinyioc.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wang
 * date: 2018/5/5
 * time: 16:04
 */
//通过封装可以添加一些操作
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public PropertyValues() {
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void addPropertyValue(PropertyValue pv){
        //可以在此方法中加一些操作，比如判定是否重复
        this.propertyValueList.add(pv);
    }
}
